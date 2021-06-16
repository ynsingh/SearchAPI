package com.SearchAPI;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SearchThread extends Thread{
    public void run()
    {
        int i =0;
        while(true) {
            try {
                for (int t = 0; t < Main.Buffer.sizeofInputBuffer(); t++) {
                    Main.Buffer.getFileFromInputBuffer();

                }

                //delete older queries
                i =i+1;
                if(i==180){
                    LinkedList.deleteByTimestamps(LinkedList.list);
                    i=0;
                }
                Thread.sleep(5000);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

