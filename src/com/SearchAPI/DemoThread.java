package com.SearchAPI;

import java.io.File;

public class DemoThread extends Thread{

    public void run()
    {

        File f = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/Query-9x19d515-7e54-490b-9401-b6a2925a7b9f.xml");
        //Main.Buffer.addFileToInputBuffer(f);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File g = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/Query-8x19d515-7e54-490b-9401-b6a2925a7b9g.xml");
       // Main.Buffer.addFileToInputBuffer(g);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        File h = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/Response@c771fd98-1ad3-461d-aeaa-100555bca655@Jeena@7e5a7fda92ad53469da4@.xml");
       // Main.Buffer.addFileToInputBuffer(h);
    }
}
