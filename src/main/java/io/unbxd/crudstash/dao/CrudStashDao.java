package io.unbxd.crudstash.dao;

public interface CrudStashDao {

    String NAME_FIELD = "name";
    String ADDRESS_FIELD = "address";
    String EMP_ID_FIELD = "employeeId";
    String DESIGNATION_FIELD = "designation";

    String getData(String id);

    void addData(String data);
}
