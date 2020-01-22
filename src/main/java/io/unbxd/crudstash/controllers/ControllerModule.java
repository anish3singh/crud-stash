package io.unbxd.crudstash.controllers;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import ro.pippo.controller.Controller;

import static com.google.inject.multibindings.Multibinder.*;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Controller> multibinder = newSetBinder(binder(), Controller.class);
        multibinder.addBinding().to(CrudStashController.class).asEagerSingleton();
    }
}
