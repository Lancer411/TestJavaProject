package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class PostgreConnectionTest {

    @Test
    public void testTestConnection() throws Exception {
        PostgreConnection connection = PostgreConnection.builder()
                .setHost        (System.getProperty("pg_ip"))
                .setPort        (System.getProperty("pg_port"))
                .setDBName      (System.getProperty("pg_database"))
                .setUsername    (System.getProperty("pg_username"))
                .setPassword(System.getProperty("pg_password"))
                .connect();
        assertNotNull(connection.testConnection());
    }
}