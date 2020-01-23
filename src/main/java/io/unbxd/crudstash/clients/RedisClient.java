package io.unbxd.crudstash.clients;

import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static io.unbxd.crudstash.constants.Constants.REDIS_HOST;
import static io.unbxd.crudstash.constants.Constants.REDIS_PORT;

@Slf4j
public class RedisClient implements Client {

    private static Client redisClient;
    private static final String HASH_NAME = "io.unbxd.data";
    private static StatefulRedisConnection<String, String> connection;

    private RedisClient() {
        RedisURI redisURI = RedisURI.create(REDIS_HOST, REDIS_PORT);
        io.lettuce.core.RedisClient lettuceClient = io.lettuce.core.RedisClient.create(redisURI);
        try {
            connection = lettuceClient.connect();
        } catch (Exception e) {
            log.error("Error while initializing redis-client: ", e);
        }
    }

    public static Client getInstance() {
        if(redisClient == null) {
            redisClient = new RedisClient();
        }
        return redisClient;
    }

    @Override
    public void add(String id, String data) {
        Map<String, String> hash = new HashMap<>();
        hash.put(id, data);

        connection.sync().hmset(HASH_NAME, hash);
    }

    @Override
    public String fetch(String id) {
        String data = connection.sync().hget(HASH_NAME, id);
        return data;
    }
}
