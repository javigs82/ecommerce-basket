package me.javigs82.basket.domain;

import me.javigs82.basket.domain.model.Item;

import java.util.Optional;

public interface ItemPort {

    Optional<Item> getItemByCode(String code);

}
