/*package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Gametora {
    public static void main(String[] args) {
        String url1 = "https://gametora.com/zh-tw/umamusume/supports"; 
        String filePath = "C:\\Users\\djes1\\Desktop\\uma\\page.txt";*/

        /*try {
            URL url = new URL("https://gametora.com/zh-tw/umamusume/supports");
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            connection.setRequestProperty("User-agent", "Mozilla/5.0");

            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder sb = new StringBuilder();
                while((line = bufferedReader.readLine())!=null) {
                    sb.append(line);
                }
                bufferedReader.close();
                //System.out.print(sb.toString());

                //FileWriter writer = new FileWriter(filePath);
                //writer.write(sb.toString());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /*try {
            Document doc = Jsoup.connect(url1).get();
            Elements links = doc.select("div.sc-70f2d7f.dSgCQa");  
            for (Element link : links) {
                String urls = link.select("a").attr("href");
                System.out.println("Link: " + urls);
            }

            //System.out.println("�w�N�Ҧ� href �g�J " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Document doc = Jsoup.connect(url1).get();
        
            Elements divs = doc.select("div");
        
            for (Element div : divs) {
                String classNames = div.className();
                System.out.println("Class: " + classNames);
            }
            //System.out.println(doc.html());
            String html = doc.html().toString();
            FileWriter writer = new FileWriter(filePath);
            writer.write(html);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
           
    }
}


*/
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
        // �]�w ChromeDriver �����|
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\djes1\\Desktop\\uma\\chromedriver135.exe");

        // �Ұ� WebDriver
        WebDriver driver = new ChromeDriver();
        driver.get("https://gametora.com/zh-tw/umamusume/supports");

        // ���� 5 ��A�T�O JavaScript �ʺA�[�������]�ھں�������ڥ[�����p�վ�^
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ������㪺 HTML ���e
        String pageSource = driver.getPageSource();

        // �����s����
        driver.quit();

        try {
            // �ϥ� Jsoup �ѪR HTML
            Document doc = Jsoup.parse(pageSource);

            // ���ؼ� div
            Elements divs = doc.select("div.sc-70f2d7f-0.dSgCQa");

            // �Ыؤ@�Ӥ��Ӧs�x href
            FileWriter writer = new FileWriter("C:\\Users\\djes1\\Desktop\\uma\\href.txt");

            for (Element div : divs) {
                // ���� div �������Ҧ� <a> ����
                Elements links = div.select("a");

                for (Element link : links) {
                    String href = link.attr("href");
                    System.out.println("��쪺�s��: " + href);
                    writer.write(href + "\n");
                }
            }

            writer.close();
            System.out.println("�Ҧ� href �s���w���\�g�J href.txt�I");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
