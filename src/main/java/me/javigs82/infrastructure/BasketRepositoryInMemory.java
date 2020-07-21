package me.javigs82.infrastructure;

import me.javigs82.domain.Basket;
import me.javigs82.domain.BasketRepository;
import me.javigs82.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class BasketRepositoryInMemory implements BasketRepository {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    Map<String, Basket> baskets = new ConcurrentHashMap<>();

    @Override
    public Optional<Basket> createBasket(String description) {
        log.trace("createBasket {}", description);
        Basket basket = new Basket(UUID.randomUUID().toString(), description);
        this.baskets.put(basket.getCode(), basket);
        return Optional.of(basket);
    }

    @Override
    public Optional<Basket> deleteBasket(String code) {
        log.trace("deleteBasket {}", code);
        if (this.baskets.containsKey(code)) {
            return Optional.of(this.baskets.remove(code));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Basket> getBasketByCode(String code) {
        log.trace("getBasketByCode {}", code);
        if (this.baskets.containsKey(code)) {
            return Optional.of(this.baskets.get(code));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Basket> addItemToBasket(String code, Item item) {
        log.trace("addItem {} ToBasket {}", code, item.getCode());
        if (this.baskets.containsKey(code)) {
            Basket basket = this.baskets.get(code);
            basket.addItem(item);
            return Optional.of(basket);
        } else {
            return Optional.empty();
        }
    }
}
