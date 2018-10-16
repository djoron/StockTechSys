/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.util.List;

import com.evdosoft.stocktechsys.models.Company;


/**
 *
 * @author dominicj
 */
public interface CompanyDao {

    public boolean saveCompanyListToDb(List<Company> companyListSql);
    public List<Company> loadCompanyListFromDb ();
    public boolean updateCompanyList(List<Company> companyList) throws Exception;
    
}
