package com.example;

public class Init {

    private Card[] initCards = new Card[6];
    private int speedBonus;
    private int staminaBonus;
    private int powerBonus;
    private int willBonus;
    private int knowledgeBonus;

    public Card[] getCard() {
        return initCards;
    }

    public void setSpeedBonusFromFront(int speedBonus) {
        this.speedBonus = speedBonus;
    }

    public void setStaminaBonusFromFront(int staminaBonus) {
        this.staminaBonus = staminaBonus;
    }

    public void setPowerBonusFromFront(int powerBonus) {
        this.powerBonus = powerBonus;
    }

    public void setWillBonusFromFront(int willBonus) {
        this.willBonus = willBonus;
    }

    public void setKnowledgeBonusFromFront(int knowledgeBonus) {
        this.knowledgeBonus = knowledgeBonus;
    }

    public void setUmaBonus(Uma uma) {
        uma.changeSpeedBonus(speedBonus);
        uma.changeStaminaBonus(staminaBonus);
        uma.changePowerBonus(powerBonus);
        uma.changeWillBonus(willBonus);
        uma.changeKnowledgeBonus(knowledgeBonus);
    }

}
