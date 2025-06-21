package com.example;
//把每回合的數值存在這裡，之後從這裡拿取資訊來計算
public class TrainStat {
    private double basic;
    private double friend;
    private double mood;
    private double people;
    private double trainBonus;
    private int hpcost;
    //設定basic數值
    public void setBasicValue(Uma uma, int type, int main){
        int level;
        switch(type){
            case 1: // speed
                level = uma.getSpeedLevel();
                if(main == 1){
                    this.basic = TrainData.SPEED_GAIN[level - 1][0];//speed 
                } else if(main == 0){
                    this.basic = TrainData.SPEED_GAIN[level - 1][1];//power
                }
                this.hpcost = TrainData.SPEED_GAIN[level-1][2];//hpcost
                break;
            case 2: // stamina
                level = uma.getStaminaLevel();
                if(main == 1){
                    this.basic = TrainData.STAMINA_GAIN[level - 1][0];//stamina
                } else if(main == 0){
                    this.basic = TrainData.STAMINA_GAIN[level - 1][1];//will
                }
                this.hpcost = TrainData.STAMINA_GAIN[level-1][2];//hpcost
                break;
            case 3: // power
                level = uma.getPowerLevel();
                if(main == 1){
                    this.basic = TrainData.POWER_GAIN[level - 1][1];//power
                } else if(main == 0){
                    this.basic = TrainData.POWER_GAIN[level - 1][0];//stamina
                }
                this.hpcost = TrainData.POWER_GAIN[level-1][2];//hpcost
                break;
            case 4: // will
                level = uma.getWillLevel();
                if(main == 1){
                    this.basic = TrainData.WILL_GAIN[level - 1][2];//will
                } else if(main == 0){
                    this.basic = TrainData.WILL_GAIN[level - 1][0];//speed
                } else if(main ==2){
                    this.basic = TrainData.WILL_GAIN[level-1][1];//power
                }
                this.hpcost = TrainData.WILL_GAIN[level-1][3];//hpcost
                break;
            case 5: // knowledge
                level = uma.getKnowledgeLevel();
                if(main == 1){
                    this.basic = TrainData.KNOWLEDGE_GAIN[level - 1][1];//knowledge 
                } else if(main == 0){
                    this.basic = TrainData.KNOWLEDGE_GAIN[level - 1][0];//speed 
                }
                this.hpcost = TrainData.KNOWLEDGE_GAIN[level-1][2];//hpcost
                break;
            default:
                break;
        }
    }
    //設定其他的數值
    public void setOtherStuff(double[] roundStat){
        this.mood=roundStat[0];
        this.friend=roundStat[1];
        this.trainBonus=roundStat[2];
        this.people=1 + 0.05*roundStat[3];//1+人頭數*0.05
        this.basic= this.basic+roundStat[4];//加上basic
    }

    //一般訓練
    public double getValue(){

        double value = 0;

        value = (basic)*(mood)*(trainBonus)*(people);

        return value;
    }
    
    //友情訓練
    public double getFriendValue(){
        
        double value = 0;

        value=(basic)*(friend)*(mood)*(trainBonus)*(people);

        return value;
    }

    public int getHPCost(){
        return this.hpcost;
    }
}
