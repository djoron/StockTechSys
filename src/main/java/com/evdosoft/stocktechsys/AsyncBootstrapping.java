package com.evdosoft.stocktechsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evdosoft.stocktechsys.service.SqlDatabaseService;
import com.evdosoft.stocktechsys.service.async.CompanyServiceAsync;

@Component
public class AsyncBootstrapping {

    @Autowired
    private CompanyServiceAsync companyServiceAsync;
    
    @Autowired
    private SqlDatabaseService sqlDatabaseService;

    public void fetchData() throws Exception {
	sqlDatabaseService.createSqlDb();
	    
	companyServiceAsync.fetchAndSaveCompanyList();
    }
}
