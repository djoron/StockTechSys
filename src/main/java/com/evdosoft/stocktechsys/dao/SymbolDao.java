/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.sql.SQLException;
import java.util.List;

import com.evdosoft.stocktechsys.StockTechSysConstants.TypeListDownload;
import com.evdosoft.stocktechsys.models.Symbol;


/**
 *
 * @author dominicj
 */
public interface SymbolDao {

    public boolean saveSymbolList(List<Symbol> symbolList, TypeListDownload val) throws Exception;
    public List<Symbol> loadSymbolListFromDb () throws SQLException;
        
}
