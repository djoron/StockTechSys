/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

import java.math.BigDecimal;
import java.util.Date;

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
public class Earnings {

    private BigDecimal  actualEPS;
    private BigDecimal  consensusEPS;
    private BigDecimal  estimatedEPS;
    private String      announceTime;
    private BigDecimal  numberOfEstimates;
    private BigDecimal  EPSSurpriseDollar;
    private Date    EPSReportDate;
    private String fiscalPeriod;
    private Date  fiscalEndDate;
    private BigDecimal  yearAgo;
    private BigDecimal  yearAgoChangePercent;
    private BigDecimal  estimatedChangePercent;
    private int    symbolId;
    public BigDecimal getActualEPS() {
        return actualEPS;
    }
    public void setActualEPS(BigDecimal actualEPS) {
        this.actualEPS = actualEPS;
    }
    public BigDecimal getConsensusEPS() {
        return consensusEPS;
    }
    public void setConsensusEPS(BigDecimal consensusEPS) {
        this.consensusEPS = consensusEPS;
    }
    public BigDecimal getEstimatedEPS() {
        return estimatedEPS;
    }
    public void setEstimatedEPS(BigDecimal estimatedEPS) {
        this.estimatedEPS = estimatedEPS;
    }
    public String getAnnounceTime() {
        return announceTime;
    }
    public void setAnnounceTime(String announceTime) {
        this.announceTime = announceTime;
    }
    public BigDecimal getNumberOfEstimates() {
        return numberOfEstimates;
    }
    public void setNumberOfEstimates(BigDecimal numberOfEstimates) {
        this.numberOfEstimates = numberOfEstimates;
    }
    public BigDecimal getEPSSurpriseDollar() {
        return EPSSurpriseDollar;
    }
    public void setEPSSurpriseDollar(BigDecimal ePSSurpriseDollar) {
        EPSSurpriseDollar = ePSSurpriseDollar;
    }
    public Date getEPSReportDate() {
        return EPSReportDate;
    }
    public void setEPSReportDate(Date ePSReportDate) {
        EPSReportDate = ePSReportDate;
    }
    public String getFiscalPeriod() {
        return fiscalPeriod;
    }
    public void setFiscalPeriod(String fiscalPeriod) {
        this.fiscalPeriod = fiscalPeriod;
    }
    public Date getFiscalEndDate() {
        return fiscalEndDate;
    }
    public void setFiscalEndDate(Date fiscalEndDate) {
        this.fiscalEndDate = fiscalEndDate;
    }
    public BigDecimal getYearAgo() {
        return yearAgo;
    }
    public void setYearAgo(BigDecimal yearAgo) {
        this.yearAgo = yearAgo;
    }
    public BigDecimal getYearAgoChangePercent() {
        return yearAgoChangePercent;
    }
    public void setYearAgoChangePercent(BigDecimal yearAgoChangePercent) {
        this.yearAgoChangePercent = yearAgoChangePercent;
    }
    public BigDecimal getEstimatedChangePercent() {
        return estimatedChangePercent;
    }
    public void setEstimatedChangePercent(BigDecimal estimatedChangePercent) {
        this.estimatedChangePercent = estimatedChangePercent;
    }
    public int getSymbolId() {
        return symbolId;
    }
    public void setSymbolId(int symbolId) {
        this.symbolId = symbolId;
    }
    
        
}
