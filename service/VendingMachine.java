package service;

import model.Snack;
import java.util.HashMap;

public class VendingMachine {

    private HashMap<String, Snack> categories;

    // Constructor
    public VendingMachine() {

        categories = new HashMap<>();

    }

    public HashMap<String, Snack> getCategories() {
        return categories;
    }

    /**
     *  Create Snack from parameters
     *  and add to HashMap
     *  and print Snack
     */
    void addCategorias(String name, double price, int amount) {

        if (categories.containsKey(name)) {
            System.out.println("The category \"" + name + "\" " + "is already exist");
        } else {
            categories.put(name, new Snack(name, price, amount));
        }
        System.out.println(categories.get(name));

    }

    /**
     *  Find Snack in HashMap
     *  and increases items amount
     */
    void addItem(String snack, int amount) {

        if (categories.containsKey(snack)) {
            if(amount > 1 && !categories.get(snack).getisAvailable()){
                categories.get(snack).setAvailable(true);
            }
            categories.get(snack).add(amount);
        } else {
            System.out.println("The category \"" + snack + "\" " + "is not exist  :(");
        }
        System.out.println(categories.get(snack));

    }

    /**
     *  Find Snack in HashMap
     *  and reduces by one items amount
     */
    void purchase(String snack, String date) {

        if (categories.containsKey(snack)) {
            if (categories.get(snack).getAmount() <= 0) {
                System.out.println("There is no more " + categories.get(snack) + " :( ");
                return;
            }
            categories.get(snack).purchase();
            categories.get(snack).addOperation(date);
        } else {
            System.out.println("The category \"" + snack + "\" " + "is not exist  :(");
        }
    }

    /**
     * Print available Snacks
     */
    void list() {
        categories.forEach(
                (k, v) -> {
                    if (v.getisAvailable()) {
                        System.out.println(v);
                    }
                }
        );
    }

    /**
     *
     * Makes Snacks not available
     */
    void clear() {
        categories.forEach(
                (k, v) -> {
                    if (v.getAmount() == 0) {
                        v.setAvailable(false);
                    }
                }
        );
    }

    /**
     * Print report For mouth
     */
    void reportMonth(String date) {

        double total = 0;
        int sold;
        double price;
        String name;
        for (String key : categories.keySet()) {
            sold = categories.get(key).soldOut(date);
            price = categories.get(key).getPrice();
            name = categories.get(key).getName();
            System.out.println(name + " "
                    + sold + " "
                    +(sold * price));
            total += (sold * price);
        }
        System.out.println("------------------");
        System.out.println("total: " + total);

    }

    /**
     * Print report that happened earlier
     */
    void report(String date) {
        double total = 0;
        int sold;
        double price;
        String name;
        for (String key : categories.keySet()) {
            sold = categories.get(key).soldOutEarly(date);
            price = categories.get(key).getPrice();
            name = categories.get(key).getName();
            System.out.println(name + " "
                    + sold + " "
                    +(sold * price));
            total += (sold * price);

        }
        System.out.println("------------------");
        System.out.println("total: " + total);

    }
}