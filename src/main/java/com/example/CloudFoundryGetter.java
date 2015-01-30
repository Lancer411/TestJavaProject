package com.example;

/**
 * Created by SIvantsov on 30.01.2015.
 */
public class CloudFoundryGetter implements PropertyGetter {
    @Override
    public String getProperty(String propertyName) {
        return System.getenv(propertyName);
    }
}
