package com.SearchAPI;
import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;


public class ListFiles {

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

           }
       } catch (Exception e) {
           System.err.println(e.getMessage());
       }
    }

}

