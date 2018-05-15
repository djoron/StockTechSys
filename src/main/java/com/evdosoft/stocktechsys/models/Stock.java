/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.models;

import java.math.BigDecimal;

import lombok.Data;

/**
 *
 * @author atlantis
 */
@Data
public class Stock {

 // xxxx a verifier les types. DOuble pour entiers, Big decimal pour double
    private String name;
    private String symbol;
    private String exchange;
    private String exchangeName;
    private String sector;
    private String industry;
    private String namecorrected;
    private String symbolcorrected;
    private String ipo;
    private BigDecimal  bookvalue;
    private BigDecimal  shortratio;
    private String dividendPayDate;
    private String exDividendDate;
    private BigDecimal pricebook;
    private BigDecimal marketcap;
    private BigDecimal dividendPerShare;
    private String dayLastUpdate; // Day software was last run to update price of stock
    private Integer obsolete;
    private char priceSource; // Default Price Source. Y=Yahoo. G = Google (by default)

    
    /*    AAPL,Apple Inc.,NMS,119.4,117.34,119.73,117.75,32482528,11/23/2015,9.22,21.4,4:00pm - <b>117.75</b>,1.46,11/12/2015,11/05/2015,5.58,656.50B.9.22,2.08
      0- Symbol,1- Name,2- exchange,3- Open, 4- Day Low, 5- Day High,6- Close,7- Last trade price,8- Volume
      9- Date,10- EPS,11- Book value,12- Last trade with time,13- Short ratio,14- Dividend pay date,15- Q ex-dividend date,
      16- Price/book, 17 - Market capitalization,18- Earnings per share,19 - Dividend per share
*/
    
}