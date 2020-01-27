package io.unbxd.crudstash;

import com.google.inject.AbstractModule;
import io.unbxd.crudstash.clients.Client;
import io.unbxd.crudstash.clients.ClientProvider;
import io.unbxd.crudstash.controllers.ControllerModule;
import io.unbxd.crudstash.dao.CrudStashDao;
import io.unbxd.crudstash.dao.CrudStashDaoImpl;

public class CrudStashModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ControllerModule());
        bind(Client.class).toProvider(ClientProvider.class).asEagerSingleton();
        bind(CrudStashDao.class).to(CrudStashDaoImpl.class).asEagerSingleton();
    }
}
