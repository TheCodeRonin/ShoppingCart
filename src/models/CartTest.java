package models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    Cart cart;

    @BeforeEach
    void setup() {
        cart = new Cart();
        cart.add(new Item("Pepsi", 1.99));
        cart.add(new Item("Coke", 1.99));
    }

    @Test
    void itemAddedTest() {
        assertTrue(cart.contains(new Item("Coke", 1.99)));
    }

    @Test
    void skipsDuplicate() {
        assertFalse(cart.add(new Item("Coke", 1.99)));
    }

    @Test
    void removedItemTest() {
        cart.remove("Coke");
        assertFalse(cart.contains(new Item("Coke", 1.99)));
    }

    @Test
    void subtotalIsValid() {
        assertEquals(3.98, cart.getSubtotal());
    }

    @Test
    void taxIsValid() {
        assertEquals(3.9, cart.getTax(30));
    }

    @Test
    void totalIsValid() {
        assertEquals(96.67, cart.getTotal(85.55, 11.12));
    }

    @Test
    void invalidRemoveState() {

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            cart.clear();
            cart.remove("Coke");
        });

    }

    @Test
    void invalidCheckoutState() {

        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
            cart.clear();
            cart.checkout();
        });
    }
}