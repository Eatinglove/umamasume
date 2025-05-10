/*package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CardEvent {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\djes1\\Desktop\\uma\\sortedhref.txt"; 

        //readEvent(filePath);
        processLineEvent("https://gametora.com/zh-tw/umamusume/supports/30028-kitasan-black");
    }

    private static void readEvent(String filePath){
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLineEvent("https://gametora.com"+line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processLineEvent(String url){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\djes1\\Desktop\\uma\\chromedriver135.exe");
        WebDriver driver = new ChromeDriver(); 
        try{
            driver.get(url);
            Thread.sleep(1000);
            writeEvent(driver);
        }
        catch(Exception e){
            System.out.println(url+" 錯誤 " + e.getMessage());
        }
        finally{
            driver.quit();
        }
    }
    //private static StringBuilder cardEvent = new StringBuilder();
    private static void writeEvent(WebDriver webdriver) {
        JavascriptExecutor js = (JavascriptExecutor) webdriver;
        StringBuilder cardEvent = new StringBuilder();
    
        // 找到所有 class="compatibility_viewer_item__SWULM" 的元素
        List<WebElement> items = webdriver.findElements(By.className("compatibility_viewer_item__SWULM"));
        int i=0;
        for (WebElement item : items) {
            try {
                // 點擊該元素，觸發 aria-expanded 變更
                item.click();
                Thread.sleep(1000); // 等待變化生效
                
                // 獲取當前的 HTML
                Document doc = Jsoup.parse(webdriver.getPageSource());
    
                // 讀取 class="tooltips_ttable_cell__3NMF" 的內容
                Elements events = doc.select("div.tooltips_ttable_cell___3NMF, td.tooltips_ttable_cell___3NMF");

                for (Element event : events) {
                    cardEvent.append(event.text()).append("\n");
                }
                cardEvent.append("-------------------------\n");
    
            } catch (Exception e) {
                System.out.println("處理事件時發生錯誤：" + e.getMessage());
            }
        }
    
        // 輸出所有讀取的內容
        System.out.println(cardEvent.toString());
    }
    
}*/
package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.io.*;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardEvent {

    private static final String DRIVER_PATH = "C:\\Users\\djes1\\Desktop\\uma\\chromedriver135.exe";
    private static final String OUTPUT_DIR = "C:\\Users\\djes1\\Desktop\\uma\\AllCardEvent";
    private static final String URL_PREFIX = "https://gametora.com";
    private static final String FILE_PATH = "C:\\Users\\djes1\\Desktop\\uma\\sortedhref.txt";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        try {
            readEventFile(FILE_PATH, driver);
        } finally {
            driver.quit();
        }
    }

    private static void readEventFile(String filePath, WebDriver driver) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String fullUrl = URL_PREFIX + line;
                processCardEvent(driver, fullUrl);
                //processCardEvent(driver, "https://gametora.com/zh-tw/umamusume/supports/30161-el-condor-pasa");
                //break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*private static void processCardEvent(WebDriver driver, String url) {
        String cardId = url.substring(url.lastIndexOf("/") + 1);
        String fileName = OUTPUT_DIR + "\\" + cardId + ".txt";
        File file = new File(fileName);*/

        /*if (file.exists()) {
            System.out.println("已存在，跳過：" + fileName);
            return;
        }*/

        /*try {
            driver.get(url);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("compatibility_viewer_item__SWULM")));

            StringBuilder cardEvent = new StringBuilder();

            List<WebElement> items = driver.findElements(By.className("compatibility_viewer_item__SWULM"));
            for (WebElement item : items) {
                try {
                    item.click();
                    Thread.sleep(1000);

                    Document doc = Jsoup.parse(driver.getPageSource());
                    Elements eventsDiv = doc.select("div.tooltips_ttable_cell___3NMF");
                    Elements eventsTd = doc.select("td.tooltips_ttable_cell___3NMF,div.tooltips_ttable_heading__jlJcE");
                    for (Element event : eventsDiv) {
                        cardEvent.append(event.text()).append("\n");
                    }
                    for (Element event : eventsTd) {
                        cardEvent.append(event.text()).append("\n");
                    }
                    
                    cardEvent.append("-------------------------\n");
                    
                } catch (Exception e) {
                    System.out.println("處理事件時發生錯誤：" + e.getMessage());
                }
            }
            //System.out.println(cardEvent.toString());
            saveToFile(cardId, cardEvent.toString());

        } catch (Exception e) {
            System.out.println("處理 " + url + " 時發生錯誤：" + e.getMessage());
        }
    }*/
    private static void processCardEvent(WebDriver driver, String url) {
    String cardId = url.substring(url.lastIndexOf("/") + 1);
    String fileName = OUTPUT_DIR + "\\" + cardId + ".txt";
    File file = new File(fileName);

    /*if (file.exists()) {
        System.out.println("已存在，跳過：" + fileName);
        return;
    }*/

    try {
        driver.get(url);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("compatibility_viewer_item__SWULM")));

        StringBuilder cardEvent = new StringBuilder();
        Set<String> seen = new HashSet<>();

        List<WebElement> items = driver.findElements(By.className("compatibility_viewer_item__SWULM"));
        for (WebElement item : items) {
            try {
                item.click();
                Thread.sleep(1000);

                Document doc = Jsoup.parse(driver.getPageSource());
                Elements events = doc.select("td.tooltips_ttable_cell___3NMF, div.tooltips_ttable_cell___3NMF, div.tooltips_ttable_heading__jlJcE");
                for (Element event : events) {
                    String text = event.text();
                    if (seen.add(text)) {
                        cardEvent.append(text).append("\n");
                    }
                }

                cardEvent.append("-------------------------\n");

            } catch (Exception e) {
                System.out.println("處理事件時發生錯誤：" + e.getMessage());
            }
        }

        saveToFile(cardId, cardEvent.toString());

    } catch (Exception e) {
        System.out.println("處理 " + url + " 時發生錯誤：" + e.getMessage());
    }
}

    private static void saveToFile(String cardId, String content) {
        File folder = new File(OUTPUT_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = OUTPUT_DIR + "\\" + cardId + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("寫入檔案時錯誤：" + e.getMessage());
        }
    }
}
