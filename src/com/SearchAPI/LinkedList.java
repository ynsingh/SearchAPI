package com.SearchAPI;

import java.io.*;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * This Class is responsible for contruction and management
 * operations on Query Broadcast Table
 */

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
        String data9;
        Node next;

        // Constructor
        Node(String d1, String d2, String d3, String d4, String d5, String d6, int d7, String d8, String d9) {
            data1 = d1;
            data2 = d2;
            data3 = d3;
            data4 = d4;
            data5 = d5;
            data6 = d6;
            data7 = d7;
            data8 = d8;
            data9 = d9;
            next = null;
        }
    }

    /** Method to insert a new node in Broadcast Query Table
     * returns a Linked List Object
     */
    public LinkedList insert(LinkedList list, String data1, String data2, String data3, String data4,
                                    String data5, String data6, int data7, String data8, String data9) {
        // Create a new node with given data
        //String data6 = TimeStamp.currentTime();
        Node new_node = new Node(data1, data2, data3, data4, data5, data6, data7, data8, data9);
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


    /** Method to print the LinkedList */

    public void printList(LinkedList list) {
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
            System.out.print(currNode.data9 + " ");

            System.out.println("\n");

            // Go to next node
            currNode = currNode.next;
        }
        System.out.println("\n");
    }

    /** Method to delete a node in the LinkedList by KEY */

    public LinkedList deleteByKey(LinkedList list, String key) {
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


    /** Method to insert a nodes from saved file to Broadcast Query Table initially */

    public LinkedList oldInsert(LinkedList list, String data1, String data2, String data3, String data4,
                                       String data5, String data6, int data7, String data8, String data9) {
        // Create a new node with given data
        Node new_node = new Node(data1, data2, data3, data4, data5, data6, data7, data8, data9);
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

    /** Method to Load initial values all to Broadcast Query Table on Reboot */

    public void loadList(LinkedList listname) {
        var line = "";
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(SearchConstants.BroadcastQueryList));

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
                String d9 = arrSplit[8];
                oldInsert(listname, d1, d2, d3, d4, d5, d6, d7, d8, d9);
            }
        }
        catch (IOException e)
        {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    /** Method to save entries of Query Broadcast Table to a File */

    public void saveList(LinkedList listname) {
        try {
            File file = new File(SearchConstants.BroadcastQueryList);
            PrintWriter texttosave = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            Node currNode = listname.head;
            while (currNode != null) {
                texttosave.println(currNode.data1 + ", " + currNode.data2 + ", " + currNode.data3
                       + ", " + currNode.data4 + ", " + currNode.data5 + ", " + currNode.data6 +
                        ", " + currNode.data7 + ", " + currNode.data8 + ", " + currNode.data9);
                currNode = currNode.next;
            }
            texttosave.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    /** Method to search any sequencenumber in list */

    public Boolean searchinList(LinkedList listname, String i) {
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

    /** Method search any sequencenumber in list and return sourceNodeID */

    public String[] getSourceIDfromList(LinkedList listname, String i) {

        String[] a =new String[2];
        a[0] = "0";  a[1] = "0";
        if (listname.head != null) {
            //int index = 1;
            Node temp = listname.head;
            while (temp != null) {
                if (temp.data1.equals(i)) {
                    a[0] = temp.data3;
                    a[1] = temp.data9;
                    break;
                }
                temp = temp.next;
            }
        }
        return a ;
    }

    /** Method to Check a Query for Duplication before inserting in List */

    public boolean checkDuplicateQuery(LinkedList listname, String i) {

        boolean duplicate = false;

        if (listname.head == null) {
            return false;
        }
        else {
            Node temp = listname.head;
            while (temp != null) {
                // Returns the index of that particular sequence if found.
                if (temp.data1.equals(i)) {
                    duplicate = true;
                    break;
                }
                temp = temp.next;
            }
            return duplicate;
        }
    }


    /**  Method to Delete old entries in List */

    public void deleteByTimestamps(LinkedList listname) {

        if (listname.head == null) {

        }
        else {
            try {
                Node temp = listname.head;

                while (temp != null) {

                    String[] arr = temp.data8.split("\\.");
                    int entry_month = Integer.parseInt(arr[0]);
                    int entry_day = Integer.parseInt(arr[1]);
                    int entry_hour = Integer.parseInt(arr[2]);
                  //  int entry_minute = Integer.parseInt(arr[3]);

                    //String[] arr1 = TimeStamp.currentTime().split("\\.");
                    String[] arr1 = currentTime().split("\\.");
                    int current_month = Integer.parseInt(arr1[0]);
                    int current_day = Integer.parseInt(arr1[1]);
                    int current_hour = Integer.parseInt(arr1[2]);
                 //   int current_minute = Integer.parseInt(arr1[3]);


                   if((entry_month != current_month) || (entry_day != current_day)) {
                       LinkedList l = new LinkedList();
                       l.deleteByKey(LinkedList.list, temp.data1);
                       l.saveList(LinkedList.list);
                    }
                    else if((entry_hour != current_hour) && (entry_hour != current_hour-1)){
                       LinkedList l = new LinkedList();
                       l.deleteByKey(LinkedList.list, temp.data1);
                       l.saveList(LinkedList.list);
                    }

                    temp = temp.next;
                }
            }
         catch (Exception e) {
            System.err.println(e.getMessage());
             }
        }
    }

    /**  Method to find Current System Time for use in List */
    
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.HH.mm.ss.SSS");
    public String currentTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return sdf.format(timestamp);
    }
}
