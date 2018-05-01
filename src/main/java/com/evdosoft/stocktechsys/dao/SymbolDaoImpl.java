/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.evdosoft.stocktechsys.StockTechSysConstants.TypeListDownload;
import com.evdosoft.stocktechsys.models.Symbol;

/**
 *
 * @author dominicj
 */
@Repository
public class SymbolDaoImpl implements SymbolDao {
    
    private static final Logger logger = LoggerFactory.getLogger(SymbolDaoImpl.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private StkDbDao sqliteDao;       
    
    /**
     * Save Company Downloaded from internet into DB. Method downloads temporary
     * or first time symbol list. Temporary is for comparison and update purposes.
     * @param symbolList : List of symbols used to build IEX company list
     * @param val : Temporary or permanent symbol list type
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveSymbolList(List<Symbol> symbolList, TypeListDownload val) throws Exception{           
                
       // Delete old Temp table
        sqliteDao.createSymbolTable();
        
        if (symbolList.size() > 0) {                          
            String sql = "INSERT INTO SYMBOL  (SYMBOL, NAME, "
                    + "DATE, ISENABLED, TYPE, IEXID) VALUES (?,?,?,?,?,?);";
           
            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	        
	        @Override
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	    		Symbol symbol = symbolList.get(i);
	    		ps.setString(1,symbol.getSymbol());
	                ps.setString(2,symbol.getName());
	                ps.setString(3,symbol.getDate());
	                ps.setString(4,symbol.getIsEnabled());
	                ps.setString(5,symbol.getType());
	                ps.setString(6,symbol.getIexId());	    	
	        }
	        
	        @Override
	        public int getBatchSize() {
	    		return symbolList.size();
	        }
	    });            
            logger.info("saveSymbolList: SymbolList saved in SqlDB...done");
        } else {
            logger.error("saveSymbolList: SymbolList save FAILED in SqlDB");
            return false;
        }
        return true;          
    }
    
    /**
     * LoadCompanyListFromDb 
     *  Load Company list from SQL DB and save in CompanyList list.
     * @version 1.0
     * @author : dominicj
     * @param SymbolList
     * @return true if successful.
     * @throws java.sql.SQLException
     */
    public List<Symbol> loadSymbolListFromDb () throws SQLException {

        logger.info("loadSymbolListFromDb - Loading list from DB ... Standby");                       
        
        String query = "SELECT * FROM SYMBOL";
        List<Symbol> symbolList = jdbcTemplate.query(query, new SymbolRowMapper());

        logger.info("Loaded SymbolList from DB ... Done");
        return symbolList;
    }

}
