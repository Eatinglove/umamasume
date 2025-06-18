package com.example;
//儲存每一回合的數值
import java.util.Random;
public class RoundInfo {
    private double[][] roundStat = new double[5][4];
    //[5]speed stamina power will knowledge
    //[4]mood people friend trainbonus(幹勁加成，友情加成，訓練加成，人頭)
    public void newTurn(Uma uma, Card[] card){
        for(int i=0;i<5;i++){
            Random rand = new Random();
            int randomValue = rand.nextInt(6);
            if(randomValue==5){//不出現
                continue;
            }
            roundStat[randomValue][0]+=card[i].getMood()/100;//幹勁相加
            roundStat[randomValue][1]*=(1+(card[i].getFriend())/100);//友情相乘
            roundStat[randomValue][2]+=card[i].getTrain()/100;
            roundStat[randomValue][3]+=1;//人數+1
        }
    }

    public double[] getRoundStat(int type){
        return roundStat[type];
    }
}
