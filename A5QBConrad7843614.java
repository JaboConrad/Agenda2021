/**
 * COMP 1020
 *
 * INSTRUCTOR    Dr. Heather Matheson
 * ASSIGNMENT    Assignment #5, question #B
 * @author       Jabo Conrad Nzitatira, 7843614
 * @version      2018-12-06
 *
 * PURPOSE: Java program that program that will be used to keep track of orders placed at a donut shop using linked lists.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class A5QBConrad7843614 {
    public static void main(String[] args) {
        OrderList test;
        test = FileReader("a4b.txt");
        test.print();
        System.out.println("End of Processing");
    }

    public static OrderList FileReader(String file){
        /** PURPOSE: This method will take the file name and read the file assigned and make appropriate additions to the
         * OrderList that contains the order of the customer.
         *
         *  OrderList command - the linked list that contains Order class objects that have been ordered.
         *  String[] tokens - The array of the tokens generated after tokenization based on ",".
         *  String line - This a single line in the file.
         *
         **/
        OrderList command  = new OrderList();
        BufferedReader fileIn;
        String line;
        String[] tokens;
        try{
            fileIn = new BufferedReader(new FileReader(file));
            line = fileIn.readLine();
            while(line != null){
                tokens = line.split(",");
                command.addToOrderList(tokens);
                line = fileIn.readLine();
            }
        }
        catch (IOException ioe){
            System.out.println("Error is on line: " + ioe.getMessage());
        }
        return command;
    }
}

class OrderList {
    private OrderNode head;
    private int cofnum = 0;
    private int donnum = 0;
    private int sandnum = 0;
    private int popnum = 0;
    private double grandtotal = 0.0;

    public OrderList() {
        head = null;
    }

    public void addToOrderList(String[] tokens) {
        /** PURPOSE: This method will take the tokens and make appropriate additions based on totalorder in an ascending
         * order.
         * String[] tokens - The array of the tokens generated after tokenization based on ",".
         *
         **/
        OrderNode current = head;
        OrderNode previous = null;

        if (tokens[0].compareTo("Coffee") == 0) {
            Coffee Order = new Coffee(Integer.parseInt(tokens[1]), tokens[2]);
            cofnum += Order.quantity;
            grandtotal += Order.totalOrder();
            while (current != null && current.order.comesBefore(Order)) {
                previous = current;
                current = current.next;
            }
            if (previous == null) {
                head = new OrderNode(Order, head);
            }
            else {
                previous.next = new OrderNode(Order, current);
            }
        }
        if (tokens[0].compareTo("Donut") == 0) {
            Donuts Order = new Donuts(Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]), tokens[3]);
            donnum+= Order.quantity;
            grandtotal += Order.totalOrder();
            while (current != null && current.order.comesBefore(Order)) {
                previous = current;
                current = current.next;
            }
            if (previous == null) {
                head = new OrderNode(Order, head);
            }
            else {
                previous.next = new OrderNode(Order, current);
            }
        }
        if (tokens[0].compareTo("Sandwich") == 0) {
            Sandwiches Order = new Sandwiches(Integer.parseInt(tokens[1]), Double.parseDouble(tokens[2]), tokens[3], tokens[4]);
            sandnum+= Order.quantity;
            grandtotal += Order.totalOrder();
            while (current != null && current.order.comesBefore(Order)) {
                previous = current;
                current = current.next;
            }
            if (previous == null) {
                head = new OrderNode(Order, head);
            }
            else {
                previous.next = new OrderNode(Order, current);
            }
        }
        if (tokens[0].compareTo("Pop") == 0) {
            Pop Order = new Pop(Integer.parseInt(tokens[1]), tokens[2], tokens[3]);
            popnum+= Order.quantity;
            grandtotal += Order.totalOrder();
            while (current != null && current.order.comesBefore(Order)) {
                previous = current;
                current = current.next;
            }
            if (previous == null) {
                head = new OrderNode(Order, head);
            }
            else {
                previous.next = new OrderNode(Order, current);
            }
        }
    }//addToOrderList


    public void print() {
        /** PURPOSE: This method prints the complete contents of the list (i.e. all the orders). Showing both the results
         *  of the toString(), the total price of that order, total quantity of donuts, coffees , sandwiches and pops in
         *  all the orders and the total of all the prices of all the orders.
         **/
        OrderNode current;
        current = head;
        while (current != null) {
            System.out.println(current.order.toString());
            current = current.next;
        }
        System.out.println("\n         ----------------GRAND TOTAL-------------------\nThe grand total of "+ cofnum+
                " cups of coffee, "+ donnum + " donuts, " + sandnum +" Sandwiches & " + popnum + " Pops is : $" + grandtotal +
                "\n                   --- enjoy your meal. ----\n\n");
    }//print
}


class OrderNode {
    //Instance Variables
    public Order order;
    public OrderNode next;

    //Constructor
    public OrderNode(Order order, OrderNode next) {
        this.order = order;
        this.next = next;
    }
}

abstract class Order{
    //Instance Variables
    protected int quantity;
    protected int totalprice;

    //Constructor
    public Order(int quantity){
        this.quantity = quantity;
    }

    public double totalOrder(){
        return totalprice;
    }//totalOrder

    public String toString() {
        return "string";
    }//toString

    public boolean comesBefore(Order newOrder){
        //Purpose: Used while sorting the linked list.
        return ( totalOrder() < newOrder.totalOrder());
    }//comesBefore
}

abstract class FoodClass extends Order{
    //Instance Variables
    protected double price;
    protected String flavour;

    //Constructor
    public FoodClass(int quantity, double price,String flavour){
        super(quantity);
        this.price = price;
        this.flavour = flavour;
    }

    public double totalOrder() {
        return super.totalOrder();
    }//totalOrder

    public String toString() {
        return "string";
    }///toString

}

abstract class DrinkClass extends Order{
    ///Instance Variables
    protected String size;

    //Constructor
    public DrinkClass(int quantity,String size){
        super(quantity);
        this.size = size;
    }

    public double totalOrder() {
        return super.totalOrder();
    }//totalOrder

    public String toString(){
        return "string";
    }//toString

}

class Coffee extends DrinkClass{
    //Constructor
    public Coffee(int quantity,String size){
        super(quantity,size);
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
    }//price

    public double totalOrder() {
        return price(size) * quantity;
    }

    public String toString() {
        return "You have ordered " + quantity+ " " + size + " cups of Coffee." +" Total: $"+ totalOrder();
    }//toString

}

class Pop extends DrinkClass{
    //Instance Variables
    private String brand;

    public Pop(int quantity,String size, String brand){
        super(quantity,size);
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

class Donuts extends FoodClass{
    //Constructor
    public Donuts(int quantity, double price,String flavour){
        super(quantity,price,flavour);
    }

    public double totalOrder() {
        // Purpose: Computes the total price of the order based on the quantity
        double totalprice = price * quantity;
        if(quantity >= 6){
            totalprice = totalprice +  totalprice * 7 / 100;
        }
        return totalprice;
    }//totalOrder

    public String toString() {
        return "You have ordered "+ quantity + " " + flavour + " Donuts" + " @ $" + price + ". Total: $" + totalOrder();
    }//toString
}

class Sandwiches extends FoodClass{
    //Instance Variables
    protected String bread;
    public Sandwiches(int quantity, double price,String filling,String bread){
        super(quantity,price,filling);
        this.bread = bread;
    }///constructor

    public double totalOrder() {
        // Purpose: Computes the total price of the order and adds tax
        double totalprice = price * quantity;
        totalprice = totalprice +  totalprice * 7 /100;
        return totalprice;
    }//totalOrder

    public String toString() {
        return "You have ordered "+ quantity + " " + "Sandwiches" + " with " + flavour +" filling @ $" + price + ". Total: $"
                + totalOrder();
    }//toString
}