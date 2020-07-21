package me.javigs82.basket.domain;

import java.math.BigDecimal;
import java.util.Objects;

public final class Item {

    final String code;
    final String description;
    final BigDecimal price;

    public Item(String code, String description, BigDecimal price) {
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

    public BigDecimal getPrice() {
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

