package com.example;
import java.util.Random;
public class Play {
    public static void main(String[] args) {
        Random random = new Random();
        int round = 72;
        Uma uma = new Uma();
        //接收uma資訊
        Card card[] = new Card[6];
        //接收card資訊
        while(round>0){
            int action=-1;
            Label label = new Label();
            Choice choice = new Choice();
            choice.setUma(uma);
            choice.setCard(card);
            choice.setLabel(label);
            choice.newTurn(round);
            //接收動作
            choice.action(action);
            int isRandomEvent = random.nextInt(100);
            if(isRandomEvent>70){
                RandomEvent randomEvent = new RandomEvent();
                randomEvent.setUma(uma);
                randomEvent.chooseEvent();
            }
            
            //更新label

            round-=1;
        }
    }
}
