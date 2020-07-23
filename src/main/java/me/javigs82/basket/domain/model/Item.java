package me.javigs82.basket.domain.model;

import java.util.Objects;

public final class Item {

    private final String code;
    private  final String description;
    //cents
    private final Integer price;

    public Item(String code, String description, Integer price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return code.equals(item.code) &&
                price.equals(item.price);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(code, price);
    }
}

