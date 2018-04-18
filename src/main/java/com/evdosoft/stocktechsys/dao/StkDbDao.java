/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.sql.SQLException;

/**
 *
 * @author atlantis
 */
public interface StkDbDao {
    public Boolean execStatement(String strsql) throws SQLException;
    public boolean createCompanyTables () throws SQLException;
    public boolean createSymbolTable () throws SQLException;
    public boolean createQuoteTable () throws SQLException; 
    public boolean createChartTable () throws SQLException;
    public boolean deleteDuplicateFromStockListTrimDb () throws SQLException;
}
