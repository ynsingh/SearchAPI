package com.SearchAPI;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.*;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class QueryManager {



    //public static Buffer Buffer;
    //Buffer = Buffer.getInstance();
    //LinkedList.loadList(LinkedList.list);
    //LinkedList.printList(LinkedList.list);





    public static void createQueryFile(String d1, String d2, String d3, String d4, String d5, String d6, int d7, String d8, String d9 ) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Queryfile");
            doc.appendChild(rootElement);
            rootElement.setAttribute("Destination", SearchConstants.neighnorTable);


            Element query = doc.createElement("query");
            rootElement.appendChild(query);

            Attr attr = doc.createAttribute("SequenceNumber");
            attr.setValue(String.valueOf(d1));
            query.setAttributeNode(attr);

            // shorten way
            // staff.setAttribute("id", "1");

            Element queryString = doc.createElement("queryString");
            queryString.appendChild(doc.createTextNode(d2));
            query.appendChild(queryString);

            Element sourceNodeID = doc.createElement("sourceNodeID");
            sourceNodeID.appendChild(doc.createTextNode(String.valueOf(d3)));
            query.appendChild(sourceNodeID);

            Element endPointAddress = doc.createElement("endPointAddress");
            endPointAddress.appendChild(doc.createTextNode(String.valueOf(d4)));
            query.appendChild(endPointAddress);

            Element portaddress = doc.createElement("portaddress");
            portaddress.appendChild(doc.createTextNode(String.valueOf(d5)));
            query.appendChild(portaddress);

            Element transport = doc.createElement("transport");
            transport.appendChild(doc.createTextNode(String.valueOf(d6)));
            query.appendChild(transport);

            Element TTL = doc.createElement("TTL");
            TTL.appendChild(doc.createTextNode(String.valueOf(d7)));
            query.appendChild(TTL);

            Element timeStamp = doc.createElement("timeStamp");
            timeStamp.appendChild(doc.createTextNode(d8));
            query.appendChild(timeStamp);

            Element NeighborNodeID = doc.createElement("NeighborNodeID");
            NeighborNodeID.appendChild(doc.createTextNode(d9));
            query.appendChild(NeighborNodeID);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(SearchConstants.OutputBuffer+"Query-"+d1+".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("Query forwarded to Buffer_out");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public static void createResultFile(List result) {

        try {
            String[] a = ForwardQuery.elements();

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Responsefile");
            doc.appendChild(rootElement);

            rootElement.setAttribute("NodeID", a[1]);
            rootElement.setAttribute("IPAddress", a[2]);
            rootElement.setAttribute("PortAddress", a[3]);
            rootElement.setAttribute("Transport", a[4]);
            rootElement.setAttribute("NeighborNodeID", a[6]);

            for(int i=0; i<result.size(); i++)
            {
                Element resultel = doc.createElement("result");
                rootElement.appendChild(resultel);

                // set attribute element
                Attr attr = doc.createAttribute("Serial_No-");
                attr.setValue(String.valueOf(i));
                resultel.setAttributeNode(attr);

                Element resultelement = doc.createElement("file_address");
                String r = (String) result.get(i);
                resultelement.appendChild(doc.createTextNode(r));
                resultel.appendChild(resultelement);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult resultfile = new StreamResult(new File(SearchConstants.OutputBuffer +
                    "Response"+"@" + a[0] + "@" + a[5] + "@" + SearchConstants.selfNodeID + "@" + ".xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, resultfile);

            System.out.println("Response Forwarded");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }







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

             xmlFile.delete();

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

}
