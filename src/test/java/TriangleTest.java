import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class TriangleTest {

    @BeforeAll
    public static void setUpAll() {

        given().baseUri("https://qa-quiz.natera.com").auth().basic("user", "c13f97b5-7b9a-4cd5-a7c7-5cdbf1d4966d")
                .params("input","3;4;5").post("/triangle");
    }

    @Test
    @DisplayName("code 200 OK")
    public void test200() {

        given().baseUri("https://qa-quiz.natera.com").auth().basic("user", "c13f97b5-7b9a-4cd5-a7c7-5cdbf1d4966d")
                .when().get().then().assertThat().statusCode(200);
    }

    @Test
    @DisplayName("code 401 unauthorized")
    public void test401() {

        given().baseUri("https://qa-quiz.natera.com").auth().basic("user", "fake-token")
                .when().get("/triangle/all").then().assertThat().statusCode(401);
    }

    @Test
    @DisplayName("code 404 not found")
    public void test404() {

        given().baseUri("https://qa-quiz.natera.com").auth().basic("user", "c13f97b5-7b9a-4cd5-a7c7-5cdbf1d4966d")
                .when().get("/triangle/notFound").then().assertThat().statusCode(404);
    }

    @Test
    @DisplayName("code 422 typo in request")
    public void test422() {

        given().baseUri("https://qa-quiz.natera.com").auth().basic("user", "c13f97b5-7b9a-4cd5-a7c7-5cdbf1d4966d")
                .when().get("/trangle/all").then().assertThat().statusCode(422);
    }
}
