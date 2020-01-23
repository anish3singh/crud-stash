package io.unbxd.crudstash.clients;

import static io.unbxd.crudstash.constants.Constants.DB_NAME;

public interface Client {

    String MONGO_CLIENT_NAME = "mongo";
    String REDIS_CLIENT_NAME = "redis";

    static Client getClient() {
        if(DB_NAME.compareToIgnoreCase(REDIS_CLIENT_NAME) == 0) {
            return RedisClient.getInstance();
        } else if(DB_NAME.compareToIgnoreCase(MONGO_CLIENT_NAME) == 0) {
            return null;
        } else {
            return null;
        }
    }

    String fetch(String id);

    void add(String id, String data);

}
