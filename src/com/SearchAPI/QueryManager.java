package com.SearchAPI;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class QueryManager {

    public static void main(String[] args)throws Exception{
        //Search txt files
        SearchMethod.dosearch("Suresh");

        LinkedList list = new LinkedList();
        LinkedList.loadList(list);
        //LinkedList.insert(list, 4,"Rajesh", 42, 43, 44);
        LinkedList.saveList(list);
        LinkedList.printList(list);

        // Search in Query List
        int n = LinkedList.searchinList(list, 3);
        if(n==-1)
            System.out.println("Searched Key not Present in Data");
        else
            LinkedList.printKey(list, n);

        ForwardQuery.forwardQuery(list,4, "Rajesh", 42, 43, 44);

    }
}

