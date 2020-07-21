package me.javigs82.basket.infraestructure;

import io.quarkus.test.junit.QuarkusTest;
import me.javigs82.basket.domain.Basket;
import me.javigs82.basket.infrastructure.BasketRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;


@QuarkusTest
public class BasketRepositoryInMemoryTest {

    @Inject
    BasketRepositoryInMemory basketRepository;

    @Test
    public void testCreateBasket() {
        Optional<Basket> basket = createBasket();
        Optional<Basket> basketGot = this.basketRepository
                .getBasketByCode(basket.get().getCode());

        Assertions.assertEquals(basket.get().getCode(), basketGot.get().getCode());
    }

    @Test
    public void testDeleteBasket() {
        Optional<Basket> basket = createBasket();
        Optional<Basket> basketDeleted = this.basketRepository
                .deleteBasket(basket.get().getCode());
        Assertions.assertTrue(basketDeleted.isPresent());
    }

    @Test
    public void testDeleteBasketNotExist() {
        Optional<Basket> basketDeleted= this.basketRepository
                .deleteBasket("not_exist_code");
        Assertions.assertTrue(basketDeleted.isEmpty());
    }

    @Test
    public void testGetBasketByCode() {
        Optional<Basket> basketCreated = createBasket();
        Optional<Basket> basketGot = this.basketRepository
                .getBasketByCode(basketCreated.get().getCode());
        Assertions.assertTrue(basketGot.isPresent());
    }

    @Test
    public void testGetBasketByCodeNotExist() {
        Optional<Basket> basketGot = this.basketRepository
                .getBasketByCode("not_exist_code");
        Assertions.assertTrue(basketGot.isEmpty());
    }

    private Optional<Basket> createBasket() {
        return this.basketRepository.createBasket("This is my basket");
    }
}