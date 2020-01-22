package io.unbxd.crudstash.dao;

import io.unbxd.crudstash.clients.Client;
import io.unbxd.crudstash.clients.ClientFactory;

import java.util.UUID;

public class CrudStashDaoImpl implements CrudStashDao {

    @Override
    public String getData(String id) {
        Client client = ClientFactory.getInstance();
        String data = client.fetch(id);

        return data;
    }

    @Override
    public void addData(String data) {
        Client client = ClientFactory.getInstance();
        client.add(UUID.randomUUID().toString(), data);
    }
}
