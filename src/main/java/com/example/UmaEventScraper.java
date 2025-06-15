package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.List;

public class UmaEventScraper {
    private static final String DRIVER_PATH = "C:\\Users\\djes1\\Desktop\\uma\\chromedriver137.exe";
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
                // 測試用，只處理一個頁面
                // processCardEvent(driver, "https://gametora.com/zh-tw/umamusume/characters/100102-special-week");
                // break;
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

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "big5"))) {
            driver.get(url);
            Thread.sleep(3000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            List<WebElement> eventBlocks = driver.findElements(By.className("eventhelper_elist__A0Bud"));

            for (WebElement block : eventBlocks) {
                String category = block.findElement(By.className("gvZhII")).getText();
                writer.write("分類：" + category + "\n");

                List<WebElement> events = block.findElements(By.className("compatibility_viewer_item__SWULM"));

                for (WebElement event : events) {
                    String eventName = event.getText();
                    writer.write("  事件：" + eventName + "\n");

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
                                    for (WebElement bonusDiv : bonusDivs) {
                                        effectText.append(bonusDiv.getText()).append(" ");
                                    }
                                    writer.write("    " + option + " → " + effectText.toString().trim() + "\n");
                                } else if (cells.size() == 1) {
                                    List<WebElement> bonusDivs = cells.get(0).findElements(By.tagName("div"));
                                    StringBuilder effectText = new StringBuilder();
                                    for (WebElement bonusDiv : bonusDivs) {
                                        effectText.append(bonusDiv.getText()).append(" ");
                                    }
                                    writer.write("    效果：" + effectText.toString().trim() + "\n");
                                }
                            }
                        } else {
                            List<WebElement> singleCells = tooltip.findElements(By.className("tooltips_ttable_cell___3NMF"));
                            for (WebElement cell : singleCells) {
                                List<WebElement> bonusDivs = cell.findElements(By.tagName("div"));
                                StringBuilder effectText = new StringBuilder();
                                for (WebElement bonusDiv : bonusDivs) {
                                    effectText.append(bonusDiv.getText()).append(" ");
                                }
                                writer.write("    " + effectText.toString().trim() + "\n");
                            }
                        }
                    } catch (Exception e) {
                        writer.write("    無 tooltip 或展開失敗\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("處理 " + url + " 時發生錯誤：" + e.getMessage());
        }
    }
}