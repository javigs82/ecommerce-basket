package me.javigs82.basket.infraestructure;

import io.quarkus.test.junit.QuarkusTest;
import me.javigs82.basket.domain.model.Discount;
import me.javigs82.basket.infrastructure.DiscountAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;


@QuarkusTest
public class DiscountAdapterTest {

    @Inject
    DiscountAdapter discountAdapter;

    @Test
    public void testGetByItemCode() {
        Optional<Discount> item = this.discountAdapter.getItemByItemCode("TSHIRT");
        item.ifPresentOrElse(item1 -> Assertions.assertEquals(item.get().getItemCode(), "TSHIRT"),
                () -> Assertions.fail());
    }

    @Test
    public void testGetByCodeNotExist() {
        Optional<Discount> item = this.discountAdapter.getItemByItemCode("PACO");
        item.ifPresentOrElse(item1 -> Assertions.fail(),
                () -> Assertions.assertTrue(true));
    }

}