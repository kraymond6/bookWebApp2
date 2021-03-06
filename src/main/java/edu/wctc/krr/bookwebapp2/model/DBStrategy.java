/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.krr.bookwebapp2.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author Kallie
 */
public interface DBStrategy {

    void closeConnection() throws SQLException;

    void deleteSingleRecordPS(String tableName, String fieldName, Object pkValue) throws Exception;

    List<Map<String, Object>> findAllRecords(String tableName) throws Exception;
    
    int insertRecord(String tableName, List colDescriptors, List colValues) throws Exception;

    void openConnection(DataSource ds) throws Exception;

    int updateRecord(String tableName, List colDesc, List colValues, String whereField, Object whereValue) throws Exception;
    
    Map<String, Object> findById(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws SQLException;
}
