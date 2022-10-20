package com.cisco.services;

import com.jayway.jsonpath.JsonPath;
import com.cisco.server.searchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Path("/")
public class Services {

    static Logger logger = LoggerFactory.getLogger(Services.class);
    int delay = 0;

    @Path("/version")
    @GET
    @Produces("application/json")
    public Response version() {
        return Response.ok().entity("Version 1.0.0").build();
    }

    @Path("/healthz")
    @GET
    @Produces("application/json")
    public Response healthz() {
        return Response.ok().entity("I'm alive").build();
    }

    @Path("/test")
    @GET
    @Produces("application/json")
    public Response test() {
        return Response.ok().entity("Version 1.0.0").build();
    }

    @Path("/search")
    @GET
    @Produces("application/json")
    public Response search(
        @DefaultValue("*") @QueryParam("category") String category
    ) throws IOException {
        

        
        try {
            Search currentSearch = new Search(category);
            // Load json file 
            String json = new String(Files.readAllBytes(Paths.get(searchService.getFilePath())));
            // Wait 5 seconds before returning the search to simulate big backend data
            delay = (int) ((Math.random() * (5000 - 1000)) + 1000);
            System.out.println("Adding " + delay + " seconds of delay");
            Thread.sleep(delay);

            System.out.println("category: " + category);

            if(category.equals("*") || category.equals("") ) {
                return Response.ok().entity( json ).build();
            } else {
                return Response.ok().entity( JsonPath.read(json, "$.*[?(@.category == '"+category+"')]")  ).build();
            }
                
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        

        
        
    }
}
