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

    @GET("/crud-stash")
    public void getMe() {
        Response response = getResponse();

        response.status(200);
        response.send("Hi ! This is Crud-Stash.");
    }

    @POST("/crud-stash/post-data")
    public void doPost() {
        String data = getRequest().getBody();
        Response response = getResponse();

        try {
            dao.addData(data);
            response.status(200);
            response.send("Successfully inserted data into " + DB_NAME);
        } catch(Exception e) {
            response.status(500);
            log.error("Error while inserting data into " + DB_NAME, e);
            response.send("Unable to insert data into " + DB_NAME);
        }
    }

    @GET("/crud-stash/get-data")
    public void getPost() {
        ParameterValue queryParam = getRequest().getQueryParameter("id");
        String[] idQueryParam = queryParam.getValues();
        Response response = getResponse();

        if(ArrayUtils.isNotEmpty(idQueryParam)) {
            try {
                response.status(200);
                response.send(dao.getData(idQueryParam[0]));
            } catch(Exception e) {
                response.status(500);
                log.error("Error while fetching from " + DB_NAME, e);
                response.send("Unable to fetch data form " + DB_NAME);
            }
        }
    }
}