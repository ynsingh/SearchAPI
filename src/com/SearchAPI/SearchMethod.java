package com.SearchAPI;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class SearchMethod {

    public static void search(String keyword) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LuceneTester c = new LuceneTester();

        // Using getDeclareMethod() method
        Method m = LuceneTester.class.getDeclaredMethod("query", String.class);
        // Using setAccessible() method
        m.setAccessible(true);
        // Using invoke() method
        m.invoke(c, keyword);
    }
    public static void dolocalsearch(String keyword) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        LuceneTester c = new LuceneTester();

        // Using getDeclareMethod() method
        Method m = LuceneTester.class.getDeclaredMethod("querylocal", String.class);
        // Using setAccessible() method
        m.setAccessible(true);
        // Using invoke() method
        m.invoke(c, keyword);
    }

    public static void findInCache(String keyword, String sequence, boolean ownnodequery) throws IOException {

        File directoryPath = new File(SearchConstants.CacheDirectory);
        FilenameFilter textFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".csv")) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        String filesList[] = directoryPath.list(textFilefilter);

        for(int i = 0; i < filesList.length; i++){

            String[] arr = filesList[i].split("@");
            String cachekey = arr[0];

            if(cachekey.equals(keyword)){

                if(ownnodequery) {
                    Path source = Paths.get(SearchConstants.CacheDirectory + filesList[i] );
                    Path destination = Paths.get(SearchConstants.OutputBuffer + filesList[i]);
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                   // System.out.println(filesList[i]);
                    System.out.println("Response from Cache sent to Own Node");
                }
                else{

                    List<String> cachelist = new ArrayList<String>();

                    String line = "";
                    String splitBy = ", ";
                    try
                    {
                        //parsing a CSV file into BufferedReader class constructor
                        BufferedReader br = new BufferedReader(new FileReader(
                                SearchConstants.CacheDirectory + filesList[i]));

                        while ((line = br.readLine()) != null)   //returns a Boolean value
                        {
                            String[] a = line.split(splitBy);
                            for(int j = 0; j<a.length; j++){
                                cachelist.add(a[j]);
                            }
                            //cachelist.add(line);
                        }
                        QueryManager.createResultFile(cachelist);
                       // createFile.createResultFile(cachelist);
                        System.out.println("Response from Cache sent to Peer Node");
                    }
                    catch (IOException e)
                    {
                        System.out.println("error");
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}

