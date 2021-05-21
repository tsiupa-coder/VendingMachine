package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReadTest {

    private VendingMachine vendingMachine;
    private Read read;

    @BeforeEach
    void setUp() {
        vendingMachine = new VendingMachine();
        read = new Read(vendingMachine);
    }

    @Test
    void read() {
    }

    @Test
    void checkParameters() {
        assertEquals(true, read.checkParameters(4, 4));
        assertEquals(false, read.checkParameters(12, 4));
        assertEquals(false, read.checkParameters(3, 8));
        assertEquals(false, read.checkParameters(0, 1));
    }

    @Test
    void isDateValid() {
        assertEquals(true, read.isDateValid("yyyy-MM-dd", "2020-04-21"));
        assertEquals(true, read.isDateValid("yyyy-MM", "2020-04"));
        assertEquals(false, read.isDateValid("yyyy-MM-dd", "2020-04"));
        assertEquals(false, read.isDateValid("yyyy-MM", "2020-04-21"));
    }

    @Test
    void splitComand() {
        String command = "addCategory \"milk\" 12.50 2";
        List<String> strings = Arrays.asList("addCategory", "milk", "12.50", "2");
        List<String> strings1;
        strings1 = read.splitComand(command);

        assertEquals(strings.size(), strings1.size());
        for (int i = 0; i < strings.size(); i++)
            assertEquals(strings.get(i), strings1.get(i));


    }
}