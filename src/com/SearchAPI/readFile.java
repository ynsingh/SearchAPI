package com.SearchAPI;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class readFile {

    public static void readQueryFile(String fileadress) {

        try {

            File xmlFile = new File(fileadress);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Message Type :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("query");

            System.out.println("----------------------------");

                Node nNode = nList.item(1);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Query Sequence Number: " + eElement.getAttribute("SequenceNumber"));
                    System.out.println("Query String : " + eElement.getElementsByTagName("queryString").item(0).getTextContent());
                    System.out.println("Source Node ID : " + eElement.getElementsByTagName("sourceNodeID").item(0).getTextContent());
                    System.out.println("End Point Address : " + eElement.getElementsByTagName("endPointAddress").item(0).getTextContent());
                    System.out.println("TTL : " + eElement.getElementsByTagName("TTL").item(0).getTextContent());
                    System.out.println("Time Stamp : " + eElement.getElementsByTagName("timeStamp").item(0).getTextContent());
                }
               xmlFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
