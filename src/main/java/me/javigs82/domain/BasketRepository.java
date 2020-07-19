package me.javigs82.domain;

import java.util.Optional;

public interface BasketRepository {

    Optional<Basket> createBasket(String description);

    /**
     * This method returns the basket if can be deleted,
     * otherwise returns Optional:Empty
     * @param code
     * @return Optional<Basket>
     */
    Optional<Basket> deleteBasket(String code);

    Optional<Basket> getBasketByCode(String code);

    Basket addItemToBasket(String code, String itemCode);

}
