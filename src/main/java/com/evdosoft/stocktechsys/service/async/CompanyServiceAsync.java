package com.evdosoft.stocktechsys.service.async;

import java.util.List;

import com.evdosoft.stocktechsys.models.Company;

import io.vertx.core.Future;

public interface CompanyServiceAsync {

    Future<List<Company>>  fetchCompanyListAsync();
    
    void saveCompanyList(List<Company> companyList);
}
