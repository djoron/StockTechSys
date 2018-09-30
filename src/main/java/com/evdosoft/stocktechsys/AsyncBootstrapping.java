package com.evdosoft.stocktechsys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;
import com.evdosoft.stocktechsys.models.Symbol;
import com.evdosoft.stocktechsys.service.CompanyService;
import com.evdosoft.stocktechsys.service.SqlDatabaseService;
import com.evdosoft.stocktechsys.service.SymbolService;
import com.evdosoft.stocktechsys.service.async.CompanyServiceAsync;
import com.evdosoft.stocktechsys.service.async.PriceHistoryServiceAsync;

import io.vertx.core.Future;

@Component
public class AsyncBootstrapping {

    private static final Logger logger = LoggerFactory.getLogger(AsyncBootstrapping.class);
    
    @Autowired
    private SqlDatabaseService sqlDatabaseService;
	
    @Autowired
    private SymbolService symbolService;
	
    @Autowired
    private PriceHistoryServiceAsync priceHistoryServiceAsync;
	
    @Autowired
    private CompanyServiceAsync companyServiceAsync;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private Parameters parameters;   
    
    public void fetchData() throws Exception {
	
	// Initialize Symbol list and database. Symbol list will be used later to generate 
	// company list
        List<Symbol> symbolList = new ArrayList<>();
        final List<Company> companyList = companyService.getCompanyListFromDb();
        
/*        sqlDatabaseService.createSqlDb();
        
        symbolList = symbolService.getSymbolList();
       symbolService.saveSymbolList(TypeListDownload.ORIGINAL, symbolList);
*/


        // Map<String,List<Chart>> listChartList;
        
 //       Future<Void> defaultFuture = Future.future(); 
//	Future<List<Company>> futureCompanyList = companyServiceAsync.fetchCompanyListAsync();	
	
/*	futureCompanyList.compose(companyList -> {
	    logger.info("Save companies synchronously...");	    
	    companyServiceAsync.saveCompanyList(companyList);
	    logger.info("DONE Save companies synchronously...");	    
	    
	    return futureCompanyList;
	})
*/
        int maxtoDownload = parameters.getGetMaxChartListToDownload();
        int max = companyList.size();
        
        companyList.subList(10,max).clear();
        
        // Future<Void> defaultFuture2 = Future.future(); 
        Future<Map<String, List<Chart>>> futureChartList = priceHistoryServiceAsync.prepareAndDownloadPriceHistory(companyList);	

        futureChartList.compose( listChartList -> {
            logger.info("Calling price history download...");
            priceHistoryServiceAsync.saveMultipleChartListSync(futureChartList);
	    return futureChartList;
	});
	
	// System.exit(0);

    }
}
