package com.SearchAPI;

import org.apache.lucene.queryParser.ParseException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * This Class is responsible for editing Query Broadcast Table
 * and forwarding, deleting or discarding queries.
 */

public class ForwardQuery {

    /**
     * This method id for propagating Own Node Query
     * to peers and adds an entry to Query Broadcast Table
     */

    public void forwardOwnQuery(LinkedList listname, String data1, String data2, String data3,
                                        String data4, String data5, String data6, int data7, String data8, String data9)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {

        LinkedList l = new LinkedList();

            System.out.println("Global Query received from Own Node, Query is Unique");

            l.insert(listname, data1, data2, data3, data4, data5, data6, data7, data8, data9);
            l.saveList(LinkedList.list);

            QueryManager q = new QueryManager();
            q.createQueryFile(data1, data2, data3, data4, data5, data6, data7, data8, data9);
            //Put Query-Seq file in output Buffer
            SearchMethod s = new SearchMethod();
            s.findInCache(data1, data2, data3, data4, data5, data6, data9, true);
            //find in cache till global response arrives
    }

    /**
     * This method id for propagating Peer Node Query
     * to neighbors and adds an entry to Query Broadcast Table
     */

    public void forwardPeerQuery(LinkedList listname, String data1, String data2, String data3,
                                        String data4, String data5, String data6, int data7, String data8, String data9)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException, ParseException {
        LinkedList l = new LinkedList();
        if(l.checkDuplicateQuery(listname, data1))
        {
            System.out.println("Peer Query received is Duplicate");
            //do nothing now
        }
        else {  // Query is not duplicate and is to be added in Broadcast Query Table


            System.out.println("Peer Query received is Unique");

            l.insert(listname, data1, data2, data3, data4, data5, data6, data7, data8, data9);
            l.saveList(LinkedList.list);

            SearchMethod s = new SearchMethod();
            LuceneTester c = new LuceneTester();
            c.query(data1, data2, data3, data4, data5, data6, data9);

            //forward response from cache
            s.findInCache(data1, data2, data3, data4, data5, data6, data9, false);

            if(data7>1) {// Query has TTL > 1 and is forwarded
                QueryManager q = new QueryManager();
                q.createQueryFile(data1, data2, data3, data4, data5, data6, data7 - 1, data8, SearchConstants.selfNodeID);
                //Put Query in Output Buffer
            }
            else System.out.println("TTL Expired");
        }
    }
}
