package io.unbxd.clients;

import io.unbxd.crudstash.clients.Client;
import io.unbxd.crudstash.clients.MongoClient;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class MongoClientTest {

    private Client mongoClient;

    @Before
    public void setupClient() {
        mongoClient = MongoClient.getInstance();
    }

    @Test
    public void testClient() {
        String data = "Sample Mock Data";
        String id = UUID.randomUUID().toString();

        mongoClient.add(id, data);
        String fetchedData = mongoClient.fetch(id);
        assertEquals(data, fetchedData);
    }
}
