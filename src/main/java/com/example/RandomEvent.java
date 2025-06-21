package com.example;
import java.util.Random;
public class RandomEvent {
    private Uma uma = new Uma();
    public void setUma(Uma uma){
        this.uma=uma;
    }
    public void chooseEvent(){
        Random random = new Random();
        int type = random.nextInt(2);
        if(type==0){
            cardEvent();
        }else if(type==1){
            umaEvent(uma.getId());
        }
    }
    public void cardEvent(){

    }
    public void umaEvent(int UmaId){
        

    }
}
