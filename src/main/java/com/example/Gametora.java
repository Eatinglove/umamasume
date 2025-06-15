//先上gametora把網址爬下來，之後會在處理
package com.example;

import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Gametora {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "uma\\chromedriver.exe");

        // 分別處理兩個頁面
        processPage("https://gametora.com/zh-tw/umamusume/supports", "uma\\href_supports.txt", "div.sc-70f2d7f-0.dSgCQa");
        processPage("https://gametora.com/zh-tw/umamusume/characters", "uma\\href_characters.txt", "div.sc-70f2d7f-0.dSgCQa");
    }

    public static void processPage(String url, String outputPath, String targetDivClass) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);

        try {
            Thread.sleep(5000); // 等待 JS 載入
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
                    System.out.println("從 " + url + " 抓到連結: " + href);
                    writer.write(href + "\n");
                }
            }

            writer.close();
            System.out.println("已寫入檔案: " + outputPath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
