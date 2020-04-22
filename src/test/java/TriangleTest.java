import io.restassured.http.Header;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TriangleTest {

    Header header = new Header("X-User", "c13f97b5-7b9a-4cd5-a7c7-5cdbf1d4966d");
    String baseUri = "https://qa-quiz.natera.com";

    @Test
    @DisplayName("add triangle")
    public void createTriangle() {

        given().header(header).baseUri(baseUri).params("input","3;4;5").post("/triangle");
    }

    @Test
    @DisplayName("get triangle/{id}")
    public void getTriangleById() {

        given().header(header).baseUri(baseUri).when().get("/triangle/1").then().statusCode(200);
    }

    @Test
    @DisplayName("delete triangle/{id}")
    public void deleteTriangleById() {

        given().header(header).baseUri(baseUri).when().delete("/triangle/1").then().statusCode(200);
    }

    @Test
    @DisplayName("get triangle/all")
    public void getAllTriangles() {

        given().header(header).baseUri(baseUri).when().get("/triangle/all").then().statusCode(200);
    }

    @Test
    @DisplayName("get triangle/{id}/perimeter")
    public void getTrianglePerimeterById() {

        given().header(header).baseUri(baseUri).when().get("/triangle/1/perimeter").then().statusCode(200);
    }

    @Test
    @DisplayName("get triangle/{id}/area")
    public void getTriangleAreaById() {

        given().header(header).baseUri(baseUri).when().get("/triangle/1/area").then().statusCode(200);
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
}
