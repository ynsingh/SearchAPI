package com.SearchAPI;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class SearchMethod {

    public void passLocalResponse(List<String> result, String searchkey){

        File file = new File(SearchConstants.OutputBuffer + searchkey + ".csv");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter texttosave = new PrintWriter(bw);

        for (String s : result) {
            texttosave.print(s + ", ");
        }
        texttosave.close();

        System.out.println("Response Saved to Cache");
    }



    public void findInCache(String sequence, String keyword, String nodeid, String ipaddress,
                            String portaddress, String transport, String neighbornode, boolean ownnodequery) throws IOException {

        File directoryPath = new File(SearchConstants.CacheDirectory);
        FilenameFilter textFilefilter = new FilenameFilter(){
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                return lowercaseName.endsWith(".csv");
            }
        };

        String[] filesList = directoryPath.list(textFilefilter);

        for (String s : filesList) {

            String[] arr = s.split("@");
            String cachekey = arr[0];

            if (cachekey.equals(keyword)) {

                if (ownnodequery) {
                    Path source = Paths.get(SearchConstants.CacheDirectory + s);
                    Path destination = Paths.get(SearchConstants.OutputBuffer + s);
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                    // System.out.println(filesList[i]);
                    System.out.println("Response from Cache sent to Own Node");
                } else {

                    List<String> cachelist = new ArrayList<String>();

                    String line;
                    String splitBy = ", ";
                    try {
                        //parsing a CSV file into BufferedReader class constructor
                        BufferedReader br = new BufferedReader(new FileReader(
                                SearchConstants.CacheDirectory + s));

                        while ((line = br.readLine()) != null)   //returns a Boolean value
                        {
                            String[] a = line.split(splitBy);
                            for (String value : a) {
                                cachelist.add(value);
                            }
                            //cachelist.add(line);
                        }

                        QueryManager q = new QueryManager();
                        q.createResultFile(cachelist, sequence, keyword, nodeid, ipaddress,
                                portaddress, transport, neighbornode, "Cache");
                       // System.out.println("Response from Cache sent to Peer Node");
                    } catch (IOException e) {
                        System.out.println("error");
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

