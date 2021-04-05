package com.SearchAPI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class QueryManager {

    public static void main(String[] args)throws Exception{

        //Loading Broadcast Query Table from saved File
        LinkedList list = new LinkedList();
        LinkedList.loadList(list);

        //Broadcast Query Table Operations
        //ForwardQuery.forwardQuery(list,49, "Rajeshwari", 42, 44, 50, "03.12.11.36.26.206");

        //Saving Broadcast Query Table to File
       // LinkedList.saveList(list);

        //Print Broadcast Query Table
        LinkedList.printList(list);

        //ask NodeID and put in createfile

        //Create new Query to be Broadcasted
        //createFile.createQueryFile("211234", "Meenakshi", "12", "13", 14, "03.12.11.36.26.206");

        // Read a Query File and get elements in ArrayList & Search the QueryString
      /*  ArrayList<Object> readfile_elements =  readFile.readIncomingFile("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/file2.xml");
        for (int i = 0; i < readfile_elements.size();i++) {
            System.out.println(readfile_elements.get(i));
        }
        // Seach QueryString found in Query File
        SearchMethod.dosearch(String.valueOf(readfile_elements.get(1)));*/

        // Read a Result File and display result FileAddress
        /*ArrayList<Object> readfile_elements =  readFile.readIncomingFile("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/file111.xml");
        for (int i = 0; i < readfile_elements.size();i++) {
            System.out.println(readfile_elements.get(i));
        }*/

    }
}

