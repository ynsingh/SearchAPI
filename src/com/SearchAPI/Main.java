package com.SearchAPI;

import java.io.File;

public class Main {


    //public static RoutingManagerBuffer routingManagerBuffer;
    // public static Buffer Buffer;    //   reqd


   public static void main(String[] args)throws Exception{


        //Loading Broadcast Query Table from saved File
        //LinkedList list = new LinkedList();


        //LinkedList.loadList(LinkedList.list);    // reqd
        //LinkedList.printList(LinkedList.list);   //  reqd

        //Broadcast Query Table Operations
        //ForwardQuery.forwardGlobalQuery(list,49, "Rajeshwar", 42, 44, 50, "03.12.11.36.26.206");

        //Print Broadcast Query Table
        //LinkedList.printList(LinkedList.list);
        //LinkedList.saveList(LinkedList.list);


        //ask NodeID and put in createfile

        //Create new Query to be Broadcasted
        // createFile.createQueryFile("211234", "Meenakshi", "12", "13", 14, "03.12.11.36.26.206");

        // Read a Query File and get elements in ArrayList & Search the QueryString
        /*ArrayList<Object> readfile_elements =  readFile.readIncomingFile("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/Query211234.xml");
        for (int i = 0; i < readfile_elements.size();i++) {
            System.out.println(readfile_elements.get(i));
        }
        // Seach QueryString found in Query File
        SearchMethod.dosearch(String.valueOf(readfile_elements.get(1)));

         */

        // Read a Result File and display result FileAddress
        /*ArrayList<Object> readfile_elements =  readFile.readIncomingFile("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/file111.xml");
        for (int i = 0; i < readfile_elements.size();i++) {
            System.out.println(readfile_elements.get(i));
        }*/


        //while(true) {
        //    listFiles.listallFiles();
        // }

        // Read all file at Buffer_in Folder


        //Buffer = Buffer.getInstance();    //  reqd


       /*
        //File file = new File("/Users/rex/Desktop/Query-a85.xml");
        File file = new File("/Users/rex/Desktop/Response#10fc7f01-4d04-43ce-9f26-da30863af39c#7e5a7fda92ad93469da0.xml");
        Buffer.addFileToInputBuffer(file);

        Buffer.getFileFromInputBuffer();

         */

        //Compiled Result file added to output buffer at readfile comment or uncomment both
        //csvFileReader.read(String.valueOf(routingManagerBuffer.getFileFromOutputBuffer()));

        //get self IDs
        //ReadNeighborTable.getselfID();

        //Local Query Search
        //QueryManager.query("Jeena", true);




       QueryManager.Load();
       QueryManager.getselfID();
       //QueryManager q = new QueryManager(); q.query("Ajay", false);




        //TestingThread testThreadObject = new TestingThread();
        //testThreadObject.start();

        //DemoThread DemoThreadObject = new DemoThread();
        //DemoThreadObject.start();

      //File a = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/Response@5be459a6-7c54-4269-a784-3cc6321b1184@Jeena@7e5a7fda92ad53469da4@.xml");
      //QueryManager.Buffer.addFileToInputBuffer(a);
      // File b = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/Response@9x19d515-7e54-490b-9401-b6a2925a7b9f@Nina@7e5a7fda90ad53469da4@.xml");
      // QueryManager.Buffer.addFileToInputBuffer(b);
       //Main.Buffer.addFileToInputBuffer(g);





    }
}

