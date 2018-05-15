/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

/**
 * Model for earnings data from IEX
 * https://iextrading.com/developer/docs/#earnings
 * 
   {"symbol":"AAPL",
   "earnings":[{
   "actualEPS":2.73,
   "consensusEPS":2.69,
   "estimatedEPS":2.69,
   "announceTime":"AMC",
   "numberOfEstimates":10,
   "EPSSurpriseDollar":0.04,
   "EPSReportDate":"2018-05-01",
   "fiscalPeriod":"Q2 2018",
   "fiscalEndDate":"2018-03-31",
   "yearAgo":2.1,
   "yearAgoChangePercent":30,
   "estimatedChangePercent":28,
   "symbolId":11},
   
 * @author dominicj
 */
@Data
public class Earnings {

    private BigDecimal  actualEPS;
    private BigDecimal  consensusEPS;
    private BigDecimal  estimatedEPS;
    private String      announceTime;
    private BigDecimal  numberOfEstimates;
    private BigDecimal  EPSSurpriseDollar;
    private LocalDate    EPSReportDate;
    private String fiscalPeriod;
    private LocalDate  fiscalEndDate;
    private BigDecimal  yearAgo;
    private BigDecimal  yearAgoChangePercent;
    private BigDecimal  estimatedChangePercent;
    private Long    symbolId;
    
            
}
