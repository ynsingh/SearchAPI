package com.SearchAPI;

import java.io.*;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


// Java program to implement a Singly Linked List
public class LinkedList {

    public static LinkedList list = new LinkedList();

    Node head; // head of list

    // Linked list Node. Node is a static nested class so main() can access it
    static class Node {

        String data1;
        String data2;
        String data3;
        String data4;
        String data5;
        String data6;
        int data7;
        String data8;
        Node next;

        // Constructor
        Node(String d1, String d2, String d3, String d4, String d5, String d6, int d7, String d8) {
            data1 = d1;
            data2 = d2;
            data3 = d3;
            data4 = d4;
            data5 = d5;
            data6 = d6;
            data7 = d7;
            data8 = d8;
            next = null;
        }
    }

    // **************INSERTION**************

    // Method to insert a new node in Broadcast Query Table
    public static LinkedList insert(LinkedList list, String data1, String data2, String data3, String data4,
                                    String data5, String data6, int data7, String data8) {
        // Create a new node with given data
        //String data6 = TimeStamp.currentTime();
        Node new_node = new Node(data1, data2, data3, data4, data5, data6, data7, data8);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        // Return the list by head
        return list;
    }




    // **************TRAVERSAL**************

    // Method to print the LinkedList.
    public static void printList(LinkedList list) {
        Node currNode = list.head;

        System.out.print("\nBroadcast Query Table: \n");

        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data1 + " ");
            System.out.print(currNode.data2 + " ");
            System.out.print(currNode.data3 + " ");
            System.out.print(currNode.data4 + " ");
            System.out.print(currNode.data5 + " ");
            System.out.print(currNode.data6 + " ");
            System.out.print(currNode.data7 + " ");
            System.out.print(currNode.data8 + " ");

            System.out.println("\n");

            // Go to next node
            currNode = currNode.next;
        }
        System.out.println("\n");
    }

    // Method to print the LinkedList by key.
    public static void printKey(LinkedList listname, String key)
    {
        Node currNode = listname.head;
        System.out.print("\nLinkedList by Key: \n");

        while (currNode != null) {

            if(currNode.data1.equals(key))
            {
                System.out.print(currNode.data1 + " ");
                System.out.print(currNode.data2 + " ");
                System.out.print(currNode.data3 + " ");
                System.out.print(currNode.data4 + " ");
                System.out.print(currNode.data5 + " ");
                System.out.print(currNode.data6 + " ");
                System.out.print(currNode.data7 + " ");
                System.out.print(currNode.data8 + " ");
            }
            currNode = currNode.next;
        }
        System.out.println("\n");
    }

    // **************DELETION BY KEY**************

    // Method to delete a node in the LinkedList by KEY
    public static LinkedList deleteByKey(LinkedList list, String key) {
        // Store head node
        Node currNode = list.head, prev = null;

        // CASE 1:
        // If head node itself holds the key to be deleted

        if (currNode != null && currNode.data1.equals(key)) {
            list.head = currNode.next; // Changed head

            // Display the message
            System.out.println(key + " found and deleted");

            // Return the updated List
            return list;
        }
        // CASE 2:
        // If the key is somewhere other than at head

        // Search for the key to be deleted,
        // keep track of the previous node as it is needed to change currNode.next
        while (currNode != null && currNode.data1 != key) {
            // If currNode does not hold key
            // continue to next node
            prev = currNode;
            currNode = currNode.next;
        }

        // If the key was present, it should be at currNode
        // Therefore the currNode shall not be null
        if (currNode != null) {
            // Since the key is at currNode
            // Unlink currNode from linked list
            prev.next = currNode.next;

            // Display the message
            System.out.println(key + " found and deleted");
        }

        //
        // CASE 3: The key is not present
        //

        // If key was not present in linked list
        // currNode should be null
        if (currNode == null) {
            // Display the message
            System.out.println(key + " not found");
        }

        // return the List
        return list;
    }

    public static String readLineByLine(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append(" "));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contentBuilder.toString();
    }


    // Method to insert all nodes from saved file to Broadcast Query Table
    public static LinkedList oldInsert(LinkedList list, String data1, String data2, String data3, String data4,
                                       String data5, String data6, int data7, String data8) {
        // Create a new node with given data
        Node new_node = new Node(data1, data2, data3, data4, data5, data6, data7, data8);
        new_node.next = null;

        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        } else {
            // Else traverse till the last node
            // and insert the new_node there
            Node last = list.head;
            while (last.next != null) {
                last = last.next;
            }

            // Insert the new_node at last node
            last.next = new_node;
        }

        // Return the list by head
        return list;
    }

    public static void loadList(LinkedList listname) {

        String line = "";
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(
                    "/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/listData.csv"));

            //if((line = br.readLine()) == null){

            //}

            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] arrSplit = line.split(", ");    // use comma as separator
                String d1 = arrSplit[0];
                String d2 = arrSplit[1];
                String d3 = arrSplit[2];
                String d4 = arrSplit[3];
                String d5 = arrSplit[4];
                String d6 = arrSplit[5];
                int d7 = Integer.parseInt(arrSplit[6]);
                String d8 = arrSplit[7];
                oldInsert(listname, d1, d2, d3, d4, d5, d6, d7, d8);
            }
        }
        catch (IOException e)
        {
            System.out.println("error");
            e.printStackTrace();
        }

    }


    public static void saveList(LinkedList listname) {
        try {
            File file = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/listData.csv");
            PrintWriter texttosave = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            Node currNode = listname.head;
            while (currNode != null) {
                texttosave.println(currNode.data1 + ", " + currNode.data2 + ", " + currNode.data3
                       + ", " + currNode.data4 + ", " + currNode.data5 + ", " + currNode.data6 +
                        ", " + currNode.data7 + ", " + currNode.data8);
                //texttosave.println(currNode.data1 + ":" + currNode.data2 + ":" + currNode.data3
                //                + ":" + currNode.data4 + ":" + currNode.data5 + ":" + currNode.data6);
                currNode = currNode.next;
            }
            texttosave.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    //search any sequencenumber in list
    public static Boolean searchinList(LinkedList listname, String i) {
        boolean sequencepresent = false;
        if (listname.head == null) {
            return sequencepresent;
        }
        else {
            //int index = 1;
            Node temp = listname.head;

            while (temp != null) {

                if (temp.data1.equals(i)) {
                    sequencepresent = true;
                    break;
                }
                temp = temp.next;
            }
            return sequencepresent;
        }
    }

    public static boolean checkDuplicateQuery(LinkedList listname, String i, String s) {

        boolean duplicate = false;

        if (listname.head == null) {
            return false;
        }

        else {

            Node temp = listname.head;

            while (temp != null) {

                // Returns the index of that particular element if found.
                if (temp.data1.equals(i) && temp.data2.equals(s)) {
                    duplicate = true;
                    break;
                }
                temp = temp.next;
            }

            return duplicate;
        }
    }

    public static String getQueryString(LinkedList listname, String i) {
        String query = "";
        if (listname.head == null) {
            return query;
        }
        else {

            Node temp = listname.head;

            while (temp != null) {

                if (temp.data1.equals(i)) {
                    query = temp.data2;
                    break;
                }
                temp = temp.next;
            }
            return query;
        }
    }

    public static void deleteByTimestamps(LinkedList listname) {

        if (listname.head == null) {

        }
        else {
            try {
                Node temp = listname.head;

                while (temp != null) {

                    String[] arr = temp.data8.split("\\.");
                    int entry_month = Integer.valueOf(arr[0]);
                    int entry_day = Integer.valueOf(arr[1]);
                    int entry_hour = Integer.valueOf(arr[2]);
                    int entry_minute = Integer.valueOf(arr[3]);


                    String[] arr1 = TimeStamp.currentTime().split("\\.");
                    int current_month = Integer.valueOf(arr1[0]);
                    int current_day = Integer.valueOf(arr1[1]);
                    int current_hour = Integer.valueOf(arr1[2]);
                    int current_minute = Integer.valueOf(arr1[3]);


                   if((entry_month != current_month) || (entry_day != current_day)) {
                        LinkedList.deleteByKey(LinkedList.list, temp.data1);
                        LinkedList.saveList(LinkedList.list);
                    }
                    else if((entry_hour != current_hour) && (entry_hour != current_hour-1)){
                        LinkedList.deleteByKey(LinkedList.list, temp.data1);
                        LinkedList.saveList(LinkedList.list);
                    }

                    temp = temp.next;
                }
            }
         catch (Exception e) {
            System.err.println(e.getMessage());
        }
        }
    }







    /*public static void main(String[] args) {

        LinkedList list = new LinkedList();
        loadList(list);
        saveList(list);
        printList(list);
        System.out.println(searchinList(list, 97));

    }*/
}
