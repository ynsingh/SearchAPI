package com.SearchAPI;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

class queryElements{

}

public class readFile {

    public static String filename = "";
    //public static ArrayList<Object> readIncomingFile(String fileaddress) {
    public static void readIncomingFile(String fileaddress) {


        ArrayList<Object> readfile_elements = new ArrayList<>();

        try {

           // File xmlFile = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_in/"+fileaddress);
            File xmlFile = new File(fileaddress);

            filename = xmlFile.getName();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

           // System.out.println("Message Type :" + doc.getDocumentElement().getNodeName());

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
                //for (int i = 0; i < readfile_elements.size();i++) {
                //    System.out.println(readfile_elements.get(i));
                //}

                ForwardQuery.forwardGlobalQuery(LinkedList.list, String.valueOf(readfile_elements.get(0)),String.valueOf(readfile_elements.get(1)),
                        String.valueOf(readfile_elements.get(2)),String.valueOf(readfile_elements.get(3)),
                        Integer.parseInt(String.valueOf(readfile_elements.get(4))),String.valueOf(readfile_elements.get(5)));




            }//for query

            else if(doc.getDocumentElement().getNodeName().equals("Responsefile") ) {
                NodeList nList = doc.getElementsByTagName("result");

                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    // System.out.println("Current Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                       // readfile_elements.add(eElement.getAttribute("Serial_No-"));
                         readfile_elements.add(eElement.getElementsByTagName("file_address").item(0).getTextContent());
                       // System.out.println(eElement.getElementsByTagName("file_address").item(0).getTextContent());
                    }
                }
                compileResults(readfile_elements);
            }

             // xmlFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //return readfile_elements;
    }


    private static void compileResults(ArrayList<Object> file_elements) {


        String[] arr = filename.split("-");
        String sequence = arr[1];
        String NodeID = arr[2];

            try {
                File file = new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_out/"+sequence+".csv");

                if(!file.exists()){
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file,true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter texttosave = new PrintWriter(bw);
                //PrintWriter texttosave = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));

                //texttosave.print( file_elements.get(0) );
                for (int i = 0; i < file_elements.size();i++) {
                    texttosave.print( file_elements.get(i) +", ");
                }
                texttosave.close();

                //QueryManager.Buffer.addFileToOutputBuffer(file);

            } catch (Exception e) {
                System.out.println("error");
            }

    }

}


