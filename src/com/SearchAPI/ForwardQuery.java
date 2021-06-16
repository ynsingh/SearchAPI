package com.SearchAPI;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;



public class ForwardQuery {
    public static String sequence = "";
    public static String searchkey = "";
    public static String ipaddress = "";
    public static String nodeid = "";
    public static String portaddress = "";
    public static String transport = "";
    public static String neighbornodeid = "";


    public static void forwardOwnQuery(LinkedList listname, String data1, String data2, String data3,
                                        String data4, String data5, String data6, int data7, String data8, String data9)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {

        if(LinkedList.checkDuplicateQuery(listname, data1, data2))
        {
            System.out.println("Global Query received from Own Node, Query is Duplicate");
            //do nothing now
        }
        else {  // Query is not duplicate and is to be added in Broadcast Query Table

            System.out.println("Global Query received from Own Node, Query is Unique");
            LinkedList.insert(listname, data1, data2, data3, data4, data5, data6, data7, data8, data9);
            LinkedList.saveList(LinkedList.list);
            QueryManager.createQueryFile(data1, data2, data3, data4, data5, data6, data7, data8, data9);
           // createFile.createQueryFile(data1, data2, data3, data4, data5, data6, data7, data8);
            //Put Query-Seq file in output Buffer
            SearchMethod.findInCache(data2, data1, true);
            //find in cache till global response arrives
        }
    }



    public static void forwardPeerQuery(LinkedList listname, String data1, String data2, String data3,
                                        String data4, String data5, String data6, int data7, String data8, String data9)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {

        if(LinkedList.checkDuplicateQuery(listname, data1, data2))
        {
            System.out.println("Peer Query is Duplicate");
            //do nothing now
        }
        else {  // Query is not duplicate and is to be added in Broadcast Query Table

            sequence = data1;
            searchkey = data2;
            nodeid = data3;
            ipaddress = data4;
            portaddress = data5;
            transport = data6;
            neighbornodeid = data9;

            System.out.println("Peer Query is Unique");
            LinkedList.insert(listname, data1, data2, data3, data4, data5, data6, data7, data8, data9);

            LinkedList.saveList(LinkedList.list);
            //dosearch
            SearchMethod.search(data2);
            //forward response from cache
            SearchMethod.findInCache(data2, data1, false);

            if(data7>1) {// Query has TTL > 1 and is forwarded
                QueryManager.createQueryFile(data1, data2, data3, data4, data5, data6, data7 - 1, data8, SearchConstants.selfNodeID);
                //createFile.createQueryFile(data1, data2, data3, data4, data5, data6, data7 - 1, data8);
                //Put Query in Output Buffer
            }
            else System.out.println("TTL Expired");
        }

    }

    public static String[] elements(){
        String[] a = {sequence, nodeid, ipaddress, portaddress, transport, searchkey, neighbornodeid};
        return a;
    }

}
