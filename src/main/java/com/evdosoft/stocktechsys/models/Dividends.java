/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model for dividends data from IEX
 * https://iextrading.com/developer/docs/#dividends
 * [{"exDate":"2018-02-09",
   "paymentDate":"2018-02-15",
   "recordDate":"2018-02-12",
   "declaredDate":"2018-02-01",
   "amount":0.63,
   "flag":"",
   "type":"Dividend income",
   "qualified":"Q",
   "indicated":""}]
    * @author dominicj
 */
public class Dividends {
   
    private Date exDate;
    private Date paymentDate;
    private Date recordDate;
    private Date declaredDate;
    private double amount;
    @JsonProperty("type")
    private String typediv;
    private String qualified;
    private String indicated;
    
    public Date getExDate() {
        return exDate;
    }
    public void setExDate(Date exDate) {
        this.exDate = exDate;
    }
    public Date getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    public Date getRecordDate() {
        return recordDate;
    }
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }
    public Date getDeclaredDate() {
        return declaredDate;
    }
    public void setDeclaredDate(Date declaredDate) {
        this.declaredDate = declaredDate;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getTypediv() {
        return typediv;
    }
    public void setTypediv(String typediv) {
        this.typediv = typediv;
    }
    public String getQualified() {
        return qualified;
    }
    public void setQualified(String qualified) {
        this.qualified = qualified;
    }
    public String getIndicated() {
        return indicated;
    }
    public void setIndicated(String indicated) {
        this.indicated = indicated;
    }
        
}
