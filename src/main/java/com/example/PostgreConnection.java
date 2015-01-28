package com.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SIvantsov on 28.01.2015.
 */
public class PostgreConnection {
    private Connection connection;

    private String hostName;
    private String port;
    private String dbName;
    private String username;
    private String password;

    public PostgreConnection() throws ClassNotFoundException, SQLException {
    }

    public static PostgreConnection builder() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return new PostgreConnection();
    }

    public PostgreConnection setHost(String hostName)
    {
        this.hostName = hostName;
        return this;
    }

    public PostgreConnection setPort(String port)
    {
        this.port = port;
        return this;
    }

    public PostgreConnection setDBName(String dbName)
    {
        this.dbName = dbName;
        return this;
    }

    public PostgreConnection setUsername(String username)
    {
        this.username = username;
        return this;
    }

    public PostgreConnection setPassword(String password)
    {
        this.password = password;
        return this;
    }

    public PostgreConnection connect() throws SQLException {
        String connectionUrl = String.format("jdbc:postgresql://%s:%s/%s", hostName, port, dbName);
        connection = DriverManager.getConnection(connectionUrl,username, password);
        return this;
    }



    public String testConnection() throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        int    majorVersion   = databaseMetaData.getDatabaseMajorVersion();
        int    minorVersion   = databaseMetaData.getDatabaseMinorVersion();

        String productName    = databaseMetaData.getDatabaseProductName();
        String productVersion = databaseMetaData.getDatabaseProductVersion();

        String result =
                "Major Version   = " + Integer.toString(majorVersion) + "\n" +
                "Minor Version   = " + Integer.toString(minorVersion) + "\n" +
                "Product Name    = " + productName + "\n" +
                "Product Version = " + productVersion;

        return result;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
