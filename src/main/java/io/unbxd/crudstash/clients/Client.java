package io.unbxd.crudstash.clients;

public interface Client {

    public String fetch(String id);

    public void add(String id, String data);

}
