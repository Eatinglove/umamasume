//這是處理支援卡的網址，以便之後爬蟲用
package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortUrls {
    public static void main(String[] args) {
        processAndSort(
            "uma\\href_supports.txt",
            "uma\\sortedhref_supports.txt"
        );

        processAndSort(
            "uma\\href_characters.txt",
            "uma\\sortedhref_characters.txt"
        );
    }

    public static void processAndSort(String inputPath, String outputPath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        lines.sort(Comparator.comparingInt(SortUrls::extractNumber));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int extractNumber(String url) {
        try {
            String[] parts = url.split("/");
            String lastPart = parts[parts.length - 1];
            return Integer.parseInt(lastPart.split("-")[0]);
        } catch (Exception e) {
            return Integer.MAX_VALUE; // 排最後，避免格式錯誤導致崩潰
        }
    }
}
