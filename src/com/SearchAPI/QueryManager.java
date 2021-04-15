package com.SearchAPI;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class QueryManager {


    //public static RoutingManagerBuffer routingManagerBuffer;
    public static Buffer Buffer;

    public static void main(String[] args)throws Exception{

        //Get own Node ID if change is there amend it


        //Loading Broadcast Query Table from saved File
        //LinkedList list = new LinkedList();
        LinkedList.loadList(LinkedList.list);

        //Broadcast Query Table Operations
        //ForwardQuery.forwardGlobalQuery(list,49, "Rajeshwar", 42, 44, 50, "03.12.11.36.26.206");



        //Print Broadcast Query Table
        //LinkedList.printList(list);

        //ask NodeID and put in createfile

        //Create new Query to be Broadcasted
        // createFile.createQueryFile("211234", "Meenakshi", "12", "13", 14, "03.12.11.36.26.206");

        // Read a Query File and get elements in ArrayList & Search the QueryString
        /*ArrayList<Object> readfile_elements =  readFile.readIncomingFile("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/Query211234.xml");
        for (int i = 0; i < readfile_elements.size();i++) {
            System.out.println(readfile_elements.get(i));
        }
        // Seach QueryString found in Query File
        SearchMethod.dosearch(String.valueOf(readfile_elements.get(1)));

         */

        // Read a Result File and display result FileAddress
        /*ArrayList<Object> readfile_elements =  readFile.readIncomingFile("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/file111.xml");
        for (int i = 0; i < readfile_elements.size();i++) {
            System.out.println(readfile_elements.get(i));
        }*/


        //while(true) {
        //    listFiles.listallFiles();
        // }

        // Read all file at Buffer_in Folder



        /*Buffer = Buffer.getInstance();

        File file = new File("/Users/rex/Desktop/Query-Ramesh.xml");
        //File file = new File("/Users/rex/Desktop/Response-211236-7e5a7fda92ad93469da9.xml");
        Buffer.addFileToInputBuffer(file);

        Buffer.getFileFromInputBuffer();

        //Compiled Result file added to output buffer at readfile comment or uncomment both
        //csvFileReader.read(String.valueOf(routingManagerBuffer.getFileFromOutputBuffer()));
         */

        //get self IDs
        ReadNeighborTable.getselfID();

    }
}

