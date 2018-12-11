/**
 * COMP 1020
 *
 * INSTRUCTOR    Dr. Heather Matheson
 * ASSIGNMENT    Assignment #3, question #1
 * @author       Jabo Conrad Nzitatira, 7843614
 * @version      2018-10-29
 *
 * PURPOSE: A classic application program is the shopping list: a way of keeping track of which groceries or other items
 * need to be bought, and which of them have already been purchased.
 */
import java.util.*;
import java.io.*;

public class A3Q1Conrad7843614 {
    public static void main(String[] args) {
        ArrayList<Item> shoppingList = new ArrayList<>();
        ArrayList<Item> purchaseList = new ArrayList<>();
        FileProcessor("a3a.txt",shoppingList,purchaseList);

        System.out.println("\nEnd of Processing.");

    }


    public static void FileProcessor(String fileName,ArrayList<Item> shoppingList, ArrayList<Item> purchaseList){
        /** PURPOSE: This method will take the file name and read the file assigned and make appropriate changes to the
         *  ArrayList<Item> shoppingList, ArrayList<Item> purchaseListbased on key words "add" and "buy".
         *
         *  ArrayList<Item> purchaseList- the arraylist of contains Item class objects that have been shopped.
         *  ArrayList<Item> shoppinglist- the arraylist of contains Item class objects that have to be shopped.
         *  String[] tokens - The array of the tokens generated after tokenization based on ",".
         *  String inputLine - This a single line in the file.
         *  String itemName - This string which contains the name of the item.
         *  int itemQuantity - This is the number of items required or purchased.
         **/
        BufferedReader fileIn;
        String inputLine;
        String[] tokens;
        String itemName;
        int itemQuantity;
        try {
            fileIn = new BufferedReader(new FileReader(fileName));
            inputLine = fileIn.readLine();
            while(inputLine != null) {
                if (inputLine.compareTo("list") == 0) {
                    printLists(shoppingList, "Shopping List",purchaseList,"Purchase List");
                }
                tokens = inputLine.split(",");
                if (tokens.length > 1) {
                    itemName = tokens[2];
                    itemQuantity = Integer.parseInt(tokens[1]);
                    shoppingList = addHandler(tokens, shoppingList);
                    purchaseList = buyHandler(tokens,shoppingList,itemName,itemQuantity,purchaseList);
                }
                inputLine = fileIn.readLine();
            }
        }
        catch (IOException ioe){
            System.out.println("Error found Sir at: " + ioe.getMessage());
        }
    }

    public static ArrayList<Item> addHandler(String[] tokens, ArrayList<Item>shoppingList){
        /** PURPOSE: This method will take the array of string tokens look for the keyword "and" make appropriate changes to the
         *  ArrayList<Item> shoppingList.
         *
         *  String[] tokens - The array of the tokens generated after tokenization based on ",".
         *  ArrayList<Item> shoppinglist- the arraylist of contains Item class objects that have to be shopped.
         * */
        String itemName = tokens[2];
        int itemQuantity = Integer.parseInt(tokens[1]);
        Item newItem = new Item(itemName, itemQuantity);
        if (tokens[0].compareTo("add") == 0) {
            int pos = findItem(shoppingList, itemName);
            if (pos != -1) {
                if (shoppingList.get(pos).getQuantity() > 0) {
                    int  quantity = shoppingList.get(pos).getQuantity() + itemQuantity;
                    shoppingList.get(pos).setQuantity(quantity);
                }
            }
            shoppingList.add(newItem);
        }
        return shoppingList;
    }

    public static ArrayList<Item> buyHandler(String[] tokens,ArrayList<Item> shoppingList,String itemName, int itemQuantity,ArrayList<Item> purchaseList){
        /** PURPOSE: This method will take the array of string tokens look for the keyword "and" make appropriate changes
         *  to the ArrayList<Item> purchaseList.
         *
         *  String[] tokens - The array of the tokens generated after tokenization based on ",".
         *  ArrayList<Item> shoppinglist- the arraylist of contains Item class objects that have to be shopped.
         *  String itemName - This string which contains the name of the item.
         *  int itemQuantity - This is the number of items required or purchased.
         * ArrayList<Item> purchaseList- the arraylist of contains Item class objects that have been shopped.
         * */
        if (tokens[0].compareTo("buy") == 0) {
            int  pos = findItem(shoppingList, itemName);
            if (pos != -1) {
                if (shoppingList.get(pos).getQuantity() > 0) {
                    int   quantity = shoppingList.get(pos).getQuantity() - itemQuantity;
                    if (quantity > 0) {
                        shoppingList.get(pos).setQuantity(quantity);
                    } else {
                        shoppingList.remove(pos);
                    }
                }
            }
            pos = findItem(purchaseList, itemName);
            if (pos != -1) {
                if (purchaseList.get(pos).getQuantity() > 0) {
                    int quantity = purchaseList.get(pos).getQuantity() + itemQuantity;
                    purchaseList.get(pos).setQuantity(quantity);
                }
            }
            else {
                purchaseList.add(new Item(itemName, itemQuantity));
            }
        }
        return purchaseList;
    }

    public static void printLists(ArrayList<Item> list,String listName,ArrayList<Item> list2,String listName2) {
        /** PURPOSE: This method will take both the 2 arraylists and  their names print them out.
         *
         * ArrayList<Item> list - 1st arraylist to be printed.
         * String listName- name of the 1st arraylist.
         * ArrayList<Item> list2 - 1st arraylist to be printed.
         * String listName2 - name of the 1st arraylist.
         * */
        if(!list.isEmpty()){
            System.out.println("\n=====================\n" + listName + ": \n");
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    System.out.println(i + 1 + ". " + list.get(i).toString());
                }
            }
            System.out.println( "\n"+ listName2 + ": \n");
            for (int i = 0; i < list2.size(); i++) {
                if (list2.get(i) != null) {
                    System.out.println(i + 1 + ". " + list2.get(i).toString());
                }
            }
        }
    }

    public static int findItem(ArrayList<Item> list, String itemName){
        /** PURPOSE: This method will take an arraylist  you are trying to add and try to check if there is any similar
         * itemName and return their position or -1 if not found.
         *
         * ArrayList<Item> list - the arraylist of contains objects of the Item class objects.
         * String itemName - name of item your looking for.
         * int found - returns the position in the events array where the similar event is found or -1 if not found
         */
        int found = -1;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().compareTo(itemName) ==0){
                found = i;
            }
        }
        return found;
    }
}

class Item{
    //Instance Variables
    private String name;
    private int quantity;

    public Item(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }//Constructor

    public String getName() {
        return name;
    }//getName Method

    public int getQuantity() {
        return quantity;
    }//getQuantity Method

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }//setQuantity Method

    public String toString(){
        return "Item Name: " + name + " Quantity: " + quantity;
    }//toString Method

}