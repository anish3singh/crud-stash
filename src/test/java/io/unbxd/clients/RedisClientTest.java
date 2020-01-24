package io.unbxd.clients;

import io.unbxd.crudstash.clients.Client;
import io.unbxd.crudstash.clients.RedisClient;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class RedisClientTest {

    private Client redisClient;

    @Before
    public void setupClient() {
        redisClient = RedisClient.getInstance();
    }

    @Test
    public void testClient() {
        String data = "Sample Mock Data";
        String id = UUID.randomUUID().toString();

        redisClient.add(id, data);
        String fetchedData = redisClient.fetch(id);
        assertEquals(data, fetchedData);
    }
}
