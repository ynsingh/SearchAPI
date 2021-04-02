package com.SearchAPI;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

class queryElements{

}

public class readFile {

    public static ArrayList<Object> readIncomingFile(String fileaddress) {

        ArrayList<Object> readfile_elements = new ArrayList<>();

        try {

            File xmlFile = new File(fileaddress);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Message Type :" + doc.getDocumentElement().getNodeName());

            if(doc.getDocumentElement().getNodeName().equals("Queryfile") )
            {
            NodeList nList = doc.getElementsByTagName("query");



                Node nNode = nList.item(0);

                //System.out.println("Current Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {

                    Element eElement = (Element) nNode;

                    /*System.out.println("Query Sequence Number: " + eElement.getAttribute("SequenceNumber"));
                    System.out.println("Query String : " + eElement.getElementsByTagName("queryString").item(0).getTextContent());
                    System.out.println("Source Node ID : " + eElement.getElementsByTagName("sourceNodeID").item(0).getTextContent());
                    System.out.println("End Point Address : " + eElement.getElementsByTagName("endPointAddress").item(0).getTextContent());
                    System.out.println("TTL : " + eElement.getElementsByTagName("TTL").item(0).getTextContent());
                    System.out.println("Time Stamp : " + eElement.getElementsByTagName("timeStamp").item(0).getTextContent());*/

                    readfile_elements.add(eElement.getAttribute("SequenceNumber"));
                    readfile_elements.add(eElement.getElementsByTagName("queryString").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("sourceNodeID").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("endPointAddress").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("TTL").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("timeStamp").item(0).getTextContent());

                }

            }//for query

            else if(doc.getDocumentElement().getNodeName().equals("Resultfile") ) {
                NodeList nList = doc.getElementsByTagName("result");

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    // System.out.println("Current Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                       // readfile_elements.add(eElement.getAttribute("Serial_No-"));
                        readfile_elements.add(eElement.getElementsByTagName("file_address").item(0).getTextContent());

                    }

                }
            }

             //  xmlFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return readfile_elements;
    }

}
