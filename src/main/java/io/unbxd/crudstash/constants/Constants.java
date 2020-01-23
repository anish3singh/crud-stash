package io.unbxd.crudstash.constants;

import io.unbxd.crudstash.util.PropertiesLoader;

import java.util.Properties;

import static java.lang.Integer.parseInt;

public interface Constants {

    String PROPERTIES_CONFIG = "crudstash.properties";
    Properties applicationProperties = new PropertiesLoader(PROPERTIES_CONFIG).getProperties();

    int PORT = 8080;
    String DB_NAME = applicationProperties.getProperty("db.name");
    String REDIS_HOST = applicationProperties.getProperty("redis.host");
    String MONGO_DB_HOST = applicationProperties.getProperty("mongo.db.host");
    int REDIS_PORT = parseInt(applicationProperties.getProperty("redis.port"));
}
