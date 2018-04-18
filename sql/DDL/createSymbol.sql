CREATE TABLE SYMBOL   (symbolID INTEGER PRIMARY KEY,   
			  SYMBOL             VARCHAR(10) NOT NULL,
			  NAME               VARCHAR(255) NOT NULL,    
              DATE               DATE, 
			  ISENABLED          BIT,    
              TYPE               VARCHAR(4),    
              IEXID              INTEGER, 
			  UNIQUE KEY (SYMBOL) );