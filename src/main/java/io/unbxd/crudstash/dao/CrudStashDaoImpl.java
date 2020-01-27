package io.unbxd.crudstash.dao;

import com.google.inject.Inject;
import io.unbxd.crudstash.clients.Client;

import java.util.UUID;

public class CrudStashDaoImpl implements CrudStashDao {

    private Client client;

    @Inject
    public CrudStashDaoImpl(Client client) {
        this.client = client;
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
        client.add(UUID.randomUUID().toString(), data);
    }
}
