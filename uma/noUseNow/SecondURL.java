//package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class SecondURL {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\djes1\\Desktop\\uma\\href_links.txt";
        String outputFile = "C:\\Users\\djes1\\Desktop\\uma\\newurl.txt";

        Set<String> hrefUrlsSet = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                hrefUrlsSet.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"))) {
            for (String url : hrefUrlsSet) {
                int attempt = 0;
                boolean success = false;

                while (attempt < 5 && !success) {
                    try {
                        Document doc = Jsoup.connect(url).get();
                        writer.write(url + " ");

                        // ?�� <body> ??�� class ?��
                        Element body = doc.select("body").first();
                        if (body != null) {
                            String bodyClass = body.attr("class");
                            writer.write(bodyClass + "\n");
                        } else {
                            writer.write("Body tag not found.\n");
                        }

                        //writer.write("\n\n------------------------------------\n\n");
                        success = true; // ?�m?���\�A�H�h�X�`?
                        //System.out.println("���\����: " + url);

                    } catch (IOException e) {
                        attempt++;
                        System.err.println("?�k����: " + url + ", ??��?: " + attempt);
                        if (attempt == 5) {
                            writer.write("?�k���\���� URL: " + url + "\n");
                        }
                    }
                }
            }
            System.out.println("�Ҧ�?�u�w�O�s�� " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
