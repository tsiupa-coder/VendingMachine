package com.company;

import service.Read;
import service.VendingMachine;

public class Main {

    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine();
        Read read = new Read(vendingMachine);
        read.read();
    }

}
