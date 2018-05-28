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
 * Model to download price History from IEX
 * Example of call: https://api.iextrading.com/1.0/stock/aapl/chart/5y
 * Typical response:
 * {"date":"2012-12-12",
 *  "open":78.2528,
 *  "high":78.2856,
 *  "low":76.6099,
 *  "close":76.9999,
 *  "volume":121650522,
 *  "unadjustedVolume":17378646,
 *  "change":-0.341143,
 *  "changePercent":-0.441,
 *  "vwap":77.2481,
 *  "label":"Dec 12, 12",
 *  "changeOverTime":0}
 * 
 * @author dominicj
 */
@Data
public class Chart {
 
    private Date date;
    private BigDecimal open;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal close;
    private Long volume;
    private Long unadjustedVolume;
    private BigDecimal change;
    private BigDecimal changePercent;
    private BigDecimal vwap;
    private String label;
    private BigDecimal changeOverTime;

}

    