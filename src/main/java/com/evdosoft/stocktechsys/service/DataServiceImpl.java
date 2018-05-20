package com.evdosoft.stocktechsys.service;

import static java.lang.System.exit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evdosoft.stocktechsys.models.Stock;
import com.evdosoft.stocktechsys.models.Symbol;
import com.evdosoft.stocktechsys.utilities.FileOperations;

/*
First time
Download Symbol list and save in Symbol Database
Download Company List and save in Company Table with link to Symbol ?

Update
Download Symbol list
Compare with original symbol list
Delete table
Download differences and save into company DB.

*/
@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private FileOperations fileOperation;
    
    private static final Logger logger = LoggerFactory.getLogger(DataServiceImpl.class);
    
    // Will contain Entire Stock List to/from SQLDB.
    private List<Stock> stockList = new ArrayList<>(); 

    // Will contain Entire StockList from the Internet
    private List<Stock> iexstockList = new ArrayList<>(); 
    // Will contain only new stocks to add while updating from Bloomberg an existing DB

        
    /* (non-Javadoc)
     * @see com.evdosoft.stocktechsys.service.DataService#collectData()
     */
    @Override
    public void collectData() {
	 boolean status=false;   
	        boolean createNewDb = false;
	        String beginTimeStamp;


	        // Main Service to access Database. Will be global and 
	        // passed as parameter to other services.
	        
	        // Initialize service for Stock list management
//	        PriceHistoryService priceHistoryService = new PriceHistoryServiceImpl();
//	        SqlDatabaseService sqlDatabaseService = new SqlDatabaseServiceImpl();
	        // To retrieve SymbolList from IEX
//	        SymbolService symbolService = new SymbolServiceImpl();
	        // Will contain symbol only List from Internet.
	        List<Symbol> symbolList = null;
	            
	        // Initialize service for Stock price management
	 //       PriceHistoryService priceHistoryService = new PriceHistoryService(databaseService);
	                
	        // Parameters from Json File will be loaded first time we call up a parameter
	        
	        if (logger.isDebugEnabled())
	        {
	            logger.debug("Logger activated");
	        }
	 
	        logger.info("Data provided for free by IEX: https://iextrading.com/api-exhibit-a" );
	        
	        beginTimeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
	        // If DB does not exist, create new one else ask user if he wants to 
	        // delete current data
//	        if (fileOperation.checkFileExist(DATABASEFILENAME)) {
//	               logger.info("Waiting for user confirmation.");
//	               createNewDb = AskUserInputNewDatabase();
//	               if (createNewDb) { 
//	                   if (!fileOperation.deleteFile(DATABASEFILENAME)) {
//	                       logger.error("Check if SQL DB File is opened by another application. Exiting");
//	                       exit(0);    
//	                   }
//	               }
//	        } else createNewDb = true;
	       
//	        GooglePriceHistoryDaoImpl stockPriceHistoryDao;
//	        stockPriceHistoryDao = new GooglePriceHistoryDaoImpl();
	        
	        if (createNewDb == true) {
	            logger.debug("Creating new Database. Hold on.");
//	            sqlDatabaseService.createSqlDb();
//	            symbolList = symbolService.getSymbolList();
//	            symbolService.saveSymbolList(TypeListDownload.ORIGINAL, symbolList);
//	            // Main method to retrieve stocklist from internet and save to DB.
//	            companyService.createCompanyList(symbolList);
	            // Now get priceHistory and save into DB.            
//	            priceHistoryService.createChartlist();
	            
	  //          status = priceHistoryService.downloadFullDailyPriceHistoryandSavetoDb(); 

	        } else {    
//	            symbolList = symbolService.getSymbolList();
//	            symbolService.saveSymbolList(TypeListDownload.TEMPORARY,symbolList);
//	            
//	            // Trim Symbol list to only missing ones
//	            
//	            // Update with missing symbolList        
//	            companyService.updateCompanyList(symbolList);
	            
	/* 
	            // xxx DO A BACKUP OF DB HERE ??
	            status = databaseService.initializeDatabase();
	            
	            // Check last Date market was opened.
	            String lastdate = priceHistoryService.getLastOpenMarketDate(Parameters.SYMBOLTOCHECKLASTMARKETOPENDATE);
	            logger.info("Get last date market was opened: {}.",String.valueOf(lastdate));

	            // Use Bloomberg to download full Stock List from internet
	            logger.debug("Download Full Stock List from Internet");
	            if (stockService.getStockListFromInternet(internetStockList)) 
	            {
	               // Initialize StokDao object
	                StockDao stockListDao = new StockDaoImpl(databaseService);

	                // Now check if new stocks to add in SQL Db. Use latest internet
	                // data and make a new list containing potential new symbols. 
	                logger.debug("Check if new stocks from Internet to add in DB...standby.");
	                status = stockService.trimUpdateInternetStocklist(internetStockList, trimstocktoadd);

	                // Trim stockList here. Remove duplicates and symbols with no data
	                // from google. This will save a stocklist in stocklisttrimDb.
	                logger.debug("Trim StockList in memory. Remove duplicates and Symbol with no data from Google.");
	                status = stockService.trimStockList(trimstocktoadd);
	                status = stockListDao.deleteDuplicateFromStocklistDb();
	                status = stockListDao.deleteDuplicateFromStocklistTrimDb();
	                stockListDao.deleteEmptyStockRecordsfromStocklistDb();
	                trimstocktoadd.clear();
	                // Load stocklistfrom DB to load proper format (symbol, name, exchange)
	                // saved with trimStockList
	                stockListDao.loadStocklistFromTrimStocklistDb(trimstocktoadd);
	                // Add these in master stocklist DB
	                stockListDao.addStocklisttoDb(trimstocktoadd);
	                // Stocks saved to database. Update Sector Data in DB. Will update old and new stocks.
	                SectorDataDaoImpl sectorData;
	                sectorData = new SectorDataDaoImpl();
	                sectorData.saveSectortoList(stockSectorList);
	                status=sectorData.addSectorListtoDb(stockSectorList);
	                // No longer need so clear it.
	                trimstocktoadd.clear();
	                // Download full history from Google for new stocks added in DB.
	    // NO this erases STOCKPRICEDAILY. UPDATE LATER           status = SqlDatabase.saveEntireDailyStockListPricetoDbComplete(YEAR_HISTORY_STRING, DAILY, trimblstocktoadd);
	                // Ok now load stocklist which contains latest changes
	            } else {
	                // If could not download data, use DB from database already.

	                logger.info("Could not download any data...Will use existing stocklist");
	            }
	            stockService.getLocalStocklist(stockList);
	*/
	         }
	/*
	            //        status = saveLastDayStockListYahooPricetoDb(stockList);
	        // Double check if Daily History is complete for all stocks except for last day.
	        // Last day is done in saveLastDayStockListPricetoDB
	        // Could have been interrupted on the initial run since it takes 4 hours to complete.
	        logger.debug("Updating current database with missing daily prices");            
	        status = priceHistoryService.UpdateMissingDailyHistoryToDb(DAILY,stockList);   

	        // Update only missing daily data since last program run.
//	        logger.debug("Updating current database with last day daily prices");                    
	        status=priceHistoryService.saveLastDayStockListPricetoDb(stockList);

	        // Check for old symbols. If so, tag them as obsolete in local DB.
	        priceHistoryService.CheckObsoleteSymbol(stockList);
	        stockService.getLocalStocklist(stockList);
	        
	        // Check for stock splits here and redownload symbol history if so.
	        // a refaire xxx status=databaseService.CheckForStockSplit(stockList);
	  */
	        // Done, close stockDB and Exit program.
	        // status = SqlDatabase.closeStockDb();
	        String endTimeStamp = null;
	        endTimeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(Calendar.getInstance().getTime());
	        
	        logger.info("Work complete. Begin {}. End {}",beginTimeStamp,endTimeStamp);
	        exit(0);
	    }   
//    }

}
