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

import io.vertx.core.Future;
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
     public void prepareAndDownloadPriceHistory(List<Company> companyList)  
     {
	int maxToDownload = parameters.getmaxChartListToDownload();	
	int period = StockTechSysConstants.DAILY;	
        
	logger.info("Fetch PriceHistory (Chartlist) asynchronously...");
	List<String> symbols = companyList.stream().map(Company::getSymbol).collect(Collectors.toList());
	Future<Map<String,List<Chart>>> future = iexDaoAsync.getDailyChartList(symbols, period, maxToDownload);
        
	Future<Void> defaultFuture = Future.future(); 
	future.compose(chartListMap -> {
	    logger.info("Save companies synchronously...");
	    saveChartList(chartListMap);
	}, defaultFuture);
    }
       

    private void saveChartList(Map<String, List<Chart>> chartListMap ) {
	vertx.executeBlocking(future -> {
	    try {
		chartDao.saveMultipleChartListToDb(chartListMap);
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
