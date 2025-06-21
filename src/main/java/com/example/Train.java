
package com.example;

public class Train {
    //基礎*育成馬娘屬性補正*友情支援*幹勁加成*一起訓練加成(訓練效果提升)*人數補正
    //基礎=basic+訓練+1等等
    //屬性補正=速度+20%等等
    //友情支援=(有彩圈才有)所有卡片友情相乘
    //幹勁加成=1+(心情*(1+所有卡片幹勁相加))
    //訓練效果=1+(所有卡片訓練效果相加)
    //人數補正=1+人頭*0.05
    private int[][] allValue = new int[5][3]; // 5 種訓練 * 最多 3 種加成屬性

    // Set - 先計算並暫存（非友情版）
    //傳入馬，訓練種類(type12345是速度耐力力量根性智力)，主副訓練(1是主0是副)
    public void setSpeedValue(Uma uma, RoundInfo roundInfo) {
        TrainStat speed = new TrainStat();
        TrainStat power = new TrainStat();
        speed.setBasicValue(uma, 1, 1);
        power.setBasicValue(uma, 1, 0);
        speed.setOtherStuff(roundInfo.getRoundStat(0));
        power.setOtherStuff(roundInfo.getRoundStat(0));

        if (roundInfo.getIsFriend()[0]) {
            allValue[0][0] = (int) (speed.getFriendValue() * uma.getSpeedBonus());
            allValue[0][1] = (int) (power.getFriendValue() * uma.getPowerBonus());
        } else {
            allValue[0][0] = (int) (speed.getValue() * uma.getSpeedBonus());
            allValue[0][1] = (int) (power.getValue() * uma.getPowerBonus());
        }
        uma.changeHp(speed.getHPCost());
    }

    public void setStaminaValue(Uma uma, RoundInfo roundInfo) {
        TrainStat stamina = new TrainStat();
        TrainStat will = new TrainStat();
        stamina.setBasicValue(uma, 2, 1);
        will.setBasicValue(uma, 2, 0);
        stamina.setOtherStuff(roundInfo.getRoundStat(1));
        will.setOtherStuff(roundInfo.getRoundStat(1));

        if (roundInfo.getIsFriend()[1]) {
            allValue[1][0] = (int) (stamina.getFriendValue() * uma.getStaminaBonus());
            allValue[1][1] = (int) (will.getFriendValue() * uma.getWillBonus());
        } else {
            allValue[1][0] = (int) (stamina.getValue() * uma.getStaminaBonus());
            allValue[1][1] = (int) (will.getValue() * uma.getWillBonus());
        }
        uma.changeHp(stamina.getHPCost());
    }

    public void setPowerValue(Uma uma, RoundInfo roundInfo) {
        TrainStat power = new TrainStat();
        TrainStat stamina = new TrainStat();
        power.setBasicValue(uma, 3, 1);
        stamina.setBasicValue(uma, 3, 0);
        power.setOtherStuff(roundInfo.getRoundStat(2));
        stamina.setOtherStuff(roundInfo.getRoundStat(2));

        if (roundInfo.getIsFriend()[2]) {
            allValue[2][0] = (int) (power.getFriendValue() * uma.getPowerBonus());
            allValue[2][1] = (int) (stamina.getFriendValue() * uma.getStaminaBonus());
        } else {
            allValue[2][0] = (int) (power.getValue() * uma.getPowerBonus());
            allValue[2][1] = (int) (stamina.getValue() * uma.getStaminaBonus());
        }
        uma.changeHp(power.getHPCost());
    }

    public void setWillValue(Uma uma, RoundInfo roundInfo) {
        TrainStat will = new TrainStat();
        TrainStat speed = new TrainStat();
        TrainStat power = new TrainStat();
        will.setBasicValue(uma, 4, 1);
        speed.setBasicValue(uma, 4, 0);
        power.setBasicValue(uma, 4, 2);
        will.setOtherStuff(roundInfo.getRoundStat(3));
        speed.setOtherStuff(roundInfo.getRoundStat(3));
        power.setOtherStuff(roundInfo.getRoundStat(3));

        if (roundInfo.getIsFriend()[3]) {
            allValue[3][0] = (int) (will.getFriendValue() * uma.getWillBonus());
            allValue[3][1] = (int) (speed.getFriendValue() * uma.getSpeedBonus());
            allValue[3][2] = (int) (power.getFriendValue() * uma.getPowerBonus());
        } else {
            allValue[3][0] = (int) (will.getValue() * uma.getWillBonus());
            allValue[3][1] = (int) (speed.getValue() * uma.getSpeedBonus());
            allValue[3][2] = (int) (power.getValue() * uma.getPowerBonus());
        }
        uma.changeHp(will.getHPCost());
    }

    public void setKnowledgeValue(Uma uma, RoundInfo roundInfo) {
        TrainStat knowledge = new TrainStat();
        TrainStat speed = new TrainStat();
        knowledge.setBasicValue(uma, 5, 1);
        speed.setBasicValue(uma, 5, 0);
        knowledge.setOtherStuff(roundInfo.getRoundStat(4));
        speed.setOtherStuff(roundInfo.getRoundStat(4));

        if (roundInfo.getIsFriend()[4]) {
            allValue[4][0] = (int) (knowledge.getFriendValue() * uma.getKnowledgeBonus());
            allValue[4][1] = (int) (speed.getFriendValue() * uma.getSpeedBonus());
        } else {
            allValue[4][0] = (int) (knowledge.getValue() * uma.getKnowledgeBonus());
            allValue[4][1] = (int) (speed.getValue() * uma.getSpeedBonus());
        }
        uma.changeHp(knowledge.getHPCost());
    }


    // Apply - 實際執行加成
    public void speedTrain(Uma uma) {
        uma.changeSpeed(allValue[0][0]);
        uma.changePower(allValue[0][1]);
    }

    public void staminaTrain(Uma uma) {
        uma.changeStamina(allValue[1][0]);
        uma.changeWill(allValue[1][1]);
    }

    public void powerTrain(Uma uma) {
        uma.changePower(allValue[2][0]);
        uma.changeStamina(allValue[2][1]);
    }

    public void willTrain(Uma uma) {
        uma.changeWill(allValue[3][0]);
        uma.changeSpeed(allValue[3][1]);
        uma.changePower(allValue[3][2]);
    }

    public void knowledgeTrain(Uma uma) {
        uma.changeKnowledge(allValue[4][0]);
        uma.changeSpeed(allValue[4][1]);
    }

    // 取得暫存的加值（方便 Label 顯示等）
    public int[] getAddValue(int type) {
        return allValue[type - 1]; // type 1~5 對應 index 0~4
    }

    // 顯示用：更新 UI Label 的數值
    public void setTrainLabel(int button, Label label, int[] addValue) {
        switch (button) {
            case 1 -> {
                label.setAddSpeed(addValue[0]);
                label.setAddPower(addValue[1]);
            }
            case 2 -> {
                label.setAddStamina(addValue[0]);
                label.setAddWill(addValue[1]);
            }
            case 3 -> {
                label.setAddPower(addValue[0]);
                label.setAddStamina(addValue[1]);
            }
            case 4 -> {
                label.setAddWill(addValue[0]);
                label.setAddSpeed(addValue[1]);
                label.setAddPower(addValue[2]);
            }
            case 5 -> {
                label.setAddKnowledge(addValue[0]);
                label.setAddSpeed(addValue[1]);
            }
        }
    }
}
