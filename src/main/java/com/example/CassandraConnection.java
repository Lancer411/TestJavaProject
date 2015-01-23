package com.example;

/**
 * Created by SIvantsov on 22.01.2015.
 */

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

public class CassandraConnection {

    public static String testConnect(String address) {
        String answer = "";

        //ResultSet results;
        //Row rows;

        // Connect to the cluster and keyspace "demo"
        String keyspace = "system";

        Cluster cluster = Cluster.builder()
                .addContactPoints(address)
                .build();

        Session session = cluster.connect(keyspace);
        Metadata metadata = cluster.getMetadata();
        answer += "Connected to cluster: metadata.getClusterName()\n";
        for (Host host : metadata.getAllHosts()) {
            answer += "Datacenter: host.getDatacenter(); Host: host.getAddress(); Rack: host.getRack()\n";
        }
        cluster.close();
        return answer;
    }


    public static String testConnect(String address, int port) {

        String answer = "";

        //ResultSet results;
        //Row rows;

        // Connect to the cluster and keyspace "demo"
        String keyspace = "system";

        Cluster cluster = Cluster.builder()
                .addContactPoints(address)
                .withPort(port)
                .build();

        Session session = cluster.connect(keyspace);
        Metadata metadata = cluster.getMetadata();
        answer += "Connected to cluster: " + metadata.getClusterName()  + "\n";
        for (Host host : metadata.getAllHosts()) {
            answer += "Datacenter: " + host.getDatacenter() + "  Host: " + host.getAddress() + " Rack:" + host.getRack() + "\n";
        }
        cluster.close();
        return answer;
            /*
        // Insert one record into the users table
        PreparedStatement statement = session.prepare(

                "INSERT INTO users" + "(lastname, age, city, email, firstname)"
                        + "VALUES (?,?,?,?,?);");

        BoundStatement boundStatement = new BoundStatement(statement);

        session.execute(boundStatement.bind("Jones", 35, "Austin",
                "bob@example.com", "Bob"));

        // Use select to get the user we just entered
        Statement select = QueryBuilder.select().all().from("demo", "users")
                .where(eq("lastname", "Jones"));
        results = session.execute(select);
        for (Row row : results) {
            System.out.format("%s %d \n", row.getString("firstname"),
                    row.getInt("age"));
        }

        // Update the same user with a new age
        Statement update = QueryBuilder.update("demo", "users")
                .with(QueryBuilder.set("age", 36))
                .where((QueryBuilder.eq("lastname", "Jones")));
        session.execute(update);

        // Select and show the change
        select = QueryBuilder.select().all().from("demo", "users")
                .where(eq("lastname", "Jones"));
        results = session.execute(select);
        for (Row row : results) {
            System.out.format("%s %d \n", row.getString("firstname"),
                    row.getInt("age"));
        }

        // Delete the user from the users table
        Statement delete = QueryBuilder.delete().from("users")
                .where(QueryBuilder.eq("lastname", "Jones"));
        results = session.execute(delete);


        // Show that the user is gone
        select = QueryBuilder.select().all().from("demo", "users");
        results = session.execute(select);
        for (Row row : results) {
            System.out.format("%s %d %s %s %s\n", row.getString("lastname"),
                    row.getInt("age"), row.getString("city"),
                    row.getString("email"), row.getString("firstname"));
        }

        // Clean up the connection by closing it*/
    }


}