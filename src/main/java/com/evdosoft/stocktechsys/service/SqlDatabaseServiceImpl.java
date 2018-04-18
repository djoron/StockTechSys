/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.evdosoft.stocktechsys.dao.SqliteDao;
import com.evdosoft.stocktechsys.dao.SqliteDaoImpl;

/**
 *
 * @author dominicj
 */
public class SqlDatabaseServiceImpl implements SqlDatabaseService {
 
    private static final Logger logger = LoggerFactory.getLogger(SqlDatabaseServiceImpl.class);
    
    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.SqlDatabaseService#createSqlDb()
     */
    @Override
    public boolean createSqlDb() throws Exception {
    
        SqliteDao sqliteDao = new SqliteDaoImpl();
        boolean status = false;
        
        status = sqliteDao.createSymbolTable() &&
                 sqliteDao.createSymbolTemporaryTable() &&
                 sqliteDao.createCompanyTables() && 
                 sqliteDao.createChartTable() &&
                 sqliteDao.createQuoteTable();
        
        
        if (status) {
            logger.info("createSqlDb: Completed successfully");
        } else {
            logger.error("createSqlDb: Failed");
        }
            
        return status;
              
    }
    
    
}
