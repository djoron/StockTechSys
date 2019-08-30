/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
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
import com.evdosoft.stocktechsys.web.resource.SymbolResource;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author dominicj
 */
@Repository
public class IexDaoImpl implements IexDao {

    private static final Logger logger = LoggerFactory.getLogger(IexDaoImpl.class);
    
    private Parameters parameters;
    
    @Autowired
    public IexDaoImpl(Parameters parameters) {
	this.parameters = parameters;
    }    
    
    /**
     *
     * @param symbolList
     * @return
     * @throws MalformedURLException
     */
    @Override
    public List<Symbol> getSymbolList() throws MalformedURLException {

    	String[] countryCodes = parameters.getIexCountryCodes();
    	int countryNumber = countryCodes.length;
    	String urlstr = "";
    	
    	List<Symbol> symbolList = new ArrayList<>();

	    for (int i = 0; i<countryNumber; i++) {
	    	int symbolAdded = 0;
	    	
	    	// example: https://cloud.iexapis.com/stable/ref-data/region/ca/symbols?token=pk_18d74cb6232f43f9b36fc9a09023ae84
	    	urlstr = parameters.getIexPrefix() + parameters.getIexCountryPrefix() + countryCodes[i] +  "/" +
	    					parameters.getIexSymbolSuffix()+parameters.getIexPublicToken();	
	    	
	    	logger.debug("getSymbolList - Launching Symbol download with - {}",urlstr);
	    	logger.info("getSymbolList - Launching Symbol download for country: {} ",countryCodes[i]);
	
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	try {
	    	    List<SymbolResource> symbolResourceList = objectMapper.readValue(new URL(urlstr), new TypeReference<List<SymbolResource>>() {
	    	    });


	    	    for(SymbolResource resource : symbolResourceList) {
	    	    	Symbol symbol = new Symbol(resource);
	    	    	symbolList.add(symbol);
	    	    	symbolAdded++;
	    	    }
		    	logger.info("getSymbolList - Read {} symbols for country: {}",symbolAdded,countryCodes[i]);

	    	    
	    	} catch (IOException e) {
	    	    // TODO Auto-generated catch block
	    	    e.printStackTrace();
	    	}
	    } // for loop
    	logger.info("getSymbolList - Read overall {} symbols",symbolList.size());
	    return symbolList;
    }

    /**
     *
     * @param symbolList
     *            Internet list with only symbol name
     * @param CompanyList
     *            Internet list with symbol and company info
     * @return
     * @throws MalformedURLException
     */
    @Override
    public List<Company> getCompanyList(List<Symbol> symbolList) throws MalformedURLException {
	String urlstr;

	// Will contain symbol only List from Internet.
	List<Company> companyList = new ArrayList<>();
	int totalsize = symbolList.size();

	// https://cloud.iexapis.com/stable/stock/AABA/company?token=pk_18d74cb6232f43f9b36fc9a09023ae84
	int count = 0;

	for (Symbol symbol : symbolList) {

	    urlstr = parameters.getIexPrefix() + "stock/" + symbol.getSymbol() + "/company"+ parameters.getIexPublicToken();
	    ObjectMapper objectMapper = new ObjectMapper();
	    Company company = null;
	    
        if (!symbol.getType().contains("cs") || !symbol.getType().contains("etf") || !symbol.getType().contains("ps") ) continue;
	    
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
		logger.warn("{}",e.getLocalizedMessage());
		count--;
	    }
	    if (count > (parameters.getMaxStocktoProcess()-1))
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

	// Will contain chart List from Internet.
	// https://api.iextrading.com/1.0/stock/aapl/chart/5y
	// "date":"2013-05-28",
	// "open":58.6678,
	// "high":58.8256,
	// "low":57.4877,
	// "close":57.5645,
	// "volume":96404189,
	// "unadjustedVolume":13772027,
	// "change":-0.48392,
	// "changePercent":-0.834,
	// "vwap":58.0274,
	// "label":"May 28, 13",
	// "changeOverTime":0}
    

	String urlstr = parameters.getIexPrefix() + "stock/" + symbol + "/chart/" + period;


	List<Chart> chartList = null;

	ObjectMapper objectMapper = new ObjectMapper();
	try {
	    chartList = objectMapper.readValue(new URL(urlstr), new TypeReference<List<Chart>>() {
	    });
  	    int size = 0;
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
    public LocalDate getLastOpenMarketDate() throws Exception {

	LocalDate lastOpenDate = null;
	List<Chart> chartList = new ArrayList<>();

	chartList = updateDailyChartList(parameters.getSymbolToCheckLastMarketOpenDate(), StockTechSysConstants.ONEMONTH);

	if (chartList.size() > 1) {
	  //   lastOpenDate = chartList.get(chartList.size() - 1).getDate();
	}
	
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