package com.evdosoft.stocktechsys.dao.async;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.evdosoft.stocktechsys.Parameters;
import com.evdosoft.stocktechsys.models.Chart;
import com.evdosoft.stocktechsys.models.Company;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

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
        int maxtoDownload = parameters.getGetMaxChartListToDownload();
        
	LocalTime t1 = LocalTime.now();
    	WebClient client = WebClient.create(vertx);
    	List<Company> companies = new ArrayList<>();
    	Future<List<Company>> future = Future.future();
    	
    	logger.info("-------------- http GET company list ASYNC - Please be patient --------------");
    	
        client
        .getAbs(urlstr)   // Path to Symbol list 
        .send(ar -> {
          if (ar.succeeded()) {            
            HttpResponse<Buffer> response = ar.result();

            // Decode the body as a json object
            JsonArray body = response.bodyAsJsonArray();  // Contains complete list          
            logger.info("Downloading Company, got HTTP response with status " + response.statusCode() + " with body of size " + body.size());
            
            WebClient client2 = WebClient.create(vertx);   
            
            int totalElements = body.size();
            // Debug mode, can reduce size of what's being downloaded
            
            if ( (totalElements > maxtoDownload) && (maxtoDownload > 0) ) {
        	totalElements = maxtoDownload - 1;
        	logger.info("Reducing download from {} to {} elements",body.size(),maxtoDownload);
            }
           
          
            final int lastIndex = totalElements-1;
            
            for(int i=0; i<body.size() ; i++ ) {
        	final int index = i;
            	JsonObject json = body.getJsonObject(i);
            	if(json.containsKey("symbol")) {
            		String symbol = json.getString("symbol");            		
            		String companyUrl = parameters.getIexPrefix() + "stock/" + symbol + "/company";
            		
            		// Vertx async http request
            		client2
            		.getAbs(companyUrl) // Get company info here.
            		.send(aar -> {
            			if(aar.succeeded()) { 
            			    	HttpResponse<Buffer> response2 = aar.result();
            			    	try {
                				JsonObject jsonCompany = response2.bodyAsJsonObject();
                				if(jsonCompany != null) {
                					Company company = readCompany(jsonCompany);   
                					if (company.getCompanyName() != null) {
                					    companies.add(company);
                					}
                				}
                				if (index % 500 == 1) { // Logger to see where we are only
                				    logger.info("Downloading company for symbol "+symbol+", got HTTP response with status " + response2.statusCode() + " from i=" + index);
                				}
                				
            			    	} catch(DecodeException e) {
            			    	    logger.warn("Failed to decode symbol {}, element #= {}", symbol, index);
            			    	} finally {
                			    	if ((index == lastIndex) /* || (index > (parameters.getMaxStocktoProcess()-1))*/ ){
                			    	    LocalTime t2 = LocalTime.now();
                			    	    logger.info("Added " + companies.size() + " companies.");
                			    	    logger.info("=========> Time taken to run asynchronously : " + t1.until(t2, ChronoUnit.SECONDS) + " seconds.");
                			    	    future.complete(companies);
                			    	}
            			    	}
            			    	
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

    @Override
    public Future<Map<String, List<Chart>>> getDailyChartList(List<String> symbols, String period, int maxNumResults) {
	 
	 WebClient client2 = WebClient.create(vertx);
	 LocalTime t1 = LocalTime.now();
	 
	 // Init map and future
	 Map<String, List<Chart>> chartMap = new HashMap<>();
	 Future<Map<String, List<Chart>>> future = Future.future();
         int lastIndex = symbols.size()-1;// Math.min(maxNumResults-1, symbols.size()-1);
         
         for(int i=0; i<= lastIndex ; i++ ) {
     		final int index = i;
     		String symbol = symbols.get(index);           		    		
    		String chartUrl = parameters.getIexPrefix() + "stock/" + symbol + "/chart/" +period;
    		
    		// Vertx async http request
    		client2
    		.getAbs(chartUrl) // Get company info here.
    		.send(aar -> {
    			if(aar.succeeded()) { 
    			    	HttpResponse<Buffer> response = aar.result();
    			    	try {
    			    	    	JsonArray body = response.bodyAsJsonArray();  // Contains complete list          
    			    	        logger.info("Downloading chartlist for symbol "+symbol+", got HTTP response with status " + response.statusCode() + " from i=" + index);
    			    	        logger.info("{}",chartUrl);
    			    	        /*if (index % 500 == 1) { // Logger to see where we are only
				    		logger.info("Downloading chartlist for symbol "+symbol+", got HTTP response with status " + response.statusCode() + " from i=" + index);
        			    	}*/
    			    	        // logger.info("Got HTTP response with status " + response.statusCode() + " with body of size " + body.size());
        				if(body != null) {
        					List<Chart> charts = null;
						try {
						    charts = readCharts(body);
						    // xxx more checks here before put ?
						    chartMap.put(symbol, charts);
						} catch (Exception e) {
						    // TODO Auto-generated catch block
						    logger.warn("Failed to decode chartlist for symbol {}", symbol);
						    logger.warn("url: {}", chartUrl);
						}
        				} else {
        				    logger.warn("---------------> Empty body :(");
        				}
        				
        				
        				
    			    	} catch(DecodeException e) {
    			    	    logger.warn("Failed to decode chart for symbol {}, element #= {}", symbol, index);
    			    	} finally {
        			    	if ((index == lastIndex)){
        			    	    LocalTime t2 = LocalTime.now();
        			    	    logger.info("Added " + chartMap.size() + " charts.");
        			    	    logger.info("=========> Time taken to run asynchronously : " + t1.until(t2, ChronoUnit.SECONDS) + " seconds.");
        			    	    future.complete(chartMap);
        			    	}
    			    	}
    			    	
    			} else {
    			    logger.warn("Something went wrong url {}", chartUrl);
    			    logger.warn("Something went wrong with chart symbol {} - {} - Stack {}", symbol, aar.cause().getMessage());
    			    aar.cause().printStackTrace();
    			}
    		});            		
         }
          
         return future;
    }

    private List<Chart> readCharts(JsonArray body) throws ParseException {
	List<Chart> chartList = new ArrayList<>();
	if (body.size() == 0) {
	    logger.info("readCharts size = 0");
	}
	for(int j=0; j<body.size(); j++) {
	    JsonObject json = body.getJsonObject(j);
	    Chart chart = readChart(json);
	    chartList.add(chart);
	}
	return chartList;
    }

    
    @SuppressWarnings("deprecation")
    private Chart readChart(JsonObject json) throws ParseException {
	Chart chart = new Chart();

	chart.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(json.getString("date")));

	chart.setOpen	(BigDecimal.valueOf(json.getDouble("open")));
	chart.setHigh	(BigDecimal.valueOf(json.getDouble("high")));
	chart.setLow	(BigDecimal.valueOf(json.getDouble("low")));
	chart.setClose	(BigDecimal.valueOf(json.getDouble("close")));
	chart.setVolume	(Long.valueOf(json.getLong("volume")));
	chart.setUnadjustedVolume(Long.valueOf(json.getLong("unadjustedVolume")));
	chart.setChange	(BigDecimal.valueOf(json.getDouble("change")));
	chart.setChangePercent(BigDecimal.valueOf(json.getDouble("changePercent")));
	chart.setVwap	(BigDecimal.valueOf(json.getDouble("vwap")));
	chart.setLabel	(json.getString("label"));
	chart.setChangeOverTime(BigDecimal.valueOf(json.getDouble("changeOverTime")));
	    
	return chart;
    }   
    
    

}
