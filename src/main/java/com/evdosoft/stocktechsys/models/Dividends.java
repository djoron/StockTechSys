/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

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
@Data
public class Dividends {
   
    private Date exDate;
    private Date paymentDate;
    private Date recordDate;
    private Date declaredDate;
    private BigDecimal amount;
    @JsonProperty("type")
    private String typediv;
    private String qualified;
    private String indicated;
            
}
