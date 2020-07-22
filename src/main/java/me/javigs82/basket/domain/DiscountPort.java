package me.javigs82.basket.domain;

import java.util.Optional;

public interface DiscountPort {

    Optional<Discount> getItemByItemCode(String itemCode);

}
