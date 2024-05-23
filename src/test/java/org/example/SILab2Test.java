package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SILab2Test {
    private List<Item> create(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    @Test
    void checkEveryBranch() {
        RuntimeException e;

        e = Assertions.assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 100));
        Assertions.assertTrue(e.getMessage().contains("allItems list can't be null!"));

        Assertions.assertTrue(SILab2.checkCart(new ArrayList<Item>(), 0));

        Assertions.assertTrue(SILab2.checkCart(new ArrayList<Item>(), -1));

        e = Assertions.assertThrows(RuntimeException.class, () -> SILab2.checkCart(create(new Item("", null, 80, 0.8f)), 100));
        Assertions.assertTrue(e.getMessage().contains("No barcode!"));

        e = Assertions.assertThrows(RuntimeException.class, () -> SILab2.checkCart(create(new Item("Test", "7T60321S2L5", 100, 0.8f)), 100));
        Assertions.assertTrue(e.getMessage().contains("Invalid character in item barcode!"));

        Assertions.assertFalse(SILab2.checkCart(create(new Item("Test", "1123456789", 350, 0.5f)), 150));

        Assertions.assertTrue(SILab2.checkCart(create(new Item("DiscountedItem", "0123456789", 350, 0.5f)), 150));
    }

    @Test
    void checkMultipleCondition() {
        // TXX
        Assertions.assertTrue(SILab2.checkCart(create(new Item("Test", "1234567890", 100, 0.5f)), 100));

        // FTX
        RuntimeException e = Assertions.assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(null, 100));
        Assertions.assertTrue(e.getMessage().contains("allItems list can't be null!"));

        // FFF
        e = Assertions.assertThrows(RuntimeException.class, () ->
                SILab2.checkCart(create(new Item(null, "7T60321S2L5", 100, 0.5f)), 100));
        Assertions.assertTrue(e.getMessage().contains("Invalid character in item barcode!"));

        // TFX
        Assertions.assertTrue(SILab2.checkCart(create(new Item("", "1234567890", 100, 0.5f)), 100));
    }
}
