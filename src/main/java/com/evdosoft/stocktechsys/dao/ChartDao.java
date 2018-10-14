/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.evdosoft.stocktechsys.models.Chart;

/**
 *
 * @author dominicj
 */
public interface ChartDao {

    public int saveChartListToDb(List<Chart> chartList, String symbol);
    void saveMultipleChartListToDb(Map<String,List<Chart>> mapChartList);

    public LocalDate getLastSavedDownloadChartDate(String symbol);

}
