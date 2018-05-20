package com.evdosoft.stocktechsys.web.resource;

import lombok.Data;

@Data
public class SymbolResource {

    private String symbol;
    private String name;
    private String date;
    private String isEnabled;
    private String type;
    private String iexId;
}
