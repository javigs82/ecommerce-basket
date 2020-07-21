package me.javigs82.basket.domain;

import java.util.Optional;

public interface BasketRepository {

    Optional<Basket> createBasket(String description);

    Optional<Basket> deleteBasket(String code);

    Optional<Basket> getBasketByCode(String code);

    Optional<Basket> addItemToBasket(String code, Item itemCode);

}
