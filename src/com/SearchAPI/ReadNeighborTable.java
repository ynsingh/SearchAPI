package com.SearchAPI;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadNeighborTable {

    public static void getselfID() {

            //Get Document builder
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;
            try {
                documentBuilder = builderFactory.newDocumentBuilder();
                //Document doc = documentBuilder.parse("/Volumes/Disk/My Docs/_M Tech/Codes/SearchAPI/neighbor_table.xml");
                Document doc = documentBuilder.parse(SearchConstants.NeighborTable);
                doc.getDocumentElement().normalize();

                SearchConstants.selfNodeID = doc.getDocumentElement().getAttribute("SELF_NODE_ID");
                SearchConstants.selfIPAddress = doc.getDocumentElement().getAttribute("SELF_IP_ADDRESS");
                SearchConstants.selfPortAddress = doc.getDocumentElement().getAttribute("SELF_PORT_ADDRESS");
                SearchConstants.selfTransport = doc.getDocumentElement().getAttribute("SELF_TRANSPORT");

                //System.out.println(SearchConstants.selfNodeID);
                //System.out.println(SearchConstants.selfIPAddress);
                //System.out.println(SearchConstants.selfPortAddress);
                //System.out.println(SearchConstants.selfTransport);


                /* NodeList nodeList = doc.getElementsByTagName("NEIGHBOUR");
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        //Get the value of ID attribute
                        String index = node.getAttributes().getNamedItem("INDEX").getNodeValue();
                        //Get value of all sub-Elements
                        String nodeID = element.getElementsByTagName("NODEID").item(0).getTextContent();
                        String nodeIP = element.getElementsByTagName("NODEIP").item(0).getTextContent();
                        String nodePort = element.getElementsByTagName("NODEPORT").item(0).getTextContent();
                        String nodeTransport = element.getElementsByTagName("NODETRANSPORT").item(0).getTextContent();
                        Pattern pattern = Pattern.compile("\\[([^]]+)\\]");
                        Matcher matcher = pattern.matcher(index);
                        matcher.find();
                        int index1 = Integer.parseInt(matcher.group(1));
                        }
                }
                */
            } catch (ParserConfigurationException | SAXException | IOException e) {
                //log.error("Exception Occurred", e);
            }
        }
    }
