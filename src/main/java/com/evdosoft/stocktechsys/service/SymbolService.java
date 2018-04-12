package com.evdosoft.stocktechsys.service;

import java.util.List;

import com.evdosoft.stocktechsys.StockTechSysConstants;
import com.evdosoft.stocktechsys.models.Symbol;

public interface SymbolService {

    /**
     * getSymbolList 
     * Download Symbol List from Internet
     * @return
     * @throws Exception
     */
    List<Symbol> getSymbolList() throws Exception;

    void saveSymbolList(StockTechSysConstants.TypeListDownload typeListDownload, List<Symbol> symbolList)
	    throws Exception;

}