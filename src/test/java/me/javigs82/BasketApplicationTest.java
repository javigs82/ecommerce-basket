package me.javigs82;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BasketApplicationTest {

    @Test
    public void testCreateBasketEndpoint() {
        given()
          .when().post("/basket")
          .then()
             .statusCode(200)
             .body(containsString("created"));
    }

}