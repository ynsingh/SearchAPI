package com.SearchAPI;

import java.io.File;

public class GlueCodeThread extends Thread{

    public void run()
    {
        while(true) {
            try {
                File file = QueryManager.Buffer.getFileFromOutputBuffer();

                if(String.valueOf(file).endsWith(".csv")) {
                    System.out.println("\033[0;31m" + "Glue Code - " + "Response received from Search API"+"\n");

                    csvFileReader.read(String.valueOf(file));
                    String[] arr = file.getName().split("@");
                    String SourceNode = arr[1];
                    if (SourceNode!="")
                    System.out.println("Node having above Files is " + SourceNode);
                    file.delete();
                }
                else if(String.valueOf(file).endsWith(".xml")){
                    System.out.println("\033[0;31m" + "Glue Code -   xml file received from Output Buffer of Search API, forwarded to communication manager");
                    file.delete();
                }

                Thread.sleep(3000);

            } catch (Exception e) {
                //System.err.println(e.getMessage());
            }
        }
    }
}
