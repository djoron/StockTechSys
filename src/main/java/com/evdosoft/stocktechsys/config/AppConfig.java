package com.evdosoft.stocktechsys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.vertx.core.Vertx;

@Configuration
public class AppConfig {

    @Bean
    public Vertx vertx() {
	return Vertx.vertx();
    }
    
//    @Bean
//    public WebClient webClient() {
//	return WebClient.create(this.vertx());
//    }
}
