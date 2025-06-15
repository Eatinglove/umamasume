package com.example;

//�ϥΪ̩ҿ�ܪ����O(�ʧ@)
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
    //�ǤJ���A�V�m����(type12345�O�t�׭@�O�O�q�کʴ��O)�A�D�ưV�m(1�O�D0�O��)
    //�t�װV�m
    public void speedTrain(Uma uma){
        TrainStat speedTrainStat = new TrainStat();
        TrainStat powerTrainStat = new TrainStat();

        speedTrainStat.setBasicValue(uma, 1,1);
        powerTrainStat.setBasicValue(uma ,1,0);

        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(1));
        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(1));
        Train.trainSpeedCal(uma, speedTrainStat, powerTrainStat);
    }

    //�@�O�V�m
    public void staminaTrain(Uma uma){
        TrainStat staminaTrainStat = new TrainStat();
        TrainStat willTrainStat = new TrainStat();

        staminaTrainStat.setBasicValue(uma, 2,1);
        willTrainStat.setBasicValue(uma ,2,0);

        staminaTrainStat.setOtherStuff(roundInfo.getRoundStat(2));
        willTrainStat.setOtherStuff(roundInfo.getRoundStat(2));
        Train.trainStaminaCal(uma, staminaTrainStat, willTrainStat);
    }

    //�O�q�V�m
    public void powerTrain(Uma uma){
        TrainStat powerTrainStat = new TrainStat();
        TrainStat staminaTrainStat = new TrainStat();

        powerTrainStat.setBasicValue(uma, 3,1);
        staminaTrainStat.setBasicValue(uma, 3,0);

        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(3));
        staminaTrainStat.setOtherStuff(roundInfo.getRoundStat(3));
        Train.trainPowerCal(uma, powerTrainStat, staminaTrainStat);
    }

    //�کʰV�m
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

    //���O�V�m
    public void knowledgeTrain(Uma uma){
        TrainStat knowledgeTrainStat = new TrainStat();
        TrainStat speedTrainStat = new TrainStat();

        knowledgeTrainStat.setBasicValue(uma, 5,1);
        speedTrainStat.setBasicValue(uma, 5,0);

        knowledgeTrainStat.setOtherStuff(roundInfo.getRoundStat(5));
        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(5));

        Train.trainKnowledgeCal(uma, knowledgeTrainStat, speedTrainStat);
    }

    //�~�X
    public void goOut(Uma uma){

    }

    //��
    public void rest(){

    }

    //�W�[�ݩ�
    public void addStat(){

    }

    //����
    public void race(){

    }

    //�O����
    public void hospital(){

    }

    //�٦��@��
}
