package com.SearchAPI;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class readFile {


    public static void readIncomingFile(File xmlFile) {

        ArrayList<Object> readfile_elements = new ArrayList<>();

        try {

            //String filename = xmlFile.getName();
            //System.out.println(filename + " is being read");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            if(doc.getDocumentElement().getNodeName().equals("Queryfile") )
            {
                System.out.println("Peer Query Recieved");

                NodeList nList = doc.getElementsByTagName("query");

                Node nNode = nList.item(0);

                //System.out.println("Current Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                {

                    Element eElement = (Element) nNode;

                    readfile_elements.add(eElement.getAttribute("SequenceNumber"));
                    readfile_elements.add(eElement.getElementsByTagName("queryString").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("sourceNodeID").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("endPointAddress").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("portaddress").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("transport").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("TTL").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("timeStamp").item(0).getTextContent());
                    readfile_elements.add(eElement.getElementsByTagName("NeighborNodeID").item(0).getTextContent());

                }
                //for (int i = 0; i < readfile_elements.size();i++) {
                //    System.out.println(readfile_elements.get(i));
                //}

                ForwardQuery.forwardPeerQuery(LinkedList.list, String.valueOf(readfile_elements.get(0)),
                        String.valueOf(readfile_elements.get(1)), String.valueOf(readfile_elements.get(2)),
                        String.valueOf(readfile_elements.get(3)), String.valueOf(readfile_elements.get(4)),
                        String.valueOf(readfile_elements.get(5)),
                        Integer.parseInt(String.valueOf(readfile_elements.get(6))),
                        String.valueOf(readfile_elements.get(7)), String.valueOf(readfile_elements.get(8)));

            }//for query

            else if(doc.getDocumentElement().getNodeName().equals("Responsefile") ) {
                NodeList nList = doc.getElementsByTagName("result");

                System.out.println("Peer Response Received");

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

                handleResults(xmlFile.getName(), readfile_elements);
            }

           // xmlFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void handleResults(String filename, ArrayList<Object> file_elements) {

        String[] arr = filename.split("@");
        String sequence = arr[1];
        String searchkey = arr[2];
        String peersource = arr[3];

        try {

            //check if sequence is in LinkedList
            if (LinkedList.searchinList(LinkedList.list, sequence)) {

                File file = new File(SearchConstants.OutputBuffer + searchkey + "@" + peersource + ".csv");

                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter texttosave = new PrintWriter(bw);

                for (int i = 0; i < file_elements.size(); i++) {
                    texttosave.print(file_elements.get(i) + ", ");
                }
                texttosave.close();

                Path source = Paths.get(SearchConstants.OutputBuffer + searchkey + "@" + peersource + ".csv");
                Path destination = Paths.get(SearchConstants.CacheDirectory + searchkey + "@" + peersource + ".csv");
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Response Saved to Cache");
            }
            else System.out.println("Response received for an expired query");

        } catch(Exception e){
            System.out.println("error");
        }
    }




    private static void compileResults(String filename, ArrayList<Object> file_elements) {

        String[] arr = filename.split("@");
        String sequence = arr[1];
        String searchkey = arr[2];
        String peersource = arr[3];

            try {

                //check if sequence is in LinkedList
                if (LinkedList.searchinList(LinkedList.list, sequence)) {

                    File file = new File(SearchConstants.OutputBuffer + searchkey + "@" + sequence + ".csv");

                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter texttosave = new PrintWriter(bw);
                    //PrintWriter texttosave = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));

                    //texttosave.print( file_elements.get(0) );

                    texttosave.print("Src"+peersource);
                    for (int i = 0; i < file_elements.size(); i++) {
                        texttosave.print(file_elements.get(i) + ", ");
                    }
                    texttosave.close();

                    Path source = Paths.get(SearchConstants.OutputBuffer + searchkey + "@" + sequence + ".csv");
                    Path destination = Paths.get(SearchConstants.CacheDirectory + searchkey + "@" + sequence + ".csv");
                    Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

                    //QueryManager.Buffer.addFileToOutputBuffer(file);
                   }
                else System.out.println("Response received for an expired query");

                } catch(Exception e){
                    System.out.println("error");
                }
    }

}
