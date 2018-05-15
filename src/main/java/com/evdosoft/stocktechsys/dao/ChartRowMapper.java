package com.evdosoft.stocktechsys.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.evdosoft.stocktechsys.models.Chart;

public class ChartRowMapper implements RowMapper<Chart> {

    @Override
    public Chart mapRow(ResultSet rs, int row) throws SQLException {
	Chart chart = new Chart();
	
	chart.setChange(rs.getBigDecimal("CHANGE"));
	chart.setChangeOverTime(rs.getBigDecimal("CHANGEOVERTIME"));
	chart.setChangePercent(rs.getBigDecimal("CHANGEPERCENT"));
	chart.setClose(rs.getBigDecimal("CLOSE"));
	Date date = rs.getDate("DATE");
	chart.setDate(date.toLocalDate());
	chart.setHigh(rs.getBigDecimal("HIGH"));
	chart.setLabel(rs.getString("LABEL"));
	chart.setLow(rs.getBigDecimal("LOW"));
	chart.setOpen(rs.getBigDecimal("OPEN"));
	chart.setUnadjustedVolume(rs.getLong("UNADJUSTEDVOLUME"));
	chart.setVolume(rs.getLong("VOLUME"));
	chart.setVwap(rs.getBigDecimal("VWAP"));
	
	return chart;
    }

}
