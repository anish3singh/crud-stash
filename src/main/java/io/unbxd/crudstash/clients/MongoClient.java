package io.unbxd.crudstash.clients;

import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.unbxd.crudstash.constants.Constants;
import org.bson.Document;

public class MongoClient implements Client {

    private static Client mongoClient;
    private static com.mongodb.MongoClient client;
    private static final String MONGO_DB_NAME = "unbxd_db";
    private static final String MONGO_COLLECTION_NAME = "crud-stash";

    private MongoClient() {
        client = new com.mongodb.MongoClient(new MongoClientURI(Constants.MONGO_DB_HOST));
    }

    public static Client getInstance() {
        if(mongoClient == null) {
            mongoClient = new MongoClient();
        }
        return mongoClient;
    }

    @Override
    public void add(String id, String data) {
        MongoDatabase database = client.getDatabase(MONGO_DB_NAME);
        MongoCollection<Document> collection = database.getCollection(MONGO_COLLECTION_NAME);

        Document document = new Document();
        document.put("data", data);
        document.put("_id", id);

        collection.insertOne(document);
    }

    @Override
    public String fetch(String id) {
        MongoDatabase database = client.getDatabase(MONGO_DB_NAME);
        MongoCollection<Document> collection = database.getCollection(MONGO_COLLECTION_NAME);

        Document document = new Document();
        document.put("_id", id);

        FindIterable<Document> documents = collection.find(document);
        Document valueDocument = documents.iterator().next();
        return valueDocument.getString("data");
    }
}
