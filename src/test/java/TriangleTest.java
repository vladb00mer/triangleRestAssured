import io.restassured.http.Header;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;


public class TriangleTest {

    static Header header;
    static String baseUri;
    String id;
    Map<String, Object> jsonAsMap;

    @BeforeAll
    @DisplayName("add triangle")
    public static void setUp() {

        header = new Header("X-User", "c13f97b5-7b9a-4cd5-a7c7-5cdbf1d4966d");
        baseUri = "https://qa-quiz.natera.com";
    }

    @Test
    @DisplayName("create triangle")
    public void createTriangle() {

        jsonAsMap = new HashMap<>();
        jsonAsMap.put("input", "3;4;5");

        given().header(header).baseUri(baseUri).
                contentType(JSON).
                body(jsonAsMap).
                when().
                post("/triangle").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    @DisplayName("get triangle/{id}")
    public void getTriangleById() {

        given().header(header).baseUri(baseUri).when().get("/triangle/{id}", "b9501cb3-735f-46de-ac5e-f713f14906d8").then().statusCode(200);
    }

    @Test
    @DisplayName("delete triangle/{id}")
    public void deleteTriangleById() {

        given().header(header).baseUri(baseUri).when().delete("/triangle/{id}", "95e9a7a7-8cd2-4269-b3b9-d23976833b61").then().statusCode(200);
    }

    @Test
    @DisplayName("get triangle/all")
    public void getAllTriangles() {

        String res = given().header(header).baseUri(baseUri).when().get("/triangle/all").asString();
        System.out.println(res);
    }

    @Test
    @DisplayName("get triangle/{id}/perimeter")
    public void getTrianglePerimeterById() {

        given().header(header).baseUri(baseUri).when().get("/triangle/{id}/perimeter", "104f57d1-cfce-40f3-8291-a383748bf247").then().statusCode(200);
    }

    @Test
    @DisplayName("get triangle/{id}/area")
    public void getTriangleAreaById() {

        given().header(header).baseUri(baseUri).when().get("/triangle/{id}/area", "0bdd8c27-df79-4a48-9ccb-612436cd2603").then().statusCode(200);
    }

    @Test
    @DisplayName("code 200 OK")
    public void test200() {

        given().header(header).baseUri(baseUri).when().get().then().assertThat().statusCode(200);
    }

    @Test
    @DisplayName("code 401 unauthorized")
    public void test401() {

        given().header(header).baseUri(baseUri).auth().basic("user", "password")
                .when().get("/triangle/all").then().assertThat().statusCode(401);
    }

    @Test
    @DisplayName("code 404 not found")
    public void test404() {

        given().header(header).baseUri(baseUri).when().get("/triangle/notFound").then().assertThat().statusCode(404);
    }

    @Test
    @DisplayName("code 422 typo in request")
    public void test422() {

        given().header(header).baseUri(baseUri).when().get("/trangle/all").then().assertThat().statusCode(422);
    }

    @AfterAll
    public static void tearDown() {

        header = null;
        baseUri = null;
    }
}
