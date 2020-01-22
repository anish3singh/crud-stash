package io.unbxd.crudstash.controllers;

import lombok.extern.slf4j.Slf4j;
import ro.pippo.controller.Controller;
import ro.pippo.controller.GET;

@Slf4j
public class CrudStashController extends Controller {

    @GET("/crud-stash/getMe")
    public void getMe() {
        getResponse().status(200);
        getResponse().send("Hi !");
    }
}
