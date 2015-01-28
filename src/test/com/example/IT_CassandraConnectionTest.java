package com.example;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IT_CassandraConnectionTest {

    @Test
    public void testTestConnect() throws Exception {
        assertNotNull(CassandraConnection.testConnect(
                System.getProperty("cas_ip"),
                Integer.parseInt(System.getProperty("cas_port"))));
    }
}