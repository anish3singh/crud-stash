package io.unbxd.crudstash;

import com.google.inject.AbstractModule;
import io.unbxd.crudstash.controllers.ControllerModule;

public class CrudStashModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new ControllerModule());
    }
}
