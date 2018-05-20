/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evdosoft.stocktechsys.StockTechSysConstants;
import com.evdosoft.stocktechsys.dao.IexDao;
import com.evdosoft.stocktechsys.dao.SymbolDao;
import com.evdosoft.stocktechsys.models.Symbol;

/**
 * Manipulate Symbol List as retrieved by IEX
 * @author dominicj
 */
@Service
public class SymbolServiceImpl implements SymbolService {
    
    private static final Logger logger = LoggerFactory.getLogger(SymbolServiceImpl.class);
    
    @Autowired
    private IexDao iexDao;
    
    @Autowired
    SymbolDao symbolDao;
    
    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.SymbolService#getSymbolList()
     */
    @Override
    public List<Symbol> getSymbolList() throws Exception {                 
        List<Symbol> symbolList = iexDao.getSymbolList();
        logger.info("Symbol List downloaded successfully");
        return symbolList;
    }

    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.SymbolService#saveSymbolList(com.evdosoft.stocktechsys.StockTechSysConstants.TypeListDownload, java.util.List)
     */
    @Override
    public void saveSymbolList(StockTechSysConstants.TypeListDownload typeListDownload, List<Symbol> symbolList) throws Exception {
        symbolDao.saveSymbolList(symbolList, typeListDownload);
        
    }

    
}
