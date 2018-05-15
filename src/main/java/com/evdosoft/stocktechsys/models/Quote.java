/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

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
@Data
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
    private Long latestVolume;
    private BigDecimal iexRealtimePrice;
    private Long iexRealtimeSize;
    private Date iexLastUpdated;
    private BigDecimal delayedPrice;
    private Date delayedPriceTime;
    private BigDecimal previousClose;
    private BigDecimal change;
    private BigDecimal changePercent;
    private BigDecimal iexMarketPercent;
    private Long iexVolume;
    private Long avgTotalVolume;
    private BigDecimal iexBidPrice;
    private Long iexBidSize;
    private BigDecimal iexAskPrice;
    private Long iexAskSize;
    private Long marketCap;
    private BigDecimal peRatio;
    private BigDecimal week52High;
    private BigDecimal week52Low;
    private double ytdChange;
    
        
}
