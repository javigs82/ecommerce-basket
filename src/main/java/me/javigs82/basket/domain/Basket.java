package me.javigs82.basket.domain;

import javax.json.bind.annotation.JsonbCreator;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Basket {

    private final String code;
    private final String description;
    //Store items as key, and quantity as value
    private final Map<Item, Short> itemMap;

    @JsonbCreator
    public Basket(String code, String description) {
        this.code = code;
        this.description = description;
        itemMap = new ConcurrentHashMap<>();
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Collection<Item> getItems() {
        return itemMap.keySet();
    }

    public void addItem(Item item) {
        itemMap.computeIfPresent(item, (i,q) -> q = (short)(q +1));
        itemMap.computeIfAbsent(item, quantity -> (short)1);
    }
}

