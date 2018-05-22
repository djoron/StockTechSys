drop table if exists company;
drop table if exists symbol;

	
	CREATE TABLE SYMBOL   (
		 SYMBOL             VARCHAR(10) NOT NULL PRIMARY KEY,   
		  NAME               VARCHAR(255) NOT NULL,   
		  DATE               DATE,      
		  ISENABLED          BIT,    
		  TYPE               VARCHAR(4),   
		  IEXID              INTEGER,   
		  UNIQUE KEY (SYMBOL) );
          
CREATE TABLE COMPANY   (ID INTEGER PRIMARY KEY,  
		  SYMBOL             VARCHAR(10) NOT NULL,   
		  COMPANYNAME        VARCHAR(255) NOT NULL,   
		  EXCHANGE    	      VARCHAR(80),    
		  INDUSTRY           VARCHAR(255),   
		  WEBSITE            VARCHAR(255),    
		  DESCRIPTION        VARCHAR(500),   
		  CEO                VARCHAR(255),    
		  ISSUETYPE          VARCHAR(80),   
		  SECTOR             VARCHAR(80),    		
		  UNIQUE KEY (SYMBOL, EXCHANGE),  
		  FOREIGN KEY(SYMBOL) REFERENCES SYMBOL(SYMBOL));              
		
