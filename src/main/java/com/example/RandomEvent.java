package com.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class RandomEvent {
    private Uma uma = new Uma();

    public void setUma(Uma uma){
        this.uma=uma;
    }

    public void chooseEvent(){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int type = random.nextInt(2);
        if(type==0){
            cardEvent(uma.getId());
        }else if(type==1){
            umaEvent(uma.getId());
        }
    }

    public void cardEvent(int UmaId, List<Integer> cardIds){
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        int type = random.nextInt(10);

        if(type<7){
            // 70% 攜帶的卡片事件
            int card_count = random.nextInt(6); // 隨機1到6張卡片
            int card_uma_id = cardIds.get(card_count);
            DataCollection.CardEvent event = DataCollection.getRandomCardEvent(card_uma_id);

            event.choices.forEach(choice -> {
                // 處理每個選項
                if (choice.choice_label.equals("UP") || choice.choice_label.equals("None")) {
                    uma.changeSpeed(choice.speed);
                    uma.changeStamina(choice.stamina);
                    uma.changePower(choice.strength);
                    uma.changeWill(choice.willpower);
                    uma.changeKnowledge(choice.intelligence);
                    uma.changeHp(choice.hp);
                    uma.changeMood(choice.mood);
                    uma.changePt(choice.skill_point);

                    //condition

                    //favor

                    //random value
                    
                    //random attribute
                    
                    //random block training
                    
                    //is_end_chained_event
                }
            });

        }else if(type>=7){
            // 30% 隨機事件
            DataCollection.CardEvent event = DataCollection.getAllCardRandomEvent();

            event.choices.forEach(choice -> {
                // 處理每個選項
                if (choice.choice_label.equals("UP") || choice.choice_label.equals("None")) {
                    uma.changeSpeed(choice.speed);
                    uma.changeStamina(choice.stamina);
                    uma.changePower(choice.strength);
                    uma.changeWill(choice.willpower);
                    uma.changeKnowledge(choice.intelligence);
                    uma.changeHp(choice.hp);
                    uma.changeMood(choice.mood);
                    uma.changePt(choice.skill_point);
                }
            });
        }
    }

    public void umaEvent(int UmaId){
        DataCollection.Event event = DataCollection.getRandomUmaEvent(UmaId);
        
        event.choices.forEach(choice -> {
                // 處理每個選項
                if (choice.choice_label.equals("UP") || choice.choice_label.equals("None")) {
                    uma.changeSpeed(choice.speed);
                    uma.changeStamina(choice.stamina);
                    uma.changePower(choice.strength);
                    uma.changeWill(choice.willpower);
                    uma.changeKnowledge(choice.intelligence);
                    uma.changeHp(choice.hp);
                    uma.changeMood(choice.mood);
                    uma.changePt(choice.skill_point);
                }
            });
    }

    public void umaOutdoorEvent(int UmaId){
        DataCollection.Event event = DataCollection.getRandomOutdoorEvent(UmaId);
        
        event.choices.forEach(choice -> {
                // 處理每個選項
                if (choice.choice_label.equals("UP") || choice.choice_label.equals("None")) {
                    uma.changeSpeed(choice.speed);
                    uma.changeStamina(choice.stamina);
                    uma.changePower(choice.strength);
                    uma.changeWill(choice.willpower);
                    uma.changeKnowledge(choice.intelligence);
                    uma.changeHp(choice.hp);
                    uma.changeMood(choice.mood);
                    uma.changePt(choice.skill_point);
                }
            });
    }
}
