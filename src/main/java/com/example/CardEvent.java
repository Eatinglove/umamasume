/* 
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
    private static final String FILE_PATH = "C:\\Users\\djes1\\Desktop\\uma\\sortedhref_supports.txt";

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
                //processCardEvent(driver, "https://gametora.com/zh-tw/umamusume/supports/30240-jungle-pocket");
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
    if(file.exists()){
        System.out.println(fileName + "Exist");
        return;
    }
    try {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("compatibility_viewer_item__SWULM")));

        StringBuilder cardEvent = new StringBuilder();
        Set<String> processedEvents = new HashSet<>();  

        List<WebElement> items = driver.findElements(By.className("compatibility_viewer_item__SWULM"));
        for (WebElement item : items) {
            try {
                item.click();
                Thread.sleep(300); 

                Document doc = Jsoup.parse(driver.getPageSource());
                
                Element titleElement = doc.selectFirst("div.tooltips_ttable_heading__jlJcE");
                if (titleElement == null) continue;
                
                String eventTitle = titleElement.text();
                
                if (processedEvents.contains(eventTitle)) continue;
                
                processedEvents.add(eventTitle);  
                
                Elements events = doc.select(
                    "div.tooltips_ttable_heading__jlJcE, " +
                    "td.tooltips_ttable_cell___3NMF, " +
                    "div.tooltips_ttable_cell___3NMF"
                );
                
                for (Element event : events) {
                    cardEvent.append(event.text()).append("\n");
                }
                cardEvent.append("-------------------------\n");

            } catch (Exception e) {
                System.out.println(" B z ?? Éµo Í¿  ~ G" + e.getMessage());
            }
        }

        saveToFile(cardId, cardEvent.toString());

    } catch (Exception e) {
        System.out.println(" B z " + url + "  Éµo Í¿  ~ G" + e.getMessage());
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
            System.out.println(" g J É®×®É¿  ~ G" + e.getMessage());
        }
    }
}
*/
package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CardEvent {

    private static final String DRIVER_PATH = "uma\\chromedriver.exe";
    private static final String OUTPUT_DIR = "uma\\AllCardEvent";
    private static final String URL_PREFIX = "https://gametora.com";
    private static final String FILE_PATH = "uma\\sortedhref_supports.txt";

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
                processCardEvent(driver, "https://gametora.com/zh-tw/umamusume/supports/10127-air-messiah");
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processCardEvent(WebDriver driver, String url) {
        String cardId = url.substring(url.lastIndexOf("/") + 1);
        File folder = new File(OUTPUT_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String filePath = OUTPUT_DIR + "\\" + cardId + ".txt";

        File output = new File(filePath);
        /*if (output.exists()) {
            System.out.println(filePath + " ¤w¦s¦b");
            return;
        }*/

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "big5"))) {
            driver.get(url);
            Thread.sleep(3000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            List<WebElement> eventBlocks = driver.findElements(By.className("eventhelper_elist__A0Bud"));

            for (WebElement block : eventBlocks) {
                String category = block.findElement(By.className("gvZhII")).getText();
                writer.write("???é¡?:" + category + "\n");

                List<WebElement> events = block.findElements(By.className("compatibility_viewer_item__SWULM"));

                for (WebElement event : events) {
                    String eventName = event.getText();
                    writer.write("  äº?ä»?:" + eventName + "\n");

                    try {
                        event.click();
                        wait.until(ExpectedConditions.attributeToBe(event, "aria-expanded", "true"));
                        Thread.sleep(300);

                        WebElement tooltip = driver.findElement(By.className("tooltips_tooltip__iWtqn"));
                        List<WebElement> rows = tooltip.findElements(By.className("tooltips_ttable_row__T8N69"));

                        if (rows.size() > 0) {
                            for (WebElement row : rows) {
                                List<WebElement> cells = row.findElements(By.className("tooltips_ttable_cell___3NMF"));
                                if (cells.size() >= 2) {
                                    String option = cells.get(0).getText();
                                    List<WebElement> bonusDivs = cells.get(1).findElements(By.tagName("div"));
                                    StringBuilder effectText = new StringBuilder();
                                    effectText.append("||"); 
                                    for (WebElement bonusDiv : bonusDivs) {
                                        String bonus = bonusDiv.getText().trim();
                                        if (!bonus.isEmpty()) {
                                            effectText.append(bonus).append("||");
                                        }
                                    }
                                    writer.write("    " + option + "->" + effectText.toString() + "\n");
                                } else if (cells.size() == 1) {
                                    List<WebElement> bonusDivs = cells.get(0).findElements(By.tagName("div"));
                                    StringBuilder effectText = new StringBuilder();
                                    effectText.append("||");
                                    for (WebElement bonusDiv : bonusDivs) {
                                        String bonus = bonusDiv.getText().trim();
                                        if (!bonus.isEmpty()) {
                                            effectText.append(bonus).append("||");
                                        }
                                    }
                                    writer.write("    ??????" + effectText.toString() + "\n");
                                }
                            }
                        } else {
                            List<WebElement> singleCells = tooltip.findElements(By.className("tooltips_ttable_cell___3NMF"));
                            for (WebElement cell : singleCells) {
                                List<WebElement> bonusDivs = cell.findElements(By.tagName("div"));
                                StringBuilder effectText = new StringBuilder();
                                effectText.append("||");
                                for (WebElement bonusDiv : bonusDivs) {
                                    String bonus = bonusDiv.getText().trim();
                                    if (!bonus.isEmpty()) {
                                        effectText.append(bonus).append("||");
                                    }
                                }
                                writer.write("    " + effectText.toString() + "\n");
                            }
                        }
                    } catch (Exception e) {
                        writer.write("error\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("error" + url + e.getMessage());
        }
    }
}


