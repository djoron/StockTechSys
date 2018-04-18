CREATE TABLE COMPANY   (companyid INTEGER PRIMARY KEY,
			  symbol             VARCHAR(10) NOT NULL, 
			  companyname        VARCHAR(255) NOT NULL, 
			  companyexchange    VARCHAR(80),  
			  industry           VARCHAR(255), 
			  website            VARCHAR(255),  
			  description        VARCHAR(500), 
			  ceo                VARCHAR(255),  
			  issuetype          VARCHAR(80), 
			  sector             VARCHAR(80),  
			
			  UNIQUE KEY (symbol, companyexchange ),
			  FOREIGN KEY(symbol) REFERENCES symbol(symbol));              