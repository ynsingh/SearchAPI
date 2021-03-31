package com.SearchAPI;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class QueryManager {

    public static void main(String[] args)throws Exception{
        //Search local txt files for local queries
        SearchMethod.dosearch("Suresh");

        /*LinkedList list = new LinkedList();
        LinkedList.loadList(list);
        LinkedList.saveList(list);
        LinkedList.printList(list);

        // Search for a particular query in LinkedList
        int n = LinkedList.searchinList(list, 3);
        if(n==-1)
            System.out.println("Searched Key not Present in Data");
        else
            LinkedList.printKey(list, n);

        ForwardQuery.forwardQuery(list,4, "Rajesh", 42, 43, 44);*/

       //createFile.createQueryFile(2, "Naresh", 12, 13, 14, "03.12.11.36.26.206");
       // readFile.readQueryFile("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer/file2.xml");

    }
}

