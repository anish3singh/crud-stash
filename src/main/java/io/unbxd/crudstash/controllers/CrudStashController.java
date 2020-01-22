package io.unbxd.crudstash.controllers;

import com.google.inject.Inject;
import io.unbxd.crudstash.dao.CrudStashDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import ro.pippo.controller.Controller;
import ro.pippo.controller.GET;
import ro.pippo.controller.POST;
import ro.pippo.core.ParameterValue;
import ro.pippo.core.Response;

import static io.unbxd.crudstash.constants.Constants.DB_NAME;

@Slf4j
public class CrudStashController extends Controller {

    private CrudStashDao dao;

    @Inject
    public CrudStashController(CrudStashDao dao) {
        this.dao = dao;
    }

    @GET("/crud-stash/getMe")
    public void getMe() {
        Response response = getResponse();

        response.status(200);
        response.send("Hi ! This is Crud-Stash.");
    }

    @POST("/crud-stash/post-data")
    public void dataPost() {
        String data = getRequest().getBody();
        dao.addData(data);

        Response response = getResponse();

        response.status(200);
        response.send("Successfully inserted data into " + DB_NAME);
    }

    @GET("/crud-stash/get-data")
    public void getPost() {
        ParameterValue queryParam = getRequest().getQueryParameter("id");
        String[] idQueryParam = queryParam.getValues();

        String responseData = "";
        if(ArrayUtils.isNotEmpty(idQueryParam)) {
            responseData = idQueryParam[0];
        }

        Response response = getResponse();

        response.status(200);
        response.send(responseData);
    }
}