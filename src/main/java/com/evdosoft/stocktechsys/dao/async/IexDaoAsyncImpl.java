package com.evdosoft.stocktechsys.dao.async;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.evdosoft.stocktechsys.Parameters;
import com.evdosoft.stocktechsys.models.Company;

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
    	
    	logger.info("-------------- http GET company list ASYNC --------------");
    	
        client
        .getAbs(urlstr)        
        .send(ar -> {
          if (ar.succeeded()) {            
            HttpResponse<Buffer> response = ar.result();

            // Decode the body as a json object
            JsonArray body = response.bodyAsJsonArray();            
            logger.info("Got HTTP response with status " + response.statusCode() + " with body of size " + body.size());
            
            WebClient client2 = WebClient.create(vertx);   
            int lastIndex = body.size() - 1;
            for(int i=0; i<body.size(); i++ ) {
//            int lastIndex = 499;
//            for(int i=0; i<500; i++ ) {
            	final int index = i;
            	JsonObject json = body.getJsonObject(i);
            	if(json.containsKey("symbol")) {
            		String symbol = json.getString("symbol");
            		String companyUrl = parameters.getIexPrefix() + "stock/" + symbol + "/company";
            		client2
            		.getAbs(companyUrl)
            		.as(BodyCodec.jsonObject())
            		.send(aar -> {
            			if(aar.succeeded()) { 
            				HttpResponse<JsonObject> jsonResponse = aar.result();
            				JsonObject jsonCompany = jsonResponse.body();
            				if(jsonCompany != null) {
            					Company company = readCompany(jsonCompany);            					
            					companies.add(company);
            				}
//            				logger.info("Got HTTP response with status " + jsonResponse.statusCode() + " from i=" + index);
            				if(index == lastIndex) {
            					LocalTime t2 = LocalTime.now();
            					logger.info("Added " + companies.size() + " companies.");
            					logger.info("=========> Time taken to run asynchronously : " + t1.until(t2, ChronoUnit.SECONDS) + " seconds.");
            					future.complete(companies);
            				}
            			} else {
            			    logger.info("Something went wrong url {}", companyUrl);
            			logger.info("Something went wrong url {}", jsonCompany);
            			    logger.info("Something went wrong symbol {} - {}", symbol, aar.cause().getMessage());
//            				aar.cause().printStackTrace();
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

}
