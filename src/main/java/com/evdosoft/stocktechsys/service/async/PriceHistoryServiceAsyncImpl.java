package com.evdosoft.stocktechsys.service.async;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     public void fetchMultiplePriceHistoryAsync(List<Company> companyList)       
     {
		int maxToDownload = parameters.getGetMaxChartListToDownload();	
		String period = StockTechSysConstants.FIVEYEARS;	
	        
		logger.info("Fetch PriceHistory (Chartlist) asynchronously...");
		List<String> symbols = companyList.stream().map(Company::getSymbol).collect(Collectors.toList());
//		Future<Map<String,List<Chart>>> future = 
		iexDaoAsync.getDailyChartList(symbols, period, maxToDownload);
	        
//		Future<Void> defaultFuture = Future.future(); 
//		future.compose(chartListMap -> {
//		    logger.info("In compose future with Map {}", chartListMap.toString());
//		    logger.info("Save chartlist synchronously...");
//		    saveMultipleChartListSync(chartListMap);
//		}, defaultFuture);
    }
       

    
    private void saveMultipleChartListSync(Map<String, List<Chart>> chartListMap ) {
		vertx.executeBlocking(future -> {
		    try {
			logger.info("In blocking save with Map {}", chartListMap.toString());
			chartDao.saveMultipleChartListToDb(chartListMap);
		    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    future.complete();
		  }, res -> {
		      logger.info("Save chartlist synchronously...");
		  });
	
	    } //vertx.executeblocking...
}
