/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author atlantis Open InitJson.json file that contains various parameters.
 *         Initializes using static init sequence Used http://jsonmate.com/ to
 *         validate JSON file.
 */

@ConfigurationProperties(prefix="stocktechsys")
public class Parameters {
        
    private String iexPrefix;
    private String iexPrefixSymbols;
    private int yearHistoryInt;
    private String yearHistoryString;
    private int hourBloombergDataAvail;
    private int iexMaxStockAtaTime;
    private int maxStocktoProcess;
    private String symbolToCheckLastMarketOpenDate; 
    private int maxDayBeforeObsolete;
    private int stringBufferSize;
    private int timeout;
    
    public String getIexPrefix() {
        return iexPrefix;
    }
    public void setIexPrefix(String iexPrefix) {
        this.iexPrefix = iexPrefix;
    }
    public String getIexPrefixSymbols() {
        return iexPrefixSymbols;
    }
    public void setIexPrefixSymbols(String iexPrefixSymbols) {
        this.iexPrefixSymbols = iexPrefixSymbols;
    }
    public int getYearHistoryInt() {
        return yearHistoryInt;
    }
    public void setYearHistoryInt(int yearHistoryInt) {
        this.yearHistoryInt = yearHistoryInt;
    }
    public String getYearHistoryString() {
        return yearHistoryString;
    }
    public void setYearHistoryString(String yearHistoryString) {
        this.yearHistoryString = yearHistoryString;
    }
    public int getHourBloombergDataAvail() {
        return hourBloombergDataAvail;
    }
    public void setHourBloombergDataAvail(int hourBloombergDataAvail) {
        this.hourBloombergDataAvail = hourBloombergDataAvail;
    }
    public int getIexMaxStockAtaTime() {
        return iexMaxStockAtaTime;
    }
    public void setIexMaxStockAtaTime(int iexMaxStockAtaTime) {
        this.iexMaxStockAtaTime = iexMaxStockAtaTime;
    }
    public int getMaxStocktoProcess() {
        return maxStocktoProcess;
    }
    public void setMaxStocktoProcess(int maxStocktoProcess) {
        this.maxStocktoProcess = maxStocktoProcess;
    }
    public String getSymbolToCheckLastMarketOpenDate() {
        return symbolToCheckLastMarketOpenDate;
    }
    public void setSymbolToCheckLastMarketOpenDate(String symbolToCheckLastMarketOpenDate) {
        this.symbolToCheckLastMarketOpenDate = symbolToCheckLastMarketOpenDate;
    }
    public int getMaxDayBeforeObsolete() {
        return maxDayBeforeObsolete;
    }
    public void setMaxDayBeforeObsolete(int maxDayBeforeObsolete) {
        this.maxDayBeforeObsolete = maxDayBeforeObsolete;
    }
    public int getStringBufferSize() {
        return stringBufferSize;
    }
    public void setStringBufferSize(int stringBufferSize) {
        this.stringBufferSize = stringBufferSize;
    }
    public int getTimeout() {
        return timeout;
    }
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    
    // public static String PARAMETERSPATH = ".\\Properties.json";
    //
    // public static String DATABASEFILENAME; // Database file name
    // public static String IEXPREFIX; // Prefix before calling IEX API
    // public static String IEXPREFIXSYMBOLS; // IEX Symbols API command
    // public static int YEAR_HISTORY_INT; // 2 How far in time Daily price
    // download. MUST BE of INT with same length
    // // (number) as below.
    // public static String YEAR_HISTORY_STRING; // 2Y How far in time Daily price
    // download. MUST BE of format NUMBER+Y
    // public static int IEXMAXSTOCKATATIME; // Max IEX stock price to download
    // simultaneously.
    //
    // public static int MAXSTOCKTOPROCESS;
    // public static String SYMBOLTOCHECKLASTMARKETOPENDATE; // Use this stock to
    // confirm last day market was open
    // // Number of seconds in given periods for price download
    // public static int MAXDAYSBEFOREOBSOLETE; // 30 Max days without price update
    // to make symbol obsolete and stop
    // // updating it in local DB.
    // public static int STRINGBUFFERSIZE; // StringBuffer max size for SQL commands
    // public static int TIMEOUT; // Timeout when sending url commands.
    //
    // static {
    // final ObjectMapper mapper = new ObjectMapper();
    // Map<String, Object> paramMap = new HashMap<String, Object>();
    //
    // try {
    // paramMap = mapper.readValue(new File(PARAMETERSPATH), new
    // TypeReference<Map<String, Object>>() {
    // });
    // } catch (IOException ex) {
    // Logger.getLogger(Parameters.class.getName()).log(Level.SEVERE, null, ex);
    // }
    //
    // DATABASEFILENAME = (String) paramMap.get("databaseFileName");
    // logger.info("Parameter loaded DATABASEILENAME: {}", DATABASEFILENAME);
    //
    // IEXPREFIX = (String) paramMap.get("iexPrefix");
    // logger.info("Parameter loaded IEXPREFIX: {}", IEXPREFIX);
    //
    // IEXPREFIXSYMBOLS = (String) paramMap.get("iexPrefixSymbols");
    // logger.info("Parameter loaded IEXPREFIXSYMBOLS: {}", IEXPREFIXSYMBOLS);
    //
    // YEAR_HISTORY_INT = Integer.parseInt((String) paramMap.get("yearHistoryInt"));
    // logger.info("Parameter loaded YEAR_HISTORY_INT: {}", YEAR_HISTORY_INT);
    //
    // YEAR_HISTORY_STRING = (String) paramMap.get("yearHistoryString");
    // logger.info("Parameter loaded YEAR_HISTORY_STRING: {}", YEAR_HISTORY_STRING);
    //
    // IEXMAXSTOCKATATIME = Integer.parseInt((String)
    // paramMap.get("iexMaxStockAtaTime"));
    // logger.info("Parameter loaded IEXMAXSTOCKATATIME : {}", IEXMAXSTOCKATATIME);
    //
    // MAXSTOCKTOPROCESS = Integer.parseInt((String)
    // paramMap.get("maxStocktoProcess"));
    // logger.info("Parameter loaded MAXSTOCKTOPROCESS : {}", MAXSTOCKTOPROCESS);
    //
    // SYMBOLTOCHECKLASTMARKETOPENDATE = (String)
    // paramMap.get("symbolToCheckLastMarketOpenDate");
    // logger.info("Parameter loaded SYMBOLTOCHECKLASTMARKETOPENDATE : {}",
    // SYMBOLTOCHECKLASTMARKETOPENDATE);
    //
    // MAXDAYSBEFOREOBSOLETE = Integer.parseInt((String)
    // paramMap.get("maxDayBeforeObsolete"));
    // logger.info("Parameter loaded MAXDAYSBEFOREOBSOLETE : {}",
    // MAXDAYSBEFOREOBSOLETE);
    //
    // STRINGBUFFERSIZE = Integer.parseInt((String)
    // paramMap.get("stringBufferSize"));
    // logger.info("Parameter loaded STRINGBUFFERSIZE : {}", STRINGBUFFERSIZE);
    //
    // TIMEOUT = Integer.parseInt((String) paramMap.get("timeout"));
    // logger.info("Parameter loaded TIMEOUT : {}", TIMEOUT);
    //
    // logger.info("Done Loading Parameters.");
    //
    // }
}
