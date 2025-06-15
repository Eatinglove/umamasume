package com.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

public class parse {
    private static final String[] DIRS = {
        "uma\\AllCardEvent",
        "uma\\AllCardInfo",
        "uma\\AllUmaEvent"
    };
    private static final String OUTPUT_FILE = "uma\\unique_lines.txt";

    public static void main(String[] args) {
        Set<String> uniqueLines = new HashSet<>();

        for (String dirPath : DIRS) {
            File dir = new File(dirPath);
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
                if (files != null) {
                    for (File file : files) {
                        extractLinesFromFile(file, uniqueLines);
                    }
                }
            }
        }

        writeLinesToFile(uniqueLines, OUTPUT_FILE);
        System.out.println("Extraction complete. Unique lines: " + uniqueLines.size());
    }

    private static void extractLinesFromFile(File file, Set<String> uniqueLines) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    uniqueLines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + file.getAbsolutePath());
        }
    }

    private static void writeLinesToFile(Set<String> lines, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outputPath), StandardCharsets.UTF_8))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + outputPath);
        }
    }
}
