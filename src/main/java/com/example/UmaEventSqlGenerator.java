package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class UmaEventSqlGenerator {
    static int eventId = 1, choiceId = 1, effectId = 1, uma_count = 1;

    static class Event {
        String event_name;
        Integer uma_id;
        String uma_name;
        String category;
        List<Choice> choices = new ArrayList<>();
    }

    static class Choice {
        Integer event_id;
        String choice_label;

        Integer speed;
        Integer stamina;
        Integer strength;
        Integer willpower;
        Integer intelligence;
        Integer hp;
        Integer mood;
        Integer skill_point;
        String event_condition;

        boolean is_random_value;
        boolean is_random_attribute;
        boolean is_random_block_training;
    }

    public static void main(String[] args) throws IOException {
        File folder = new File("uma\\AllUmaEvent");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        BufferedWriter writer = new BufferedWriter(
            new OutputStreamWriter(new java.io.FileOutputStream("output.sql"), StandardCharsets.UTF_8)
        );
        writeSchema(writer);

        for (File file : files) {
            processFile(file, writer);
        }

        writer.close();
        System.out.println("SQL 已成功輸出到 output.sql");
    }

    private static void writeSchema(BufferedWriter writer) throws IOException {
        writer.write(
            "Use uma_db;\n"+
            "CREATE TABLE uma_table (\n" +
            "    count INTEGER PRIMARY KEY,\n" +
            "    uma_id INTEGER,\n" +
            "    uma_name TEXT\n" +
            ");\n" +
            "CREATE TABLE events (\n" +
            "    event_id INTEGER PRIMARY KEY,\n" +
            "    event_name TEXT,\n" +
            "    uma_id INTEGER,\n" +
            "    uma_name TEXT,\n" +
            "    category TEXT\n" +
            ");\n" +
            "CREATE TABLE event_choices (\n" +
            "    choice_id INTEGER PRIMARY KEY,\n" +
            "    event_id INTEGER,\n" +
            "    choice_label TEXT,\n" +
            "    speed INTEGER,\n" +
            "    stamina INTEGER,\n" +
            "    strength INTEGER,\n" +
            "    willpower INTEGER,\n" +
            "    intelligence INTEGER,\n" +
            "    hp INTEGER,\n" +
            "    mood INTEGER,\n" +
            "    skill_point INTEGER,\n" +
            "    event_condition TEXT,\n" +
            "    is_random_value BOOLEAN,\n" +
            "    is_random_attribute BOOLEAN,\n" +
            "    is_random_block_training BOOLEAN\n" +
            ");\n"
        );
        writer.newLine();
    }

    private static void processFile(File file, BufferedWriter writer) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        String category = null;
        Event currentEvent = null;
        Integer uma_id = null;
        String uma_name = null;

        String[] names = file.getName().split("-", 2);
        uma_id = Integer.parseInt(names[0].trim());
        uma_name = names[1].replace(".txt", "").trim();

        writeUmaSQL(uma_id, uma_name, writer);

        for (String line : lines) {
            line = line.trim();
            // 分類處理
            if (line.startsWith("分類:")) {
                category = line.substring(3).trim();

            // 事件處理
            } else if (line.startsWith("事件:")) {
                if (currentEvent != null) {
                    writeEventSQL(currentEvent, writer);
                    currentEvent = null; // 重置當前事件
                }
                currentEvent = new Event();
                currentEvent.event_name = line.substring(3).trim();
                currentEvent.uma_id = uma_id;
                currentEvent.uma_name = uma_name;
                currentEvent.category = category;
            
            //skip分隔線
            } else if (line.contains("--------------------")) {
            
            // 處理選項
            } else {
                Choice choice = new Choice();
                choice.event_id = eventId;
                choice.speed = 0;
                choice.stamina = 0;
                choice.strength = 0;
                choice.willpower = 0;
                choice.intelligence = 0;
                choice.hp = 0;
                choice.mood = 0;
                choice.skill_point = 0;
                choice.is_random_value = false;
                choice.is_random_attribute = false;
                choice.is_random_block_training = false;
                if (line.contains("上選項")) {
                    choice.choice_label = "'Up'";
                } else if (line.contains("下選項")) {
                    choice.choice_label = "'Down'";
                } else if (line.contains("中間選項")) {
                    choice.choice_label = "'Middle'";
                } else {
                    choice.choice_label = "'None'";
                }

                List<String> effects = new ArrayList<String>();
                effects = parseEffects(line.trim());
                
                for (String effect : effects) {
                    //System.out.println("處理效果: " + uma_id + "-" + uma_name + " - " + currentEvent.event_name + " - " + effect);

                    if (effect.contains("會隨機在")) {
                        choice.is_random_value = true;
                    }
                    if (effect.contains("(隨機)")) {
                        choice.is_random_attribute = true;
                    }

                    if (effect.contains("速度")) {
                        choice.speed += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                    } else if (effect.contains("持久力")) {
                        choice.stamina += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                    } else if (effect.contains("力量")) {
                        choice.strength += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                    } else if (effect.contains("智力")) {
                        choice.intelligence += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                    } else if (effect.contains("意志力")) {
                        choice.willpower += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                    } else if (effect.contains("完全恢復體力")) {
                        choice.hp = 9999;
                    } else if (effect.contains("體力")) {
                        choice.hp += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                    } else if (effect.contains("幹勁")) {
                        choice.mood += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                    } else if (effect.contains("所有屬性")) {
                        choice.speed += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                        choice.stamina += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                        choice.strength += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                        choice.intelligence += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                        choice.willpower += Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                    } else if (effect.contains("技能靈感")) {
                        
                    } else if (effect.contains("狀態")) {
                        String temp = effect.replace("獲得", "").replace("狀態", "").trim();
                        if (temp == null || temp.isEmpty()) {
                            choice.event_condition = "NULL";
                        } else {
                            choice.event_condition = "'" + temp + "'";
                        }
                    } else if (effect.contains("技能")) {
                        choice.skill_point = Integer.valueOf(effect.replaceAll("[^0-9+-]", ""));
                    } else if (effect.contains("在下一回合，會禁用 4 種隨機的訓練類型")) {
                        choice.is_random_block_training = true;
                    } else if (effect.contains("或")){
                        String temp = choice.choice_label;
                        currentEvent.choices.add(choice);
                        choice = new Choice();
                        choice.event_id = eventId;
                        choice.speed = 0;
                        choice.stamina = 0;
                        choice.strength = 0;
                        choice.willpower = 0;
                        choice.intelligence = 0;
                        choice.hp = 0;
                        choice.mood = 0;
                        choice.skill_point = 0;
                        choice.is_random_value = true;
                        choice.is_random_attribute = false;
                        choice.is_random_block_training = false;
                        choice.choice_label = temp;
                    }
                }
                currentEvent.choices.add(choice);
            }
        }
        if (currentEvent != null) writeEventSQL(currentEvent, writer);
        
    }

    private static List<String> parseEffects(String raw) {
        String[] parts = raw.split("\\|\\|");
        List<String> results = new ArrayList<>();
        for (String part : parts) {
            if (!part.isBlank()) results.add(part.trim());
        }
        return results;
    }

    private static void writeUmaSQL(Integer uma_id, String uma_name, BufferedWriter writer) throws IOException {
        writer.write(String.format("INSERT INTO uma_table (count, uma_id, uma_name) VALUES (%d, %d, '%s');\n", uma_count, uma_id, escape(uma_name)));
        uma_count++;
    }

    private static void writeEventSQL(Event event, BufferedWriter writer) throws IOException {
        writer.write(String.format("INSERT INTO events (event_id, event_name, uma_id, uma_name, category) VALUES (%d, '%s', %d, '%s', '%s');\n",
                eventId, escape(event.event_name), event.uma_id,escape(event.uma_name), escape(event.category)));
        for (Choice choice : event.choices) {
            writer.write(String.format("INSERT INTO event_choices (choice_id, event_id, choice_label, speed, stamina, strength, willpower, intelligence, hp, mood, skill_point, event_condition, is_random_value, is_random_attribute, is_random_block_training) VALUES (%d, %d, %s, %d, %d, %d, %d, %d, %d, %d, %d, %s, %d, %d, %d);\n",
                choiceId, eventId, choice.choice_label, choice.speed, choice.stamina, choice.strength, choice.willpower,
                choice.intelligence, choice.hp, choice.mood, choice.skill_point, choice.event_condition, choice.is_random_value ? 1 : 0,
                choice.is_random_attribute ? 1 : 0, choice.is_random_block_training ? 1 : 0));
            choiceId++;
        }
        eventId++;
    }

    private static String escape(String s) {
        System.out.println("escape: " + s);
        return s.replace("'", "''");
    }
}
