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
    @Path("/scanPorts")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getHosts() {
        return CheckPort.scanHosts();
    }

    @Path("/connect/{address}/{port}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("address") String address,
                        @PathParam("port") String port) {
        boolean connectResult = CheckPort.scan(address, Integer.parseInt(port));
        if (connectResult)
            return CassandraConnection.testConnect(address, Integer.parseInt(port));
        else
            return Boolean.toString(connectResult);
    }


    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello " + System.getProperty("env_name") + " world!";
    }

    @Path("/getEnvVar/{varName}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getVariable(@PathParam("varName") String variableName) {
        String variable = System.getProperty(variableName);
        if (variable == null) variable = "No such variable!";
        else variable = variableName + " = " + variable;
        return variable;
    }

    @Path("/getAllVars")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAllVariables() {
        Map<String, String> environment = System.getenv();
        String answer = "";
        for (Map.Entry<String, String> entry : environment.entrySet()) {
            answer += entry.getKey() + " = " + entry.getValue() + "\n";
        }
        if (answer.isEmpty()) answer = "No environment variables!";
        return answer;
    }

    @Path("/postgres")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkPostgreSQL() throws SQLException, ClassNotFoundException {
        PostgresConnection connection = PostgresConnection.builder()
                .setHost("192.168.2.49")
                .setPort("5432")
                .setDBName("postgres")
                .setUsername("webadmin")
                .setPassword("Npvx3MERzx")
                .connect();

        return connection.testConnection();
    }

    @Path("/postgresByConfig")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkPostgreSQLConnfectioByConf() throws SQLException, ClassNotFoundException {
        PostgresConnection connection = PostgresConnection.builder()
                .setHost(System.getProperty("pg_ip"))
                .setPort(System.getProperty("pg_port"))
                .setDBName(System.getProperty("pg_database"))
                .setUsername(System.getProperty("pg_username"))
                .setPassword(System.getProperty("pg_password"))
                .connect();

        return connection.testConnection();
    }

    @Path("/cassandraByConfig")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkCassandraConnectionByConf() {
        return CassandraConnection.testConnect(
                System.getProperty("cas_ip"),
                Integer.parseInt(System.getProperty("cas_port")));
    }

    @Path("/cassandraTest")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean testCassandra() {
        String result;
        try {
            result = CassandraConnection.testConnect(
                    System.getProperty("cas_ip"),
                    Integer.parseInt(System.getProperty("cas_port")));

        } catch (Exception e) {
            return false;
        }
        return !result.isEmpty();
    }

    @Path("/postgresTest")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean testPostgre() {
        PostgresConnection connection = null;
        try {
            connection = PostgresConnection.builder()
                    .setHost(System.getProperty("pg_ip"))
                    .setPort(System.getProperty("pg_port"))
                    .setDBName(System.getProperty("pg_database"))
                    .setUsername(System.getProperty("pg_username"))
                    .setPassword(System.getProperty("pg_password"))
                    .connect();
            String result = connection.testConnection();
            if (result.isEmpty()) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}