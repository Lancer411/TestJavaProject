package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @Path("/connect/{address}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("address") String address) {

        return CassandraConnection.testConnect(address);
        //return "Stub";
    }
    @Path("/connect/{address}/{port}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("address") String address,
                        @PathParam("port") String port) {
        boolean connectResult = CheckPort.scan(address, Integer.parseInt(port));
        if(connectResult)
               return CassandraConnection.testConnect(address, Integer.parseInt(port));
        else
                return  Boolean.toString(connectResult);
    }
    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello cloud world!";
    }


}