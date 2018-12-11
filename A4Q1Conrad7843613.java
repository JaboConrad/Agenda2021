/**
 * COMP 1020
 *
 * INSTRUCTOR    Dr. Heather Matheson
 * ASSIGNMENT    Assignment #4, question #1
 * @author       Jabo Conrad Nzitatira, 7843614
 * @version      2018-11-21
 *
 * PURPOSE: Java program that program that will be used to keep track of orders placed at a donut shop.
 */



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class A4Q1Conrad7843613 {
    public static void main(String[] args) {
        ArrayList<Order> command ;
        command = FileProcessor("a4b.txt");
        System.out.println("Your unsorted Order:" + "\n");
        print(command);
        System.out.println("Your ascendingly sorted Order:" + "\n");
        sort(command);
        print(command);
        System.out.println("End of Processing");
    }

    public static  ArrayList<Order> FileProcessor(String fileName){
        /** PURPOSE: This method will take the file name and read the file assigned and make appropriate additions to the
         * ArrayList<Order> that contains the order of the customer.
         *
         *  ArrayList<Order> Order - the arraylist that contains Order class objects that have been ordered.
         *  ArrayList<Item> shoppinglist- the arraylist of contains Item class objects that have to be shopped.
         *  String[] tokens - The array of the tokens generated after tokenization based on ",".
         *  String line - This a single line in the file.
         *
         **/
        ArrayList<Order> order =  new ArrayList<Order>(100);
        BufferedReader fileIn;
        String line;
        String[] tokens;
        try{
            fileIn = new BufferedReader(new FileReader(fileName));
            line = fileIn.readLine();
            while(line != null){
                tokens = line.split(",");
                if(tokens[0].compareTo("Coffee")==0){
                    Coffee Order = new Coffee(Integer.parseInt(tokens[1]),tokens[2]);
                    order.add(Order);
                }
                if(tokens[0].compareTo("Donut")==0){
                    Donuts Order = new Donuts(Integer.parseInt(tokens[1]),Double.parseDouble(tokens[2]),tokens[3]);
                    order.add(Order);
                }
                if(tokens[0].compareTo("Sandwich")==0){
                    Sandwiches Order = new Sandwiches(Integer.parseInt(tokens[1]),Double.parseDouble(tokens[2]),tokens[3],tokens[4]);
                    order.add(Order);
                }
                if(tokens[0].compareTo("Pop")==0){
                    Pop Order = new Pop(Integer.parseInt(tokens[1]),tokens[2],tokens[3]);
                    order.add(Order);
                }
                line = fileIn.readLine();
            }
        }
        catch (IOException ioe){
            System.out.println("Sir error is on line: " + ioe.getMessage());
        }
        return order;
    }

    public static void sort(ArrayList<Order> list){
        /** PURPOSE: This method will take the ArrayList<Order> order and sort the items ordered using selection sort based
         * size of the price of the total order the smallest price coming first and the largest coming last.
         *
         *  ArrayList<Order> list - the arraylist that contains Order class objects that have been ordered.
         *  int count1- the first counter.
         *  int count2- the first counter.
         *  int largest - the largest in the list so far.
         *  Order temp - temporary Order object used to hold the last item in the order.
         *
         **/
        int count1;
        int count2;
        int largest;
        Order temp;
        for(count1 = 0; count1 <list.size()-1; count1++){
            largest = 0;
            for(count2 = 1; count2 < list.size()- count1;count2++){
                if(list.get(largest).totalOrder() < list.get(count2).totalOrder()){
                    largest = count2;
                }
            }
            temp = list.get(list.size() - 1 - count1);
            list.set(list.size()-1-count1,list.get(largest));
            list.set(largest,temp);
        }
    }


    public static void print(ArrayList<Order> list){
        /** PURPOSE: This method prints the complete contents of the list (i.e. all the orders). Showing both the results
         *  of the toString(), the total price of that order, total quantity of donuts, coffees , sandwiches and pops in
         *  all the orders and the total of all the prices of all the orders.
         *
         *  ArrayList<Order> list - the array list that contains Order class objects that have been ordered.
         *  int cofnum - the coffee counter.
         *  int donnum - the donut counter.
         *  int sandnum - the sandwich counter.
         *  int popnum - the pop counter.
         *  double grandtotal - the total price of the order.
         **/
        double grandtotal = 0.0;
        int cofnum = 0;
        int donnum = 0;
        int sandnum = 0;
        int popnum = 0;
        for (int i = 0; i < list.size() ; i++) {
            grandtotal += list.get(i).totalOrder();
            System.out.println(list.get(i).toString());
        }
        for (int i = 0; i < list.size() ; i++) {
            if(list.get(i).getClass().getTypeName().compareTo("Coffee") == 0){
                cofnum += list.get(i).getQuantity();
            }
            if(list.get(i).getClass().getTypeName().compareTo("Donuts") == 0){
                donnum += list.get(i).getQuantity();
            }
            if(list.get(i).getClass().getTypeName().compareToIgnoreCase("Sandwiches") == 0){
                sandnum += list.get(i).getQuantity();
            }
            else{
                popnum += list.get(i).getQuantity();
            }
        }
        System.out.println("\n         ----------------GRAND TOTAL-------------------\nThe grand total "+ cofnum+
                " cups of coffee, "+ donnum + " donuts, " + sandnum +" Sandwiches & " + popnum + " Pops is : $" + grandtotal +
                "\n                   --- enjoy your meal. ----\n\n");
    }
}

class Collection{
    //Instance Variables
    ArrayList<Order> list;

    public void addToCollection(String[] tokens){
        if(tokens[0].compareTo("Coffee")==0){
            Coffee Order = new Coffee(Integer.parseInt(tokens[1]),tokens[2]);
            list.add(Order);
        }
        if(tokens[0].compareTo("Donut")==0){
            Donuts Order = new Donuts(Integer.parseInt(tokens[1]),Double.parseDouble(tokens[2]),tokens[3]);
            list.add(Order);
        }
        if(tokens[0].compareTo("Sandwich")==0){
            Sandwiches Order = new Sandwiches(Integer.parseInt(tokens[1]),Double.parseDouble(tokens[2]),tokens[3],tokens[4]);
            list.add(Order);
        }
        if(tokens[0].compareTo("Pop")==0){
            Pop Order = new Pop(Integer.parseInt(tokens[1]),tokens[2],tokens[3]);
            list.add(Order);
        }
    }//addToCollection

    public void sort(){
        int count1;
        int count2;
        int largest;
        Order temp;
        for(count1 = 0; count1 <list.size()-1; count1++){
            largest = 0;
            for(count2 = 1; count2 < list.size()-count1;count2++){
                if(list.get(largest).totalOrder() <list.get(count2).totalOrder()){
                    largest = count2;
                }
            }
            temp = list.get(list.size() - 1 - count1);
            list.set(list.size()-1-count1,list.get(largest));
            list.set(largest,temp);
        }
    }//sort
}

abstract class Order{
    //Instance Variables
    public int quantity;
    private int totalprice;

    public Order(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double totalOrder(){
        return totalprice;
    }

    public String toString() {
        return "string";
    }
}

class Coffee extends Order{
    //Instance Variables
    private String size;

    public Coffee(int quantity,String size){
        super(quantity);
        this.size = size;
    }

    public double price(String size){
        double price = 0.0;
        if(size.compareTo("small") == 0){
            price = 1.39;
        }
        if(size.compareTo("medium") == 0){
            price = 1.69;
        }
        if(size.compareTo("large") == 0){
            price = 1.99;
        }
        return price;
    }

    public double totalOrder() {
        return price(size) * quantity;
    }

    public String toString() {
        return "You have ordered " + quantity+ " " + size + " cups of Coffee." +" Total: $"+ totalOrder();
    }
}

class Donuts extends Order{
    //Instance Variables
    private double price;
    private String flavour;

    public Donuts(int quantity, double price,String flavour){
        super(quantity);
        this.price = price;
        this.flavour = flavour;
    }

    public double totalOrder() {
        double totalprice = price * quantity;
        if(quantity >= 6){
            totalprice = totalprice +  totalprice * 7 / 100;
        }
        return totalprice;
    }

    public String toString() {
        return "You have ordered "+ quantity + " " + flavour + " Donuts" + " @ $" + price + ". Total: $" + totalOrder();
    }

}


class Sandwiches extends Order{
    //Instance Variables
    private double price;
    private String filling;
    private String bread;

    public Sandwiches(int quantity, double price,String filling,String bread){
        super(quantity);
        this.price = price;
        this.filling = filling;
        this.bread = bread;
    }///constructor

    public double totalOrder() {
        // Purpose: Computes the total price of the order and adds tax
        double totalprice = price * quantity;
        totalprice = totalprice +  totalprice * 7 /100;
        return totalprice;
    }//totalOrder

    public String toString() {
        return "You have ordered "+ quantity + " " + "Sandwiches" + " with " + filling +" filling @ $" + price + ". Total: $" + totalOrder();
    }//toString
}

class Pop extends Order{
    //Instance Variables
    private String size;
    private String brand;

    public Pop(int quantity,String size, String brand){
        super(quantity);
        this.size = size;
        this.brand = brand;
    }//constructor

    public double price(String size){
        double price = 0.0;
        if(size.compareTo("small") == 0){
            price = 1.79;
        }
        if(size.compareTo("medium") == 0){
            price = 2.09;
        }
        if(size.compareTo("large") == 0){
            price = 2.49;
        }
        return price;
    }//price

    public double totalOrder() {
        // Purpose: Computes the total price of the order based on the quantity
        double totalprice = price(size) * quantity;
        if(quantity >= 6){
            totalprice = totalprice +  totalprice * 7 /100;
        }
        return totalprice;
    }//totalOrder

    public String toString() {
        return "You have ordered " + quantity + " " + brand + " pops." +" Total: $"+ totalOrder();
    }//toString
}