package io.unbxd.crudstash.dao;

public interface CrudStashDao {

    String getData(String id);

    void addData(String data);
}
