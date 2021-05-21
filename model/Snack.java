package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Snack {

    private String name;
    private double price;
    private int amount;
    private boolean isAvailable;

    private List<Operation> operations;

    //Constructor
    public Snack(String name, double price, int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;
        isAvailable = true;
        operations = new ArrayList<>();
    }

    //getters
    public String getName() {

        return name;
    }

    public double getPrice() {

        return price;
    }

    public int getAmount() {

        return amount;
    }

    public boolean getisAvailable() {

        return isAvailable;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    //Setters
    public void setAvailable(boolean isAvailable) {

        this.isAvailable = isAvailable;
    }

    //add items
    public void add(int amount){

        this.amount += amount;
    }

    //purchase items
    public void purchase(){

        amount--;
    }

    // Inner class for containing items's date purchase
    class Operation {
        String date;
        Operation(String date){
            this.date = date;

        }
        String getDate(){
            return date;
        }
    }

    //Add data about item's date purchase
    public void addOperation(String date){

        operations.add(new Operation(date));
    }

    //return number of items purchased that happened for month
    public int soldOut(String date){
        int i = 0;
        for (Operation operation: operations) {
            if(operation.date.contains(date)){
                i++;
            }
        }
        return i;
    }

    //return number of items purchased that happened earlier
    public int soldOutEarly(String date){
        int i = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        for(Operation operation: operations){
            try {
                if(sd.parse(operation.getDate()).before(sdf.parse(date))){
                    i++;
                }
            } catch (ParseException e) {
                System.out.println("Oпс :( ");
            }
        }
        return i;
    }

    @Override
    public String toString() {

        return "\"" + name + "\" " + price + " " + amount;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, price, amount);
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == this) return true;
        if(!(obj instanceof Snack)) {
            return false;
        }
        Snack snack = (Snack) obj;
        return Objects.equals(name, snack.name)&&
                this.amount == snack.amount &&
                this.price == snack.price;
    }

}
