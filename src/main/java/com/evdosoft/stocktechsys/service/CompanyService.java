package com.evdosoft.stocktechsys.service;

import java.util.List;

import com.evdosoft.stocktechsys.models.Company;
import com.evdosoft.stocktechsys.models.Symbol;

public interface CompanyService {

    /**
     * Creates Company list. Calls internet DAOs to do so and saves it into
     * SQLDb for persistence.
     * 
     * @param val Type of List to be saved in DB. Permanent or temporary
     * @param symbolList List as downloaded on IEX. use Symbol List to build company List
     * @return True if download and save ok. False otherwise.
     * @throws Exception
     */
    boolean createCompanyList(List<Symbol> symbolList) throws Exception;

    /**
     * Update Company list. Calls internet DAOs to do so and saves it into
     * SQLDb for persistence.
     * @param symbolList Symbol list as downloaded from IEX
     * @return True if download and save ok. False otherwise.
     * @throws Exception
     */
    boolean updateCompanyList(List<Symbol> symbolList) throws Exception;

    /**
     * Read CompanyList from local DB. 
     * @return List of Companies.
     * @throws Exception
     */
    List<Company> getCompanyListFromDb() throws Exception;

}