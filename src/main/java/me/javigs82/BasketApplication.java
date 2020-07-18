package me.javigs82;

import io.quarkus.vertx.web.Route;
import io.quarkus.vertx.web.RouteBase;
import io.quarkus.vertx.web.RoutingExchange;
import io.vertx.core.http.HttpMethod;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RouteBase(produces = "application/json")
public class BasketApplication {

   @Route( path = "/basket", methods = HttpMethod.POST )
    void createBasket(RoutingExchange ex) {
       ex.ok("basket created");
    }

    @Route(path = "/basket/:id", methods = HttpMethod.DELETE )
    void deleteBasket(RoutingExchange ex) {
        ex.ok("basket deleted");
    }

    @Route(path = "/basket/:id", methods = HttpMethod.GET )
    void getBasketById(RoutingExchange ex) {
        ex.ok("hello " + ex.getParam("id").orElse("notfound"));
    }

    @Route(path=":id/item/:itemId", methods = HttpMethod.POST )
    void addItemToBasket(RoutingExchange ex) {
        ex.ok("basket created");
    }

}
