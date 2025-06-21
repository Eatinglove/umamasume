package com.example;
//儲存每一回合的數值
import java.util.Random;
public class RoundInfo {
    private double[][] roundStat = new double[5][5];
    public boolean[] isFriend= new boolean[5];
    //[5]speed stamina power will knowledge
    //[5]mood people friend trainbonus basic(幹勁加成，友情加成，訓練加成，人頭，訓練)
    public void newTurn(Uma uma, Card[] card){
        
        for (int i = 0; i < 5; i++) {
            roundStat[i][1] = 1.0; 
            }

        for(int i=0;i<6;i++){//六張卡
            Random rand = new Random();
            int randomValue = rand.nextInt(6);//0=speed, 1=stamina, 2=power, 3=will, 4=knowledge
            if(randomValue==5){//不出現
                continue;
            }
            roundStat[randomValue][0]+=card[i].getMood()/100;//幹勁相加
            roundStat[randomValue][1]*=(1+(card[i].getFriend())/100);//友情相乘
            roundStat[randomValue][2]+=card[i].getTrain()/100;//訓練加成
            roundStat[randomValue][3]+=1;//人數+1
            roundStat[randomValue][4]+=card[i].getTrain();//basic
            if(card[i].getFriend()>80){
                isFriend[randomValue]=true;
            }
            card[i].setTrainEquip(randomValue);//紀錄這張卡這回合出現在哪個訓練
        }
    }

    public double[] getRoundStat(int type){
        return roundStat[type];
    }

    public void setFriendFalse(){
        for(int i=0;i<5;i++){
            isFriend[i]=false;
        }
    }

    public boolean[] getIsFriend(){
        return isFriend;
    }
}
