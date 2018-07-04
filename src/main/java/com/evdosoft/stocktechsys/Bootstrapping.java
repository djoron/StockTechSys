package com.evdosoft.stocktechsys;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evdosoft.stocktechsys.StockTechSysConstants.TypeListDownload;
import com.evdosoft.stocktechsys.models.Symbol;
import com.evdosoft.stocktechsys.service.CompanyService;
import com.evdosoft.stocktechsys.service.PriceHistoryService;
import com.evdosoft.stocktechsys.service.SqlDatabaseService;
import com.evdosoft.stocktechsys.service.SymbolService;

@Component
public class Bootstrapping {

	@Autowired
    	private SqlDatabaseService sqlDatabaseService;
    	
    	@Autowired
    	private SymbolService symbolService;

    	@Autowired
    	private CompanyService companyService;
    	
    	@Autowired
    	private PriceHistoryService priceHistoryService;
    	

    	public void prepareAndFetchData() throws Exception {
    	
       	    if (sqlDatabaseService.checkExistSqlDb()== false) {
	    	    
	    	    List<Symbol> symbolList = new ArrayList<>(); 
	
	    	    sqlDatabaseService.createSqlDb();
		    
	    	    symbolList = symbolService.getSymbolList();
	    	    symbolService.saveSymbolList(TypeListDownload.ORIGINAL, symbolList);
	    	    
	    	    companyService.createCompanyList(symbolList);
	    	    
	    	    priceHistoryService.createChartlist();
    	    
        }
            	    
    	    
    	System.out.println("!! DONE !!");
    	    
    	}
}
