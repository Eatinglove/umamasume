package com.example;
import java.util.Random;
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
        //�p��basic
        speedTrainStat.setBasicValue(uma, 1,1);
        powerTrainStat.setBasicValue(uma ,1,0);
        //�p���L(�ͱ��A�H�Y��)
        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(0));
        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(0));
        //�]�V�m
        Train.trainSpeedCal(uma, speedTrainStat, powerTrainStat);
    }

    //�@�O�V�m
    public void staminaTrain(Uma uma){
        TrainStat staminaTrainStat = new TrainStat();
        TrainStat willTrainStat = new TrainStat();

        staminaTrainStat.setBasicValue(uma, 2,1);
        willTrainStat.setBasicValue(uma ,2,0);

        staminaTrainStat.setOtherStuff(roundInfo.getRoundStat(1));
        willTrainStat.setOtherStuff(roundInfo.getRoundStat(1));
        Train.trainStaminaCal(uma, staminaTrainStat, willTrainStat);
    }

    //�O�q�V�m
    public void powerTrain(Uma uma){
        TrainStat powerTrainStat = new TrainStat();
        TrainStat staminaTrainStat = new TrainStat();

        powerTrainStat.setBasicValue(uma, 3,1);
        staminaTrainStat.setBasicValue(uma, 3,0);

        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(2));
        staminaTrainStat.setOtherStuff(roundInfo.getRoundStat(2));
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

        willTrainStat.setOtherStuff(roundInfo.getRoundStat(3));
        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(3));
        powerTrainStat.setOtherStuff(roundInfo.getRoundStat(3));

        Train.trainWillCal(uma, willTrainStat, speedTrainStat, powerTrainStat);
    }

    //���O�V�m
    public void knowledgeTrain(Uma uma){
        TrainStat knowledgeTrainStat = new TrainStat();
        TrainStat speedTrainStat = new TrainStat();

        knowledgeTrainStat.setBasicValue(uma, 5,1);
        speedTrainStat.setBasicValue(uma, 5,0);

        knowledgeTrainStat.setOtherStuff(roundInfo.getRoundStat(4));
        speedTrainStat.setOtherStuff(roundInfo.getRoundStat(4));

        Train.trainKnowledgeCal(uma, knowledgeTrainStat, speedTrainStat);
    }

    //�~�X
    public void goOut(Uma uma){
        Random rand = new Random();
        int goOutChoice = rand.nextInt();
        switch (goOutChoice) {
            case 0://�ۺq
                uma.changeMood(2);
                break;
            case 1://���B
                uma.changeMood(1);
                uma.changeHp(10);
                break;
            case 2://����
                uma.changeMood(1);
                int hp = rand.nextInt(3);
                if(hp == 0 ){
                    uma.changeHp(10);//�p�N
                }else if(hp == 1){
                    uma.changeHp(20);//���N
                }else if(hp == 2 ){
                    uma.changeHp(30);//�j�N
                }
                break;
            default:
                break;
        }
    }

    //��
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

    //�W�[�ݩ�
    public void addStat(){

    }

    //����
    public void race(){

    }

    //�O����
    public void hospital(){
        uma.changeHp(20);
    }

    //�٦��@��
}
