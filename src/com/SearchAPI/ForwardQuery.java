package com.SearchAPI;

public class ForwardQuery {

    public static void forwardQuery(LinkedList listname, int data1, String data2, int data3,
                                    int data4, int data5, String data6) {

        if(LinkedList.checkDuplicateQuery(listname, data1, data2))
        {
            System.out.println("Query is Duplicate");
            //do nothing now
        }
        else {   // Query is not duplicate and is to be added and put in Output Buffer
            System.out.println("Query is Unique");
            LinkedList.insert(listname, data1, data2, data3, data4, data5);
            if(data5>1) {
                createFile.createQueryFile(data1, data2, data3, data4, data5 - 1, data6);
            }
        }

        /*int i = LinkedList.searchinList(listname, data1);
        if(i==-1)
        {
            // Query is not duplicate and is to be added and put in Broadcast Query Table
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
}
        /* Search for a particular query in Broadcast Query Table
       int n = LinkedList.searchinList(list, 3);
        if(n==-1)
            System.out.println("Searched Key not Present in Data");
        else
            LinkedList.printKey(list, n);*/
