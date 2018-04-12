package com.evdosoft.stocktechsys.service;

public interface PriceHistoryService {

    /**
     * Create chart (price history) list for a given symbol and saves it in 
     * local Db. Take company list from local SQL DB.
     * @return
     * @throws Exception
     */
    boolean createChartlist() throws Exception;

    /**
     * Create chart (price history) list for a given symbol and saves it in 
     * local Db. Take company list from local SQL DB.
     * @return
     * @throws Exception
     */
    boolean updateChartlist() throws Exception;

}