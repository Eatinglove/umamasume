package com.example;

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

        speedTrainStat.setBasicValue(uma, 1,1);
        powerTrainStat.setBasicValue(uma ,1,0);

        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(1));
        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(1));
        Train.trainSpeedCal(uma, speedTrainStat, powerTrainStat);
    }

    //耐力訓練
    public void staminaTrain(Uma uma){
        TrainStat staminaTrainStat = new TrainStat();
        TrainStat willTrainStat = new TrainStat();

        staminaTrainStat.setBasicValue(uma, 2,1);
        willTrainStat.setBasicValue(uma ,2,0);

        staminaTrainStat.setOtherStuff(roundInfo.getRoundStat(2));
        willTrainStat.setOtherStuff(roundInfo.getRoundStat(2));
        Train.trainStaminaCal(uma, staminaTrainStat, willTrainStat);
    }

    //力量訓練
    public void powerTrain(Uma uma){
        TrainStat powerTrainStat = new TrainStat();
        TrainStat staminaTrainStat = new TrainStat();

        powerTrainStat.setBasicValue(uma, 3,1);
        staminaTrainStat.setBasicValue(uma, 3,0);

        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(3));
        staminaTrainStat.setOtherStuff(roundInfo.getRoundStat(3));
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

        willTrainStat.setOtherStuff(roundInfo.getRoundStat(4));
        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(4));
        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(4));

        Train.trainWillCal(uma, willTrainStat, speedTrainStat, powerTrainStat);
    }

    //智力訓練
    public void knowledgeTrain(Uma uma){
        TrainStat knowledgeTrainStat = new TrainStat();
        TrainStat speedTrainStat = new TrainStat();

        knowledgeTrainStat.setBasicValue(uma, 5,1);
        speedTrainStat.setBasicValue(uma, 5,0);

        knowledgeTrainStat.setOtherStuff(roundInfo.getRoundStat(5));
        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(5));

        Train.trainKnowledgeCal(uma, knowledgeTrainStat, speedTrainStat);
    }

    //外出
    public void goOut(Uma uma){

    }

    //休息
    public void rest(){

    }

    //增加屬性
    public void addStat(){

    }

    //比賽
    public void race(){

    }

    //保健室
    public void hospital(){

    }

    //還有一些
}
