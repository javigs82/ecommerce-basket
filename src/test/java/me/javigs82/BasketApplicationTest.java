package me.javigs82;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
public class BasketApplicationTest {

    @Test
    public void testCreateBasketEndpoint() {
        given()
                .when().post("/basket")
                .then()
                .statusCode(200)
                .body(containsString("code"));
    }

    @Test //only test if it cannot be deleted by security
    public void testDeleteBasketEndpoint() {
        String delete404 = "not_exist_" + java.util.Calendar.getInstance().getTimeInMillis();
        given()
                .when().delete("/basket/" + delete404)
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetBasketByCodeEndpoint() {
        given()
                .when().get("/basket/adf-asdf45-2rASD-12")
                .then()
                .statusCode(404);
    }

}