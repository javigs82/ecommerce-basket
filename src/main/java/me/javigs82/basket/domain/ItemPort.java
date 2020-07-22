package me.javigs82.basket.domain;

import java.util.Optional;

public interface ItemPort {

    Optional<Item> getItemByCode(String code);

}
