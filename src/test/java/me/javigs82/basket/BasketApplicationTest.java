package me.javigs82.basket;

import io.quarkus.test.junit.QuarkusTest;
import me.javigs82.basket.domain.Basket;
import org.junit.jupiter.api.Test;

import javax.json.bind.JsonbBuilder;

import static io.restassured.RestAssured.given;


@QuarkusTest
public class BasketApplicationTest {

    @Test
    public void testCreateBasketEndpoint() {
        given()
                .when().post("/basket")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeleteBasketEndpoint() {
        //create basket
        Basket basket = createBasket();
        //delete created
        given()
                .when().delete("/basket/" + basket.getCode())
                .then()
                .statusCode(200);

    }

    @Test //only test if does not exist
    public void testDeleteBasketNotFoundEndpoint() {
        String delete404 = "not_exist_basket";
        given()
                .when().delete("/basket/" + delete404)
                .then()
                .statusCode(404);
    }

    @Test
    public void testGetBasketByCodeEndpoint() {
        //create one
        Basket basket = createBasket();
        //getById
        given()
                .when().get("/basket/" + basket.getCode())
                .then()
                .statusCode(200);
    }

    @Test
    public void testGetBasketByCodeNotFoundEndpoint() {
        //create one
        String code = "not_exist_basket";
        //getById
        given()
                .when().get("/basket/" + code)
                .then()
                .statusCode(404);
    }

    @Test
    public void testAddItemToBasketEndpoint() {
        //create one
        Basket basket = createBasket();
        //getById
        given()
                .when().put("/basket/" + basket.getCode() + "/item/MUG")
                .then()
                .statusCode(200);
    }

    @Test
    public void testAddItemToBasketNotFoundEndpoint() {
        //getById
        given()
                .when().put("/basket/not-found-basket/item/MUG")
                .then()
                .statusCode(404);
    }

    @Test
    public void testAddItemNotExistToBasketEndpoint() {
        //create one
        Basket basket = createBasket();
        String item404 = "not_exist_item";
        //getById
        given()
                .when().put("/basket/" + basket.getCode() + "/item/" + item404)
                .then()
                .statusCode(404);
    }

    private Basket createBasket() {
        return JsonbBuilder.create().fromJson(
                given()
                        .when().post("/basket")
                        .then()
                        .extract().response().asString(), Basket.class);
    }

}