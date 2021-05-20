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

        public static void createQueryFile(String d1, String d2, String d3, String d4, String d5, String d6, int d7, String d8 ) {

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

                // write the content into xml file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_out/Query-"+d1+".xml"));

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
                rootElement.setAttribute("PortAdrress", a[3]);
                rootElement.setAttribute("Transport", a[4]);

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

                StreamResult resultfile = new StreamResult(new File("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/buffer_out" +
                        "/Response"+"@" + a[0] + "@" + a[5] + "@" + SearchConstants.selfNodeID + ".xml"));

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






}

