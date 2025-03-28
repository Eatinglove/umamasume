package com.example;

import java.io.*;
import java.util.*;

public class SortUrls {
    public static void main(String[] args) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\djes1\\Desktop\\uma\\href.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        lines.sort(Comparator.comparingInt(SortUrls::extractNumber));

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\djes1\\Desktop\\uma\\sortedhref.txt"))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int extractNumber(String url) {
        String[] parts = url.split("/");
        String lastPart = parts[parts.length - 1];
        return Integer.parseInt(lastPart.split("-")[0]);
    }
}
