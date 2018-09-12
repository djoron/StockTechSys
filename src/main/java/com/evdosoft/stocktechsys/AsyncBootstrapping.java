package com.evdosoft.stocktechsys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evdosoft.stocktechsys.StockTechSysConstants.TypeListDownload;
import com.evdosoft.stocktechsys.models.Symbol;
import com.evdosoft.stocktechsys.service.SqlDatabaseService;
import com.evdosoft.stocktechsys.service.SymbolService;
import com.evdosoft.stocktechsys.service.async.CompanyServiceAsync;
import com.evdosoft.stocktechsys.service.async.PriceHistoryServiceAsync;

@Component
public class AsyncBootstrapping {

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
	
	companyServiceAsync.fetchAndSaveCompanyList();
	
	priceHistoryServiceAsync.prepareAndDownloadPriceHistory();
	
	// System.exit(0);

    }
}
