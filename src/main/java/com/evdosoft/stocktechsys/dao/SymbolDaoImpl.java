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
    
    private Logger logger = LoggerFactory.getLogger(SymbolDaoImpl.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;    
    
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
        
        if (symbolList.size() > 0) {                          
            String sql = "INSERT INTO SYMBOL  (SYMBOL, EXCHANGE, NAME, "
                    + "DATE, TYPE, IEXID, REGION, CURRENCY, ISENABLED) VALUES (?,?,?,?,?,?,?,?,?) "
                    + "ON DUPLICATE KEY UPDATE SYMBOL = VALUES(SYMBOL), "
                    + "NAME = VALUES(NAME), DATE = VALUES(DATE), TYPE = VALUES(DATE) ,"
                    + "IEXID = VALUES(IEXID), REGION = VALUES(REGION), CURRENCY = VALUES(CURRENCY), "
                    + "ISENABLED = VALUES(ISENABLED);";

            logger.info("saveSymbolList: Will save symbol list in DB. Be patient.");

            jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	        
	        @Override
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
	    		Symbol symbol = symbolList.get(i);
	    		ps.setString(1,symbol.getSymbol());
	    		ps.setString(2,symbol.getExchange());
	            ps.setString(3,symbol.getName());
	            ps.setDate  (4,java.sql.Date.valueOf(symbol.getDate()));
	            ps.setString(5,symbol.getType());
	            ps.setString(6,symbol.getIexId());
	            ps.setString(7,symbol.getRegion());
	            ps.setString(8,symbol.getCurrency());
	            ps.setBoolean(9,symbol.getIsEnabled());
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
