package com.evdosoft.stocktechsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.evdosoft.stocktechsys.service.async.CompanyServiceAsync;

@Component
public class AsyncBootstrapping {

    @Autowired
    private CompanyServiceAsync companyServiceAsync;

    public void fetchData() {
	companyServiceAsync.fetchAndSaveCompanyList();
    }
}
