package com.evdosoft.stocktechsys.service.async;

import java.util.List;
import java.util.Map;

import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;

import io.vertx.core.Future;

public interface PriceHistoryServiceAsync {

    Future<Map<String, List<Chart>>> prepareAndDownloadPriceHistory(List<Company> companyList);   
    public void saveMultipleChartListSync(Map<String, List<Chart>> chartListMap );
}
