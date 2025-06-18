package com.example;
//把每回合的數值存在這裡，之後從這裡拿取資訊來計算
public class TrainStat {
    private int basic;
    private int friend;
    private int mood;
    private int people;
    private int trainBonus;
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

    }
}
