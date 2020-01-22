package io.unbxd.crudstash.dao;

import java.util.UUID;

public interface CrudStashDao {

    String getData(String id);

    void addData(String data);
}
