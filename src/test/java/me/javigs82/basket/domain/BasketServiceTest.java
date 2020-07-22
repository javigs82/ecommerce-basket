package me.javigs82.basket.domain;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

@QuarkusTest
public class BasketServiceTest {

    @Inject
    BasketService basketService;

    @Test
    public void testCreateBasket() throws ExecutionException, InterruptedException {
        CompletionStage<Basket> cs = createBasket();
        cs.thenAccept(b -> Assertions.assertTrue(b.getCode() != null && b.getCode() != ""))
                .toCompletableFuture().get();
    }

    @Test
    public void testDeleteBasket() throws ExecutionException, InterruptedException {
        Basket createdBasket = createBasket().toCompletableFuture().get();
            this.basketService.deleteBasket(createdBasket.getCode())
                    .thenAccept(deletedBasket ->
                            Assertions.assertEquals(deletedBasket.getCode(), createdBasket.getCode())
        ).toCompletableFuture().get();
    }

    @Test
    public void testDeleteNotFoundBasket() throws ExecutionException, InterruptedException {
        String code = "code_not_found";
        this.basketService.deleteBasket(code).thenAccept(
                basketDeleted -> Assertions.assertEquals(basketDeleted, null)
        ).toCompletableFuture().get();
    }

    @Test
    public void testGetBasketByCode() throws ExecutionException, InterruptedException {
        Basket basketCreated = createBasket().toCompletableFuture().get();
        this.basketService.getBasketByCode(basketCreated.getCode()).thenAccept(
                basketGot -> Assertions.assertEquals(basketCreated.getCode(), basketGot.getCode())
        ).toCompletableFuture().get();
    }

    @Test
    public void testGetBasketByCodeNotFound() throws ExecutionException, InterruptedException {
        String code = "code_not_found";
        this.basketService.getBasketByCode(code).thenAccept(
                basketGot -> Assertions.assertEquals(basketGot, null)
        ).toCompletableFuture().get();
    }

    @Test
    public void testAddItemBasket() throws ExecutionException, InterruptedException {
        AddItemToBasketEvent event =
                new AddItemToBasketEvent(createBasket()
                        .toCompletableFuture().get().getCode(), "TSHIRT");
        this.basketService.addItemBasket(event)
                .thenAccept(basket ->
                        Assertions.assertEquals(basket.getItems().size(), 1))
                .toCompletableFuture().get();
    }

    @Test
    public void testAddItemNotFoundBasket() throws ExecutionException, InterruptedException {
        AddItemToBasketEvent event =
                new AddItemToBasketEvent(createBasket()
                        .toCompletableFuture().get().getCode(), "PEPE");

        this.basketService.addItemBasket(event)
                .thenAccept(basket ->
                        Assertions.assertEquals(basket,null ))
                .toCompletableFuture().get();
    }

    @Test
    public void testCalculateBasket() throws ExecutionException, InterruptedException {
        AddItemToBasketEvent event =
                new AddItemToBasketEvent(createBasket()
                        .toCompletableFuture().get().getCode(), "PEPE");

        this.basketService.addItemBasket(event)
                .thenAccept(basket ->
                        Assertions.assertEquals(basket,null ))
                .toCompletableFuture().get();
    }



    private CompletionStage<Basket> createBasket() {
        return this.basketService.createBasket("");
    }
}
