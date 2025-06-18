package com.example;

import java.sql.*;

public class DataCollection {
    // MySQL 資料庫連線設定
    // 請根據實際情況修改以下參數
    private static String url = "jdbc:mysql://localhost:3306/uma_db";
    private static String user = "root";
    private static String password = "Sawandcamein0705";

    // 獲取指定 count 的 UMA 資料
    // 這個方法會查詢資料庫中的 uma_table 表，根據 count
    public static void GetUmaByCount(int count) {
        // SQL 查詢語句
        String sql = "SELECT * FROM uma_table WHERE count = ?;";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, count);

            // 執行查詢
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasData = false;

                while (rs.next()) {
                    hasData = true;

                    int uma_id = rs.getInt("uma_id");
                    String uma_name = rs.getString("uma_name");
                    System.out.println("uma_id: " + uma_id + ", uma_name: " + uma_name);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 獲取指定 UMA ID 的事件資料
    // 這個方法會查詢資料庫中的 events 表，根據 uma_id
    public static void GetUmaEventById(int uma_id) {
            // SQL 查詢語句
            String sql = "SELECT * FROM events WHERE uma_id = ?;";

            try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql);
            ) {
                stmt.setInt(1, uma_id);

                // 執行查詢
                try (ResultSet rs = stmt.executeQuery()) {
                    boolean hasData = false;

                    while (rs.next()) {
                        hasData = true;

                        int event_id = rs.getInt("event_id");
                        String event_name = rs.getString("event_name");
                        String uma_name = rs.getString("uma_name");
                        String category = rs.getString("category");
                        System.out.println("event_id: " + event_id + ", event_name: " + event_name + ", uma_name: " + uma_name + ", category: " + category);
                    }
                    if (!hasData) {
                        System.out.println("查無資料");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    

    public static void GetUmaOutdoorEventById() {
        ;
    }

    // 獲取指定事件 ID 的選項資料
    // 這個方法會查詢資料庫中的 event_choices 表，根據 event_id
    public static void GetUmaEventChoiceByEventId(int event_id) {
        // SQL 查詢語句
        String sql = "SELECT * FROM event_choices WHERE event_id = ?;";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, event_id);

            // 執行查詢
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasData = false;

                while (rs.next()) {
                    hasData = true;

                    int choice_id = rs.getInt("choice_id");
                    String choice_label = rs.getString("choice_label");
                    int speed = rs.getInt("speed");
                    int stamina = rs.getInt("stamina");
                    int strength = rs.getInt("strength");
                    int willpower = rs.getInt("willpower");
                    int intelligence = rs.getInt("intelligence");
                    int hp = rs.getInt("hp");
                    int mood = rs.getInt("mood");
                    int skill_point = rs.getInt("skill_point");
                    String event_condition = rs.getString("event_condition");
                    Boolean is_random_value = rs.getBoolean("is_random_value");
                    Boolean is_random_attribute = rs.getBoolean("is_random_attribute");
                    Boolean is_random_block_training = rs.getBoolean("is_random_block_training");
                    System.out.println("choice_id: " + choice_id + ", choice_label: " + choice_label +
                            ", speed: " + speed + ", stamina: " + stamina + ", strength: " + strength +
                            ", willpower: " + willpower + ", intelligence: " + intelligence +
                            ", hp: " + hp + ", mood: " + mood + ", skill_point: " + skill_point +
                            ", event_condition: " + event_condition +
                            ", is_random_value: " + is_random_value +
                            ", is_random_attribute: " + is_random_attribute +
                            ", is_random_block_training: " + is_random_block_training);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 獲取指定 count 的 UMA 卡片資料
    // 這個方法會查詢資料庫中的 card_table 表，根據 count
    public static void GetUmaCardByCount(int count) {
        // SQL 查詢語句
        String sql = "SELECT * FROM card_table WHERE count = ?;";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, count);

            // 執行查詢
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasData = false;

                while (rs.next()) {
                    hasData = true;

                    int uma_id = rs.getInt("uma_id");
                    String uma_name = rs.getString("uma_name");
                    System.out.println("uma_id: " + uma_id + ", uma_name: " + uma_name);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 獲取指定 UMA ID 的卡片事件資料
    // 這個方法會查詢資料庫中的 card_events 表，根據 uma_id
    public static void GetCardEventByCardUmaId(int uma_id) {
        // SQL 查詢語句
        String sql = "SELECT * FROM card_events WHERE uma_id = ?;";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, uma_id);

            // 執行查詢
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasData = false;

                while (rs.next()) {
                    hasData = true;

                    int event_id = rs.getInt("event_id");
                    String uma_name = rs.getString("uma_name");
                    String category = rs.getString("category");
                    System.out.println("event_id: " + event_id + ", uma_name: " + uma_name + ", category: " + category);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 獲取指定事件 ID 的卡片選擇資料
    // 這個方法會查詢資料庫中的 card_event_choices 表，根據 card_event_id
    public static void GetCardEventChoiceByCardEventId(int card_event_id) {
        // SQL 查詢語句
        String sql = "SELECT * FROM card_event_choices WHERE event_id = ?;";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, card_event_id);

            // 執行查詢
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasData = false;

                while (rs.next()) {
                    hasData = true;

                    int choice_id = rs.getInt("choice_id");
                    String choice_label = rs.getString("choice_label");
                    int chain_index = rs.getInt("chain_index");
                    int speed = rs.getInt("speed");
                    int stamina = rs.getInt("stamina");
                    int strength = rs.getInt("strength");
                    int willpower = rs.getInt("willpower");
                    int intelligence = rs.getInt("intelligence");
                    int hp = rs.getInt("hp");
                    int mood = rs.getInt("mood");
                    int skill_point = rs.getInt("skill_point");
                    int character_favor = rs.getInt("character_favor");
                    String event_condition = rs.getString("event_condition");
                    Boolean is_random_value = rs.getBoolean("is_random_value");
                    Boolean is_random_attribute = rs.getBoolean("is_random_attribute");
                    Boolean is_random_block_training = rs.getBoolean("is_random_block_training");
                    Boolean is_end_chained_event = rs.getBoolean("is_end_chained_event");
                    System.out.println("choice_id: " + choice_id + ", choice_label: " + choice_label +
                            ", chain_index: " + chain_index + ", speed: " + speed +
                            ", stamina: " + stamina + ", strength: " + strength +
                            ", willpower: " + willpower + ", intelligence: " + intelligence +
                            ", hp: " + hp + ", mood: " + mood + ", skill_point: " + skill_point +
                            ", character_favor: " + character_favor +
                            ", event_condition: " + event_condition +
                            ", is_random_value: " + is_random_value +
                            ", is_random_attribute: " + is_random_attribute +
                            ", is_random_block_training: " + is_random_block_training +
                            ", is_end_chained_event: " + is_end_chained_event);

                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 獲取指定 CARD UMA ID 和等級的卡片資訊
    // 這個方法會查詢資料庫中的 card_info 表，根據 uma_id 和 level_name
    public static void GetCardInfoByCardUmaIdAndLevel(int uma_id, String level_name) {
        // SQL 查詢語句
        String sql = "SELECT * FROM card_info WHERE uma_id = ? AND level_name = ?;";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, uma_id);
            stmt.setString(2, level_name);

            // 執行查詢
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasData = false;

                while (rs.next()) {
                    hasData = true;

                    int card_info_id = rs.getInt("card_info_id");
                    String uma_name = rs.getString("uma_name");
                    int friend_bonus = rs.getInt("friend_bonus");
                    int motivation_bonus = rs.getInt("motivation_bonus");
                    int appear_rate_bonus = rs.getInt("appear_rate_bonus");
                    int initial_favor_gauge = rs.getInt("initial_favor_gauge");
                    int initial_speed_gauge = rs.getInt("initial_speed_gauge");
                    int initial_stamina_gauge = rs.getInt("initial_stamina_gauge");
                    int initial_strength_gauge = rs.getInt("initial_strength_gauge");
                    int initial_willpower_gauge = rs.getInt("initial_willpower_gauge");
                    int initial_intelligence_gauge = rs.getInt("initial_intelligence_gauge");
                    int speed_enhancement = rs.getInt("speed_enhancement");
                    int stamina_enhancement = rs.getInt("stamina_enhancement");
                    int strength_enhancement = rs.getInt("strength_enhancement");
                    int willpower_enhancement = rs.getInt("willpower_enhancement");
                    int intelligence_enhancement = rs.getInt("intelligence_enhancement");
                    int skill_point_enhancement = rs.getInt("skill_point_enhancement");
                    int intelligence_hp_recovery = rs.getInt("intelligence_hp_recovery");
                    int training_bonus = rs.getInt("training_bonus");
                    int failure_rate_bonus = rs.getInt("failure_rate_bonus");
                    System.out.println("card_info_id: " + card_info_id + ", uma_name: " + uma_name +
                            ", friend_bonus: " + friend_bonus + ", motivation_bonus: " + motivation_bonus +
                            ", appear_rate_bonus: " + appear_rate_bonus + ", initial_favor_gauge: " + initial_favor_gauge +
                            ", initial_speed_gauge: " + initial_speed_gauge + ", initial_stamina_gauge: " + initial_stamina_gauge +
                            ", initial_strength_gauge: " + initial_strength_gauge + ", initial_willpower_gauge: " + initial_willpower_gauge +
                            ", initial_intelligence_gauge: " + initial_intelligence_gauge +
                            ", speed_enhancement: " + speed_enhancement + ", stamina_enhancement: " + stamina_enhancement +
                            ", strength_enhancement: " + strength_enhancement + ", willpower_enhancement: " + willpower_enhancement +
                            ", intelligence_enhancement: " + intelligence_enhancement +
                            ", skill_point_enhancement: " + skill_point_enhancement +
                            ", intelligence_hp_recovery: " + intelligence_hp_recovery +
                            ", training_bonus: " + training_bonus +
                            ", failure_rate_bonus: " + failure_rate_bonus);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 獲取所有隨機事件的卡片事件資料
    // 這個方法會查詢資料庫中的 card_events 表，根據 category = "隨機事件"
    public static void GetAllCardRandomEvent() {
        // SQL 查詢語句
        String sql = "SELECT * FROM card_events WHERE category = \"隨機事件\";";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            // 執行查詢
            try (ResultSet rs = stmt.executeQuery()) {
                boolean hasData = false;

                while (rs.next()) {
                    hasData = true;

                    int event_id = rs.getInt("event_id");
                    String event_name = rs.getString("event_name");
                    int uma_id = rs.getInt("uma_id");
                    String uma_name = rs.getString("uma_name");
                    System.out.println("event_id: " + event_id + ", event_name: " + event_name + ", uma_id: " + uma_id + ", uma_name: " + uma_name);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
