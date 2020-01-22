package io.unbxd.crudstash;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.extern.slf4j.Slf4j;
import ro.pippo.controller.Controller;
import ro.pippo.controller.ControllerApplication;
import ro.pippo.core.Pippo;

import java.util.Set;

import static io.unbxd.crudstash.constants.Constants.PORT;

@Slf4j
public class CrudStashApplication extends ControllerApplication {

    @Inject
    public CrudStashApplication(Set<Controller> controllers) {
        controllers.forEach(this::addControllers);
    }

    public static void main(String[] args) {
        try {
            Injector injector = Guice.createInjector(new CrudStashModule());
            CrudStashApplication application = injector.getInstance(CrudStashApplication.class);
            (new Pippo(application)).start(PORT);

        } catch (Exception e) {
            log.error("Error initializing Application: ", e);
            throw new RuntimeException(e);
        }
    }
}