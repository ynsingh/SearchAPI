package com.SearchAPI;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;
import java.io.FileWriter;

public class OwnQuery {

    public static void query(String queryString, Boolean Global) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, IOException {

        if(Global == true) {// Query in Peers
            String TStamp = TimeStamp.currentTime();
            UUID uuid = UUID.randomUUID();
            String Sequence = uuid.toString();

           // update Broadcast Query Table
            ForwardQuery.forwardOwnQuery(LinkedList.list, Sequence, queryString, SearchConstants.selfNodeID,
                    SearchConstants.selfIPAddress, SearchConstants.selfPortAddress, SearchConstants.selfTransport, SearchConstants.TTL, TStamp, SearchConstants.selfNodeID);
        }
        else{
            //Query own node Data
            System.out.println("Local Query received from Own Node");
            SearchMethod.dolocalsearch(queryString);
        }
    }

    //pass result of own node query to Glue Code
    public static void passLocalResponse(List<String> result, String searchkey){

        //    for(String indlresult:result)
        //        System.out.println(indlresult);

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

        for (int i = 0; i < result.size(); i++) {
            texttosave.print(result.get(i) + ", ");
        }
        texttosave.close();

        System.out.println("Response Saved to Cache");


    }

}
