package me.javigs82.domain;

import javax.json.bind.annotation.JsonbCreator;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Basket {

    private final String code;
    private final String description;
    //Store items by code
    private Map<String, Item> itemMap;

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
        return itemMap.values();
    }

    public void addItem(Item e) {
        itemMap.put(e.getCode(), e);
    }
}

