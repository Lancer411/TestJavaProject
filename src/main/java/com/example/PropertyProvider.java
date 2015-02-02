package com.example;

/**
 * Created by SIvantsov on 30.01.2015.
 */
public class PropertyProvider{
    private static PropertyGetter propertyGetter;

    public PropertyProvider()
    {
        if(propertyGetter == null) {
            if(System.getenv("TOMCAT_USER").equalsIgnoreCase("tomcat"))
                propertyGetter = new JelasticGetter();
            else
                propertyGetter = new CloudFoundryGetter();
        }
    }

    public static synchronized PropertyGetter getInstance()
    {
        if(propertyGetter == null) {
            new PropertyProvider();
        }
        return propertyGetter;
    }

    public static synchronized void setGetter(PropertyGetter getter)
    {
        propertyGetter = getter;
    }

}
