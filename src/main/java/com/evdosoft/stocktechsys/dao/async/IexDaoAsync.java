package com.evdosoft.stocktechsys.dao.async;

import java.util.List;

import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;

import io.vertx.core.Future;

/**
 * Async methods
 * @author Eva
 *
 */
public interface IexDaoAsync {

    public Future<List<Company>>  getCompanyList();
    public Future<List<Chart>>    getDailyChartList();
}

