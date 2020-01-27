package io.unbxd.crudstash.dao;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.inject.Inject;
import io.unbxd.crudstash.clients.Client;

public class CrudStashDaoImpl implements CrudStashDao {

    private Client client;
    private JsonParser parser;

    @Inject
    public CrudStashDaoImpl(Client client) {
        this.client = client;
        parser = new JsonParser();
    }

    @Override
    public String getData(String id) {
        String data = "Unable to fetch for id: " + id;
        if(client != null) {
            data = client.fetch(id);
        }
        return data;
    }

    @Override
    public void addData(String data) {
        JsonObject jsonObject = validateJson(data);
        if(!validateData(jsonObject)) {
            throw new RuntimeException("Input data does not match schema.");
        }

        client.add(jsonObject.get(EMP_ID_FIELD).getAsString(), data);
    }

    private JsonObject validateJson(String data) {
        JsonElement parsedData = parser.parse(data);
        JsonObject jsonObject = parsedData.getAsJsonObject();

        return jsonObject;
    }

    private boolean validateData(JsonObject jsonObject)  {
        boolean hasName = jsonObject.has(NAME_FIELD);
        boolean hasAddress = jsonObject.has(ADDRESS_FIELD);
        boolean hasEmployeeId = jsonObject.has(EMP_ID_FIELD);
        boolean hasDesignation = jsonObject.has(DESIGNATION_FIELD);
        return hasName && hasAddress && hasEmployeeId && hasDesignation;
    }
}
