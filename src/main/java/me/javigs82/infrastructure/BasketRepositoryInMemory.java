package me.javigs82.infrastructure;

import me.javigs82.domain.Basket;
import me.javigs82.domain.BasketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Optional;

@ApplicationScoped
public class BasketRepositoryInMemory implements BasketRepository {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    HashMap<String, Basket> baskets = new HashMap<>();

    public Optional<Basket> createBasket(String description) {
        Basket basket = new Basket(description);
        this.baskets.put(basket.getCode(), basket);
        log.info("memory size {}", this.baskets.size());
        return Optional.of(basket);
    }

    public Optional<Basket> deleteBasket(String code) {
        if (this.baskets.containsKey(code)) {
            return Optional.of(this.baskets.remove(code));
        } else {
            return Optional.empty();
        }
    }

    public Optional<Basket> getBasketByCode(String code) {
        if (this.baskets.containsKey(code)) {
            return Optional.of(this.baskets.get(code));
        } else {
            return Optional.empty();
        }
    }

    public Basket addItemToBasket(String code, String itemCode) {
        return this.baskets.get(code);
    }


}
