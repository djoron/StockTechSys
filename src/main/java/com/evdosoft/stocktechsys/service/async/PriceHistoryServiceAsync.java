package com.evdosoft.stocktechsys.service.async;

import java.util.List;

import com.evdosoft.stocktechsys.models.Company;

public interface PriceHistoryServiceAsync {

    void prepareAndDownloadPriceHistory(List<Company> companyList);    
}
