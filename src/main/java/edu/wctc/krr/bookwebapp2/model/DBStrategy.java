/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.krr.bookwebapp2.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kallie
 */
public interface DBStrategy {

    void closeConnection() throws SQLException;

    int deleteSingleRecordPS(String tableName, String fieldName, Object pkValue) throws Exception;

    List<Map<String, Object>> findAllRecords(String tableName) throws Exception;

    int insertRecord(String tableName, List colDescriptors, List colValues) throws Exception;

    void openConnection(String driverClass, String url, String userName, String password) throws Exception;

    int updateRecord(String tableName, List colDesc, List colValues, String whereField, Object whereValue) throws Exception;
    
}
