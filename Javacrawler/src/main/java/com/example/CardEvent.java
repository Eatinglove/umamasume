package com.example;

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
        processLineEvent("https://gametora.com/zh-tw/umamusume/supports/30242-almond-eye");
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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\djes1\\Desktop\\uma\\chromedriver.exe");
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
                item.click();
                Thread.sleep(1000); 
                
                Document doc = Jsoup.parse(webdriver.getPageSource());
    
                Elements events = doc.select("div.tooltips_ttable_cell___3NMF");
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
    
    

    
}
