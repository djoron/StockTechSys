/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.evdosoft.stocktechsys.models.Company;

/**
 *
 * @author dominicj
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

    private Logger logger = LoggerFactory.getLogger(CompanyDaoImpl.class);

    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    /**
     * Save Company Downloaded from internet into DB.
     * 
     * @param companyList
     * @param companyListSql
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveCompanyList(List<Company> companyList) throws Exception {

	String sql = "SET FOREIGN_KEY_CHECKS=0;";
	jdbcTemplate.execute(sql);


	if (companyList.size() > 0) {
	    // Just insert, not replace here.
	    sql = ("INSERT INTO COMPANY (SYMBOL, COMPANYNAME, "
		    + "EXCHANGE, INDUSTRY, WEBSITE, DESCRIPTION, CEO, ISSUETYPE, "
		    + "SECTOR) VALUES (?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY "
		    + "UPDATE COMPANYNAME = VALUES(COMPANYNAME),"
		    + "EXCHANGE = VALUES(EXCHANGE), INDUSTRY = VALUES(INDUSTRY),"
		    + "WEBSITE = VALUES(WEBSITE),"
		    + "DESCRIPTION = VALUES(DESCRIPTION), CEO = VALUES(CEO),"
		    + "ISSUETYPE = VALUES(ISSUETYPE), SECTOR = VALUES(SECTOR);");

	    jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
	    
        	@Override
	        public void setValues(PreparedStatement ps, int i) throws SQLException {
        	    Company company = companyList.get(i);
        	    ps.setString(1, company.getSymbol());
        	    ps.setString(2, company.getCompanyName());
        	    ps.setString(3, company.getExchange());
        	    ps.setString(4, company.getIndustry());
        	    ps.setString(5, company.getWebsite());
        	    ps.setString(6, company.getDescription());
        	    ps.setString(7, company.getCeo());
        	    ps.setString(8, company.getIssueType());
        	    ps.setString(9, company.getSector());
        
        	}
	    
	    
	        @Override
	        public int getBatchSize() {
	    		return companyList.size();
	        }
	    });            
	        sql = "SET FOREIGN_KEY_CHECKS=1;";
		jdbcTemplate.execute(sql);

	    logger.info("saveCompanyList: CompanylList saved in SqlDB...done");

       } else {
        logger.error("saveCompanyList: CompanyList save FAILED in SqlDB");
        return false;
    }
    return true; 
    
    }

    /**
     * LoadCompanyListFromDb Load Company list from SQL DB and save in CompanyList
     * list.
     * 
     * @version 1.0
     * @author : dominicj
     * @param CompanyList
     * @return true if successful.
     * @throws java.sql.SQLException
     */
    public List<Company> loadCompanyListFromDb() {

	logger.info("loadCompanyListFromDb - Loading list from DB ... Standby");

        String query = "SELECT * FROM COMPANY";
        List<Company> companyList = jdbcTemplate.query(query, new CompanyRowMapper());

	return companyList;
    }

    /**
     * Update Company Downloaded from internet into DB.
     * 
     * @param companyList
     * @param companyListSql
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateCompanyList(List<Company> companyList) throws Exception {

	/*
	 * https://stackoverflow.com/questions/38226340/find-the-difference-between-two-
	 * collections-in-java-8
	 * 
	 * List<Book> books1 = ...; List<Book> books2 = ...; Set<Integer> ids =
	 * books2.stream() .map(Book::getId) .collect(Collectors.toSet()); List<Book>
	 * parentBooks = books1.stream() .filter(book -> !ids.contains(book.getId()))
	 * .collect(Collectors.toList());
	 * 
	 */

	if (companyList.size() > 0) {
	    String SQL = "INSERT OR REPLACE INTO COMPANY (SYMBOL, COMPANYNAME, "
		    + "EXCHANGE, INDUSTRY, WEBSITE, DESCRIPTION, CEO, ISSUETYPE, "
		    + "SECTOR) VALUES (?,?,?,?,?,?,?,?,?);";
//	    for (Company s : companyList) {
//		prepStmt.setString(1, s.getSymbol());
//		prepStmt.setString(2, s.getCompanyName());
//		prepStmt.setString(3, s.getExchange());
//		prepStmt.setString(4, s.getIndustry());
//		prepStmt.setString(5, s.getWebsite());
//		prepStmt.setString(6, s.getDescription());
//		prepStmt.setString(7, s.getCeo());
//		prepStmt.setString(8, s.getIssueType());
//		prepStmt.setString(9, s.getSector());
//
//		prepStmt.addBatch();
//	    }

	   
	    logger.info("updateCompanyList: CompanyList saved in SqlDB...done");
	} else {
	    logger.error("updateCompanyList: CompanyList save FAILED in SqlDB");	  
	    return false;
	}	
	return true;
    }

}
