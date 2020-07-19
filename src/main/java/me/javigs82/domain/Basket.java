package me.javigs82.domain;

import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

public final class Basket {

    private final String code;
    private final String description;
    private ConcurrentSkipListSet<Item> items;

    public Basket(String description) {
        this.code = UUID.randomUUID().toString();
        this.description = description;
        items = new ConcurrentSkipListSet<>();
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public ConcurrentSkipListSet<Item> getItems() {
        return items;
    }

    public void addItem(Item e) {
        items.add(e);
    }
}

