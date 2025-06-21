package com.example;
import java.util.Random;
//使用者所選擇的指令(動作)
public class Choice {
    private Label label = new Label();
    private RoundInfo roundInfo = new RoundInfo();
    private Uma uma = new Uma();
    private Card[] card = new Card[6];
    private int choose;
    private Train train = new Train();

    public void setUma(Uma uma){
        this.uma=uma;
    } 
    public void setCard(Card[] card){
        this.card=card;
    }
    public void setLabel(Label label){
        this.label = new Label();
    }
    public void newTurn(){
        roundInfo.newTurn(uma, card);
        train.setTrainLabel(choose, label, train.getAddValue(choose));
    }
    public void setTrainValue(){
    
        train.setSpeedValue(uma, roundInfo);
        train.setStaminaValue(uma, roundInfo);
        train.setPowerValue(uma, roundInfo);
        train.setWillValue(uma, roundInfo);
        train.setKnowledgeValue(uma, roundInfo);
        
    }
    public void doTrain(int type){
        // HP過低機率失敗
        if (uma.getHp() < 50) {
            int prob = 100 - (50 - uma.getHp()) * 2;
            int success = new Random().nextInt(100);
            if (success > prob) {
                uma.changeMood(-1);
                switch (type) {
                    case 1 -> uma.changeSpeed(-5);
                    case 2 -> uma.changeStamina(-5);
                    case 3 -> uma.changePower(-5);
                    case 4 -> uma.changeWill(-5);
                    case 5 -> uma.changeKnowledge(-5);
                }
                return;
            }
        }

        switch (type) {
            case 1 -> train.speedTrain(uma);
            case 2 -> train.staminaTrain(uma);
            case 3 -> train.powerTrain(uma);
            case 4 -> train.willTrain(uma);
            case 5 -> train.knowledgeTrain(uma);
        }

        this.choose = type - 1; // 儲存這回合的訓練選擇（0~4）
    }

    //外出
    public void goOut(Uma uma){
        Random rand = new Random();
        int goOutChoice = rand.nextInt();
        switch (goOutChoice) {
            case 0://唱歌
                uma.changeMood(2);
                break;
            case 1://散步
                uma.changeMood(1);
                uma.changeHp(10);
                break;
            case 2://神社
                uma.changeMood(1);
                int hp = rand.nextInt(3);
                if(hp == 0 ){
                    uma.changeHp(10);//小吉
                }else if(hp == 1){
                    uma.changeHp(20);//中吉
                }else if(hp == 2 ){
                    uma.changeHp(30);//大吉
                }
                break;
            default:
                break;
        }
    }

    //休息
    public void rest(Uma uma){
        Random rand = new Random();
        int hp = rand.nextInt(100);
        if(hp<=33){
            uma.changeHp(30);
        }else if(hp>33 && hp<=66){
            uma.changeHp(50);
        }else{
            uma.changeHp(70);
        }
    }

    //增加屬性
    public void addStat(Uma uma, int speed, int stamina, int power, int will, int knowledge){
        uma.changeSpeed(speed);
        uma.changeStamina(stamina);
        uma.changePower(power);
        uma.changeWill(will);
        uma.changeKnowledge(knowledge);
    }

    //比賽
    public void race(Uma uma, int speed, int stamina, int power, int will, int knowledge){
        uma.changeSpeed(speed);
        uma.changeStamina(stamina);
        uma.changePower(power);
        uma.changeWill(will);
        uma.changeKnowledge(knowledge);
        uma.changeHp(-20);
    }

    //保健室
    public void hospital(){
        uma.changeHp(20);
    }
    //如果卡片這回合出現在的地方等於這回合選的訓練，對應卡片的情誼增加
    public void endRound(Uma uma){
        for(int i=0;i<6;i++){
            if(card[i].getTrainEquip()==choose){
                if(uma.condition.isLovelove()){
                    card[i].setLove(7);
                }
                card[i].setLove(5);
            }
        }
    }
    //還有一些
}
