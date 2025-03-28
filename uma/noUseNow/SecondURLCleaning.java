//package com.example;

import java.io.*;

public class SecondURLCleaning {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\djes1\\Desktop\\uma\\newurl.txt";  // ?入文件路?
        String outputFile = "C:\\Users\\djes1\\Desktop\\uma\\new2url.txt";  // ?出文件路?

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"))) {

            String line;
            String urlLine = null;

            while ((line = reader.readLine()) != null) {urlLine = line;
                 if (line.contains("【") && urlLine != null) {
                    // 如果?前行包含 "【" 且存在urlLine
                    writer.write(urlLine + "\n");
                    urlLine = null;  // 重置urlLine，准??理下一?URL
                } else {
                    // 如果?前行不包含 "【"，???查下一行
                    urlLine = null;
                }
            }

            System.out.println("符合?件的?容已保存到 " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
