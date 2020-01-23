package io.unbxd;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jayway.restassured.response.Response;
import io.unbxd.crudstash.CrudStashApplication;
import io.unbxd.crudstash.CrudStashModule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import ro.pippo.core.Pippo;
import ro.pippo.test.PippoRule;
import ro.pippo.test.PippoTest;

import static io.unbxd.crudstash.constants.Constants.PORT;
import static org.junit.Assert.assertEquals;

public class CrudStashApplicationTest extends PippoTest {

    @ClassRule
    public static PippoRule pippoRule;

    @BeforeClass
    public static void setup() {
        Injector injector = Guice.createInjector(new CrudStashModule());
        CrudStashApplication application = injector.getInstance(CrudStashApplication.class);
        Pippo pippo = (new Pippo(application));
        pippoRule = new PippoRule(pippo);
        pippo.start(PORT);
    }

    @Test
    public void testServerUp() {
        when().get("/").then().statusCode(200);
    }

    @Test
    public void testDataFetch() {
        String data = "Sample Mock Data";
        String id = "b9e1490d-aa18-4b17-a351-e21015643111";
        Response response = when().get("/crud-stash/get-data?id=" + id);

        response.then().statusCode(200);
        String fetchedData = response.print();

        assertEquals(data, fetchedData);
    }

    @Test
    public void testDataPost() {
        String data = "Sample Mock Data 2";
        Response response = given().body(data.getBytes()).when().post("/crud-stash/post-data");

        response.then().statusCode(200);
    }
}