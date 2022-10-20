package com.cisco.configuration;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class CustomApplication extends ResourceConfig
{
    public CustomApplication()
    {
        System.out.println("#################################");
        System.out.println("##### SEARCH REST SERVICES ######");
        System.out.println("#################################");

        packages("com.cisco.configuration");
        register(JacksonFeature.class);
    }
}