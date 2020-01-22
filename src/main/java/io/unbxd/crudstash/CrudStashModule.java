package io.unbxd.crudstash;

import com.google.inject.AbstractModule;
import io.unbxd.crudstash.controllers.ControllerModule;
import io.unbxd.crudstash.dao.CrudStashDao;
import io.unbxd.crudstash.dao.CrudStashDaoImpl;

public class CrudStashModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ControllerModule());
        bind(CrudStashDao.class).to(CrudStashDaoImpl.class).asEagerSingleton();
    }
}
