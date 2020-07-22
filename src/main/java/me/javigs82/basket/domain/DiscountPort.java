package me.javigs82.basket.domain;

import me.javigs82.basket.domain.model.Discount;

import java.util.Optional;

public interface DiscountPort {

    Optional<Discount> getItemByItemCode(String itemCode);

}
