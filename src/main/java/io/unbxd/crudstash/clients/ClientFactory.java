package io.unbxd.crudstash.clients;

import static io.unbxd.crudstash.constants.Constants.DB_NAME;

public class ClientFactory {

    private static Client client;
    private static final String MONGO_CLIENT_NAME = "mongo";
    private static final String REDIS_CLIENT_NAME = "redis";

    public ClientFactory() { initializeClient(); }

    public static Client getInstance() { return client; }

    private void initializeClient() {
        if(DB_NAME.compareToIgnoreCase(REDIS_CLIENT_NAME) == 0) {
            client = new RedisClient();
        } else if(DB_NAME.compareToIgnoreCase(MONGO_CLIENT_NAME) == 0) {
            client = new MongoClient();
        } else {
            client = null;
        }
    }
}
