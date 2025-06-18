package com.example;
import java.util.Random;
//使用者所選擇的指令(動作)
public class Choice {
    private RoundInfo roundInfo = new RoundInfo();
    private Uma uma = new Uma();
    private Card[] card = new Card[6];
    public void setUma(Uma uma){
        this.uma=uma;
    } 
    public void setCard(Card[] card){
        this.card=card;
    }
    public void newTurn(){
        roundInfo.newTurn(uma, card);
    }
    //傳入馬，訓練種類(type12345是速度耐力力量根性智力)，主副訓練(1是主0是副)
    //速度訓練
    public void speedTrain(Uma uma){
        TrainStat speedTrainStat = new TrainStat();
        TrainStat powerTrainStat = new TrainStat();
        //計算basic
        speedTrainStat.setBasicValue(uma, 1,1);
        powerTrainStat.setBasicValue(uma ,1,0);
        //計算其他(友情，人頭等)
        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(0));
        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(0));
        //跑訓練
        Train.trainSpeedCal(uma, speedTrainStat, powerTrainStat);
    }

    //耐力訓練
    public void staminaTrain(Uma uma){
        TrainStat staminaTrainStat = new TrainStat();
        TrainStat willTrainStat = new TrainStat();

        staminaTrainStat.setBasicValue(uma, 2,1);
        willTrainStat.setBasicValue(uma ,2,0);

        staminaTrainStat.setOtherStuff(roundInfo.getRoundStat(1));
        willTrainStat.setOtherStuff(roundInfo.getRoundStat(1));
        Train.trainStaminaCal(uma, staminaTrainStat, willTrainStat);
    }

    //力量訓練
    public void powerTrain(Uma uma){
        TrainStat powerTrainStat = new TrainStat();
        TrainStat staminaTrainStat = new TrainStat();

        powerTrainStat.setBasicValue(uma, 3,1);
        staminaTrainStat.setBasicValue(uma, 3,0);

        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(2));
        staminaTrainStat.setOtherStuff(roundInfo.getRoundStat(2));
        Train.trainPowerCal(uma, powerTrainStat, staminaTrainStat);
    }

    //根性訓練
    public void willTrain(Uma uma){
        TrainStat willTrainStat = new TrainStat();
        TrainStat speedTrainStat = new TrainStat();
        TrainStat powerTrainStat = new TrainStat();

        willTrainStat.setBasicValue(uma, 4,1);
        speedTrainStat.setBasicValue(uma, 4,0);
        powerTrainStat.setBasicValue(uma, 4,2);

        willTrainStat.setOtherStuff(roundInfo.getRoundStat(3));
        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(3));
        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(3));

        Train.trainWillCal(uma, willTrainStat, speedTrainStat, powerTrainStat);
    }

    //智力訓練
    public void knowledgeTrain(Uma uma){
        TrainStat knowledgeTrainStat = new TrainStat();
        TrainStat speedTrainStat = new TrainStat();

        knowledgeTrainStat.setBasicValue(uma, 5,1);
        speedTrainStat.setBasicValue(uma, 5,0);

        knowledgeTrainStat.setOtherStuff(roundInfo.getRoundStat(4));
        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(4));

        Train.trainKnowledgeCal(uma, knowledgeTrainStat, speedTrainStat);
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
    public void addStat(){

    }

    //比賽
    public void race(){

    }

    //保健室
    public void hospital(){
        uma.changeHp(20);
    }

    //還有一些
}
