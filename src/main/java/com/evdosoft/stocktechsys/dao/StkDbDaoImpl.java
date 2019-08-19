/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.evdosoft.stocktechsys.Parameters;

/**
 *
 * @author atlantis
 */
@Repository
public class StkDbDaoImpl implements StkDbDao {

    @Autowired
    private Parameters parameters;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
	private Environment env;
    
    private static final Logger logger = LoggerFactory.getLogger(StkDbDaoImpl.class);

    /**
     * execStatement Execute an Sql Statement.
     * 
     * @version 1.0
     * @author : dj
     * @param strsql
     * @return true if successful.
     * @throws java.sql.SQLException
     */

    @Override
    public Boolean execStatement(String strsql) throws SQLException {
		try {
		    jdbcTemplate.execute(strsql);
		    return true;
		} catch (DataAccessException e) {
		    logger.error("execStatement: returned an error on {}", strsql);
		    logger.error("{} : {}", e.getClass().getName(), e.getMessage());
		    return false;
		}
    }

    /**
    *
    * Create Database if it doesn't already exist
    * 
    * @version 1.0
    * @author : dj
    * @return true if successful.
    * @throws java.sql.SQLException
    */
   @Override
   public boolean createDatabase() throws SQLException {
	
	logger.info("createSchema Symbol starting");
	
	String datasourceUrl = env.getProperty("spring.datasource.url");
	logger.info("sourceurl extracted {}",datasourceUrl );

	// Assuming something like: jdbc:mysql://localhost:3306/stkdb
	// [.]*\/\w*
/*	Pattern pattern = Pattern.compile("'[.]*\\/\\w*'");
	Matcher matcher = pattern.matcher(datasourceUrl);
	String dbName = ""; //"stkdb";
	if (matcher.find())
	{
		logger.info("1 {}",matcher.group(1) );
	    // System.out.println(matcher.group(1));
	    dbName = matcher.group(1);
	}
*/
//	String dbName = datasourceUrl.replaceAll("'[.]*\\/\\w*'", "");

//	logger.info("dbname extracted {}",dbName);
	
	String query = "CREATE DATABASE IF NOT EXISTS stkdb;";

	return execStatement(query);
   }
      
    /**
     *
     * Create CompanyList Table to contain company info.
     * 
     * @version 1.0
     * @author : dj
     * @return true if successful.
     * @throws java.sql.SQLException
     */
    @Override
    public boolean createCompanyTables() throws SQLException {
	logger.info("createCompanyTable Company starting");
	String query = "CREATE TABLE COMPANY   (ID INTEGER PRIMARY KEY AUTO_INCREMENT," + 
		"  SYMBOL             VARCHAR(10) NOT NULL, " + 
		"  COMPANYNAME        VARCHAR(255) NOT NULL, " + 
		"  EXCHANGE    	      VARCHAR(80),  " + 
		"  INDUSTRY           VARCHAR(255), " + 
		"  WEBSITE            VARCHAR(255),  " + 
		"  DESCRIPTION        TEXT, " + 
		"  CEO                VARCHAR(255),  " + 
		"  ISSUETYPE          VARCHAR(80), " + 
		"  SECTOR             VARCHAR(80),  " + 		
		"  UNIQUE KEY (SYMBOL, EXCHANGE)," + 
		"  FOREIGN KEY(SYMBOL) REFERENCES SYMBOL(SYMBOL));              ";
	
	return execStatement(query);

	}

    /**
     *
     * Create SymbolList Table to contain Symbol info.
     * 
     * @version 1.0
     * @author : dj
     * @return true if successful.
     * @throws java.sql.SQLException
     */
    @Override
    public boolean createSymbolTable() throws SQLException {
	logger.info("createSymbolTable Symbol starting");
	
	String query = "CREATE TABLE SYMBOL   ( " + 
		"  SYMBOL             VARCHAR(10) NOT NULL PRIMARY KEY, " + 
		"  NAME               VARCHAR(255) NOT NULL, " + 
		"  DATE               DATE, "    + 
		"  ISENABLED          BIT, "  + 
		"  TYPE               VARCHAR(10), " + 
		"  IEXID              INTEGER, " + 
		"  UNIQUE KEY (SYMBOL) );";

	return execStatement(query);
    }
   
    /**
     *
     * Create Quote Table to contain stock prices.
     * 
     * @version 1.0
     * @author : dj
     * @return true if successful.
     * @throws java.sql.SQLException
     */
    @Override
    public boolean createQuoteTable() throws SQLException {
	// stmt = null;
	// stmt = c.createStatement();

	logger.info("createQuoteTable starting");
	String query = 
		"CREATE TABLE QUOTE " + "(quoteID INTEGER PRIMARY KEY," + " SYMBOL       VARCHAR(10) NOT NULL,"
			+ " COMPANYNAME            TEXT, " + " PRIMARYEXCHANGE         TEXT, "
			+ " SECTOR                 TEXT, " + " CALCULATIONPRICE       TEXT, "
			+ " OPEN                   TEXT, " + " OPENTIME               TEXT, "
			+ " CLOSE                  TEXT, " + " CLOSETIME              TEXT, "
			+ " LATESTPRICE            TEXT, " + " LATESTSOURCE           TEXT, "
			+ " LATESTTIME             TEXT, " + " LATESTUPDATE           TEXT, "
			+ " LATESTVOLUME           TEXT, " + " IEXREALTIMEPRICE       TEXT, "
			+ " IEXREALTIMESIZE        TEXT, " + " IEXLASTUPDATED         TEXT, "
			+ " DELAYEDPRICE           TEXT, " + " DELAYEDPRICETIME       TEXT, "
			+ " PREVIOUSCLOSE          TEXT, " + " CHANGE                 TEXT, "
			+ " CHANGEPERCENT          TEXT, " + " IEXMARKETPERCENT       TEXT, "
			+ " IEXVOLUME              TEXT, " + " AVGTOTALVOLUME         TEXT, "
			+ " IEXBIDPRICE            TEXT, " + " IEXBIDSIZE             TEXT, "
			+ " IEXASKPRICE            TEXT, " + " IEXASKSIZE             TEXT, "
			+ " MARKETCAP              TEXT, " + " PERATIO                TEXT, "
			+ " WEEK52HIGH             TEXT, " + " WEEK52LOW              TEXT, "
			+ " YTDCHANGE              TEXT, "
			+ "UNIQUE (SYMBOL, PRIMARYEXCHANGE, CLOSETIME), "
			+ "FOREIGN KEY(SYMBOL) REFERENCES SYMBOL(SYMBOL)" + ");"
			+ "CREATE INDEX DATE_IDX ON QUOTE (SYMBOL, PRIMARYEXCHANGE, CLOSETIME);";

	return execStatement(query);
    }

    /**
     *
     * Create Quote Table to contain stock prices.
     * 
     * @version 1.0
     * @author : dj
     * @return true if successful.
     * @throws java.sql.SQLException
     */
    @Override
    public boolean createChartTable() throws SQLException {
	// stmt = null;
	// stmt = c.createStatement();

	logger.info("createChartTable starting");	
	String query = 
		"CREATE TABLE CHART ( "
	                + " chartID INTEGER PRIMARY KEY AUTO_INCREMENT," 
	                + " SYMBOL                 VARCHAR(10) NOT NULL,"
			+ " `DATE`                 DATE, " 
			+ " OPEN                   DECIMAL(19,4), "
			+ " HIGH                   DECIMAL(19,4), " 
			+ " LOW                    DECIMAL(19,4), "
			+ " CLOSE                  DECIMAL(19,4), "
			+ " VOLUME                 BIGINT(20), "
			+ " UNADJUSTEDVOLUME       BIGINT(20), " 
			+ " CHANGEAMOUNT           DECIMAL(19,2), "
			+ " CHANGEPERCENT          DECIMAL(19,2), " 
			+ " VWAP                   DECIMAL(19,2), "
			+ " LABEL                  VARCHAR(50),   " 
			+ " CHANGEOVERTIME         DECIMAL(19,2), "
			+ " UNIQUE KEY SYMBOL_DATE (SYMBOL, `DATE`), "
			+ " FOREIGN KEY(SYMBOL) REFERENCES SYMBOL(SYMBOL)"
			+ "); ";
	
	String query2 = "CREATE INDEX CHART_IDX ON CHART (SYMBOL(10), `DATE`);";
	
	boolean status = false;
	status = execStatement(query);
	status = status && execStatement(query2);

	return status; 
    }

    @Override
    public boolean createEarningsTable() throws SQLException {
	// stmt = null;
	// stmt = c.createStatement();

	logger.info("createEarningsTable starting");	
	String query = 
		"CREATE TABLE EARNINGS ( "
	                + " earningsID INTEGER PRIMARY KEY AUTO_INCREMENT," 
	                + " SYMBOL                 VARCHAR(10) NOT NULL,"
					+ " ACTUALEPS              DECIMAL(3,2), " 
					+ " CONSENSUSEPS           DECIMAL(3,2), "
					+ " ESTIMATEDEPS           DECIMAL(3,2), " 
					+ " ANNOUNCETIME           VARCHAR(10), "
					+ " NUMBEROFESTIMATES      INTEGER, "
					+ " EPSSURPRISEDOLLAR      DECIMAL(3,2), "
					+ " EPSREPORTDATE          DATE, " 
					+ " FISCALPERIOD           VARCHAR(10), "
					+ " FISCALENDDATE          DATE, " 
					+ " YEARAGO                DECIMAL(3,2), "
					+ " YEARAGOCHANGEPERCENT   DECIMAL(3,2),   " 
					+ " ESTIMATEDCHANGEPERCENT DECIMAL(10,2), "
					+ " SYMBOLID               INTEGER,"
					+ " UNIQUE KEY SYMBOL (SYMBOL), "
					+ " FOREIGN KEY(SYMBOL) REFERENCES SYMBOL(SYMBOL)"
					+ "); ";
	
	String query2 = "CREATE INDEX EARNINGS_IDX ON EARNINGS (SYMBOL(10));";
	
	boolean status = false;
	status = execStatement(query);
	status = status && execStatement(query2);

	return status; 
    }

    /**
     *
     * Create SymbolList Table to contain Symbol info.
     * 
     * @version 1.0
     * @author : dj
     * @return true if successful.
     * @throws java.sql.SQLException
     */
    @Override
    public boolean dropAllTables() throws SQLException {
	logger.info("dropAllTables starting");

	String query = 	"SET FOREIGN_KEY_CHECKS=0;";
	jdbcTemplate.execute(query);
	query = "DROP TABLE IF EXISTS COMPANY;";
	jdbcTemplate.execute(query);
	query = "DROP TABLE IF EXISTS SYMBOL;";
	jdbcTemplate.execute(query);	
	query =	"DROP TABLE IF EXISTS CHART;";
	jdbcTemplate.execute(query);	
	query = "DROP TABLE IF EXISTS QUOTE;";
	jdbcTemplate.execute(query);
	query = "DROP TABLE IF EXISTS EARNINGS;";
	jdbcTemplate.execute(query);
	query = "SET FOREIGN_KEY_CHECKS=1;";
	jdbcTemplate.execute(query);

	return true;
    }


	
	
    @Override
    public boolean deleteDuplicateFromStockListTrimDb() throws SQLException {

	String query = "DELETE FROM STOCKLISTTRIM " + "WHERE ROWID NOT IN " + "(" + "SELECT MIN (ROWID) "
		+ "FROM STOCKLISTTRIM " + "GROUP BY SYMBOL, EXCHANGE);";

	try {
	    if (execStatement(query) == true) {
		// logger.info("createCompanyTables created successfully");
		return true;
	    } else {
		logger.error("deleteDuplicateFromStockListTrimDb did not complete.");
		return false;
	    }
	} catch (Exception e) {
	    logger.error("deleteDuplicateFromStockListTrimDb did not complete.");
	    logger.error("{} : {}", e.getClass().getName(), e.getMessage());
	    return false;
	}
    }
    
    @Override
    public boolean checkDbExist () throws SQLException {

	String query = "SELECT IF(EXISTS (SELECT SYMBOL FROM SYMBOL), 'Yes','No')";

	try {
	    if (execStatement(query) == true) {
		// logger.info("createCompanyTables created successfully");
		return true;
	    } else {
		logger.info("checkDbExist: does not exist. Else1.");
		return false;
	    }
	} catch (Exception e) {
		logger.info("checkDbExist: does not exist. Else2.");
	    return false;
	}
    
    }

}

/*
 * Find duplicate rows with data from 2 columns
 * 
 * SELECT symbol, name, COUNT(*) FROM stocklist GROUP BY symbol, name HAVING
 * COUNT(*) > 1
 * 
 * 
 * delete duplicate rows keeping only one
 * 
 * delete from stocklist where rowid not in ( select min(rowid) from stocklist
 * group by symbol , exchange )
 */

/*
 * Search values =10 select * from stockpricedaily where ( ( (CAST(close as
 * double)) = 10.0) and date = "2016-03-11")
 * 
 */

/*
 * find last date update SELECT date, COUNT(*) FROM stockpricedaily GROUP BY
 * date order by date
 * 
 */