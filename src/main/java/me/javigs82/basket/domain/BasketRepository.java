package me.javigs82.basket.domain;

import me.javigs82.basket.domain.model.Basket;
import me.javigs82.basket.domain.model.Discount;
import me.javigs82.basket.domain.model.Item;

import java.util.Optional;

public interface BasketRepository {

    Optional<Basket> createBasket(String description);

    Optional<Basket> deleteBasket(String code);

    Optional<Basket> getBasketByCode(String code);

    Optional<Basket> addItemToBasket(String code, Item item, Optional<Discount> discount);

}
