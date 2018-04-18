/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.net.MalformedURLException;
import java.util.List;
import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;
import com.evdosoft.stocktechsys.models.Quote;
import com.evdosoft.stocktechsys.models.Symbol;

/**
 *
 * @author dominicj
 */
public interface IexDao {
 
    
    public List<Symbol>   getSymbolList() throws Exception;
    public List<Company>  getCompanyList(List<Symbol> symbolList) throws MalformedURLException;
    public List<Chart>    getDailyChartList(String symbol, String period) throws MalformedURLException;
    public List<Chart>    updateDailyChartList(String symbol, String period) throws MalformedURLException;
    public String         getLastOpenMarketDate () throws Exception;
}
