package com.evdosoft.stocktechsys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.evdosoft.stocktechsys.models.Symbol;

public class SymbolRowMapper implements RowMapper<Symbol> {

    @Override
    public Symbol mapRow(ResultSet rs, int rowNum) throws SQLException {
	Symbol symbol = new Symbol();
	
	symbol.setDate(rs.getDate("DATE"));
	symbol.setIexId(rs.getString("IEXID"));
	symbol.setIsEnabled(rs.getBoolean("ISENABLED"));
	symbol.setName(rs.getString("NAME"));
	symbol.setSymbol(rs.getString("SYMBOL"));
	symbol.setType(rs.getString("TYPE"));
	
	return symbol;
    }

}
