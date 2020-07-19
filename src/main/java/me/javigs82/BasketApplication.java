package me.javigs82;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.quarkus.vertx.web.RoutingExchange;
import io.vertx.core.http.HttpMethod;
import io.vertx.mutiny.core.eventbus.EventBus;
import me.javigs82.domain.Basket;
import me.javigs82.domain.BasketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import java.util.Optional;

@ApplicationScoped
@RouteBase(produces = "application/json")
public class BasketApplication {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    @Inject
    EventBus bus;

    @Inject
    BasketService basketService;


    @Route(path = "/basket", methods = HttpMethod.POST)
    void createBasket(RoutingExchange ex) {
        log.info("POST /basket/");
        bus.<Basket>request("create-basket-event", "")
                .subscribeAsCompletionStage().thenAccept(s -> ex.ok(JsonbBuilder.create().toJson(s.body())));
    }

    //If basket is not present then throw 404.
    @Route(path = "/basket/:code", methods = HttpMethod.DELETE)
    void deleteBasket(RoutingExchange ex) {
        String code =  ex.getParam("code").get();
        log.info("DELETE /basket/{}", code);
        bus.<Basket>request("delete-basket-event", code)
                .subscribeAsCompletionStage().thenAccept(s -> {
                    log.info("thena aceept");
                    if (s.body()!=null) {
                        log.info("OK");
                        ex.ok(JsonbBuilder.create().toJson(s.body()));
                    }else {
                        log.info("404");
                        ex.notFound().end("");
                    }
                });
        //bus.sendAndForget("delete-basket-event", code);
        /*bus.<Basket>request("delete-basket-event", code)
                .subscribeAsCompletionStage().wh
                thenAccept(s -> ex.ok(""))
                .exceptionally(exception -> ex.notFound().end());*/
        //ex.ok(""); //set empty string
    }

    @Route(path = "/basket/:code", methods = HttpMethod.GET)
    void getBasketByCode(RoutingExchange ex) {
        ex.ok(basketService.getBasketByCode(ex.getParam("code").orElse("Not found")));
    }

    @Route(path = "/basket/:code/item/:itemCode", methods = HttpMethod.POST)
    void addItemToBasket(RoutingExchange ex) {
        ex.ok("item added");
    }

}
