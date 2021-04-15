package com.SearchAPI;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class OwnQuery {

    public static void query(String queryString, Boolean Global) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        if(Global == true) {// Query in Peers
            String TStamp = TimeStamp.currentTime();
            String Seq = "1";
            //create a query file and send query to all neighbors
            createFile.createQueryFile(Seq, queryString, SearchConstants.selfNodeID, SearchConstants.selfIPAddress,
                    SearchConstants.selfPortAddress, SearchConstants.selfTransport , SearchConstants.TTL, TStamp  );
            // update Broadcast Query Table
            ForwardQuery.forwardOwnQuery(LinkedList.list, Seq, queryString, SearchConstants.selfNodeID,
                    SearchConstants.selfIPAddress, SearchConstants.selfPortAddress, SearchConstants.selfTransport, SearchConstants.TTL, TStamp);
        }
        else{
            //Query own node Data
            SearchMethod.dolocalsearch(queryString);
        }
    }

    //pass result of own node query to Glue Code
    public static void passLocalResponse(List<String> result){
        //Add in output Buffer
            for(String indlresult:result)
            System.out.println(indlresult);
    }

}
