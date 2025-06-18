package com.example;
//卡片的類，裡面有許多跟卡片的數據
public class Card {
    private int friend; // 友情加成
    private int mood; // 幹勁加成
    private int train; // 訓練加成
    private int initSpeed;
    private int initStamina;
    private int initPower;
    private int initWill;
    private int initKnowledge;

    private int speed;
    private int stamina;
    private int power;
    private int will;
    private int knowledge;
    private int pt;

    private int failRateDecrease;
    private int knowledgeRecover;

    private int initLove; // 初期情誼
    private int love; // 情誼
    private int appearRate; // 擅長率

    public int getFriend() {
        return friend;
    }

    public void setFriend(int friend) {
        this.friend = friend;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getTrain() {
        return train;
    }

    public void setTrain(int train) {
        this.train = train;
    }

    public int getInitSpeed() {
        return initSpeed;
    }

    public void setInitSpeed(int initSpeed) {
        this.initSpeed = initSpeed;
    }

    public int getInitStamina() {
        return initStamina;
    }

    public void setInitStamina(int initStamina) {
        this.initStamina = initStamina;
    }

    public int getInitPower() {
        return initPower;
    }

    public void setInitPower(int initPower) {
        this.initPower = initPower;
    }

    public int getInitWill() {
        return initWill;
    }

    public void setInitWill(int initWill) {
        this.initWill = initWill;
    }

    public int getInitKnowledge() {
        return initKnowledge;
    }

    public void setInitKnowledge(int initKnowledge) {
        this.initKnowledge = initKnowledge;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getWill() {
        return will;
    }

    public void setWill(int will) {
        this.will = will;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(int knowledge) {
        this.knowledge = knowledge;
    }

    public int getPt() {
        return pt;
    }

    public void setPt(int pt) {
        this.pt = pt;
    }

    public int getFailRateDecrease() {
        return failRateDecrease;
    }

    public void setFailRateDecrease(int failRateDecrease) {
        this.failRateDecrease = failRateDecrease;
    }

    public int getKnowledgeRecover() {
        return knowledgeRecover;
    }

    public void setKnowledgeRecover(int knowledgeRecover) {
        this.knowledgeRecover = knowledgeRecover;
    }

    public int getInitLove() {
        return initLove;
    }

    public void setInitLove(int initLove) {
        this.initLove = initLove;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getAppearRate() {
        return appearRate;
    }

    public void setAppearRate(int appearRate) {
        this.appearRate = appearRate;
    }

    public void initCard(int friend, int mood, int train,
                         int initSpeed, int initStamina, int initPower, int initWill, int initKnowledge,
                         int failRateDecrease, int knowledgeRecover,
                         int initLove,  int appearRate,
                         int speed, int stamina, int power, int will, int knowledge, int pt) {
        this.friend = friend;
        this.mood = mood;
        this.train = train;
        this.initSpeed = initSpeed;
        this.initStamina = initStamina;
        this.initPower = initPower;
        this.initWill = initWill;
        this.initKnowledge = initKnowledge;
        this.failRateDecrease = failRateDecrease;
        this.knowledgeRecover = knowledgeRecover;
        this.initLove = initLove;
        this.love = 0 + initLove;
        this.appearRate = appearRate;
        this.speed = speed;
        this.stamina = stamina;
        this.power = power;
        this.will = will;
        this.knowledge = knowledge;
        this.pt = pt;
    }
}
