package me.javigs82.basket.infrastructure;

import me.javigs82.basket.domain.model.Discount;
import me.javigs82.basket.domain.DiscountPort;

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
        discounts.put("PEN", new Discount(UUID.randomUUID().toString(), "PEN", (short) 3, (byte)33));
        discounts.put("TSHIRT", new Discount(UUID.randomUUID().toString(), "TSHIRT", (short) 2, (byte)50));
        discounts.put("MUG", new Discount(UUID.randomUUID().toString(), "MUG", (short) 1, (byte)10));
    }

    @Override
    public Optional<Discount> getItemByItemCode(String itemCode) {
        return Optional.ofNullable(this.discounts.get(itemCode));
    }
}
