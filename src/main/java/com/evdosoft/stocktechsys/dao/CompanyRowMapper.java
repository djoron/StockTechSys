package com.evdosoft.stocktechsys.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.evdosoft.stocktechsys.models.Company;

public class CompanyRowMapper implements RowMapper<Company> {

    @Override
    public  mapRow(ResultSet rs, int rowNum) throws SQLException {
	Company Company = new Company();
	
	Company.setCompanyName(rs.getString("COMPANYNAME"));
	Company.setIexId(rs.getString("IEXID"));
	Company.setIsEnabled(rs.getString("ISENABLED"));
	Company.setName(rs.getString("NAME"));
	Company.setCompany(rs.getString("Company"));
	Company.setType(rs.getString("TYPE"));
	
	return Company;
    }

}
