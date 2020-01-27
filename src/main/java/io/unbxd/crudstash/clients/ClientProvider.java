package io.unbxd.crudstash.clients;

import com.google.inject.Provider;

import static io.unbxd.crudstash.clients.Client.MONGO_CLIENT_NAME;
import static io.unbxd.crudstash.clients.Client.REDIS_CLIENT_NAME;
import static io.unbxd.crudstash.constants.Constants.DB_NAME;

public class ClientProvider implements Provider<Client> {

    @Override
    public Client get() {
        if(DB_NAME.compareToIgnoreCase(REDIS_CLIENT_NAME) == 0) {
            return RedisClient.getInstance();
        } else if(DB_NAME.compareToIgnoreCase(MONGO_CLIENT_NAME) == 0) {
            return MongoClient.getInstance();
        } else {
            return null;
        }
    }
}
