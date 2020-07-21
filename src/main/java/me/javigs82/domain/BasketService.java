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

    @Inject
    ItemPort itemPort;

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
        log.debug("deleting basket by code {}", code);
        return CompletableFuture.supplyAsync(() ->
             this.basketRepository.deleteBasket(code).orElse(null)
        );
    }

    //calculate here final price to ensure final price comes from server
    @ConsumeEvent(value = "get-basket-event")
    public CompletionStage<Basket> getBasketByCode(String code) {
        log.debug("getting basket by code {}", code);
        return CompletableFuture.supplyAsync(() ->
                this.basketRepository.getBasketByCode(code).orElse(null)
        );
    }

    @ConsumeEvent(value = "add-item-basket-event")
    public CompletionStage<Basket> addItemBasket(AddItemToBasketEvent event) {
        log.debug("adding item {} to basket {}", event.itemCode, event.basketCode);
        return CompletableFuture.supplyAsync(() ->{
                Optional<Item> item = this.itemPort.getItemByCode(event.itemCode);
                if (item.isPresent()) {
                 return this.basketRepository
                         .addItemToBasket(event.basketCode, item.get()).get();
                } else {
                    return null;
                }
        });
    }

}
