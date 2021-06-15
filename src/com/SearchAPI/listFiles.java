package com.SearchAPI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;


public class listFiles {

   public static void listallFiles() {

       ArrayList<Object> xmlfiles = new ArrayList<>();

       try {
           File f = new File(SearchConstants.InputBuffer);

           FilenameFilter filter = new FilenameFilter() {
               @Override
               public boolean accept(File f, String name) {
                   // We want to find only .xml files
                   return name.endsWith(".xml");
               }
           };


           File[] files = f.listFiles(filter);

           // Get the names of the files by using the .getName() method
           for (int i = 0; i < files.length; i++) {
               System.out.println(files[i].getName());


               /*String Str = String.valueOf(files[i].getName());
               String[] arrSplit = Str.split("-");

               if(arrSplit[0].equals("Query")){
                   ArrayList<Object> readfile_elements =  readFile.readIncomingFile(String.valueOf(files[i]));
                   for (Object readfile_element : readfile_elements) {
                       System.out.println(readfile_element);
                   }
                   System.out.println("---------------------------");
               }
               else{

               }*/

               
              // ArrayList<Object> readfile_elements =  readFile.readIncomingFile(String.valueOf(files[i]));
               //for (Object readfile_element : readfile_elements) {
               //    System.out.println(readfile_element);
               //}
               //System.out.println("---------------------------");
           }
       } catch (Exception e) {
           System.err.println(e.getMessage());
       }


    }

}