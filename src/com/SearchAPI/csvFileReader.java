package com.SearchAPI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class csvFileReader {
    public static final String delimiter = ", ";

    public static void read(String csvFile) {
        List<String> result = new ArrayList<String>();
        try {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split(delimiter);
                for (String tempStr : tempArr) {
                   System.out.print(tempStr + "\n");
                }
                System.out.println();
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
