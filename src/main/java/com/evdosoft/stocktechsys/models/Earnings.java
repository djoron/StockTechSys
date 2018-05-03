/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

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

    protected float  actualEPS;
    protected float  consensusEPS;
    protected float  estimatedEPS;
    protected float  announceTime;
    protected float  numberOfEstimates;
    protected float  EPSSurpriseDollar;
    protected Date   EPSReportDate;
    protected String fiscalPeriod;
    protected float  fiscalEndDate;
    protected float  yearAgo;
    protected float  yearAgoChangePercent;
    protected float  estimatedChangePercent;
    protected int    symbolId;
    public float getActualEPS() {
        return actualEPS;
    }
    public void setActualEPS(float actualEPS) {
        this.actualEPS = actualEPS;
    }
    public float getConsensusEPS() {
        return consensusEPS;
    }
    public void setConsensusEPS(float consensusEPS) {
        this.consensusEPS = consensusEPS;
    }
    public float getEstimatedEPS() {
        return estimatedEPS;
    }
    public void setEstimatedEPS(float estimatedEPS) {
        this.estimatedEPS = estimatedEPS;
    }
    public float getAnnounceTime() {
        return announceTime;
    }
    public void setAnnounceTime(float announceTime) {
        this.announceTime = announceTime;
    }
    public float getNumberOfEstimates() {
        return numberOfEstimates;
    }
    public void setNumberOfEstimates(float numberOfEstimates) {
        this.numberOfEstimates = numberOfEstimates;
    }
    public float getEPSSurpriseDollar() {
        return EPSSurpriseDollar;
    }
    public void setEPSSurpriseDollar(float ePSSurpriseDollar) {
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
    public float getFiscalEndDate() {
        return fiscalEndDate;
    }
    public void setFiscalEndDate(float fiscalEndDate) {
        this.fiscalEndDate = fiscalEndDate;
    }
    public float getYearAgo() {
        return yearAgo;
    }
    public void setYearAgo(float yearAgo) {
        this.yearAgo = yearAgo;
    }
    public float getYearAgoChangePercent() {
        return yearAgoChangePercent;
    }
    public void setYearAgoChangePercent(float yearAgoChangePercent) {
        this.yearAgoChangePercent = yearAgoChangePercent;
    }
    public float getEstimatedChangePercent() {
        return estimatedChangePercent;
    }
    public void setEstimatedChangePercent(float estimatedChangePercent) {
        this.estimatedChangePercent = estimatedChangePercent;
    }
    public int getSymbolId() {
        return symbolId;
    }
    public void setSymbolId(int symbolId) {
        this.symbolId = symbolId;
    }

    
}
