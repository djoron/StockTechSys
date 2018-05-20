/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evdosoft.stocktechsys.Parameters;
import com.evdosoft.stocktechsys.dao.ChartDao;
import com.evdosoft.stocktechsys.dao.IexDao;
import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;

/**
 *
 * @author dominicj
 */
@Service
public class PriceHistoryServiceImpl implements PriceHistoryService {

    private static final Logger logger = LoggerFactory.getLogger(PriceHistoryServiceImpl.class);
    
    @Autowired
    private Parameters parameters;
    
    @Autowired
    private IexDao iexDao;
    
    @Autowired
    private ChartDao chartDao;
    
    @Autowired
    private CompanyService companyService;
    
    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.PriceHistoryService#createChartlist()
     */
    @Override
    public boolean createChartlist() throws Exception {
        List<Company> companyList;
        List<Chart> chartList;
        int count=0;
        
        int daysSaved = 0;
        // Get company list from SQL DB.
        companyList = companyService.getCompanyListFromDb();
        
        int totalSymbols = companyList.size();
        
        for (Company company: companyList ) {    
            try {
                count++;
                chartList = iexDao.getDailyChartList(company.getSymbol(), parameters.getYearHistoryString());
                if (chartList != null) {
                    daysSaved = chartDao.saveChartListToDb(chartList, company.getSymbol());
                    if (daysSaved>0) {
                        logger.info("createChartListToDb: {} of {} - {} days saved"
                        + " in SqlDB for symbol {}...",count, totalSymbols, daysSaved, company.getSymbol());
                    } else
                    {
                        logger.warn("createChartlist - Symbol {} chart NOT saved",company.getSymbol());
                        // return false; Remove, if one symbol failed, whole thing stopped.
                    }    
                }    
            } catch (IOException e) {
                logger.error("createChartlist - Exception e {}",e);
                count--;
                return false;
            }   
        } // for
        return true;
    }
    
    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.PriceHistoryService#updateChartlist()
     */
    @Override
    public boolean updateChartlist() throws Exception {

        CompanyService companyService = new CompanyServiceImpl();
        List<Company> companyList;
        List<Chart> chartList;
        int count=0;
        int daysSaved = 0;
       
        boolean status;
        // Get company list from SQL DB.
        companyList = companyService.getCompanyListFromDb();        
        
        // Will hold sorted company list by SQL fetch date
        List <Company> companySort = new ArrayList<>();
        // Need to go through service to fetch info
        int totalSymbols = companyList.size();
        
        // Build subsets of company downloads (less than 1 month history or more).
        for (Company company: companyList ) {    
            try {
                count++;
                chartList = iexDao.getDailyChartList(company.getSymbol(), parameters.getYearHistoryString());
                if (chartList != null) {
                    daysSaved = chartDao.saveChartListToDb(chartList, company.getSymbol());
                    if (daysSaved > 0) {
                        logger.info("updateChartListToDb: {} of {} - {} days saved"
                        + " in SqlDB for symbol {}...",count, totalSymbols, daysSaved, company.getSymbol());

                    } else
                    {
                        logger.warn("updateChartlist - Symbol {} chart NOT saved",company.getSymbol());
                        // return false; Remove, if one symbol failed, whole thing stopped.
                    }    
                }    
            } catch (IOException e) {
                logger.error("updateChartlist - Exception e {}",e);
                count--;
                return false;
            }   
            // Used mainly for debug to process less symbols
            if (count > parameters.getMaxStocktoProcess()) { 
                break;
            }
        } // for
        return true;

    }
}
