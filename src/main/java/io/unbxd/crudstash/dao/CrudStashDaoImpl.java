package io.unbxd.crudstash.dao;

import io.unbxd.crudstash.clients.Client;

import java.util.UUID;

public class CrudStashDaoImpl implements CrudStashDao {

    @Override
    public String getData(String id) {
        String data = "Unable to fetch for id: " + id;
        Client client = Client.getClient();
        if(client != null) {
            data = client.fetch(id);
        }
        return data;
    }

    @Override
    public void addData(String data) {
        Client client = Client.getClient();
        client.add(UUID.randomUUID().toString(), data);
    }
}
