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
    private String iexPrefixSymbolsUs;
    private String iexPrefixSymbolsCa;
    private String iexPublicToken;
    private int yearHistoryInt;
    private String yearHistoryString;
    private int hourBloombergDataAvail;
    private int iexMaxStockAtaTime;
    private int maxStocktoProcess;
    private String symbolToCheckLastMarketOpenDate; 
    private int maxDayBeforeObsolete;
    private int stringBufferSize;
    private int timeout;
    private int getMaxChartListToDownload; 
        
    public String getIexPrefix() {
        return iexPrefix;
    }
    public void setIexPrefix(String iexPrefix) {
        this.iexPrefix = iexPrefix;
    }
    public String getIexPrefixSymbolsUs() {
        return iexPrefixSymbolsUs;
    }
    public String getIexPrefixSymbolsCa() {
        return iexPrefixSymbolsCa;
    }
    public String getIexPublicToken() {
        return iexPublicToken;
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
    public int getGetMaxChartListToDownload() {
        return getMaxChartListToDownload;
    }
    public void setGetMaxChartListToDownload(int getMaxChartListToDownload) {
        this.getMaxChartListToDownload = getMaxChartListToDownload;
    }
 
}
