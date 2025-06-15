package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CardInfo {

    private static final String DRIVER_PATH = "uma\\chromedriver.exe";
    private static final String OUTPUT_DIR = "uma\\AllCardInfo";
    private static final String URL_PREFIX = "https://gametora.com";

    private static final StringBuilder cardEffect = new StringBuilder();

    public static void main(String[] args) {
        String filePath = "uma\\sortedhref_supports.txt"; 
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
            System.out.println("exists , skipping：" + fileName);
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
            System.out.println("Exception when dealing with link (" + url + "):" + e.getMessage());
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

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8"))) {
            writer.write(cardEffect.toString());
        } catch (IOException e) {
            System.out.println("Exception when writing into file: " + e.getMessage());
        } finally {
            cardEffect.setLength(0);
        }
    }
}
