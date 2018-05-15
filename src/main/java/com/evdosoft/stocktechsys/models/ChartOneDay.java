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
 * Model to download last day price History from IEX
 * Example of call: https://api.iextrading.com/1.0/stock/aapl/chart/5y
 * Typical response:
[ {"date":"20180105",
* "minute":"10:59",
* "label":"10:59 AM",
* "high":175.19,
* "low":175.18,
* "average":175.185,
* "volume":200,
* "notional":35037,
* "numberOfTrades":2,
* "marketHigh":175.19,
* "marketLow":175.065,
* "marketAverage":175.161,
* "marketVolume":60642,
* "marketNotional":10622121.2319,
* "marketNumberOfTrades":437,
* "marketChangeOverTime":0.009666597496022644,
* "changeOverTime":0.010608840123221757}
 *},...,{...}]
 * @author dominicj
 */

@Data
public class ChartOneDay {
   
    private Date date;
//    private Time minute;
    private String label;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal average;
    private Long volume;
    private Long notional;
    private Long numberOfTrades;
    private BigDecimal marketHigh;
    private BigDecimal marketLow;
    private BigDecimal marketAverage;
    private Long marketVolume;
    private BigDecimal marketNotional;
    private BigDecimal marketNumberOfTrades;
    private double marketChangeOverTime;
    private double changeOverTime;
  }
