package service;

import model.Snack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineTest {

    private VendingMachine vendingMachine;

    @BeforeEach
    void setUp() {
        vendingMachine = new VendingMachine();
    }

    @Test
    void addCategorias() {
        vendingMachine.addCategorias("snack", 12.67, 12);
        vendingMachine.addCategorias("snack1", 24.45, 4);
        vendingMachine.addCategorias("snack2", 45.53, 13);

        HashMap<String, Snack> snacks = vendingMachine.getCategories();

        assertEquals("snack", snacks.get("snack").getName());
        assertEquals(12.67, snacks.get("snack").getPrice());
        assertEquals(12, snacks.get("snack").getAmount());

        assertEquals("snack1", snacks.get("snack1").getName());
        assertEquals(24.45, snacks.get("snack1").getPrice());
        assertEquals(4, snacks.get("snack1").getAmount());

        assertEquals("snack2", snacks.get("snack2").getName());
        assertEquals(45.53, snacks.get("snack2").getPrice());
        assertEquals(13, snacks.get("snack2").getAmount());
    }

    @Test
    void addItem() {
        vendingMachine.addCategorias("snack", 12.67, 12);
        vendingMachine.addCategorias("snack1", 24.45, 4);
        vendingMachine.addCategorias("snack2", 45.53, 13);

        vendingMachine.addItem("snack", 12);
        vendingMachine.addItem("snack1", 4);
        vendingMachine.addItem("snack2", 13);

        HashMap<String, Snack> snacks = vendingMachine.getCategories();

        assertEquals(24, snacks.get("snack").getAmount());
        assertEquals(8, snacks.get("snack1").getAmount());
        assertEquals(26, snacks.get("snack2").getAmount());

    }

    @Test
    void purchase() {

        vendingMachine.addCategorias("snack", 12.67, 12);
        vendingMachine.addCategorias("snack1", 24.45, 4);
        vendingMachine.addCategorias("snack2", 45.53, 13);

        vendingMachine.purchase("snack", "2020-04-12");
        vendingMachine.purchase("snack", "2020-04-12");
        vendingMachine.purchase("snack", "2020-04-12");

        vendingMachine.purchase("snack1", "2020-04-12");
        vendingMachine.purchase("snack1", "2020-04-12");

        vendingMachine.purchase("snack2", "2020-04-12");

        HashMap<String, Snack> snacks = vendingMachine.getCategories();

        assertEquals(9, snacks.get("snack").getAmount());
        assertEquals(2, snacks.get("snack1").getAmount());
        assertEquals(12, snacks.get("snack2").getAmount());

        vendingMachine.purchase("snack1", "2020-04-12");
        vendingMachine.purchase("snack1", "2020-04-12");
        vendingMachine.purchase("snack1", "2020-04-12");
        vendingMachine.purchase("snack1", "2020-04-12");

        assertEquals(0, snacks.get("snack1").getAmount());
    }

    @Test
    void list() {

        vendingMachine.addCategorias("snack", 12.67, 12);
        vendingMachine.addCategorias("snack1", 24.45, 4);
        vendingMachine.addCategorias("snack2", 45.53, 13);
        System.out.println("---------");
        vendingMachine.list();
        System.out.println("---------");
        vendingMachine.addCategorias("snack", 12.67, 12);
        vendingMachine.addCategorias("snack1", 24.45, 4);
        vendingMachine.addCategorias("snack2", 45.53, 13);
        System.out.println("---------");
        vendingMachine.list();

    }

    @Test
    void clear() {
        vendingMachine.addCategorias("snack", 24.45, 4);
        vendingMachine.purchase("snack", "2020-04-12");
        vendingMachine.purchase("snack", "2020-04-12");
        vendingMachine.purchase("snack", "2020-04-12");
        vendingMachine.purchase("snack", "2020-04-12");

        vendingMachine.clear();

        HashMap<String, Snack> snacks = vendingMachine.getCategories();

        assertEquals(false, snacks.get("snack").getisAvailable());

        vendingMachine.addItem("snack", 4);

        snacks = vendingMachine.getCategories();

        assertEquals(true, snacks.get("snack").getisAvailable());
    }

    @Test
    void reportMonth() {
        vendingMachine.addCategorias("snack", 12.67, 12);
        vendingMachine.addCategorias("snack1", 24.45, 4);
        vendingMachine.addCategorias("snack2", 45.53, 13);

        vendingMachine.purchase("snack", "2020-04-12");
        vendingMachine.purchase("snack1", "2020-04-14");
        vendingMachine.purchase("snack2", "2020-05-13");

        vendingMachine.reportMonth("2020-04");
        System.out.println("============");
        vendingMachine.reportMonth("2020-05");
        System.out.println("============");
        vendingMachine.reportMonth("2020-06");


    }

    @Test
    void report() {
        vendingMachine.addCategorias("snack", 12.67, 12);
        vendingMachine.addCategorias("snack1", 24.45, 4);
        vendingMachine.addCategorias("snack2", 45.53, 13);

        vendingMachine.purchase("snack", "2020-04-12");
        vendingMachine.purchase("snack1", "2020-05-14");
        vendingMachine.purchase("snack2", "2020-06-13");

        vendingMachine.report("2020-04-13");
        System.out.println("============");
        vendingMachine.reportMonth("2020-05-15");
        System.out.println("============");
        vendingMachine.reportMonth("2020-06-16");
    }
}