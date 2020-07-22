package me.javigs82.basket.infrastructure;

import me.javigs82.basket.domain.Discount;
import me.javigs82.basket.domain.Item;
import me.javigs82.basket.domain.ItemPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class ItemAdapter implements ItemPort {

    //key: itemCode, value: item
    private final Map<String, Item> items = new HashMap<>();

    //key: itemCode, value: discount
    private final Map<String, Discount> discounts = new HashMap<>();

    @PostConstruct
    public void init() {
        //items
        items.put("PEN", new Item("PEN", "Pen", 500));
        items.put("TSHIRT", new Item("TSHIRT", "T-Shirt", 2000));
        items.put("MUG", new Item("MUG", "Coffee Mug", 750));

        //discounts
        discounts.put("PEN", new Discount(UUID.randomUUID().toString(), "PEN", (short) 3, (byte)33));
        discounts.put("TSHIRT", new Discount(UUID.randomUUID().toString(), "PEN", (short) 2, (byte)50));
        discounts.put("MUG", new Discount(UUID.randomUUID().toString(), "PEN", (short) 1, (byte)10));

    }

    @Override
    public Optional<Item> getItemByCode(String code) {
        return Optional.ofNullable(this.items.get(code));
    }
}
