package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class DataCollection {
    // 隨機數生成器
    private static Random random = new Random();

    // 用於存儲已經處理過的事件 ID，避免重複
    private static HashSet<Integer> umaEventSet = new HashSet<>();
    private static HashSet<Integer> cardEventSet = new HashSet<>();

    // 用於存儲卡片的連鎖ID和計數
    private static HashMap<Integer, Integer> umaCardChainedCount = new HashMap<>();

    // MySQL 資料庫連線設定
    // 請根據實際情況修改以下參數
    private static String url = "jdbc:mysql://localhost:3306/uma_db";
    private static String user = "root";
    private static String password = "Sawandcamein0705";
    
    static class Event {
        Integer event_id;
        String event_name;
        Integer uma_id;
        String uma_name;
        String category;
        List<Choice> choices = new ArrayList<>();
        
    }

    static class Choice {
        Integer choice_id;
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

    static class CardEvent {
        Integer event_id;
        String event_name;
        Integer uma_id;
        String uma_name;
        String category;
        List<CardChoice> choices = new ArrayList<>();
    }

    static class CardChoice {
        Integer choice_id;
        Integer event_id;
        String choice_label;
        Integer chain_index;

        Integer speed;
        Integer stamina;
        Integer strength;
        Integer willpower;
        Integer intelligence;
        Integer hp;
        Integer mood;
        Integer skill_point;
        Integer character_favor;
        String event_condition;

        boolean is_random_value;
        boolean is_random_attribute;
        boolean is_random_block_training;
        boolean is_end_chained_event;
    }

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
    // 並返回一個 Event 物件的列表
    public static List<Event> GetUmaEventsById(int uma_id) {
        //init
        List<Event> events = new ArrayList<>();

        // SQL 查詢語句
        String sql = "SELECT * FROM events WHERE uma_id = ? and category != \"外出事件\";";

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
                    Event event = new Event();

                    event.event_id = rs.getInt("event_id");
                    event.event_name = rs.getString("event_name");
                    event.uma_id = rs.getInt("uma_id");
                    event.uma_name = rs.getString("uma_name");
                    event.category = rs.getString("category");
                    event.choices = GetUmaEventChoicesByEventId(event.event_id);

                    events.add(event);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }
    
    // 獲取指定 UMA ID 的外出事件資料
    // 這個方法會查詢資料庫中的 events 表，根據 uma_id
    // 並返回一個 Event 物件的列表
    public static List<Event> GetUmaOutdoorEventsById(int uma_id) {
        //init
        List<Event> outdoorEvents = new ArrayList<>();

        // SQL 查詢語句
            String sql = "SELECT * FROM events WHERE uma_id = ? and category = \"外出事件\";";

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
                        Event event = new Event();

                        event.event_id = rs.getInt("event_id");
                        event.event_name = rs.getString("event_name");
                        event.uma_id = rs.getInt("uma_id");
                        event.uma_name = rs.getString("uma_name");
                        event.category = rs.getString("category");

                        outdoorEvents.add(event);
                    }
                    if (!hasData) {
                        System.out.println("查無資料");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        return outdoorEvents;
    }

    // 獲取指定事件 ID 的選項資料
    // 這個方法會查詢資料庫中的 event_choices 表，根據 event_id
    // 並返回一個 Choice 物件的列表
    public static List<Choice> GetUmaEventChoicesByEventId(int event_id) {
        //init
        List<Choice> choices = new ArrayList<>();

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
                    Choice choice = new Choice();

                    choice.choice_id = rs.getInt("choice_id");
                    choice.event_id = rs.getInt("event_id");
                    choice.choice_label = rs.getString("choice_label");
                    choice.speed = rs.getInt("speed");
                    choice.stamina = rs.getInt("stamina");
                    choice.strength = rs.getInt("strength");
                    choice.willpower = rs.getInt("willpower");
                    choice.intelligence = rs.getInt("intelligence");
                    choice.hp = rs.getInt("hp");
                    choice.mood = rs.getInt("mood");
                    choice.skill_point = rs.getInt("skill_point");
                    choice.event_condition = rs.getString("event_condition");
                    choice.is_random_value = rs.getBoolean("is_random_value");
                    choice.is_random_attribute = rs.getBoolean("is_random_attribute");
                    choice.is_random_block_training = rs.getBoolean("is_random_block_training");

                    choices.add(choice);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return choices;
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
    public static List<CardEvent> GetCardEventByCardUmaId(int uma_id) {
        //init
        List<CardEvent> cardEvents = new ArrayList<>();

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
                    CardEvent cardEvent = new CardEvent();

                    cardEvent.event_id = rs.getInt("event_id");
                    cardEvent.event_name = rs.getString("event_name");
                    cardEvent.uma_id = rs.getInt("uma_id");
                    cardEvent.uma_name = rs.getString("uma_name");
                    cardEvent.category = rs.getString("category");
                    cardEvent.choices = GetCardEventChoiceByCardEventId(cardEvent.event_id);

                    cardEvents.add(cardEvent);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardEvents;
    }

    // 獲取指定事件 ID 的卡片選項資料
    // 這個方法會查詢資料庫中的 card_event_choices 表，根據 card_event_id
    // 並返回一個 CardChoice 物件的列表
    public static List<CardChoice> GetCardEventChoiceByCardEventId(int card_event_id) {
        //init
        List<CardChoice> cardChoices = new ArrayList<>();

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
                    CardChoice cardChoice = new CardChoice();

                    cardChoice.choice_id = rs.getInt("choice_id");
                    cardChoice.event_id = rs.getInt("event_id");
                    cardChoice.choice_label = rs.getString("choice_label");
                    cardChoice.chain_index = rs.getInt("chain_index");
                    cardChoice.speed = rs.getInt("speed");
                    cardChoice.stamina = rs.getInt("stamina");
                    cardChoice.strength = rs.getInt("strength");
                    cardChoice.willpower = rs.getInt("willpower");
                    cardChoice.intelligence = rs.getInt("intelligence");
                    cardChoice.hp = rs.getInt("hp");
                    cardChoice.mood = rs.getInt("mood");
                    cardChoice.skill_point = rs.getInt("skill_point");
                    cardChoice.character_favor = rs.getInt("character_favor");
                    cardChoice.event_condition = rs.getString("event_condition");
                    cardChoice.is_random_value = rs.getBoolean("is_random_value");
                    cardChoice.is_random_attribute = rs.getBoolean("is_random_attribute");
                    cardChoice.is_random_block_training = rs.getBoolean("is_random_block_training");
                    cardChoice.is_end_chained_event = rs.getBoolean("is_end_chained_event");

                    cardChoices.add(cardChoice);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cardChoices;
    }

    // 獲取指定 CARD UMA ID 和等級的卡片資訊
    // 這個方法會查詢資料庫中的 card_info 表，根據 uma_id 和 level_name
    public static void GetCardInfoByCardUmaIdAndLevel(int uma_id) {
        // SQL 查詢語句
        String sql = "SELECT * FROM card_info WHERE uma_id = ? AND level_name = \"MAX BREAK\";";

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
    public static CardEvent getAllCardRandomEvent() {
        //init 
        List<CardEvent> events = new ArrayList<>();

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
                    CardEvent event = new CardEvent();

                    event.event_id = rs.getInt("event_id");
                    event.event_name = rs.getString("event_name");
                    event.uma_id = rs.getInt("uma_id");
                    event.uma_name = rs.getString("uma_name");
                    event.category = rs.getString("category");
                    event.choices = GetCardEventChoiceByCardEventId(event.event_id);

                    events.add(event);
                }
                if (!hasData) {
                    System.out.println("查無資料");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        random.setSeed(System.currentTimeMillis());

        while (true) {
            int i = 0;
            // 隨機選擇一個事件
            int randomIndex = random.nextInt(events.size());
            CardEvent randomEvent = events.get(randomIndex % events.size());

            // 檢查是否已經處理過這個事件
            if (!umaEventSet.contains(randomEvent.event_id)) {
                // 如果沒有處理過，則將其加入已處理集合並返回
                umaEventSet.add(randomEvent.event_id);
                return randomEvent;
            }

            if (i > 100) {
                // 如果已經嘗試了 100 次還沒有找到未處理的事件，則返回 null
                System.out.println("沒有找到未處理的隨機事件");
                return null;
            }
            i++;
        }

    }

    // 獲取指定 UMA ID 的隨機一個事件並傳回
    public static Event getRandomUmaEvent(int uma_id) {
        random.setSeed(System.currentTimeMillis());
        List<Event> events = GetUmaEventsById(uma_id);

        // 如果沒有找到任何事件，則返回 null
        if (events.isEmpty()) {
            System.out.println("沒有找到任何事件");
            return null;
        }

        while (true) {
            int i = 0;
            // 隨機選擇一個事件
            int randomIndex = random.nextInt(events.size());
            Event randomEvent = events.get(randomIndex % events.size());

            // 檢查是否已經處理過這個事件
            if (!umaEventSet.contains(randomEvent.event_id)) {
                // 如果沒有處理過，則將其加入已處理集合並返回
                umaEventSet.add(randomEvent.event_id);
                return randomEvent;
            }

            if (i > 100) {
                // 如果已經嘗試了 100 次還沒有找到未處理的事件，則返回 null
                System.out.println("沒有找到未處理的事件");
                return null;
            }
            i++;
        }
    }

    // 獲取指定 UMA ID 的隨機一個外出事件並傳回
    public static Event getRandomOutdoorEvent(int uma_id) {
        random.setSeed(System.currentTimeMillis());
        List<Event> outdoorEvents = GetUmaOutdoorEventsById(uma_id);

        // 如果沒有找到任何外出事件，則返回 null
        if (outdoorEvents.isEmpty()) {
            System.out.println("沒有找到任何外出事件");
            return null;
        }

        while (true) {
            int i = 0;
            // 隨機選擇一個外出事件
            int randomIndex = random.nextInt(outdoorEvents.size());
            Event randomOutdoorEvent = outdoorEvents.get(randomIndex % outdoorEvents.size());

            // 檢查是否已經處理過這個事件
            if (!umaEventSet.contains(randomOutdoorEvent.event_id)) {
                // 如果沒有處理過，則將其加入已處理集合並返回
                umaEventSet.add(randomOutdoorEvent.event_id);
                return randomOutdoorEvent;
            }

            if (i > 100) {
                // 如果已經嘗試了 100 次還沒有找到未處理的外出事件，則返回 null
                System.out.println("沒有找到未處理的外出事件");
                return null;
            }
            i++;
        }
    }

    // 獲取指定 UMA ID 的隨機一個卡片事件並傳回
    public static CardEvent getRandomCardEvent(int uma_id) {
        random.setSeed(System.currentTimeMillis());
        List<CardEvent> cardEvents = GetCardEventByCardUmaId(uma_id);

        // 如果沒有找到任何事件，則返回 null
        if (cardEvents.isEmpty()) {
            System.err.println("沒有找到任何卡片事件");
            return null;
        }

        while (true) {
            int i = 0;

            // 隨機選擇一個卡片事件
            int randomIndex = random.nextInt(cardEvents.size());
            CardEvent randomCardEvent = cardEvents.get(randomIndex % cardEvents.size());

            // 檢查是否已經處理過這個事件
            if (!cardEventSet.contains(randomCardEvent.event_id)) {
                // 如果沒有連鎖計數，則初始化連鎖計數
                if (umaCardChainedCount.get(randomCardEvent.event_id) == null) {
                    // 初始化連鎖計數為 0
                    umaCardChainedCount.put(randomCardEvent.event_id, 0);
                }

                // 檢查是否有連鎖選項
                if (randomCardEvent.choices.get(0).chain_index != 0) {
                    int chainedCount = umaCardChainedCount.get(randomCardEvent.event_id);
                    int chain_index = randomCardEvent.choices.get(0).chain_index;

                    if (chain_index == chainedCount + 1){
                        // 如果連鎖計數等於選項的連鎖索引，則返回這個事件
                        umaCardChainedCount.put(randomCardEvent.event_id, chainedCount + 1);
                        return randomCardEvent;
                    }

                } else {
                    // 如果沒有連鎖選項，則直接返回這個事件
                    cardEventSet.add(randomCardEvent.event_id);
                    return randomCardEvent;
                }
            }

            if (i > 100) {
                // 如果已經嘗試了 100 次還沒有找到未處理的事件，則返回 null
                System.out.println("沒有找到未處理的卡片事件");
                return null;
            }
            i++;
        }
    }
}




