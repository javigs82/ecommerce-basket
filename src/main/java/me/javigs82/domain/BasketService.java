package me.javigs82.domain;

import io.quarkus.vertx.ConsumeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class BasketService {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Inject
    BasketRepository basketRepository;

    @ConsumeEvent(value = "create-basket-event")
    public CompletionStage<Basket> createBasket(String event) {
        log.debug("creating basket");
        String description = "basket created at: " + Calendar.getInstance().getTimeInMillis();
        return CompletableFuture.supplyAsync(() ->
         this.basketRepository.createBasket(description).get()
        );
    }

    @ConsumeEvent(value = "delete-basket-event")
    public CompletionStage<Basket> deleteBasket(String code) {
        log.debug("deleting basket {}", code);
        return CompletableFuture.supplyAsync(() ->
         this.basketRepository.deleteBasket(code).orElseGet(() -> new Basket("dummy"))
        );
    }

    //return basket to ensure immutable
    @ConsumeEvent(value = "add-item-basket-event")
    public String addItemBasket(String eventType) {
        return "Item added";
    }

    //calculate here final price to ensure final price comes from server
    @ConsumeEvent(value = "get-basket-event")
    public String getBasketByCode(String event) {
        return "TODO";
    }

}
