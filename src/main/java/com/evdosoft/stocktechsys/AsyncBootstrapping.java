package com.evdosoft.stocktechsys;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evdosoft.stocktechsys.StockTechSysConstants.TypeListDownload;
import com.evdosoft.stocktechsys.models.Company;
import com.evdosoft.stocktechsys.models.Symbol;
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

    public void fetchData() throws Exception {
	
	// Initialize Symbol list and database. Symbol list will be used later to generate 
	// company list
        List<Symbol> symbolList = new ArrayList<>(); 
        sqlDatabaseService.createSqlDb();
        
        symbolList = symbolService.getSymbolList();
        symbolService.saveSymbolList(TypeListDownload.ORIGINAL, symbolList);
	
        Future<Void> defaultFuture = Future.future(); 
	Future<List<Company>> futureCompanyList = companyServiceAsync.fetchCompanyList();	
	futureCompanyList.compose(companyList -> {
	    logger.info("Save companies synchronously...");	    
	    companyServiceAsync.saveCompanyList(companyList);
	    logger.info("DONE Save companies synchronously...");	    
	    
	    return futureCompanyList;
	}).compose(companyList -> {
	    logger.info("Calling price history download...");
	    priceHistoryServiceAsync.prepareAndDownloadPriceHistory(companyList);   
	    
	}, defaultFuture);
	
	// System.exit(0);

    }
}
