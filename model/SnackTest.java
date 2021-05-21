package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnackTest {

    private Snack snack;
    @BeforeEach
    void setUp() {
        snack = new Snack("snacks", 12.50, 10);

    }

    @Test
    void getName() {
        assertEquals("snacks", snack.getName());
    }

    @Test
    void getPrice() {
        assertEquals(12.50, snack.getPrice());
    }

    @Test
    void getAmount() {
        assertEquals(10 , snack.getAmount());
    }

    @Test
    void getisAvailable() {
        assertEquals(true, snack.getisAvailable());
    }

    @Test
    void setAvailable() {
        snack.setAvailable(false);
        assertEquals(false, snack.getisAvailable());
        snack.setAvailable(true);
    }

    @Test
    void add() {
        snack.add(5);
        assertEquals(15 , snack.getAmount());
    }

    @Test
    void purchase() {
        snack.purchase();
        assertEquals(9 , snack.getAmount());
    }

    @Test
    void addOperation() {
        snack.addOperation("2021-05-20");
        assertEquals("2021-05-20", snack.getOperations().get(0).getDate());
    }

    @Test
    void soldOut() {
        snack.addOperation("2021-05-20");
        snack.addOperation("2021-05-27");
        assertEquals(2, snack.soldOut("2021-05"));

    }

    @Test
    void soldOutEarly() {
        snack.addOperation("2021-05-20");
        snack.addOperation("2021-05-27");
        assertEquals(1, snack.soldOutEarly("2021-05-24"));
    }
}