package com.SearchAPI;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.UUID;

public class OwnQuery {

    public static void query(String queryString, Boolean Global) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, IOException {

        if(Global == true) {// Query in Peers
            String TStamp = TimeStamp.currentTime();
            UUID uuid = UUID.randomUUID();
            String Sequence = uuid.toString();

           // update Broadcast Query Table
            ForwardQuery.forwardOwnQuery(LinkedList.list, Sequence, queryString, SearchConstants.selfNodeID,
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
