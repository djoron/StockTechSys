package com.evdosoft.stocktechsys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.evdosoft.stocktechsys.models.Company;

public class CompanyRowMapper implements RowMapper<Company> {

    @Override
    public  Company mapRow(ResultSet rs, int rowNum) throws SQLException {
	Company company = new Company();
	
	company.setId(rs.getLong("ID"));
	company.setCompanyName(rs.getString("COMPANYNAME"));
	company.setCeo(rs.getString("CEO"));
	company.setDescription(rs.getString("DESCRIPTION"));
	company.setExchange(rs.getString("EXCHANGE"));
	company.setIndustry(rs.getString("INDUSTRY"));
	company.setIssueType(rs.getString("ISSUETYPE"));
	company.setSector(rs.getString("SECTOR"));
	company.setSymbol(rs.getString("SYMBOL"));
	company.setWebsite(rs.getString("WEBSITE"));
	
	return company;
    }

}
