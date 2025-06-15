package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CardInfoSqlGenerator {
    static int card_id = 1;

    static class Card_info {
        Integer card_info_id;
        Integer uma_id;
        String uma_name;
        String level_name;

        Integer friend_bonus;
        Integer motivation_bonus;
        Integer appear_rate_bonus;
        Integer initial_favor_gauge;
        Integer initial_speed_gauge;
        Integer initial_stamina_gauge;
        Integer initial_strength_gauge;
        Integer initial_willpower_gauge;
        Integer initial_intelligence_gauge;
        Integer speed_enhancement;
        Integer stamina_enhancement;
        Integer strength_enhancement;
        Integer willpower_enhancement;
        Integer intelligence_enhancement;
        Integer skill_point_enhancement;
        Integer intelligence_hp_recovery;
        Integer training_bonus;
        Integer failure_rate_bonus;
    }


    public static void main(String[] args) throws IOException {
        File folder = new File("uma\\AllCardInfo");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new java.io.FileOutputStream("output_Info.sql"), StandardCharsets.UTF_8)
        );
        writeSchema(writer);

        for (File file : files) {
            processFile(file, writer);
        }

        writer.close();
        System.out.println("SQL 已成功輸出到 output_Info.sql");
    }

    private static void writeSchema(BufferedWriter writer) throws IOException {
        writer.write(
            "CREATE TABLE card_info (\n" +
            "    card_info_id INTEGER PRIMARY KEY,\n" +
            "    uma_id INTEGER,\n" +
            "    uma_name TEXT,\n" +
            "    level_name TEXT,\n" +
            "    friend_bonus INTEGER,\n" +
            "    motivation_bonus INTEGER,\n" +
            "    appear_rate_bonus INTEGER,\n" +
            "    initial_favor_gauge INTEGER,\n" +
            "    initial_speed_gauge INTEGER,\n" +
            "    initial_stamina_gauge INTEGER,\n" +
            "    initial_strength_gauge INTEGER,\n" +
            "    initial_willpower_gauge INTEGER,\n" +
            "    initial_intelligence_gauge INTEGER,\n" +
            "    speed_enhancement INTEGER,\n" +
            "    stamina_enhancement INTEGER,\n" +
            "    strength_enhancement INTEGER,\n" +
            "    willpower_enhancement INTEGER,\n" +
            "    intelligence_enhancement INTEGER,\n" +
            "    skill_point_enhancement INTEGER,\n" +
            "    intelligence_hp_recovery INTEGER,\n" +
            "    training_bonus INTEGER,\n" +
            "    failure_rate_bonus INTEGER\n" +
            ");\n"
        );
        writer.newLine();
    }

    private static void processFile(File file, BufferedWriter writer) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        Card_info currentInfo = card_init();
        currentInfo.card_info_id = card_id;
        Integer uma_id = null;
        String uma_name = null;
        String level_name = null;

        String[] names = file.getName().split("-", 2);
        uma_id = Integer.parseInt(names[0].trim());
        uma_name = names[1].replace(".txt", "").trim();
        currentInfo.uma_id = uma_id;
        currentInfo.uma_name = uma_name;

        for (String line : lines) {
            line = line.trim();
            // 等級處理
            if (line.startsWith("==")) {
                currentInfo.level_name = line.replaceAll("==", "").trim();

            //skip分隔線
            } else if (line.contains("-----------------------")) {
                write_CardInfo(currentInfo, writer);
                currentInfo = card_init();
                currentInfo.card_info_id = card_id;
                currentInfo.uma_id = uma_id;
                currentInfo.uma_name = uma_name;
                currentInfo.level_name = null;

            // 處理選項
            } else {
                System.out.println("處理效果: " + card_id + "-" + uma_name + " - " + line);

                if (line.contains("友情加成")) {
                    currentInfo.friend_bonus += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("幹勁效果提升")) {
                    currentInfo.motivation_bonus += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("擅長率提升")) {
                    currentInfo.appear_rate_bonus += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("初期情誼量條")) {
                    currentInfo.initial_favor_gauge += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("初期速度")) {
                    currentInfo.initial_speed_gauge += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("初期持久力")) {
                    currentInfo.initial_stamina_gauge += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("初期力量")) {
                    currentInfo.initial_strength_gauge += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("初期意志力")) {
                    currentInfo.initial_willpower_gauge += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("初期智力")) {
                    currentInfo.initial_intelligence_gauge += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("速度加成")) {
                    currentInfo.speed_enhancement += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("耐力加成")) {
                    currentInfo.stamina_enhancement += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("力量加成")) {
                    currentInfo.strength_enhancement += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("意志加成")) {
                    currentInfo.willpower_enhancement += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("智力加成")) {
                    currentInfo.intelligence_enhancement += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("技能點數加成")) {
                    currentInfo.skill_point_enhancement += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("智力友情回復量")) {
                    currentInfo.intelligence_hp_recovery += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("訓練效果提升")) {
                    currentInfo.training_bonus += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                } else if (line.contains("失敗率下降")) {
                    currentInfo.failure_rate_bonus += Integer.valueOf(line.replaceAll("[^0-9+-]", ""));
                }
            }
        }
    }
        
    

    private static List<String> parselines(String raw) {
        String[] parts = raw.split("\\|\\|");
        List<String> results = new ArrayList<>();
        for (String part : parts) {
            if (!part.isBlank()) results.add(part.trim());
        }
        return results;
    }

    private static Card_info card_init(){
        Card_info currentInfo = new Card_info();
        currentInfo.card_info_id = null;
        currentInfo.uma_name = null;
        currentInfo.level_name = null;

        currentInfo.friend_bonus = 0;
        currentInfo.motivation_bonus = 0;
        currentInfo.appear_rate_bonus = 0;
        currentInfo.initial_favor_gauge = 0;
        currentInfo.initial_speed_gauge = 0;
        currentInfo.initial_stamina_gauge = 0;
        currentInfo.initial_strength_gauge = 0;
        currentInfo.initial_willpower_gauge = 0;
        currentInfo.initial_intelligence_gauge = 0;
        currentInfo.speed_enhancement = 0;
        currentInfo.stamina_enhancement = 0;
        currentInfo.strength_enhancement = 0;
        currentInfo.willpower_enhancement = 0;
        currentInfo.intelligence_enhancement = 0;
        currentInfo.skill_point_enhancement = 0;
        currentInfo.intelligence_hp_recovery = 0;
        currentInfo.training_bonus = 0;
        currentInfo.failure_rate_bonus = 0;

        return currentInfo;
    }

    private static void write_CardInfo(Card_info currentInfo, BufferedWriter writer) throws IOException {
        writer.write(String.format("INSERT INTO card_info (card_info_id, uma_id, uma_name, level_name, friend_bonus, motivation_bonus, "+
        "appear_rate_bonus, initial_favor_bonus, initial_speed_bonus, initial_stamina_bonus, initial_strength_bonus, initial_willpower_bonus, "+
        "initial_intelligence_bonus, speed_enhancement, stamina_enhancement, strength_enhancement, willpower_enhancement, intelligence_enhancement, skill_point_enhancement"+
        "intelligence_hp_recovery, training_bonus, failure_rate_bonus) VALUES (%d, %d, '%s', '%s', %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d);\n",
            currentInfo.card_info_id, currentInfo.uma_id, currentInfo.uma_name == null ? "NULL" : escape(currentInfo.uma_name), currentInfo.level_name == null ? "NULL" : escape(currentInfo.level_name),
            currentInfo.friend_bonus, currentInfo.motivation_bonus, currentInfo.appear_rate_bonus, currentInfo.initial_favor_gauge,
            currentInfo.initial_speed_gauge, currentInfo.initial_stamina_gauge, currentInfo.initial_strength_gauge, 
            currentInfo.initial_willpower_gauge, currentInfo.initial_intelligence_gauge, currentInfo.speed_enhancement,
            currentInfo.stamina_enhancement, currentInfo.strength_enhancement, currentInfo.willpower_enhancement,
            currentInfo.intelligence_enhancement, currentInfo.skill_point_enhancement, currentInfo.intelligence_hp_recovery,
            currentInfo.training_bonus, currentInfo.failure_rate_bonus));
        
        card_id++;
        writer.newLine();
    }

    private static String escape(String s) {
        return s.replace("'", "''");
    }
}
