package me.javigs82.basket.infrastructure;

import me.javigs82.basket.domain.Discount;
import me.javigs82.basket.domain.DiscountPort;
import me.javigs82.basket.domain.Item;
import me.javigs82.basket.domain.ItemPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class DiscountAdapter implements DiscountPort {

    //key: itemCode, value: discount
    private final Map<String, Discount> discounts = new HashMap<>();

    @PostConstruct
    public void init() {
        //discounts
        discounts.put("PEN", new Discount(UUID.randomUUID().toString(), "PEN", (short) 3, (byte)33));
        discounts.put("TSHIRT", new Discount(UUID.randomUUID().toString(), "TSHIRT", (short) 2, (byte)50));
        discounts.put("MUG", new Discount(UUID.randomUUID().toString(), "MUG", (short) 1, (byte)10));

    }

    @Override
    public Optional<Discount> getItemByItemCode(String itemCode) {
        return Optional.ofNullable(this.discounts.get(itemCode));
    }
}
