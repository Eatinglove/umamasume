//���Wgametora����}���U�ӡA����|�b�B�z
package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;

public class Gametora {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\djes1\\Desktop\\uma\\chromedriver135.exe");

        // ���O�B�z��ӭ���
        processPage("https://gametora.com/zh-tw/umamusume/supports", "C:\\Users\\djes1\\Desktop\\uma\\href_supports.txt", "div.sc-70f2d7f-0.dSgCQa");
        processPage("https://gametora.com/zh-tw/umamusume/characters", "C:\\Users\\djes1\\Desktop\\uma\\href_characters.txt", "div.sc-70f2d7f-0.dSgCQa");
    }

    public static void processPage(String url, String outputPath, String targetDivClass) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);

        try {
            Thread.sleep(5000); // ���� JS ���J
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String pageSource = driver.getPageSource();
        driver.quit();

        try {
            Document doc = Jsoup.parse(pageSource);
            Elements divs = doc.select(targetDivClass);

            FileWriter writer = new FileWriter(outputPath);

            for (Element div : divs) {
                Elements links = div.select("a");
                for (Element link : links) {
                    String href = link.attr("href");
                    System.out.println("�q " + url + " ���s��: " + href);
                    writer.write(href + "\n");
                }
            }

            writer.close();
            System.out.println("�w�g�J�ɮ�: " + outputPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
