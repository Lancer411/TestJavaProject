package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.Map;

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

    @Path("/getEnvVar/{varName}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getVariable(@PathParam("varName") String variableName) {
        String variable = System.getProperty(variableName);
        if(variable == null) variable = "No such variable!";
                else variable = variableName + " = " + variable;
        return variable;
    }
    @Path("/getAllVars")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllVariables() {
        Map<String, String> environment = System.getenv();
        String answer = "";
        for(Map.Entry<String, String> entry : environment.entrySet())
        {
            answer += entry.getKey() + " = " + entry.getValue() + "\n";
        }
        if(answer.isEmpty()) answer = "No environment variables!";
        return answer;
    }

    @Path("/postgre")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkPostgreSQL() throws SQLException, ClassNotFoundException {
        PostgreConnection connection = PostgreConnection.builder()
                .setHost        ("192.168.2.49")
                .setPort        ("5432")
                .setDBName      ("postgres")
                .setUsername    ("webadmin")
                .setPassword    ("Npvx3MERzx")
                .connect        ();

        return connection.testConnection();
    }
}