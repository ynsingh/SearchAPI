package com.SearchAPI;

import java.io.File;

/**  This Class is responsible for monitoring of input buffer to receive files
 *  and purge the Query Broadcast Table entries after their Lifetime */
public class MaintenanceThread extends Thread{
    public void run()
    {
        int i =0;
        LinkedList l = new LinkedList();
        while(true) {
            try {
                for (int t = 0; t < QueryManager.Buffer.sizeofInputBuffer(); t++) {
                    //QueryManager.Buffer.getFileFromInputBuffer();

                    File a = QueryManager.Buffer.getFileFromInputBuffer();
                    QueryManager q = new QueryManager();
                    q.readFile(a);

                }
                //delete older queries
                i =i+1;
                if(i==3600){
                    l.deleteByTimestamps(LinkedList.list);
                    i=0;
                }
                Thread.sleep(1000);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

