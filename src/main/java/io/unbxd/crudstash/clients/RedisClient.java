package io.unbxd.crudstash.clients;

import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static io.unbxd.crudstash.constants.Constants.REDIS_HOST;
import static io.unbxd.crudstash.constants.Constants.REDIS_PORT;

@Slf4j
public class RedisClient implements Client {

    private static final String HASH_NAME = "io.unbxd.data";
    private static StatefulRedisClusterConnection<String, String> connection;

    public RedisClient() {
        RedisURI redisURI = RedisURI.create(REDIS_HOST, REDIS_PORT);
        RedisClusterClient redisClusterClient = RedisClusterClient.create(redisURI);

        connection = redisClusterClient.connect();
    }

    @Override
    public void add(String id, String data) {
        Map<String, String> hash = new HashMap<>();
        connection.sync().hmset(HASH_NAME, hash);
    }

    @Override
    public String fetch(String id) {
        String data = connection.sync().hget(HASH_NAME, id);
        return data;
    }
}
