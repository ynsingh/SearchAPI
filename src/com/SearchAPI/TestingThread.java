package com.SearchAPI;

        import java.io.File;
        import java.io.FilenameFilter;

public class TestingThread extends Thread{

    public void run()
    {
        while(true) {
            try {
                File[] files = null;
                File f = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in");
                FilenameFilter filter = new FilenameFilter() {
                    @Override
                    public boolean accept(File f, String name) {
                        // We want to find only .xml files
                        return name.endsWith(".xml");
                    }
                };
                files = f.listFiles(filter);
                for (int i = 0; i < files.length; i++) {
                    //System.out.println("File Received in Buffer -"+files[i].getName());

                    QueryManager.Buffer.addFileToInputBuffer(files[i]);   //reqd


                }
                Thread.sleep(5000);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}