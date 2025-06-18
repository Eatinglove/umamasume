package com.example;
//�x�s�C�@�^�X���ƭ�
import java.util.Random;
public class RoundInfo {
    private double[][] roundStat = new double[5][5];
    //[5]speed stamina power will knowledge
    //[4]mood people friend trainbonus(�F�l�[���A�ͱ��[���A�V�m�[���A�H�Y�A�V�m)
    public void newTurn(Uma uma, Card[] card){

        for (int i = 0; i < 5; i++) {
            roundStat[i][1] = 1.0; 
            }

        for(int i=0;i<5;i++){
            Random rand = new Random();
            int randomValue = rand.nextInt(6);
            if(randomValue==5){//���X�{
                continue;
            }
            roundStat[randomValue][0]+=card[i].getMood()/100;//�F�l�ۥ[
            roundStat[randomValue][1]*=(1+(card[i].getFriend())/100);//�ͱ��ۭ�
            roundStat[randomValue][2]+=card[i].getTrain()/100;//�V�m�[��
            roundStat[randomValue][3]+=1;//�H��+1
            roundStat[randomValue][4]+=card[i].getTrain();//basic
            
        }
    }

    public double[] getRoundStat(int type){
        return roundStat[type];
    }
}
