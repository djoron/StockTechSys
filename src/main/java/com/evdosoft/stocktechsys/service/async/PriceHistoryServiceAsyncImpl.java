package com.evdosoft.stocktechsys.service.async;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evdosoft.stocktechsys.dao.ChartDao;
import com.evdosoft.stocktechsys.dao.async.IexDaoAsync;
import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

@Service
public class PriceHistoryServiceAsyncImpl implements PriceHistoryServiceAsync {

    private static final Logger logger = LoggerFactory.getLogger(PriceHistoryServiceAsyncImpl.class);
	    
    @Autowired
    private IexDaoAsync iexDaoAsync;
    
    @Autowired
    private Vertx vertx;
    
    @Autowired
    private ChartDao chartDao;
    
    @Override
    public void fetchAndSavePriceHistoryList() {
	Future<Void> defaultFuture = Future.future(); 
	logger.info("Fetch price History asynchronously...");
	Future<List<Chart>> future = iexDaoAsync.getDailyChartList();
	future.compose(dailyChartList -> {
	    logger.info("Save daily ChartList synchronously...");
	    // saveCompanyList(List);
	}, defaultFuture);

	/***** Code original du service pricehistory */
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
	    
	
	
	
	
	
	
    }

    private void saveCompanyList(List<Company> companyList) {
	vertx.executeBlocking(future -> {
	    try {
		companyDao.saveCompanyList(companyList);
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    future.complete();
	  }, res -> {
	    System.out.println("Company list saved synchronously.");
	  });
    }

}
