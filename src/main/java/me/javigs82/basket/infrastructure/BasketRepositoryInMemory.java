package me.javigs82.basket.infrastructure;

import me.javigs82.basket.domain.Basket;
import me.javigs82.basket.domain.BasketRepository;
import me.javigs82.basket.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class BasketRepositoryInMemory implements BasketRepository {

    private final Logger log = LoggerFactory.getLogger(getClass().getName());

    private final Map<String, Basket> baskets = new ConcurrentHashMap<>();

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
        return Optional.ofNullable(this.baskets.remove(code));
    }

    @Override
    public Optional<Basket> getBasketByCode(String code) {
        log.trace("getBasketByCode {}", code);
        return Optional.ofNullable(this.baskets.get(code));
    }

    @Override
    public Optional<Basket> addItemToBasket(String code, Item item) {
        log.trace("addItem {} ToBasket {}", code, item.getCode());
        return Optional.ofNullable(baskets.computeIfPresent(code, (c, b) -> {
            b.addItem(item);
            return b;
        }));
    }
}
