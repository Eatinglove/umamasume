package com.example;

public class Label {
    // 現在的數值
    private String nowSpeed = "";
    private String nowStamina = "";
    private String nowPower = "";
    private String nowWill = "";
    private String nowKnowledge = "";
    private String nowHp = "";
    private String nowMood = "";
    private String nowSkillPoint = "";
    // 現在設施等級
    private String speedLevel = "";
    private String staminaLevel = "";
    private String powerLevel = "";
    private String willLevel = "";
    private String knowledgeLevel = "";
    // 最大值
    private String maxSpeed = "";
    private String maxStamina = "";
    private String maxPower = "";
    private String maxWill = "";
    private String maxKnowledge = "";
    // 增加數值
    private String addSpeed = "";
    private String addStamina = "";
    private String addPower = "";
    private String addWill = "";
    private String addKnowledge = "";
    // 失敗率
    private String failRate = "";
    // 剩下回合
    private String remainingRound = "";

    private String log = "";

    // now 屬性 getter/setter
    public String getNowSpeed() { return nowSpeed; }
    public void setNowSpeed(int nowSpeed) { this.nowSpeed = String.valueOf(nowSpeed); }

    public String getNowStamina() { return nowStamina; }
    public void setNowStamina(int nowStamina) { this.nowStamina = String.valueOf(nowStamina); }

    public String getNowPower() { return nowPower; }
    public void setNowPower(int nowPower) { this.nowPower = String.valueOf(nowPower); }

    public String getNowWill() { return nowWill; }
    public void setNowWill(int nowWill) { this.nowWill = String.valueOf(nowWill); }

    public String getNowKnowledge() { return nowKnowledge; }
    public void setNowKnowledge(int nowKnowledge) { this.nowKnowledge = String.valueOf(nowKnowledge); }

    public String getNowHp() { return nowHp; }
    public void setNowHp(int nowHp) { this.nowHp = String.valueOf(nowHp); }

    public String getNowMood() { return nowMood; }
    public void setNowMood(int nowMood) { this.nowMood = String.valueOf(nowMood); }

    public String getNowSkillPoint() { return nowSkillPoint; }
    public void setNowSkillPoint(int nowSkillPoint) { this.nowSkillPoint = String.valueOf(nowSkillPoint); }

    // level 屬性 getter/setter
    public String getSpeedLevel() { return speedLevel; }
    public void setSpeedLevel(int speedLevel) { this.speedLevel = String.valueOf(speedLevel); }

    public String getStaminaLevel() { return staminaLevel; }
    public void setStaminaLevel(int staminaLevel) { this.staminaLevel = String.valueOf(staminaLevel); }

    public String getPowerLevel() { return powerLevel; }
    public void setPowerLevel(int powerLevel) { this.powerLevel = String.valueOf(powerLevel); }

    public String getWillLevel() { return willLevel; }
    public void setWillLevel(int willLevel) { this.willLevel = String.valueOf(willLevel); }

    public String getKnowledgeLevel() { return knowledgeLevel; }
    public void setKnowledgeLevel(int knowledgeLevel) { this.knowledgeLevel = String.valueOf(knowledgeLevel); }

    // max 屬性 getter/setter
    public String getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(int maxSpeed) { this.maxSpeed = String.valueOf(maxSpeed); }

    public String getMaxStamina() { return maxStamina; }
    public void setMaxStamina(int maxStamina) { this.maxStamina = String.valueOf(maxStamina); }

    public String getMaxPower() { return maxPower; }
    public void setMaxPower(int maxPower) { this.maxPower = String.valueOf(maxPower); }

    public String getMaxWill() { return maxWill; }
    public void setMaxWill(int maxWill) { this.maxWill = String.valueOf(maxWill); }

    public String getMaxKnowledge() { return maxKnowledge; }
    public void setMaxKnowledge(int maxKnowledge) { this.maxKnowledge = String.valueOf(maxKnowledge); }

    // add 屬性 getter/setter
    public String getAddSpeed() { return addSpeed; }
    public void setAddSpeed(int addSpeed) { this.addSpeed = String.valueOf(addSpeed); }

    public String getAddStamina() { return addStamina; }
    public void setAddStamina(int addStamina) { this.addStamina = String.valueOf(addStamina); }

    public String getAddPower() { return addPower; }
    public void setAddPower(int addPower) { this.addPower = String.valueOf(addPower); }

    public String getAddWill() { return addWill; }
    public void setAddWill(int addWill) { this.addWill = String.valueOf(addWill); }

    public String getAddKnowledge() { return addKnowledge; }
    public void setAddKnowledge(int addKnowledge) { this.addKnowledge = String.valueOf(addKnowledge); }

    // failRate getter/setter
    public String getFailRate() { return failRate; }
    public void setFailRate(int failRate) { this.failRate = String.valueOf(failRate); }

    // 其他
    public String getRemainingRound() { return remainingRound; }
    public void setRemainingRound(int remainingRound) { this.remainingRound = String.valueOf(remainingRound); }

    public String getLog() { return log; }
    public void setLog(String log) { this.log = log; }
}
