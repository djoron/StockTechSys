/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.evdosoft.stocktechsys.dao.CompanyDao;
import com.evdosoft.stocktechsys.dao.CompanyDaoImpl;
import com.evdosoft.stocktechsys.dao.IexDao;
import com.evdosoft.stocktechsys.dao.IexDaoImpl;
import com.evdosoft.stocktechsys.models.Company;
import com.evdosoft.stocktechsys.models.Symbol;
        
/**
 *
 * @author atlantis
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    
    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
        
    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.CompanyService#createCompanyList(java.util.List)
     */
    @Override
    public boolean createCompanyList(List<Symbol>symbolList) throws Exception {
        boolean status;
        
        // Dao to access internat Data
        IexDao iexDao = new IexDaoImpl() {};
        
        List<Company> companyList = iexDao.getCompanyList(symbolList);
        
        // Use internet to download full Symbol List to populate new DB 
        if (!symbolList.isEmpty()) 
        {
            
            if (!companyList.isEmpty()) 
            {
                // Save in SQL DB.
                logger.info("createCompanyList: Downloaded {} elements.",companyList.size());
                logger.info("createCompanyList: getCompanyList. Saving into DB.",companyList.size());
                CompanyDao companyDao = new CompanyDaoImpl();
                companyDao.saveCompanyList(companyList);

            } else {
                // If returns 0 no data so cannot build stocklist. Must exit
                logger.info("createStockList: getCompanyList: Could not be built.");
                return false;
            }
        } else
        {
            // If returns 0 no data so cannot build stocklist. Must exit
            logger.info("createCompanyList: Could not download any data...exciting");
            return false;
        }

        
        return true;
        
    }

        /* (non-Javadoc)
	 * @see com.evdosoft.stocktechsys.service.CompanyService#updateCompanyList(java.util.List)
	 */
    @Override
    public boolean updateCompanyList(List<Symbol>symbolList) throws Exception {
        boolean status;
        
        // Dao to access internat Data
        IexDao iexDao = new IexDaoImpl() {};
        
        // xxxx SymbolList is full here. Needs to be trimmed.
        
        // Use internet to download full Symbol List to populate new DB 
        if (!symbolList.isEmpty()) 
        {

            // xxxx SymbolList is full here. Needs to be trimmed.

            // We have data. Build company List, download info from internet.
            List<Company> companyList = iexDao.getCompanyList(symbolList);
            
            if (!companyList.isEmpty()) 
            {
    
                // Save in SQL DB.
                logger.info("updateCompanyList: Downloaded {} elements.",companyList.size());
                logger.info("updateCompanyList: Saving into DB.",companyList.size());
                CompanyDao companyDao = new CompanyDaoImpl();
                companyDao.updateCompanyList(companyList);

            } else {
                // If returns 0 no data so cannot build stocklist. Must exit
                logger.info("updateCompanyList: getCompanyList: Could not be built.");
                return false;
            }
        } else
        {
            // If returns 0 no data so cannot build stocklist. Must exit
            logger.info("updateCompanyList: Could not download any data...exciting");
            return false;
        }

        
        return true;
        
    }

    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.CompanyService#getCompanyListFromDb()
     */
    @Override
    public List<Company> getCompanyListFromDb() throws Exception {
    
        List<Company> companyList = new ArrayList<Company>();

        CompanyDao companyDao = new CompanyDaoImpl();
        companyList = companyDao.loadCompanyListFromDb();    
        return companyList;

    }
}
