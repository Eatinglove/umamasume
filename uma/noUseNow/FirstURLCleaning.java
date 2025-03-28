//this is use to clean not url thing such as javacript something 

//package com.example;

import java.io.*;
import java.util.*;

public class FirstURLCleaning {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\djes1\\Desktop\\uma\\href_links.txt";

        List<String> filteredUrls = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("https")) {
                    filteredUrls.add(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(inputFile), "UTF-8"))) {
            for (String url : filteredUrls) {
                writer.write(url + "\n");
            }
            System.out.println("已修改 " + inputFile + "，?保留以 https ??的 URL");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
