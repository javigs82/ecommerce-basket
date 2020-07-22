package me.javigs82.basket.infrastructure;

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


@ApplicationScoped
public class ItemAdapter implements ItemPort {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private final Map<String, Item> items = new HashMap<>();

    @PostConstruct
    public void init() {
        items.put("PEN", new Item("PEN", "Pen", BigDecimal.valueOf(500)));
        items.put("TSHIRT", new Item("TSHIRT", "T-Shirt", BigDecimal.valueOf(2000)));
        items.put("MUG", new Item("MUG", "Coffee Mug", BigDecimal.valueOf(750)));
    }

    @Override
    public Optional<Item> getItemByCode(String code) {
        log.debug("getItemByCode {}", code);
        return Optional.ofNullable(this.items.get(code));
    }
}
