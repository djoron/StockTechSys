/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.evdosoft.stocktechsys.models.Chart;

/**
 *
 * @author dominicj
 */
@Repository
public class ChartDaoImpl implements ChartDao {

    private static final Logger logger = LoggerFactory.getLogger(ChartDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public int saveChartListToDb(List<Chart> chartList, String symbol) {

	if (chartList.size() > 0) {

	    String sql = "INSERT INTO CHART (" 
	                + " SYMBOL, `DATE`, OPEN, HIGH, LOW, CLOSE, "
			+ " VOLUME, UNADJUSTEDVOLUME, `CHANGE`, CHANGEPERCENT, VWAP, LABEL, CHANGEOVERTIME"
			+ ")"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ;";
	   //	+ "ON DUPLICATE KEY UPDATE DATE = (?), ;";
	   
	   // INSERT INTO mytable (col1, col2, col3) VALUES (?, ?, ?)
	   // ON DUPLICATE KEY UPDATE col1=VALUES(col1), col2=VALUES(col2), col3=VALUES(col3);

	   jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
		    
       		@Override
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
       	    	Chart ch = chartList.get(i);
		    ps.setString(1, symbol);		    
		    // ps.setDate(2, java.sql.Date.valueOf(ch.getDate()));
		    
		    java.util.Date utilDate = new java.util.Date();
		    utilDate = ch.getDate();
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    
		    ps.setDate(2, sqlDate);
		    //ps.setDate(2, ch.getDate());
		    ps.setBigDecimal(3, ch.getOpen());
		    ps.setBigDecimal(4, ch.getHigh());
		    ps.setBigDecimal(5, ch.getLow());
		    ps.setBigDecimal(6, ch.getClose());
		    ps.setLong(7, ch.getVolume());
		    ps.setLong(8, ch.getUnadjustedVolume());
		    ps.setBigDecimal(9, ch.getChange());
		    ps.setBigDecimal(10, ch.getChangePercent());
		    ps.setBigDecimal(11, ch.getVwap());
		    ps.setString(12, ch.getLabel());
		    ps.setBigDecimal(13, ch.getChangeOverTime());
       		}

		    @Override
		        public int getBatchSize() {
		    		return chartList.size();
		        }
		    });     
	    
	} else {
	    logger.error("saveChartListToDb: ChartList save FAILED in SqlDB at symbol {}.", symbol);	    
	    return 0;
	}
	return chartList.size();
    }

    @Override
    public LocalDate getLastSavedDownloadChartDate(String symbol) {
	LocalDate date = null;
	if (symbol != null) {
	    String SQL = "SELECT * FROM CHART" + " WHERE SYMBOL = ?"
			+ " AND DATE = (SELECT MAX(DATE)" + " FROM CHART WHERE SYMBOL = ?)";

	    Chart chart = jdbcTemplate.queryForObject(SQL, new ChartRowMapper());
	    if(chart != null) {
		// date = chart.getDate();
	    }
	}
	
	return date;
    }
    
}
/*
 * xxx
 * 
 * 
 * 
 * ResultSet rs = prepStmt.executeQuery(); int found = 0; try { while (
 * rs.next() ) { // if we go in while, there is 1 element found++; }
 * prepStmt.close(); switch (found) { case 0: // We found a new stock
 * newstockList.add(s); logger.info
 * ("TrimUpdateBloombergStockList. Potential new stock. Will check later if exists {}-{}-{} "
 * ,s.getSymbol(),s.getName(),s.getExchange()); break; case 1: // logger.info
 * ("TrimUpdateBloombergStockList Already exists {}-{}-{} ",s.getSymbol(),s.
 * getName(),s.getExchange()); break; default:// Means found twice in
 * STOCKLISTBLOOMBERG. Possible. It gets trimmed later.
 * logger.warn("TrimUpdateBloombergStockList Found Twice {}-{}-{} ",s.getSymbol(
 * ),s.getName(),s.getExchange()); break; } } catch ( Exception e ) {
 * logger.error("{} : {}",e.getClass().getName(),e.getMessage() ); return false;
 * // System.exit(0); // Exception }
 * 
 * } c.setAutoCommit(true);
 * 
 * logger.info("Trimmed Bloomberg last day data...Done"); return true; }
 * 
 * 
 * 
 * 
 * 
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 * public static boolean TrimUpdateBloombergStockList (List<Stock>
 * bloombergstockList, List<Stock> newstockList ) throws SQLException {
 * 
 * boolean status = false; int id1 = 0; String symbol; // Stock symbol String
 * name; // Stock name
 * 
 * c.setAutoCommit(false);
 * 
 * // Scan bloombergstockList and check if it exists in DB. // If it does, just
 * skip to next iteration. // If it doesn't, add symbol into newstockList for
 * later use;
 * 
 * for (Stock s: bloombergstockList) { id1++; String SymbolTmp = s.getSymbol();
 * if (SymbolTmp.contains("NTNX")) {
 * logger.info("getBloombergStockList: NTNX found"); } // This takes a long
 * time. xxxxx prepStmt = c.prepareStatement(
 * "SELECT * FROM STOCKLISTBLOOMBERG WHERE SYMBOL = ? " + "AND EXCHANGE = ? ;"
 * );
 * 
 * // Not efficient. If AAPL already exists, will still have to scan OTC, NYSE,
 * and etc markets with this symbol // But best way to detect new symbol or even
 * old symbol with new exchange... prepStmt.setString(1,s.getSymbol()); //
 * prepStmt.setString(2,s.getName()); prepStmt.setString(2,s.getExchange());
 * ResultSet rs = prepStmt.executeQuery(); int found = 0; try { while (
 * rs.next() ) { // if we go in while, there is 1 element found++; }
 * prepStmt.close(); switch (found) { case 0: // We found a new stock
 * newstockList.add(s); logger.info
 * ("TrimUpdateBloombergStockList. Potential new stock. Will check later if exists {}-{}-{} "
 * ,s.getSymbol(),s.getName(),s.getExchange()); break; case 1: // logger.info
 * ("TrimUpdateBloombergStockList Already exists {}-{}-{} ",s.getSymbol(),s.
 * getName(),s.getExchange()); break; default:// Means found twice in
 * STOCKLISTBLOOMBERG. Possible. It gets trimmed later.
 * logger.warn("TrimUpdateBloombergStockList Found Twice {}-{}-{} ",s.getSymbol(
 * ),s.getName(),s.getExchange()); break; } } catch ( Exception e ) {
 * logger.error("{} : {}",e.getClass().getName(),e.getMessage() ); return false;
 * // System.exit(0); // Exception }
 * 
 * } c.setAutoCommit(true);
 * 
 * logger.info("Trimmed Bloomberg last day data...Done"); return true; }
 */