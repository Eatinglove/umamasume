/*package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CardInfo {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\djes1\\Desktop\\uma\\sortedhref.txt"; 

        //readInfo(filePath);
        processLineInfo("https://gametora.com/zh-tw/umamusume/supports/10001-special-week");
        //processLineEvent("https://gametora.com/zh-tw/umamusume/supports/10001-special-week");
    }
    private static void readInfo(String filePath){
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLineInfo("https://gametora.com"+line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void processLineInfo(String url) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\djes1\\Desktop\\uma\\chromedriver135.exe");
        WebDriver driver = new ChromeDriver(); 
        
        try {
            driver.get(url); 
            Thread.sleep(2000); 
    
            // 點擊 class 為 "supports_div_button__jWkLB" 且包含 "+5" 的按鈕
            List<WebElement> plus5Buttons = driver.findElements(By.className("supports_div_button__jWkLB"));
            for (WebElement plus5Button : plus5Buttons) {
                if (plus5Button.getText().contains("+5")) {
                    plus5Button.click();
                    Thread.sleep(100);
                    plus5Button.click();
                    Thread.sleep(100);
                    plus5Button.click();
                    Thread.sleep(100);
                    plus5Button.click();
                    Thread.sleep(100);
                    break;
                }
            }
            
            writeInfo(driver);//max level 4 break

            List<WebElement> minus5Buttons = driver.findElements(By.className("supports_div_button__jWkLB"));
            for(WebElement minus5Button : minus5Buttons){
                if(minus5Button.getText().contains("-5")){
                    minus5Button.click();//3 break
                    Thread.sleep(100);
                    writeInfo(driver);
                    minus5Button.click();//2 break
                    Thread.sleep(100);
                    writeInfo(driver);
                    minus5Button.click();//1 break
                    Thread.sleep(100);
                    writeInfo(driver);
                    minus5Button.click();//no break
                    Thread.sleep(100);
                    writeInfo(driver);
                    minus5Button.click();
                    Thread.sleep(100);
                    minus5Button.click();
                    Thread.sleep(100);
                    minus5Button.click();
                    Thread.sleep(100);
                    minus5Button.click();
                    Thread.sleep(100);
                    minus5Button.click();
                    Thread.sleep(100);
                    minus5Button.click();
                    writeInfo(driver);//lv1
                    saveToTxtInfo(url);
                }
            }
            //writeEvent(driver);
            //System.out.println(cardEvent.toString());
        } catch (Exception e) {
            System.out.println("處理 " + url + " 時發生錯誤：" + e.getMessage());
        } finally {
            driver.quit(); 
        }
    }*/
    /*private static void processLineEvent(String url){
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
    }*/
    /*private static StringBuilder cardEffect = new StringBuilder();
    //private static StringBuilder cardEvent = new StringBuilder();

    private static void writeInfo(WebDriver webDriver) {
        Document doc = Jsoup.parse(webDriver.getPageSource());
        Elements effects = doc.select("div.supports_infobox_effect_text_caption__8WT0j");
        for (Element effect : effects) {
            cardEffect.append(effect.text()).append("\n"); 
        }
        cardEffect.append("-------------------------\n");
    }*/
    /*private static void writeEvent(WebDriver webdriver){
        Document doc = Jsoup.parse(webdriver.getPageSource());
        Elements events = doc.select("div.eventhelper_elist__A0Bud");
        for(Element event : events){
            cardEvent.append(event.text()).append("\n");
        }
        cardEvent.append("-------------------------\n");
    }*/
   /*  private static void saveToTxtInfo(String url){

        String fileName="C:\\Users\\djes1\\Desktop\\uma\\AllCardInfo\\"+url.substring(url.lastIndexOf("/") + 1) + ".txt";
        File folder = new File("C:\\Users\\djes1\\Desktop\\uma\\AllCardInfo");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(cardEffect.toString()); 
            writer.flush();
        } catch (IOException e) {
            System.out.println("寫入文件時發生錯誤：" + e.getMessage());
        } finally {
            cardEffect.setLength(0); 
        }

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
import java.util.List;

public class CardInfo {

    private static final String DRIVER_PATH = "C:\\Users\\djes1\\Desktop\\uma\\chromedriver135.exe";
    private static final String OUTPUT_DIR = "C:\\Users\\djes1\\Desktop\\uma\\AllCardInfo";
    private static final String URL_PREFIX = "https://gametora.com";

    private static final StringBuilder cardEffect = new StringBuilder();

    public static void main(String[] args) {
        String filePath = "C:\\Users\\djes1\\Desktop\\uma\\sortedhref_supports.txt"; 
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        try {
            readInfo(filePath, driver);
        } finally {
            driver.quit();
        }
    }

    private static void readInfo(String filePath, WebDriver driver) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String fullUrl = URL_PREFIX + line;
                processLineInfo(driver, fullUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processLineInfo(WebDriver driver, String url) {
        String cardId = url.substring(url.lastIndexOf("/") + 1);
        String fileName = OUTPUT_DIR + "\\" + cardId + ".txt";
        File file = new File(fileName);

        if (file.exists()) {
            System.out.println("已存在，跳過：" + fileName);
            return;
        }

        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("supports_div_button__jWkLB")));

            List<WebElement> plus5Buttons = driver.findElements(By.className("supports_div_button__jWkLB"));
            for (WebElement plus5 : plus5Buttons) {
                if (plus5.getText().contains("+5")) {
                    for (int i = 0; i < 4; i++) {
                        plus5.click();
                        Thread.sleep(100);
                    }
                    break;
                }
            }

            cardEffect.append("== MAX BREAK ==\n");
            writeInfo(driver);

            List<WebElement> minus5Buttons = driver.findElements(By.className("supports_div_button__jWkLB"));
            for (WebElement minus5 : minus5Buttons) {
                if (minus5.getText().contains("-5")) {
                    cardEffect.append("== 3 BREAK ==\n");
                    minus5.click(); Thread.sleep(100); writeInfo(driver);

                    cardEffect.append("== 2 BREAK ==\n");
                    minus5.click(); Thread.sleep(100); writeInfo(driver);

                    cardEffect.append("== 1 BREAK ==\n");
                    minus5.click(); Thread.sleep(100); writeInfo(driver);

                    cardEffect.append("== NO BREAK ==\n");
                    minus5.click(); Thread.sleep(100); writeInfo(driver);

                    for (int i = 0; i < 6; i++) {
                        minus5.click(); Thread.sleep(100);
                    }

                    cardEffect.append("== LV 1 ==\n");
                    writeInfo(driver);
                    saveToTxtInfo(url);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("處理 " + url + " 時發生錯誤：" + e.getMessage());
        }
    }

    private static void writeInfo(WebDriver driver) {
        Document doc = Jsoup.parse(driver.getPageSource());
        Elements effects = doc.select("div.supports_infobox_effect_text_caption__8WT0j");
        for (Element effect : effects) {
            cardEffect.append(effect.text()).append("\n");
        }
        cardEffect.append("-------------------------\n");
    }

    private static void saveToTxtInfo(String url) {
        File folder = new File(OUTPUT_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String cardId = url.substring(url.lastIndexOf("/") + 1);
        String fileName = OUTPUT_DIR + "\\" + cardId + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(cardEffect.toString());
        } catch (IOException e) {
            System.out.println("寫入檔案時錯誤：" + e.getMessage());
        } finally {
            cardEffect.setLength(0);
        }
    }
}
