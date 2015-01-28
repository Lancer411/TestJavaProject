package com.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SIvantsov on 28.01.2015.
 */
public class PostgresConnection {
    private Connection connection;

    private String hostName;
    private String port;
    private String dbName;
    private String username;
    private String password;

    public PostgresConnection() throws ClassNotFoundException, SQLException {
    }

    public static PostgresConnection builder() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return new PostgresConnection();
    }

    public PostgresConnection setHost(String hostName) {
        this.hostName = hostName;
        return this;
    }

    public PostgresConnection setPort(String port) {
        this.port = port;
        return this;
    }

    public PostgresConnection setDBName(String dbName) {
        this.dbName = dbName;
        return this;
    }

    public PostgresConnection setUsername(String username) {
        this.username = username;
        return this;
    }

    public PostgresConnection setPassword(String password) {
        this.password = password;
        return this;
    }

    public PostgresConnection connect() throws SQLException {
        String connectionUrl = String.format("jdbc:postgresql://%s:%s/%s", hostName, port, dbName);
        connection = DriverManager.getConnection(connectionUrl, username, password);
        return this;
    }


    public String testConnection() throws SQLException {
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        int majorVersion = databaseMetaData.getDatabaseMajorVersion();
        int minorVersion = databaseMetaData.getDatabaseMinorVersion();

        String productName = databaseMetaData.getDatabaseProductName();
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
