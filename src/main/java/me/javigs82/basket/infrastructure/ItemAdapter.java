package me.javigs82.basket.infrastructure;

import me.javigs82.basket.domain.model.Discount;
import me.javigs82.basket.domain.model.Item;
import me.javigs82.basket.domain.ItemPort;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class ItemAdapter implements ItemPort {

    //key: itemCode, value: item
    private final Map<String, Item> items = new HashMap<>();

    @PostConstruct
    public void init() {
        //items
        items.put("PEN", new Item("PEN", "Pen", 500));
        items.put("TSHIRT", new Item("TSHIRT", "T-Shirt", 2000));
        items.put("MUG", new Item("MUG", "Coffee Mug", 750));
        
    }

    @Override
    public Optional<Item> getItemByCode(String code) {
        return Optional.ofNullable(this.items.get(code));
    }
}
