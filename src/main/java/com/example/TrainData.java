package com.example;

public class TrainData {
    public static final int[][] SPEED_GAIN = {
        {10, 5, -21},  //speed, power, hpCost
        {11, 5, -22},
        {12, 5, -23},
        {13, 6, -25},
        {14, 7, -27}
    };

    public static final int[][] STAMINA_GAIN = {
        {9, 4, -19}, //stamina will hpcost
        {10, 4, -20},
        {11, 4, -21},
        {12, 5, -23},
        {13, 6, -25}
    };

    public static final int[][] POWER_GAIN = {
        {5, 8, -20},  //stamina, power hpCost
        {5, 9, -21},
        {5, 10, -22},
        {6, 11, -24},
        {7, 12, -26}
    };

    public static final int[][] WILL_GAIN = {
        {4, 4, 8, -22},  // speed, power, will, hpCost
        {4, 4, 9, -23},
        {4, 4, 10, -24},
        {5, 4, 11, -26},
        {5, 5, 12, -28}
    };

    public static final int[][] KNOWLEDGE_GAIN = {
        {2, 9, 5},   // speed, knowledge, pt, hpRecover
        {2, 10, 5},
        {2, 11, 5},
        {3, 12, 5},
        {4, 13, 5}
    };
}
