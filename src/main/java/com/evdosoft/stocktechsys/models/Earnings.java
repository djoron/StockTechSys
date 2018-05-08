/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

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

    private double  actualEPS;
    private double  consensusEPS;
    private double  estimatedEPS;
    private double  announceTime;
    private double  numberOfEstimates;
    private double  EPSSurpriseDollar;
    private Date    EPSReportDate;
    private String fiscalPeriod;
    private Date  fiscalEndDate;
    private double  yearAgo;
    private double  yearAgoChangePercent;
    private double  estimatedChangePercent;
    private int    symbolId;
    public double getActualEPS() {
        return actualEPS;
    }
    public void setActualEPS(double actualEPS) {
        this.actualEPS = actualEPS;
    }
    public double getConsensusEPS() {
        return consensusEPS;
    }
    public void setConsensusEPS(double consensusEPS) {
        this.consensusEPS = consensusEPS;
    }
    public double getEstimatedEPS() {
        return estimatedEPS;
    }
    public void setEstimatedEPS(double estimatedEPS) {
        this.estimatedEPS = estimatedEPS;
    }
    public double getAnnounceTime() {
        return announceTime;
    }
    public void setAnnounceTime(double announceTime) {
        this.announceTime = announceTime;
    }
    public double getNumberOfEstimates() {
        return numberOfEstimates;
    }
    public void setNumberOfEstimates(double numberOfEstimates) {
        this.numberOfEstimates = numberOfEstimates;
    }
    public double getEPSSurpriseDollar() {
        return EPSSurpriseDollar;
    }
    public void setEPSSurpriseDollar(double ePSSurpriseDollar) {
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
    public double getYearAgo() {
        return yearAgo;
    }
    public void setYearAgo(double yearAgo) {
        this.yearAgo = yearAgo;
    }
    public double getYearAgoChangePercent() {
        return yearAgoChangePercent;
    }
    public void setYearAgoChangePercent(double yearAgoChangePercent) {
        this.yearAgoChangePercent = yearAgoChangePercent;
    }
    public double getEstimatedChangePercent() {
        return estimatedChangePercent;
    }
    public void setEstimatedChangePercent(double estimatedChangePercent) {
        this.estimatedChangePercent = estimatedChangePercent;
    }
    public int getSymbolId() {
        return symbolId;
    }
    public void setSymbolId(int symbolId) {
        this.symbolId = symbolId;
    }
    
}
