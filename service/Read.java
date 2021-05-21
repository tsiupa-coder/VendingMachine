package service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Read {

    private VendingMachine vendingMachine;
    private String wrongSettings = "Wrong settings";

    public Read(VendingMachine vendingMachine) {

        this.vendingMachine = vendingMachine;
    }

    /**
     * reads commands
     * splits their
     * and chooses in switche
     * and try to validated
     */
    public void read() {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("/*********************/");
        System.out.println("SNACKS VENDING MACHINE");
        System.out.println("/*********************/");
        System.out.println("WAITING FOR COMMANDS");

        while (true) {
            command = scanner.nextLine();
            List<String> list = splitComand(command);

                switch (list.get(0)){
                    case "addCategory":
                        if(!(checkParameters(4, list.size())) || !(list.get(1) instanceof String)){
                            System.out.println("Wrong parameters");
                            break;
                        }
                        try {
                            vendingMachine.addCategorias(list.get(1),
                                    Double.parseDouble(list.get(2)),
                                    Integer.parseInt(list.get(3)));
                        }catch (NumberFormatException e){
                            System.out.println("Wrong parameters, perhaps use for price a dot instead of a comma");
                        }
                        break;

                    case "addItem":
                        if(!(checkParameters(3, list.size())) || !(list.get(1) instanceof String)){
                            System.out.println(wrongSettings);
                            break;
                        }
                        try {
                            vendingMachine.addItem(list.get(1),
                                                Integer.parseInt(list.get(2)));
                        }
                        catch (NumberFormatException e){
                            System.out.println("Wrong parameters, perhaps use correct Integer");
                        }
                        break;

                    case "purchase":
                        if(!(checkParameters(3, list.size())) ||
                                !(list.get(1) instanceof String) ||
                                !(isDateValid("yyyy-MM-dd", list.get(2)))) {

                            System.out.println("Wrong settings, can't parse date");
                            break;
                        }
                        vendingMachine.purchase(list.get(1), list.get(2));
                        break;

                    case "list":
                        vendingMachine.list();
                        break;

                    case "clear":
                        vendingMachine.clear();
                        break;

                    case "report":
                        if(!(checkParameters(2, list.size()))) {
                            System.out.println(wrongSettings);
                            break;
                        }
                        if(isDateValid("yyyy-MM-dd", list.get(1))){
                            vendingMachine.report(list.get(1));
                        }else if(isDateValid("yyyy-MM", list.get(1))) {
                            vendingMachine.reportMonth(list.get(1));
                        }else {
                            System.out.println(wrongSettings);
                        }
                        break;

                    default:
                        System.out.println("The command don't recognized");
                }
        }
}
    /**
     * checks if the size is the same
     */
    boolean checkParameters(int count, int listSize){

         if(listSize == count) {
             return true;
         }
        return false;
    }

    /**
     * checks if the date is correct
     */
    boolean isDateValid(String dateFormat, String date ){

        DateFormat sdf = new SimpleDateFormat(dateFormat);

        if(dateFormat.length() != date.length()) {
            return false;
        }
        try {
            sdf.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;

    }

    /**
     * Splits command on words
     * but saved words in " " as a one words
     */
    List<String> splitComand(String command){

        List<String> list = new ArrayList<>();

        if(command.contains("\"")){
            try {
                String[] strings = command.split("\"", 2);

                strings[0] = strings[0].replaceAll("\\s+", "");
                list.add(strings[0]);

                String[] strings1 = strings[1].split("\"");
                list.add(strings1[0]);

                strings1[1] = strings1[1].substring(1);
                strings = strings1[1].split(" ");

                list.addAll(Arrays.asList(strings));

                return list;

            }catch (IndexOutOfBoundsException e){
                System.out.println("Опс :( ");
            }
        }
        return Arrays.asList(command.split(" "));
    }
}
