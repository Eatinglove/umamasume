package com.example;
//馬的類
public class Uma {
    private int speed;
    private int stamina;
    private int power;
    private int will;
    private int knowledge;
    private int hp;//(default 100, range 0 to 100)
    private int pt;
    private int mood;//(default 3, range 1 to 5)

    private int speedBonus;
    private int staminaBonus;
    private int powerBonus;
    private int willBonus;
    private int knowledgeBonus;

    private int speedLevel;
    private int staminaLevel;
    private int powerLevel;
    private int willLevel;
    private int knowledgeLevel;

    private int maxLevel = 5;
    private int maxHp = 100;
    private int id;
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return id;
    }
    Condition condition = new Condition();
    public int getMaxLevel(){
        return maxLevel;
    }

    public void changeMaxHp(int value){
        this.maxHp+=value;
    }

    public int getMaxHp(){
        return maxHp;
    }

    public void changeSpeed(int value) {
        this.speed += value;
    }

    public void changeStamina(int value) {
        this.stamina += value;
    }

    public void changePower(int value) {
        this.power += value;
    }

    public void changeWill(int value) {
        this.will += value;
    }

    public void changeKnowledge(int value) {
        this.knowledge += value;
    }

    public void changeHp(int value) {
        //超過上限就設為上限，低於0就是0
        this.hp += value;
        if(this.hp>maxHp){
            this.hp=maxHp;
        }else if(this.hp<0){
            this.hp=0;
        }
    }

    public void changePt(int value) {
        this.pt += value;
    }

    public void changeMood(int value) {
        this.mood += value;
        if(this.mood>5){
            this.mood=5;
        }
        if(this.mood<1){
            this.mood=1;
        }
    }

    public void changeSpeedBonus(int value) {
        this.speedBonus += value;
    }

    public void changeStaminaBonus(int value) {
        this.staminaBonus += value;
    }

    public void changePowerBonus(int value) {
        this.powerBonus += value;
    }

    public void changeWillBonus(int value) {
        this.willBonus += value;
    }

    public void changeKnowledgeBonus(int value) {
        this.knowledgeBonus += value;
    }

    public int getSpeedLevel() {
        return speedLevel;
    }

    public void setSpeedLevel(int speedLevel) {
        this.speedLevel = speedLevel;
    }

    public int getStaminaLevel() {
        return staminaLevel;
    }

    public void setStaminaLevel(int staminaLevel) {
        this.staminaLevel = staminaLevel;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public int getWillLevel() {
        return willLevel;
    }

    public void setWillLevel(int willLevel) {
        this.willLevel = willLevel;
    }

    public int getKnowledgeLevel() {
        return knowledgeLevel;
    }

    public void setKnowledgeLevel(int knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStamina() {
        return stamina;
    }

    public int getPower() {
        return power;
    }

    public int getWill() {
        return will;
    }

    public int getKnowledge() {
        return knowledge;
    }

    public int getHp() {
        return hp;
    }

    public int getPt() {
        return pt;
    }

    public int getMood() {
        return mood;
    }

    public int getSpeedBonus() {
        return speedBonus;
    }

    public int getStaminaBonus() {
        return staminaBonus;
    }

    public int getPowerBonus() {
        return powerBonus;
    }

    public int getWillBonus() {
        return willBonus;
    }

    public int getKnowledgeBonus() {
        return knowledgeBonus;
    }

    public void initUma(
        int speed, int stamina, int power, int will, int knowledge,
        int speedBonus, int staminaBonus, int powerBonus, int willBonus, int knowledgeBonus
    ) {
        this.speed = speed;
        this.stamina = stamina;
        this.power = power;
        this.will = will;
        this.knowledge = knowledge;

        this.speedBonus = speedBonus;
        this.staminaBonus = staminaBonus;
        this.powerBonus = powerBonus;
        this.willBonus = willBonus;
        this.knowledgeBonus = knowledgeBonus;

        this.speedLevel = 1;
        this.staminaLevel = 1;
        this.powerLevel = 1;
        this.willLevel = 1;
        this.knowledgeLevel = 1;

        this.pt = 120;
        this.mood = 3;//1到5
        this.hp = 100;
        this.maxLevel = 5;
    }
}
