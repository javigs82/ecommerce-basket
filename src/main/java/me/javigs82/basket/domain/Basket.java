package me.javigs82.basket.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.bind.annotation.JsonbCreator;
import javax.json.bind.annotation.JsonbTransient;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class Basket {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private final String code;
    private final String description;
    //Store items as key, and quantity as value
    private final Map<Item, Short> itemMap;


    @JsonbCreator
    public Basket(String code, String description) {
        this.code = code;
        this.description = description;
        this.itemMap = new ConcurrentHashMap<>();
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

    @JsonbTransient
    public BigDecimal getPriceNumber() {
        BigDecimal price = BigDecimal.valueOf(0);
        Iterator<Map.Entry<Item, Short>> entries = this.itemMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Item, Short> entry = entries.next();
            long totalItemPrice = (entry.getKey().getPrice() * entry.getValue().intValue());
            price = price.add(BigDecimal.valueOf(totalItemPrice));
        }
        return price;
    }

    /**
     *
     * @return price as string formatted with the current locale
     */
    public String getPrice() {
        BigDecimal price = getPriceNumber();
        StringBuilder builder = new StringBuilder();
        //from cts to units
        price = price.movePointLeft(Monety.CURRENCY.getDefaultFractionDigits()  );
        //for number of decimals
        price = price.setScale(Monety.CURRENCY.getDefaultFractionDigits(),
                Monety.ROUNDING_MODE);
        builder.append(price)
                .append(" ")
                .append(Monety.CURRENCY.getSymbol());
        return builder.toString();
    }
}

