package com.evdosoft.stocktechsys.dao.async;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.evdosoft.stocktechsys.Parameters;
import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.codec.BodyCodec;

/**
 * Use Vertx to get http results asynchronously
 * @author Eva
 *
 */
@Repository
public class IexDaoAsyncImpl implements IexDaoAsync {
    
    private static final Logger logger = LoggerFactory.getLogger(IexDaoAsyncImpl.class);
    
    @Autowired
    private Vertx vertx;
    
    @Autowired
    private Parameters parameters;      

    @Override
    public Future<List<Company>> getCompanyList() {
	String urlstr = parameters.getIexPrefix() + parameters.getIexPrefixSymbols();	
	LocalTime t1 = LocalTime.now();
    	WebClient client = WebClient.create(vertx);
    	List<Company> companies = new ArrayList<>();
    	Future<List<Company>> future = Future.future();
    	
    	logger.info("-------------- http GET Chart list ASYNC --------------");
    	
        client
        .getAbs(urlstr)        
        .send(ar -> {
          if (ar.succeeded()) {            
            HttpResponse<Buffer> response = ar.result();

            // Decode the body as a json object
            JsonArray body = response.bodyAsJsonArray();            
            logger.info("Got HTTP response with status " + response.statusCode() + " with body of size " + body.size());
            
            WebClient client2 = WebClient.create(vertx);   
            
            final int lastIndex;
            if (parameters.getMaxStocktoProcess() < (body.size()) ) { 
        	lastIndex = parameters.getMaxStocktoProcess()-1; // Parameters can override to download less data.
            } else {
        	lastIndex = body.size()-1;
            }
           
            for(int i=0; i<=lastIndex; i++ ) {
            	final int index = i;
            	JsonObject json = body.getJsonObject(i);
            	if(json.containsKey("symbol")) {
            		String symbol = json.getString("symbol");
            		String companyUrl = parameters.getIexPrefix() + "stock/" + symbol + "/company";
            		client2
            		.getAbs(companyUrl)
            		.as(BodyCodec.jsonObject())
            		.send(aar -> {
				if(index == lastIndex) { // Last index ? Exit after with future.complete
					LocalTime t2 = LocalTime.now();
					logger.info("Added " + companies.size() + " companies.");
					logger.info("=========> Time taken to run asynchronously : " + t1.until(t2, ChronoUnit.SECONDS) + " seconds.");
					future.complete(companies);
				}

            		        if(aar.succeeded()) { 
            				HttpResponse<JsonObject> jsonResponse = aar.result();
            				JsonObject jsonCompany = jsonResponse.body();
            				if(jsonCompany != null) {
            					Company company = readCompany(jsonCompany);   
            					if (company.getCompanyName() != null) {
            					    companies.add(company);
            					}
            				}
//            				logger.info("Got HTTP response with status " + jsonResponse.statusCode() + " from i=" + index);
            			} else {
            			    logger.warn("Something went wrong url {}", companyUrl);
            			    logger.warn("Something went wrong symbol {} - {} - Stack {}", symbol, aar.cause().getMessage());
            			    aar.cause().printStackTrace();
            			}
            		});            		
            	}            	
            }            
            
          } else {
              	  logger.info("Something went wrong " + ar.cause().getMessage());
//        	  ar.cause().printStackTrace();
//        	  future.fail(ar.cause().getMessage());
          }
        }); 
        
        return future;
    }

    /**
     * Download chart data or price history data from Iex from a given company
     * symbol
     * 
     * @param company
     * @param period
     * @return
     * @throws MalformedURLException
     */
    @Override
    public Future<List<Chart>> getDailyChartList() {

	// Will contain chart List from Internet.
	// https://api.iextrading.com/1.0/stock/aapl/chart/5y
	// "date":"2013-05-28",
	// "open":58.6678,
	// "high":58.8256,
	// "low":57.4877,
	// "close":57.5645,
	// "volume":96404189,
	// "unadjustedVolume":13772027,
	// "change":-0.48392,
	// "changePercent":-0.834,
	// "vwap":58.0274,
	// "label":"May 28, 13",
	// "changeOverTime":0}

	String urlstr = parameters.getIexPrefix() + "stock/" + symbol + "/chart/" + period;
	LocalTime t1 = LocalTime.now();
    	WebClient client = WebClient.create(vertx);
    	List<Chart> chartList = new ArrayList<>();
    	Future<List<Chart>> future = Future.future();
    	
    	logger.info("-------------- http GET Chart list ASYNC --------------");
    	
        client
        .getAbs(urlstr)        
        .send(ar -> {
          if (ar.succeeded()) {            



	List<Chart> chartList = null;

	ObjectMapper objectMapper = new ObjectMapper();
	try {
	    chartList = objectMapper.readValue(new URL(urlstr), new TypeReference<List<Chart>>() {
	    });
  	    int size = 0;
	    size = chartList.size();
	    // logger.info("getDailyChartList - Read {} dates",size);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return chartList;
    }

    private Company readCompany(JsonObject jsonCompany) {
	Company company = new Company();
	company.setSymbol(jsonCompany.getString("symbol"));
	company.setCompanyName(jsonCompany.getString("companyName"));
	company.setCeo(jsonCompany.getString("CEO"));
	company.setDescription(jsonCompany.getString("description"));
	company.setExchange(jsonCompany.getString("exchange"));
	company.setIndustry(jsonCompany.getString("industry"));
	company.setIssueType(jsonCompany.getString("issueType"));
	company.setSector(jsonCompany.getString("sector"));
	company.setWebsite(jsonCompany.getString("website"));		
	return company;
    }   

}
