package com.SearchAPI;

public class ForwardQuery {

    public static void forwardQuery(LinkedList listname, int data1, String data2, int data3,
                                    int data4, int data5) {
        if((LinkedList.checkDuplicateQuery(listname, data1, data2)==false))
        {
            System.out.println("Query is Duplicate");
            //do nothing now
        }
        else {
            System.out.println("Query is Unique");
            LinkedList.insert(listname, data1,data2, data3, data4, data5);
        }


        // Query is not duplicate and is to be added and put in Output Buffer
    }
}