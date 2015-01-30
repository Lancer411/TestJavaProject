package com.example;

/**
 * Created by SIvantsov on 30.01.2015.
 */
public class JelasticGetter implements PropertyGetter {
    @Override
    public String getProperty(String propertyName) {
        return System.getProperty(propertyName);
    }
}
