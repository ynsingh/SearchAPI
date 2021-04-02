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

    public class createFile {

        public static void createQueryFile(int d1, String d2, int d3, int d4, int d5, String d6 ) {

            try {

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("Queryfile");
                doc.appendChild(rootElement);

                // staff elements
                Element query = doc.createElement("query");
                rootElement.appendChild(query);

                // set attribute to staff element
                Attr attr = doc.createAttribute("SequenceNumber");
                attr.setValue(String.valueOf(d1));
                query.setAttributeNode(attr);

                // shorten way
                // staff.setAttribute("id", "1");

                // firstname elements
                Element queryString = doc.createElement("queryString");
                queryString.appendChild(doc.createTextNode(d2));
                query.appendChild(queryString);

                // lastname elements
                Element sourceNodeID = doc.createElement("sourceNodeID");
                sourceNodeID.appendChild(doc.createTextNode(String.valueOf(d3)));
                query.appendChild(sourceNodeID);

                // nickname elements
                Element endPointAddress = doc.createElement("endPointAddress");
                endPointAddress.appendChild(doc.createTextNode(String.valueOf(d4)));
                query.appendChild(endPointAddress);

                // salary elements
                Element TTL = doc.createElement("TTL");
                TTL.appendChild(doc.createTextNode(String.valueOf(d5)));
                query.appendChild(TTL);

                // salary elements
                Element timeStamp = doc.createElement("timeStamp");
                timeStamp.appendChild(doc.createTextNode(d6));
                query.appendChild(timeStamp);

                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_out/file"+d1+".xml"));

                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);

                transformer.transform(source, result);

                System.out.println("File saved!");

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }

        public static void createResultFile( List result) {

            try {

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("Resultfile");
                doc.appendChild(rootElement);

                // staff elements
                //Element resultel = doc.createElement("result");
                //rootElement.appendChild(resultel);

                // set attribute to staff element
                //Attr attr = doc.createAttribute("From Node");
                //attr.setValue(String.valueOf(nodeID));
                //resultel.setAttributeNode(attr);

                // shorten way
                // staff.setAttribute("id", "1");

                for(int i=0; i<result.size(); i++)
                {
                    Element resultel = doc.createElement("result");
                    rootElement.appendChild(resultel);

                    // set attribute to staff element
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

                int nodeID = 111;
                StreamResult resultfile = new StreamResult(new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_out/file"+nodeID+".xml"));

                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);

                transformer.transform(source, resultfile);

                System.out.println("File saved!");

            } catch (ParserConfigurationException pce) {
                pce.printStackTrace();
            } catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
        }




}

