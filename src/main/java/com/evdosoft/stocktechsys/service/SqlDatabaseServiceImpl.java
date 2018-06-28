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
 
        logger.info("createSqlDb: Starting");
        
        // First drop any table
        status = status && stkDbDao.dropAllTables();
 
        // Now create new tables
        status = status && stkDbDao.createSymbolTable();
        status = status && stkDbDao.createCompanyTables();
        status = status && stkDbDao.createChartTable();
        // status = status && stkDbDao.createQuoteTable();
                
        if (status) {
            logger.info("createSqlDb: Completed successfully");
        } else {
            logger.error("createSqlDb: Failed");
        }
            
        return status;
              
    }
 
    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.SqlDatabaseService#createSqlDb()
     */
    @Override
    public boolean checkExistSqlDb() throws Exception {
    
        boolean status = true;
 
        logger.info("checkExistSqlDb: Checking if SqlDb exists");
        
        // First drop any table
        status = status && stkDbDao.checkDbExist();
 
        if (status) {
            logger.info("checkExistSqlDb: SqlDb exists");
        } else {
            logger.info("checkExistSqlDb: SqlDb does not exists");
        }
            
        return status;
              
    }
 
    
}
