package com.evdosoft.stocktechsys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Parameters.class)
public class StocktechsysApplication implements CommandLineRunner {

    @Autowired
    private Bootstrapping bootstrapping;
    
    @Autowired
    private AsyncBootstrapping asyncBootstrapping;

    public static void main(String[] args) {
	SpringApplication.run(StocktechsysApplication.class, args);
    }

    // POINT DE DEPART DE L'APPLICATION
    @Override
	public void run(String... args) throws Exception {
	    
		bootstrapping.prepareAndFetchData();   
	
	//	asyncBootstrapping.fetchData();
	
	}
}
