package com.SearchAPI;

import java.io.*;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// Java program to implement a Singly Linked List
public class LinkedList {

    Node head; // head of list

    // Linked list Node. Node is a static nested class so main() can access it
    static class Node {

        String data1;
        String data2;
        String data3;
        String data4;
        int data5;
        String data6;
        Node next;

        // Constructor
        Node(String d1, String d2, String d3, String d4, int d5, String d6) {
            data1 = d1;
            data2 = d2;
            data3 = d3;
            data4 = d4;
            data5 = d5;
            data6 = d6;
            next = null;
        }
    }

    // **************INSERTION**************

    // Method to insert a new node in Broadcast Query Table
    public static LinkedList insert(LinkedList list, String data1, String data2, String data3, String data4, int data5) {
        // Create a new node with given data
        String data6 = TimeStamp.currentTime();
        Node new_node = new Node(data1, data2, data3, data4, data5, data6);
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


    // Method to insert all nodes from saved file to Broadcast Query Table
    public static LinkedList oldInsert(LinkedList list, String data1, String data2, String data3, String data4, int data5, String data6) {
        // Create a new node with given data
        Node new_node = new Node(data1, data2, data3, data4, data5, data6);
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

    public static void loadList(LinkedList listname) {
        try {
            String filePath = "/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/listData.txt";

            String str = readLineByLine(filePath);
            String[] arrSplit = str.split(":");
            int i = 0;
            while (i < arrSplit.length) {
                //int d1 = Integer.parseInt(arrSplit[i]);
                String d1 = arrSplit[i];
                String d2 = arrSplit[i + 1];
                String d3 = arrSplit[i + 2];
                String d4 = arrSplit[i + 3];
                // int d3 = Integer.parseInt(arrSplit[i + 2]);
                //int d4 = Integer.parseInt(arrSplit[i + 3]);
                int d5 = Integer.parseInt(arrSplit[i + 4]);
                String d6 = arrSplit[i+5];
               // listname = oldInsert(listname, d1, d2, d3, d4, d5, d6);
                oldInsert(listname, d1, d2, d3, d4, d5, d6);
                i = i + 6;
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }


    public static void saveList(LinkedList listname) {
        try {
            File file = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/listData.txt");
            PrintWriter texttosave = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            Node currNode = listname.head;
            while (currNode != null) {
                //texttosave.println(currNode.data1 + " " + currNode.data2 + " " + currNode.data3
                //       + " " + currNode.data4 + " " + currNode.data5 + " " + currNode.data6);
                texttosave.println(currNode.data1 + ":" + currNode.data2 + ":" + currNode.data3
                                + ":" + currNode.data4 + ":" + currNode.data5 + ":" + currNode.data6);
                currNode = currNode.next;
            }
            texttosave.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    //search any sequencenumber in list
    public static int searchinList(LinkedList listname, String i) {
        if (listname.head == null) {
            return -1;
        }

        int index = 1;
        Node temp = listname.head;

        // While loop is used to search the entire Linked
        // List starting from the tail
        while (temp != null) {

            // Returns the index of that particular element,
            // if found.
            if (temp.data1.equals(i)) {
                return index;
            }

            // Gradually increases index while
            // traversing through the Linked List
            index++;
            temp = temp.next;
        }

        // Returns -1 if the element is not found
        return -1;
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



    /*public static void main(String[] args) {

        LinkedList list = new LinkedList();
        loadList(list);
        saveList(list);
        printList(list);
        System.out.println(searchinList(list, 97));

    }*/
}
