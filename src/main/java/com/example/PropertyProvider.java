package com.example;

import java.util.logging.Logger;

/**
 * Created by SIvantsov on 30.01.2015.
 */
public class PropertyProvider {
    private static PropertyGetter propertyGetter;

    public PropertyProvider() {
        if (propertyGetter == null) {
            Logger logger = Logger.getLogger("PROPERTY GETTER LOGGER");

            if (System.getenv("TOMCAT_USER") != null) {
                propertyGetter = new JelasticGetter();
                logger.info("Jelastic getter created.");
            } else {
                propertyGetter = new CloudFoundryGetter();
                logger.info("CloudFoundry getter created.");
            }
        }
    }

    public static synchronized PropertyGetter getInstance() {
        if (propertyGetter == null) {
            new PropertyProvider();
        }
        return propertyGetter;
    }

    public static synchronized void setGetter(PropertyGetter getter) {
        propertyGetter = getter;
    }

}
