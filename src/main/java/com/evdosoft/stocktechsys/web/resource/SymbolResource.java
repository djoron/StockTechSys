package com.evdosoft.stocktechsys.web.resource;

import lombok.Data;

@Data
public class SymbolResource {

	private String symbol;
	private String exchange;
    private String name;
    private String date;
    private String type;
    private String iexId;
    private String region;
    private String currency;
    private Boolean isEnabled;

}
