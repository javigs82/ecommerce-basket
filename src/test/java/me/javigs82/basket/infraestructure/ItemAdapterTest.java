package me.javigs82.basket.infraestructure;

import io.quarkus.test.junit.QuarkusTest;
import me.javigs82.basket.domain.Item;
import me.javigs82.basket.infrastructure.ItemAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.Optional;


@QuarkusTest
public class ItemAdapterTest {

    @Inject
    ItemAdapter itemAdapter;

    @Test
    public void testGetByCode() {
        Optional<Item> item = this.itemAdapter.getItemByCode("TSHIRT");
        Assertions.assertEquals(item.get().getCode(), "TSHIRT");
        Assertions.assertNotEquals(item.get().getCode(), "PEN");
    }

}