package io.unbxd.crudstash.clients;

import static io.unbxd.crudstash.constants.Constants.DB_NAME;

public interface Client {

    String MONGO_CLIENT_NAME = "mongo";
    String REDIS_CLIENT_NAME = "redis";

    String fetch(String id);

    void add(String id, String data);

}
