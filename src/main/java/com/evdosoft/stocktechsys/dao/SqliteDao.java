/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evdosoft.stocktechsys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author atlantis
 */
public interface SqliteDao {
    
    public Connection openSqlDatabase ();
    public boolean closeSqlDatabase (Connection c);
    public Boolean execStatement(String strsql) throws SQLException;
    public PreparedStatement execPrepStatementRead(String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException;
    // public PreparedStatement execPrepStatementWrite(String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException;
    public boolean createCompanyTables () throws SQLException;
    public boolean createSymbolTable () throws SQLException;
    public boolean createSymbolTemporaryTable () throws SQLException;
    public boolean createQuoteTable () throws SQLException; 
    public boolean createChartTable () throws SQLException;
    public boolean deleteDuplicateFromStockListTrimDb () throws SQLException;
}
