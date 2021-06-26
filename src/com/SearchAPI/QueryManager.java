package com.SearchAPI;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
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
import org.apache.lucene.queryParser.ParseException;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class QueryManager {

    public static Buffer Buffer;

    public static void Load(){

        Buffer = Buffer.getInstance();

        LinkedList a = new LinkedList();
        a.loadList(LinkedList.list);
       // a.printList(LinkedList.list);

        ManagementThread threadObject = new ManagementThread();
        threadObject.start();
    }

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

        }
        catch (ParserConfigurationException | SAXException | IOException e) {
        }
    }



    public void query(String queryString, Boolean Global) throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, IOException, ParseException {


        LinkedList a = new LinkedList();
        if(Global) {// Query in Peers
            String TStamp = a.currentTime();
            UUID uuid = UUID.randomUUID();
            String Sequence = uuid.toString();

            // update Broadcast Query Table
            ForwardQuery f = new ForwardQuery();
            f.forwardOwnQuery(LinkedList.list, Sequence, queryString, SearchConstants.selfNodeID,
                    SearchConstants.selfIPAddress, SearchConstants.selfPortAddress, SearchConstants.selfTransport,
                    SearchConstants.TTL, TStamp, SearchConstants.selfNodeID);
        }
        else{
            //Query own node Data
            System.out.println("Local Query received from Own Node");
            LuceneTester c = new LuceneTester();
            c.dolocalsearch(queryString);
        }
    }




    public void createQueryFile(String d1, String d2, String d3, String d4, String d5,
                                String d6, int d7, String d8, String d9 ) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();

            Element rootElement = doc.createElement("Queryfile");
            doc.appendChild(rootElement);
            rootElement.setAttribute("Destination", "NeighborTable");


            Element query = doc.createElement("query");
            rootElement.appendChild(query);

            Attr attr = doc.createAttribute("SequenceNumber");
            attr.setValue(String.valueOf(d1));
            query.setAttributeNode(attr);

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

            transformer.transform(source, result);

            System.out.println("Query forwarded to Buffer_out");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

    public void createResultFile(List result, String sequence, String searchQuery, String nodeid, String ipaddress,
                                 String portaddress, String transport, String neighbornode, String ResponseSource) {

        try {
           // String[] a = ForwardQuery.elements();

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Responsefile");
            doc.appendChild(rootElement);

            rootElement.setAttribute("IPAddress", ipaddress);
            rootElement.setAttribute("SourceNodeID", nodeid);
            rootElement.setAttribute("PortAddress", portaddress);
            rootElement.setAttribute("Transport", transport);
            rootElement.setAttribute("NeighborNodeID", neighbornode);

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

            String sourceofresponse = SearchConstants.selfNodeID;
            String Src = "Index";
            if(ResponseSource =="Cache") {
                sourceofresponse = nodeid;
                Src = "Cache";
            }
            else if(ResponseSource == "Neighbor") {
                sourceofresponse = nodeid;
                Src = "Neighbor";
            }

            StreamResult resultfile = new StreamResult(new File(SearchConstants.OutputBuffer +
                    "Response"+"@" + sequence + "@" + searchQuery + "@" + sourceofresponse + "@" + ".xml"));

            transformer.transform(source, resultfile);
            System.out.println(Src + " Response Forwarded");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }




    public void readIncomingFile(File xmlFile) {

        ArrayList<Object> readfile_elements = new ArrayList<>();

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            if(doc.getDocumentElement().getNodeName().equals("Queryfile") )
            {
                System.out.println("Peer Query Recieved");

                NodeList nList = doc.getElementsByTagName("query");

                Node nNode = nList.item(0);

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

                ForwardQuery f = new ForwardQuery();
                f.forwardPeerQuery(LinkedList.list, String.valueOf(readfile_elements.get(0)),
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

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        readfile_elements.add(eElement.getElementsByTagName("file_address").item(0).getTextContent());
                    }
                }



               // handleResults(xmlFile.getName(), readfile_elements);// Display and Save to Cache
                String[] arr = xmlFile.getName().split("@");
                String sequence = arr[1];
                String searchkey = arr[2];
                String peersource = arr[3];

                LinkedList l = new LinkedList();
                String[] data = l.getSourceIDfromList(LinkedList.list, sequence);

                try {

                    //check if sequence is in LinkedList

                    if (l.searchinList(LinkedList.list, sequence)) {

                        File file = new File(SearchConstants.CacheDirectory + searchkey + "@" + peersource + ".csv");

                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        FileWriter fw = new FileWriter(file, true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter texttosave = new PrintWriter(bw);

                        for (Object readfile_element : readfile_elements) {
                            texttosave.print(readfile_element + ", ");
                        }
                        texttosave.close();

                        //if own query also display results
                       // LinkedList l = new LinkedList();

                        if(data[0].equals(SearchConstants.selfNodeID)){

                            Path source = Paths.get(SearchConstants.CacheDirectory + searchkey + "@" + peersource + ".csv");
                            Path destination = Paths.get(SearchConstants.OutputBuffer + searchkey + "@" + peersource + ".csv");
                            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Response sent for Display");
                        }

                        System.out.println("Response Saved to Cache");
                    }
                    else System.out.println("Response received for an expired query");

                } catch(Exception e){
                    System.out.println("error");
                }



                //Send response also to neighbor
                String SourceNodeID = data[0];
                String NeighborNodeID = data[1];

                if (SourceNodeID.equals(SearchConstants.selfNodeID) || SourceNodeID.equals("0")) {
                } else {
                    createResultFile(readfile_elements, sequence, searchkey,
                            peersource,"","","", NeighborNodeID, "Neighbor");
                }

            }

             xmlFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
