package me.javigs82.basket.infraestructure;

import io.quarkus.test.junit.QuarkusTest;
import me.javigs82.basket.domain.model.Basket;
import me.javigs82.basket.domain.model.Discount;
import me.javigs82.basket.domain.model.Item;
import me.javigs82.basket.infrastructure.BasketRepositoryInMemory;
import me.javigs82.basket.infrastructure.DiscountAdapter;
import me.javigs82.basket.infrastructure.ItemAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Optional;

@QuarkusTest
public class BasketRepositoryInMemoryTest {

    @Inject
    BasketRepositoryInMemory basketRepository;

    @Inject
    ItemAdapter itemAdapter;

    @Inject
    DiscountAdapter discountAdapter;

    @Test
    public void testCreateBasket() {
        Optional<Basket> basket = createBasket();
        basket.ifPresentOrElse(b1 -> {
                    this.basketRepository
                            .getBasketByCode(b1.getCode())
                            .ifPresentOrElse(b2 -> {
                                Assertions.assertEquals(b1.getCode(), b2.getCode());
                                Assertions.assertEquals(b1.getItems().size(), 0);
                                Assertions.assertEquals(b1.getPriceNumber(), BigDecimal.ZERO);
                                    },
                                    () -> Assertions.fail());
                },
                () -> Assertions.fail()
        );
    }

    @Test
    public void testDeleteBasket() {
        Optional<Basket> basket = createBasket();
        basket.ifPresentOrElse(b1 -> {
                    this.basketRepository
                            .deleteBasket(b1.getCode())
                            .ifPresentOrElse(b2 -> Assertions.assertEquals(b1.getCode(), b2.getCode()),
                                    () -> Assertions.fail());
                },
                () -> Assertions.fail()
        );
    }

    @Test
    public void testDeleteBasketNotExist() {
        Optional<Basket> basketDeleted = this.basketRepository
                .deleteBasket("not_exist_code");
        basketDeleted.ifPresentOrElse(
                b -> Assertions.fail(),
                () -> Assertions.assertTrue(true)
        );
    }

    @Test
    public void testGetBasketByCode() {
        Optional<Basket> basketCreated = createBasket();
        basketCreated.ifPresentOrElse(
                b1 -> this.basketRepository
                        .getBasketByCode(b1.getCode()).ifPresentOrElse(
                                b2 -> Assertions.assertEquals(b1.getCode(), b2.getCode()),
                                () -> Assertions.fail()
                        ),
                () -> Assertions.fail()
        );
    }

    @Test
    public void testGetBasketByCodeNotExist() {
        Optional<Basket> basketGot = this.basketRepository
                .getBasketByCode("not_exist_code");
        basketGot.ifPresentOrElse(
                b -> Assertions.fail(),
                () -> Assertions.assertTrue(true)
        );
    }

    @Test
    public void testAddItemToBasket() {
        Optional<Basket> basketCreated = createBasket();
        Optional<Item> item = this.itemAdapter.getItemByCode("TSHIRT");
        basketCreated.ifPresent(b -> {
            item.ifPresent(i -> {
                this.basketRepository.addItemToBasket(b.getCode(), i, Optional.empty())
                        .ifPresentOrElse(
                                b2 -> Assertions.assertTrue(b.getItems().containsKey(i)),
                                () -> Assertions.fail()
                        );
            });
        });

    }

    @Test
    public void testAddItemWithDiscountToBasket() {
        String itemCode = "TSHIRT";
        Optional<Discount> discount = this.discountAdapter.getItemByItemCode(itemCode);
        Optional<Basket> basketCreated = createBasket();
        Optional<Item> item = this.itemAdapter.getItemByCode(itemCode);
        basketCreated.ifPresent(b -> {
            item.ifPresent(i -> {
                this.basketRepository.addItemToBasket(b.getCode(), i, discount)
                        .ifPresentOrElse(
                                b2 -> {
                                    Assertions.assertTrue(b.getItems().containsKey(i));
                                    Assertions.assertEquals(b.getDiscount().get(i).getItemCode(), itemCode);
                                },
                                () -> Assertions.fail()
                        );
            });
        });

    }

    @Test
    public void testAddItemWithNODiscountToBasket() {
        String itemCode = "CHELO";
        Optional<Basket> basketCreated = createBasket();
        Optional<Item> itemGot = this.itemAdapter.getItemByCode(itemCode);
        Optional<Discount> discount = this.discountAdapter.getItemByItemCode(itemCode);
        basketCreated.ifPresent(basket -> {
            itemGot.ifPresent(item -> {
                this.basketRepository.addItemToBasket(basket.getCode(), item, discount)
                        .ifPresentOrElse(
                                b2 -> {
                                    //items exist
                                    Assertions.assertTrue(b2.getItems().containsKey(itemGot));
                                    //no disccounts
                                    Assertions.assertTrue(b2.getDiscount().size() == 0);
                                },
                                () -> Assertions.fail()
                        );
            });
        });
    }

    @Test
    public void testGetPriceOneItemToBasket() {
        Optional<Basket> basketCreated = createBasket();
        Optional<Item> item = this.itemAdapter.getItemByCode("TSHIRT");
        basketCreated.ifPresent(b -> {
            item.ifPresent(i -> {
                this.basketRepository.addItemToBasket(b.getCode(), i, Optional.empty())
                        .ifPresentOrElse(
                                b2 -> {
                                    Assertions.assertTrue(b.getItems().containsKey(i));
                                },
                                () -> Assertions.fail()
                        );
            });
        });
    }

    private Optional<Basket> createBasket() {
        return this.basketRepository.createBasket("This is my basket");
    }
}