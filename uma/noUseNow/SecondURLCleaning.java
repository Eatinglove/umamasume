//package com.example;

import java.io.*;

public class SecondURLCleaning {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\djes1\\Desktop\\uma\\newurl.txt";  // ?�J����?
        String outputFile = "C:\\Users\\djes1\\Desktop\\uma\\new2url.txt";  // ?�X����?

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "UTF-8"))) {

            String line;
            String urlLine = null;

            while ((line = reader.readLine()) != null) {urlLine = line;
                 if (line.contains("�i") && urlLine != null) {
                    // �p�G?�e��]�t "�i" �B�s�burlLine
                    writer.write(urlLine + "\n");
                    urlLine = null;  // ���murlLine�A��??�z�U�@?URL
                } else {
                    // �p�G?�e�椣�]�t "�i"�A???�d�U�@��
                    urlLine = null;
                }
            }

            System.out.println("�ŦX?��?�e�w�O�s�� " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
