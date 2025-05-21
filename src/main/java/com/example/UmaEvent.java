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

public class UmaEvent {

    private static final String DRIVER_PATH = "C:\\Users\\djes1\\Desktop\\uma\\chromedriver135.exe";
    private static final String OUTPUT_DIR = "C:\\Users\\djes1\\Desktop\\uma\\AllUmaEvent";
    private static final String URL_PREFIX = "https://gametora.com";
    private static final String FILE_PATH = "C:\\Users\\djes1\\Desktop\\uma\\sortedhref_characters.txt";

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
                //processCardEvent(driver, "https://gametora.com/zh-tw/umamusume/characters/109001-verxina");
                //break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

            List<WebElement> items = driver.findElements(By.className("compatibility_viewer_item__SWULM"));
            for (WebElement item : items) {
                try {
                    item.click();
                    Thread.sleep(100); 

                    Document doc = Jsoup.parse(driver.getPageSource());

                    Elements events = doc.select("td.tooltips_ttable_cell___3NMF, div.tooltips_ttable_cell___3NMF, div.tooltips_ttable_heading__jlJcE");

                    for (Element event : events) {
                        String text = event.text();
                        cardEvent.append(text).append("\n");
                        //System.out.println(text);
                    }

                    cardEvent.append("-------------------------\n");

                } catch (Exception e) {
                    System.out.println("failuwu" + e.getMessage());
                }
            }

            saveToFile(cardId, cardEvent.toString());

        } catch (Exception e) {
            System.out.println("failuwu" + e.getMessage());
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
