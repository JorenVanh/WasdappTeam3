/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.realdolmen.wasdapp.repositories;

import com.realdolmen.wasdapp.exceptions.NoQueryPossibleException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author demun
 */
public abstract class AbstractRepository<C,T> {
    public static final String LOGIN = "root";
    public static final String PASSWORD = "root";
    public static String DRIVER = "com.mysql.jdbc.Driver";
    public static String URL = "jdbc:mysql://localhost:3306/wasdapp?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String tableName;
    private String idName;
    private String url;
    
     public AbstractRepository(String tableName, String idName) {
        this.tableName = tableName;
        this.idName = idName;
        this.url = URL;
    }
     
      protected AbstractRepository(String tableName, String idName, String url) {
        this.idName = idName;
        this.tableName = tableName;
        this.url = url;
    }
      public Connection createConnection() throws SQLException {
          System.out.println("Setting up connection...");
        return DriverManager.getConnection(url, LOGIN, PASSWORD);
    }
      
    public List<C> findAll() throws NoQueryPossibleException {
        List<C> listToFill = null;
        try (Connection connection = createConnection()) {
            System.out.println("connection succes");
            PreparedStatement pstatement = connection.prepareStatement("SELECT * FROM " + tableName);
            ResultSet resultSet = pstatement.executeQuery();
            listToFill = new ArrayList<>();
            while (resultSet.next()) {
                listToFill.add(createObject(resultSet));
            }
        } catch (Exception e) {
            throw new NoQueryPossibleException("Find all " + tableName + " can not be excecuted");
        }
        return listToFill;
    }
     public abstract C createObject(ResultSet resultSet);
     
     public abstract String getColumnString();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
