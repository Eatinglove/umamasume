//this is use to get the very first url which need further processing 

//package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FirstURL {
    public static void main(String[] args) {
        String url = "https://wiki.biligame.com/umamusume/%E7%B9%81%E4%B8%AD%E6%94%AF%E6%8F%B4%E5%8D%A1%E4%B8%80%E8%A7%88";
        String outputFile = "C:\\Users\\djes1\\Desktop\\uma\\href_links.txt"; 

        // no repeat
        Set<String> hrefUrlsSet = new HashSet<>();

        try {
            // get html content 
            Document doc = Jsoup.connect(url).get();

            Elements links = doc.select("a"); // get all <a>

            //  for all <a> get href
            for (Element link : links) {
                String href = link.absUrl("href"); //get the url
                if (!href.isEmpty()) {
                    //no repeat href
                    hrefUrlsSet.add(href);
                }
            }

            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "GB18030"))) {
                writer.write("link for firt time\n");
                for (String hrefUrl : hrefUrlsSet) {
                    writer.write(hrefUrl + "\n");
                }
            }

            System.out.println("output is in " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
