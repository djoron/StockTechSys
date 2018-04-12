
package com.evdosoft.stocktechsys;

/**
 *
 * @author atlantis
 * Main Program
 */
public class StockTechSysConstants {
    /**
     * 
     * @param args the command line 
     * @throws java.io.IOException 
     */
    // GLOBAL CONSTANT   
    // See parameters.java for initialization of program parameters.

    public static String   FIVEYEARS = "5y";
    public static String    TWOYEARS = "2y";
    public static String     ONEYEAR = "1y";
    public static String         YTD = "ytd";
    public static String   SIXMONTHS = "6m";
    public static String THREEMONTHS = "3m";
    public static String    ONEMONTH = "1m";

    public static int DAILY  = 24*60*60; // = 86400 
    public static int MIN240 =   240*60; 
    public static int MIN120 =   120*60; 
    public static int MIN60  =    60*60; 
    public static int MIN30  =    30*60; 
    public static int MIN15  =    15*60; 
    public static int MIN10  =    10*60; 
    public static int MIN5   =     5*60; 
    
    public static enum TypeListDownload 
    {   // COmpany List download type
        ORIGINAL,  // Master list downloaded first time DB is created.
        TEMPORARY; // Temp List downloaded to compare with master
    }
       
}


