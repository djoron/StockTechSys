/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 *"symbol": "AAPL",
  "companyName": "Apple Inc.",
  "primaryExchange": "Nasdaq Global Select",
  "sector": "Technology",
  "calculationPrice": "tops",
  "open": 154,
  "openTime": 1506605400394,
  "close": 153.28,
  "closeTime": 1506605400394,
  "high": 154.80,
  "low": 153.25,
  "latestPrice": 158.73,
  "latestSource": "Previous close",
  "latestTime": "September 19, 2017",
  "latestUpdate": 1505779200000,
  "latestVolume": 20567140,
  "iexRealtimePrice": 158.71,
  "iexRealtimeSize": 100,
  "iexLastUpdated": 1505851198059,
  "delayedPrice": 158.71,
  "delayedPriceTime": 1505854782437,
  "previousClose": 158.73,
  "change": -1.67,
  "changePercent": -0.01158,
  "iexMarketPercent": 0.00948,
  "iexVolume": 82451,
  "avgTotalVolume": 29623234,
  "iexBidPrice": 153.01,
  "iexBidSize": 100,
  "iexAskPrice": 158.66,
  "iexAskSize": 100,
  "marketCap": 751627174400,
  "peRatio": 16.86,
  "week52High": 159.65,
  "week52Low": 93.63,
  "ytdChange": 0.3665,


 * @author atlantis
 */
public class Quote {
    
    private String symbol; 
    private String companyName;
    private String primaryExchange;
    private String sector;
    private String calculationPrice;
    private BigDecimal open;
    private Date openTime;
    private BigDecimal close;
    private Date closeTime;
    private BigDecimal latestPrice;
    private String latestSource;
    private Date latestTime;
    private Date latestUpdate; 
    private int latestVolume;
    private BigDecimal iexRealtimePrice;
    private int iexRealtimeSize;
    private Date iexLastUpdated;
    private BigDecimal delayedPrice;
    private Date delayedPriceTime;
    private BigDecimal previousClose;
    private double change;
    private double changePercent;
    private double iexMarketPercent;
    private int iexVolume;
    private int avgTotalVolume;
    private BigDecimal iexBidPrice;
    private int iexBidSize;
    private BigDecimal iexAskPrice;
    private int iexAskSize;
    private int marketCap;
    private double peRatio;
    private BigDecimal week52High;
    private BigDecimal week52Low;
    
    private double ytdChange;
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getPrimaryExchange() {
        return primaryExchange;
    }
    public void setPrimaryExchange(String primaryExchange) {
        this.primaryExchange = primaryExchange;
    }
    public String getSector() {
        return sector;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }
    public String getCalculationPrice() {
        return calculationPrice;
    }
    public void setCalculationPrice(String calculationPrice) {
        this.calculationPrice = calculationPrice;
    }
    public BigDecimal getOpen() {
        return open;
    }
    public void setOpen(BigDecimal open) {
        this.open = open;
    }
    public Date getOpenTime() {
        return openTime;
    }
    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }
    public BigDecimal getClose() {
        return close;
    }
    public void setClose(BigDecimal close) {
        this.close = close;
    }
    public Date getCloseTime() {
        return closeTime;
    }
    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
    public BigDecimal getLatestPrice() {
        return latestPrice;
    }
    public void setLatestPrice(BigDecimal latestPrice) {
        this.latestPrice = latestPrice;
    }
    public String getLatestSource() {
        return latestSource;
    }
    public void setLatestSource(String latestSource) {
        this.latestSource = latestSource;
    }
    public Date getLatestTime() {
        return latestTime;
    }
    public void setLatestTime(Date latestTime) {
        this.latestTime = latestTime;
    }
    public Date getLatestUpdate() {
        return latestUpdate;
    }
    public void setLatestUpdate(Date latestUpdate) {
        this.latestUpdate = latestUpdate;
    }
    public int getLatestVolume() {
        return latestVolume;
    }
    public void setLatestVolume(int latestVolume) {
        this.latestVolume = latestVolume;
    }
    public BigDecimal getIexRealtimePrice() {
        return iexRealtimePrice;
    }
    public void setIexRealtimePrice(BigDecimal iexRealtimePrice) {
        this.iexRealtimePrice = iexRealtimePrice;
    }
    public int getIexRealtimeSize() {
        return iexRealtimeSize;
    }
    public void setIexRealtimeSize(int iexRealtimeSize) {
        this.iexRealtimeSize = iexRealtimeSize;
    }
    public Date getIexLastUpdated() {
        return iexLastUpdated;
    }
    public void setIexLastUpdated(Date iexLastUpdated) {
        this.iexLastUpdated = iexLastUpdated;
    }
    public BigDecimal getDelayedPrice() {
        return delayedPrice;
    }
    public void setDelayedPrice(BigDecimal delayedPrice) {
        this.delayedPrice = delayedPrice;
    }
    public Date getDelayedPriceTime() {
        return delayedPriceTime;
    }
    public void setDelayedPriceTime(Date delayedPriceTime) {
        this.delayedPriceTime = delayedPriceTime;
    }
    public BigDecimal getPreviousClose() {
        return previousClose;
    }
    public void setPreviousClose(BigDecimal previousClose) {
        this.previousClose = previousClose;
    }
    public double getChange() {
        return change;
    }
    public void setChange(double change) {
        this.change = change;
    }
    public double getChangePercent() {
        return changePercent;
    }
    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }
    public double getIexMarketPercent() {
        return iexMarketPercent;
    }
    public void setIexMarketPercent(double iexMarketPercent) {
        this.iexMarketPercent = iexMarketPercent;
    }
    public int getIexVolume() {
        return iexVolume;
    }
    public void setIexVolume(int iexVolume) {
        this.iexVolume = iexVolume;
    }
    public int getAvgTotalVolume() {
        return avgTotalVolume;
    }
    public void setAvgTotalVolume(int avgTotalVolume) {
        this.avgTotalVolume = avgTotalVolume;
    }
    public BigDecimal getIexBidPrice() {
        return iexBidPrice;
    }
    public void setIexBidPrice(BigDecimal iexBidPrice) {
        this.iexBidPrice = iexBidPrice;
    }
    public int getIexBidSize() {
        return iexBidSize;
    }
    public void setIexBidSize(int iexBidSize) {
        this.iexBidSize = iexBidSize;
    }
    public BigDecimal getIexAskPrice() {
        return iexAskPrice;
    }
    public void setIexAskPrice(BigDecimal iexAskPrice) {
        this.iexAskPrice = iexAskPrice;
    }
    public int getIexAskSize() {
        return iexAskSize;
    }
    public void setIexAskSize(int iexAskSize) {
        this.iexAskSize = iexAskSize;
    }
    public int getMarketCap() {
        return marketCap;
    }
    public void setMarketCap(int marketCap) {
        this.marketCap = marketCap;
    }
    public double getPeRatio() {
        return peRatio;
    }
    public void setPeRatio(double peRatio) {
        this.peRatio = peRatio;
    }
    public BigDecimal getWeek52High() {
        return week52High;
    }
    public void setWeek52High(BigDecimal week52High) {
        this.week52High = week52High;
    }
    public BigDecimal getWeek52Low() {
        return week52Low;
    }
    public void setWeek52Low(BigDecimal week52Low) {
        this.week52Low = week52Low;
    }
    public double getYtdChange() {
        return ytdChange;
    }
    public void setYtdChange(double ytdChange) {
        this.ytdChange = ytdChange;
    }

    
}
