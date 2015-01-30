package com.example;

/**
 * Created by SIvantsov on 30.01.2015.
 */
public class PropertyProvider{
    private static PropertyProvider instance;
    private static PropertyGetter propertyGetter;

    public static String getStaticProperty(String propertyName)
    {
        return getInstance().propertyGetter.getProperty(propertyName);
    }

    public PropertyProvider()
    {
        instance = this;
    }

    public static synchronized PropertyProvider getInstance()
    {
        if(instance == null) {
            new PropertyProvider();
            //change this to JelasticGetter to get another realisation
            if(System.getenv("TOMCAT_USER").equalsIgnoreCase("tomcat"))
                propertyGetter = new JelasticGetter();
            else
                propertyGetter = new CloudFoundryGetter();
        }
        return instance;
    }

    public static void setGetter(PropertyGetter getter) {
        getInstance().setPropertyGetter(getter);
    }
    private void setPropertyGetter(PropertyGetter getter)
    {
        this.propertyGetter = getter;
    }
}
