package com.evdosoft.stocktechsys.events;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.evdosoft.stocktechsys.dao.CompanyDao;
import com.evdosoft.stocktechsys.models.Company;
import com.evdosoft.stocktechsys.service.async.PriceHistoryServiceAsync;

@Component
public class CompanyListFetchedListener implements ApplicationListener<CompanyListFetchedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(CompanyListFetchedListener.class);
	    
    @Autowired
    private CompanyDao companyDao;
    
    @Autowired
    private PriceHistoryServiceAsync priceHistoryServiceAsync;
    
    @Override
    public void onApplicationEvent(CompanyListFetchedEvent event) {
	logger.info("---------- RECEIVING EVENT CompanyListFetchedEvent --------");
	
	List<Company> companyList = event.getCompanyList();		
	saveCompanies(companyList);	
	priceHistoryServiceAsync.fetchMultiplePriceHistoryAsync(companyList);
    }

    private void saveCompanies(List<Company> companyList) {
	logger.info("Save companies synchronously...");	    
	companyDao.saveCompanyListToDb(companyList);
	logger.info("DONE Save companies synchronously...");
    }

}
