package com.SearchAPI;

import java.lang.reflect.InvocationTargetException;

public class ForwardQuery {
    public static String sequence = "";
    public static String querystring = "";


    public static void forwardOwnQuery(LinkedList listname, String data1, String data2, String data3,
                                        String data4, String data5, String data6, int data7, String data8)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        if(LinkedList.checkDuplicateQuery(listname, data1, data2))
        {
            System.out.println("Own Query is Duplicate");
            //do nothing now
        }
        else {  // Query is not duplicate and is to be added in Broadcast Query Table

            sequence = data1;
            querystring = data2;
            System.out.println("Own Query is Unique");
            LinkedList.insert(listname, data1, data2, data3, data4, data5, data6, data7, data8);
            LinkedList.saveList(LinkedList.list);

        }
    }



    public static void forwardPeerQuery(LinkedList listname, String data1, String data2, String data3,
                                        String data4, String data5, String data6, int data7, String data8)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        if(LinkedList.checkDuplicateQuery(listname, data1, data2))
        {
            System.out.println("Peer Query is Duplicate");
            //do nothing now
        }
        else {  // Query is not duplicate and is to be added in Broadcast Query Table

            sequence = data1;
            querystring = data2;
            System.out.println("Peer Query is Unique");
            LinkedList.insert(listname, data1, data2, data3, data4, data5, data6, data7, data8);
            LinkedList.saveList(LinkedList.list);
            //dosearch
            SearchMethod.dosearch(data2);
            if(data7>1) {// Query has TTL > 1 and is forwarded
                createFile.createQueryFile(data1, data2, data3, data4, data5, data6, data7 - 1, data8);
            }
            else System.out.println("TTL Expired");
        }

        /*int i = LinkedList.searchinList(listname, data1);
        if(i==-1)
        {
            // Query is not duplicate and is to be added in Broadcast Query Table
            System.out.println("Query is Unique");
            LinkedList.insert(listname, data1, data2, data3, data4, data5);
            if(data5>1) {// Query has TTL > 0 and is forwarded
                createFile.createQueryFile(data1, data2, data3, data4, data5 - 1, data6);
            }

        }
        else {
            System.out.println("Query is Duplicate");
            //do nothing now
        }*/

    }

    public static String elements(){
        String a = sequence;
        return a;
    }


}
        /* Search for a particular query in Broadcast Query Table
       int n = LinkedList.searchinList(list, 3);
        if(n==-1)
            System.out.println("Searched Key not Present in Data");
        else
            LinkedList.printKey(list, n);*/
