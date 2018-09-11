package com.evdosoft.stocktechsys.service.async;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evdosoft.stocktechsys.Parameters;
import com.evdosoft.stocktechsys.StockTechSysConstants;
import com.evdosoft.stocktechsys.dao.ChartDao;
import com.evdosoft.stocktechsys.dao.async.IexDaoAsync;
import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;
import com.evdosoft.stocktechsys.service.CompanyService;

import io.vertx.core.Vertx;

@Service
public class PriceHistoryServiceAsyncImpl implements PriceHistoryServiceAsync {

    private static final Logger logger = LoggerFactory.getLogger(PriceHistoryServiceAsyncImpl.class);
	
    private Parameters parameters;
    
    @Autowired
    public PriceHistoryServiceAsyncImpl(Parameters parameters) {
	this.parameters = parameters;
    }  
    
    @Autowired
    private IexDaoAsync iexDaoAsync;
    
    @Autowired
    private Vertx vertx;
    
    @Autowired
    private ChartDao chartDao;
    
    @Autowired
    private CompanyService companyService;
    
    @Override
    /**
     * Build List of Strings containing symbols taken from Company List. Then call async download with list.
     */
     public void prepareAndDownloadPriceHistory()  
     {
    
	List<Company> companyList;
	List<String> symbolList = null;
	companyList = companyService.getCompanyListFromDb();
	int maxToDownload = parameters.getmaxChartListToDownload();
	int count = 0;
	int period = StockTechSysConstants.DAILY;

	int totalSymbols = companyList.size();
        
        for (Company company: companyList ) {    
             count++;
             symbolList.add(company.getSymbol());

             if ((count % maxToDownload) == 0) {
        	 fetchAndSavePriceHistoryList(symbolList, period);
        	symbolList.clear();
             }  
        
        } // for
        if (symbolList.size() > 0) {
            // Complete download of last list which is smaller than % maxToDownload
            fetchAndSavePriceHistoryList(symbolList, period);
            symbolList.clear();
        }
        
    }
    
    @Override
    public void fetchAndSavePriceHistoryList(List<String> symbolList, int period) {
//	Future<Void> defaultFuture = Future.future(); 
//	logger.info("Fetch PriceHistory (Chartlist) asynchronously...");
//
//	
//	Future<Map<String,List<Chart>>> future = iexDaoAsync.getDailyChartList(symbolList, period);
//	future.compose(chartList -> {
//	    logger.info("Save companies synchronously...");
//	    saveChartList(chartList);
//	}, defaultFuture);

    }

    private void saveChartList(List<Chart> chartList) {
//	vertx.executeBlocking(future -> {
//	    try {
//		chartDao.saveChartListToDb(chartList, symbol);
//	    } catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	    }
//	    future.complete();
//	  }, res -> {
//	    System.out.println("Company list saved synchronously.");
//	  });

    }
}
