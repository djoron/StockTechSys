package com.evdosoft.stocktechsys.service.async;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evdosoft.stocktechsys.dao.CompanyDao;
import com.evdosoft.stocktechsys.dao.async.IexDaoAsync;
import com.evdosoft.stocktechsys.models.Company;

import io.vertx.core.Future;
import io.vertx.core.Vertx;

@Service
public class CompanyServiceAsyncImpl implements CompanyServiceAsync {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceAsyncImpl.class);
	    
    @Autowired
    private IexDaoAsync iexDaoAsync;
    
    @Autowired
    private Vertx vertx;
    
    @Autowired
    private CompanyDao companyDao;
    
    @Override
    public void fetchAndSaveCompanyList() {
	Future<Void> defaultFuture = Future.future(); 
	logger.info("Fetch companies asynchronously...");
	Future<List<Company>> future = iexDaoAsync.getCompanyList();
	future.compose(companyList -> {
	    logger.info("Save companies synchronously...");
	    saveCompanyList(companyList);
	}, defaultFuture);

    }

    private void saveCompanyList(List<Company> companyList) {
	vertx.executeBlocking(future -> {
	    try {
		companyDao.saveCompanyList(companyList);
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    future.complete();
	  }, res -> {
	    System.out.println("Company list saved synchronously.");
	  });
    }

}
