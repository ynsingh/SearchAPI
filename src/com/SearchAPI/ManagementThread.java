package com.SearchAPI;

public class ManagementThread extends Thread{
    public void run()
    {
        int i =0;
        LinkedList l = new LinkedList();
        while(true) {
            try {
                for (int t = 0; t < QueryManager.Buffer.sizeofInputBuffer(); t++) {
                      QueryManager.Buffer.getFileFromInputBuffer();
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

