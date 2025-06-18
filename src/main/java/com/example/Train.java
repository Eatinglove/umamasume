package com.example;

public class Train {
    //��¦*�|�����Q�ݩʸɥ�*�ͱ��䴩*�F�l�[��*�@�_�V�m�[��(�V�m�ĪG����)*�H�Ƹɥ�
    //��¦=basic+�V�m+1����
    //�ݩʸɥ�=�t��+20%����
    //�ͱ��䴩=(���m��~��)�Ҧ��d���ͱ��ۭ�
    //�F�l�[��=1+(�߱�*(1+�Ҧ��d���F�l�ۥ[))
    //�V�m�ĪG=1+(�Ҧ��d���V�m�ĪG�ۥ[)
    //�H�Ƹɥ�=1+�H�Y*0.05
    public static void trainSpeedCal(Uma uma, TrainStat speedTrainStat, TrainStat powerTrainStat) {
        double speedValueDouble = speedTrainStat.getValue();
        double powerValueDouble = powerTrainStat.getValue();

        int speedValueInt = (int) speedValueDouble;
        int powerValueInt = (int) powerValueDouble;

        uma.changeSpeed(speedValueInt);
        uma.changePower(powerValueInt);
    }

    public static void trainStaminaCal(Uma uma, TrainStat staminaTrainStat, TrainStat willTrainStat) {
        double staminaValueDouble = staminaTrainStat.getValue();
        double willValueDouble = willTrainStat.getValue();

        int staminaValueInt = (int) staminaValueDouble;
        int willValueInt = (int) willValueDouble;

        uma.changeStamina(staminaValueInt);
        uma.changeWill(willValueInt);
    }

    public static void trainPowerCal(Uma uma, TrainStat powerTrainStat, TrainStat stamTrainStat) {
        double powerValueDouble = powerTrainStat.getValue();
        double staminaValueDouble = stamTrainStat.getValue();

        int powerValueInt = (int) powerValueDouble;
        int staminaValueInt = (int) staminaValueDouble;

        uma.changePower(powerValueInt);
        uma.changeStamina(staminaValueInt);
    }

    public static void trainWillCal(Uma uma, TrainStat willTrainStat, TrainStat speedTrainStat, TrainStat powerTrainStat) {
        double willValueDouble = willTrainStat.getValue();
        double speedValueDouble = speedTrainStat.getValue();
        double powerValueDouble = powerTrainStat.getValue();

        int willValueInt = (int) willValueDouble;
        int speedValueInt = (int) speedValueDouble;
        int powerValueInt = (int) powerValueDouble;

        uma.changeWill(willValueInt);
        uma.changeSpeed(speedValueInt);
        uma.changePower(powerValueInt);
    }

    public static void trainKnowledgeCal(Uma uma, TrainStat knowledgeTrainStat, TrainStat speedTrainStat) {
        double knowledgeValueDouble = knowledgeTrainStat.getValue();
        double speedValueDouble = speedTrainStat.getValue();

        int knowledgeValueInt = (int) knowledgeValueDouble;
        int speedValueInt = (int) speedValueDouble;

        uma.changeKnowledge(knowledgeValueInt);
        uma.changeSpeed(speedValueInt);
    }

    public static void trainSpeedFriendCal(Uma uma, TrainStat speedTrainStat, TrainStat powerTrainStat){
        double speedValueDouble = speedTrainStat.getFriendValue();
        double powerValueDouble = powerTrainStat.getFriendValue();

        int speedValueInt = (int) speedValueDouble;
        int powerValueInt = (int) powerValueDouble;

        uma.changeSpeed(speedValueInt);
        uma.changePower(powerValueInt);
    }

    public static void trainStaminaFriendCal(Uma uma, TrainStat staminaTrainStat, TrainStat willTrainStat) {
        double staminaValueDouble = staminaTrainStat.getFriendValue();
        double willValueDouble = willTrainStat.getFriendValue();

        int staminaValueInt = (int) staminaValueDouble;
        int willValueInt = (int) willValueDouble;

        uma.changeStamina(staminaValueInt);
        uma.changeWill(willValueInt);
    }

    public static void trainPowerFriendCal(Uma uma, TrainStat powerTrainStat, TrainStat staminaTrainStat) {
        double powerValueDouble = powerTrainStat.getFriendValue();
        double staminaValueDouble = staminaTrainStat.getFriendValue();

        int powerValueInt = (int) powerValueDouble;
        int staminaValueInt = (int) staminaValueDouble;

        uma.changePower(powerValueInt);
        uma.changeStamina(staminaValueInt);
    }

    public static void trainWillFriendCal(Uma uma, TrainStat willTrainStat, TrainStat speedTrainStat, TrainStat powerTrainStat) {
        double willValueDouble = willTrainStat.getFriendValue();
        double speedValueDouble = speedTrainStat.getFriendValue();
        double powerValueDouble = powerTrainStat.getFriendValue();

        int willValueInt = (int) willValueDouble;
        int speedValueInt = (int) speedValueDouble;
        int powerValueInt = (int) powerValueDouble;

        uma.changeWill(willValueInt);
        uma.changeSpeed(speedValueInt);
        uma.changePower(powerValueInt);
    }

    public static void trainKnowledgeFriendCal(Uma uma, TrainStat knowledgeTrainStat, TrainStat speedTrainStat) {
        double knowledgeValueDouble = knowledgeTrainStat.getFriendValue();
        double speedValueDouble = speedTrainStat.getFriendValue();

        int knowledgeValueInt = (int) knowledgeValueDouble;
        int speedValueInt = (int) speedValueDouble;

        uma.changeKnowledge(knowledgeValueInt);
        uma.changeSpeed(speedValueInt);
    }

}
