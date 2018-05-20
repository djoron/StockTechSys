/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evdosoft.stocktechsys.dao.StkDbDao;

/**
 *
 * @author dominicj
 */
@Service
public class SqlDatabaseServiceImpl implements SqlDatabaseService {
 
    @Autowired private StkDbDao stkDbDao;
    private static final Logger logger = LoggerFactory.getLogger(SqlDatabaseServiceImpl.class);
    
    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.SqlDatabaseService#createSqlDb()
     */
    @Override
    public boolean createSqlDb() throws Exception {
    
        boolean status = true;
        
        status = status && stkDbDao.createSymbolTable();

                //  StkDbDao.createSymbolTemporaryTable() &&
        status = status && stkDbDao.createCompanyTables();
//        status = status && stkDbDao.createChartTable();
//        status = status && stkDbDao.createQuoteTable();
                
        if (status) {
            logger.info("createSqlDb: Completed successfully");
        } else {
            logger.error("createSqlDb: Failed");
        }
            
        return status;
              
    }
    
    
}
