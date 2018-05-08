/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.evdosoft.stocktechsys.Parameters;
import com.evdosoft.stocktechsys.StockTechSysConstants;
import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;
import com.evdosoft.stocktechsys.models.Symbol;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author dominicj
 */
@Repository
public class IexDaoImpl implements IexDao {

    private static final Logger logger = LoggerFactory.getLogger(IexDaoImpl.class);
    
    @Autowired
    private Parameters parameters;
    
    /**
     *
     * @param symbolList
     * @return
     * @throws MalformedURLException
     */
    @Override
    public List<Symbol> getSymbolList() throws MalformedURLException {

	String urlstr = parameters.getIexPrefix() + parameters.getIexPrefixSymbols();
	int size = 0;
	List<Symbol> symbolList = null;
	// logger.debug("getSymbolList - Launching Symbol download - IEX Url
	// {}",urlstr);

	ObjectMapper objectMapper = new ObjectMapper();
	try {
	    symbolList = objectMapper.readValue(new URL(urlstr), new TypeReference<List<Symbol>>() {
	    });
	    size = symbolList.size();
	    // logger.info("getSymbolList - Read {} symbols",size);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return symbolList;
    }

    /**
     *
     * @param symbolList
     *            Internet list with only symbol name
     * @param CompanyList
     *            Interet list with symbol and company info
     * @return
     * @throws MalformedURLException
     */
    @Override
    public List<Company> getCompanyList(List<Symbol> symbolList) throws MalformedURLException {
	String urlstr;

	// Will contain symbol only List from Internet.
	List<Company> companyList = new ArrayList<>();
	int totalsize = symbolList.size();

	// https://api.iextrading.com/1.0/stock/aapl/company
	int count = 0;

	for (Symbol symbol : symbolList) {

	    urlstr = parameters.getIexPrefix() + "stock/" + symbol.getSymbol() + "/company";
	    ObjectMapper objectMapper = new ObjectMapper();
	    Company company = null;

	    count++;
	    try {
		company = objectMapper.readValue(new URL(urlstr), new TypeReference<Company>() {
		});
		if (company.getCompanyName() != null) {
		    companyList.add(company);
		    logger.info("getCompanyList - Symbol {} of {}: ({})-{}", count, totalsize, company.getSymbol(),
			    company.getCompanyName());
		} else {
		    logger.info("getCompanyList - Skipping: ({})-{}", company.getSymbol(), company.getCompanyName());
		    count--;
		}

	    } catch (IOException e) {
		logger.warn("getCompanyList - Skipping unknown symbol from API: ({})", symbol.getSymbol());
		count--;
	    }
	    if (count > parameters.getMaxStocktoProcess())
		break;
	} // for

	if (count > 0) {
	    logger.info("getCompanyList- Total symbols returned {} of {} ", count, totalsize);
	} else {
	    logger.error("getCompanyList - No symbol returned !");
	}
	return companyList;
    }

    /**
     * Download chart data or price history data from Iex from a given company
     * symbol
     * 
     * @param company
     * @param period
     * @return
     * @throws MalformedURLException
     */
    @Override
    public List<Chart> getDailyChartList(String symbol, String period) throws MalformedURLException {

	// Will contain quote List from Internet.
	// https://api.iextrading.com/1.0/stock/aapl/chart/5y

	String urlstr = parameters.getIexPrefix() + "stock/" + symbol + "/chart/" + period;

	int size = 0;
	List<Chart> chartList = null;
	// logger.debug("getDailyChartList - Launching Symbol chart download - IEX Url
	// {}",urlstr);

	ObjectMapper objectMapper = new ObjectMapper();
	try {
	    chartList = objectMapper.readValue(new URL(urlstr), new TypeReference<List<Chart>>() {
	    });
	    size = chartList.size();
	    // logger.info("getDailyChartList - Read {} dates",size);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return chartList;
    }

    /**
     * Download chart data or price history data from Iex from a given company
     * symbol
     * 
     * @param company
     * @param period
     * @return
     * @throws MalformedURLException
     */
    @Override
    public List<Chart> updateDailyChartList(String symbol, String period) throws MalformedURLException {

	// Will contain quote List from Internet.
	// https://api.iextrading.com/1.0/stock/aapl/chart/5y

	String urlstr = parameters.getIexPrefix() + "stock/" + symbol + "/chart/" + period;

	int size = 0;
	List<Chart> chartList = null;
	// logger.debug("getDailyChartList - Launching Symbol chart download - IEX Url
	// {}",urlstr);

	ObjectMapper objectMapper = new ObjectMapper();
	try {
	    chartList = objectMapper.readValue(new URL(urlstr), new TypeReference<List<Chart>>() {
	    });
	    size = chartList.size();
	    // logger.info("getDailyChartList - Read {} dates",size);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return chartList;
    }

    /**
     * This method will return in an String the last date the market was opened or
     * the last date a symbol was traded. It basically requests the price of AAPL
     * from Google and returns the last date a price is available. Does not check if
     * last time was 16:00 since some days end at noon.
     * 
     * @author atlantis
     * @param symbol
     *            : Symbol to check last available trading date.
     * @return String with last date
     * @throws java.lang.Exception
     */
    @Override
    public String getLastOpenMarketDate() throws Exception {

	String lastOpenDate = "";
	List<Chart> chartList = new ArrayList<>();

	chartList = updateDailyChartList(parameters.getSymbolToCheckLastMarketOpenDate(), StockTechSysConstants.ONEMONTH);

	if (chartList.size() > 1) {
	    lastOpenDate = chartList.get(chartList.size() - 1).getDate();
	} else
	    lastOpenDate = "";

	return lastOpenDate;
    }
}

/*
 * xxxx public static int findLatestSymbolTimestampfromDb(String symbol, String
 * exchange) throws SQLException { String sym = null; String dat = null; String
 * timestamp = null;
 * 
 * c.setAutoCommit(false); prepStmt = c.prepareStatement( "SELECT * FROM CHART "
 * + "WHERE SYMBOL = ?"+ "AND DATE = (SELECT MAX(DATE) " +
 * "FROM CHART WHERE SYMBOL = ?); " );
 * 
 * prepStmt.setString(1,symbol); prepStmt.setString(2,exchange);
 * prepStmt.setString(3,symbol); // prepStmt.addBatch();
 * 
 * ResultSet rs = prepStmt.executeQuery(); try { while ( rs.next() ) { sym =
 * rs.getString("symbol"); dat = rs.getString("date"); timestamp =
 * rs.getString("timestamp"); // System.out.println( "symbol = " + sym ); //
 * System.out.println( "Date= " + dat ); // System.out.println(); }
 * prepStmt.close(); c.setAutoCommit(true);
 * 
 * } catch ( Exception e ) {
 * logger.error("{} : {}",e.getClass().getName(),e.getMessage() ); //
 * System.exit(0); return 0; } return Integer.parseInt(timestamp); }
 * 
 * }
 */