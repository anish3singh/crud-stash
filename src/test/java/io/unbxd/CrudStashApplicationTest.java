package io.unbxd;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jayway.restassured.response.Response;
import io.unbxd.crudstash.CrudStashApplication;
import io.unbxd.crudstash.CrudStashModule;
import org.apache.logging.log4j.util.Strings;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import ro.pippo.core.Pippo;
import ro.pippo.test.PippoRule;
import ro.pippo.test.PippoTest;

import java.util.UUID;

import static io.unbxd.crudstash.constants.Constants.PORT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        String id3 = "unx021";
        String data = "Sample Mock Data";
        String id2 = UUID.randomUUID().toString();
        String id1 = "b9e1490d-aa18-4b17-a351-e21015643111";
        Response response4 = when().get("/crud-stash/get-data");
        Response response3 = when().get("/crud-stash/get-data?id=");
        Response response1 = when().get("/crud-stash/get-data?id=" + id1);
        Response response2 = when().get("/crud-stash/get-data?id=" + id2);
        Response response5 = when().get("/crud-stash/get-data?id=" + id3);

        response1.then().statusCode(200);
        response2.then().statusCode(200);
        response3.then().statusCode(200);
        response4.then().statusCode(404);
        response5.then().statusCode(200);

        String fetchedData1 = response1.asString();
        String fetchedData2 = response2.asString();
        String fetchedData3 = response3.asString();
        String fetchedData4 = response4.asString();
        String fetchedData5 = response5.asString();

        assertEquals(data, fetchedData1);
        assertEquals(Strings.EMPTY , fetchedData2);
        assertEquals(Strings.EMPTY , fetchedData3);
        assertTrue(fetchedData4.contains("Cannot find a route for 'GET /crud-stash/get-data'"));
    }

    @Test
    public void testDataPost() {
        String data1 = "Sample Mock Data 2";
        String data2 = "{\n" +
                "  \"name\": \"ujjwal\",\n" +
                "  \"employeeId\": \"unx021\",\n" +
                "  \"address\": \"Bangalore\",\n" +
                "  \"designation\": \"software engineer\"\n" +
                "}";
        String data3 = "{\n" +
                "  \"name\": \"ujjwal\",\n" +
                "  \"employee\": \"unx021\",\n" +
                "  \"address\": \"Bangalore\",\n" +
                "  \"designation\": \"software engineer\"\n" +
                "}";
        Response response2 = when().post("/crud-stash/post-data");
        Response response1 = given().body(data1.getBytes()).when().post("/crud-stash/post-data");
        Response response3 = given().body(data2.getBytes()).when().post("/crud-stash/post-data");
        Response response4 = given().body(data3.getBytes()).when().post("/crud-stash/post-data");

        response1.then().statusCode(500);
        response2.then().statusCode(500);
        response3.then().statusCode(200);
        response4.then().statusCode(500);
    }
}