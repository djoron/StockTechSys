package com.evdosoft.stocktechsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evdosoft.stocktechsys.service.SqlDatabaseService;
import com.evdosoft.stocktechsys.service.SymbolService;

@Component
public class Bootstrapping {

	@Autowired
    	private SqlDatabaseService sqlDatabaseService;
    	
    	@Autowired
    	private SymbolService symbolService;
    	
    	public void prepareAndFetchData() {
    	    
    	    System.out.println("in Bootstrapping !");
    	    
    	}
}
