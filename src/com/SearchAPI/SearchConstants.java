package com.SearchAPI;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class SearchConstants {

    public static String selfNodeID = "7e5a7fda92ad93469da0bc567893ab4c398c0e9f";
    public static String selfIPAddress = "192.168.1.100";
    public static String selfPortAddress = "555";
    public static String selfTransport = "UDP";
    public static int TTL = 64;
    public static String CacheDirectory = "Cache/";
    public static String InputBuffer = "buffer_in/";
    public static String OutputBuffer = "buffer_out/";
    public static String NeighborTable = "neighbor_table.xml";
    public static String BroadcastQueryList = "listData.csv";
    public static String IndexDirectory = "lucene-3.6.2/Index";
    public static String DataDirectory = "lucene-3.6.2/Data";


    public static void reloadIDS(){
        getselfID();
    }

    public static void getselfID() {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(SearchConstants.NeighborTable);
            doc.getDocumentElement().normalize();

            SearchConstants.selfNodeID = doc.getDocumentElement().getAttribute("SELF_NODE_ID");
            SearchConstants.selfIPAddress = doc.getDocumentElement().getAttribute("SELF_IP_ADDRESS");
            SearchConstants.selfPortAddress = doc.getDocumentElement().getAttribute("SELF_PORT_ADDRESS");
            SearchConstants.selfTransport = doc.getDocumentElement().getAttribute("SELF_TRANSPORT");

        } catch (ParserConfigurationException | SAXException | IOException e) {

        }
    }


}
