package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GameComputerPlayer;
import com.example.euchregamestate.GameFramework.infoMessage.GameInfo;

import java.util.ArrayList;
import java.util.Random;

public class EuchreComputerPlayer extends GameComputerPlayer {

    private static final String TAG = "EuchreComputerPlayer";

    protected EuchreState latestState = null;

    public EuchreComputerPlayer(String name){
        super(name);
    }

    protected void receiveInfo(GameInfo info){
        //If we no game-state, ignore
        if (!(info instanceof EuchreState)) {
            return;
        }
        // get the latest state
        latestState = (EuchreState) info;
        // set the player num
        int playerNum = this.playerNum;
        // do what it do
        if(latestState.turn == playerNum){
            // pause for one second
            sleep(1);
            // send an action from this computer player
            if(latestState.gameStage == 1 || latestState.gameStage == 2){
                game.sendAction(new EuchrePassAction(this));
            }
            else if(latestState.gameStage == 3){
                /*Random rand = new Random();
                ArrayList<Card> hand = latestState.getPlayerHand(playerNum);

                ArrayList<Card> valid = new ArrayList<>();

                for(int i = 0; i < hand.size(); i++){
                    if(hand.get(i).getSuit() == latestState.currentSuit){
                        valid.add(hand.get(i)); // adds card to possible valid plays
                    }
                }
                // if valid array is empty then any card is valid
                if(valid.isEmpty()){
                    int num = rand.nextInt(hand.size());
                    Card cardPlay = hand.get(num);
                    game.sendAction(new EuchrePlayCardAction(this, cardPlay));
                }
                else {
                    int num = rand.nextInt(valid.size());
                    Card cardPlay = valid.get(num);
                    game.sendAction(new EuchrePlayCardAction(this, cardPlay));
                }*/

                ArrayList<Card> hand = latestState.getPlayerHand(playerNum);
                Card cardPlay;
                if(hand.get(0) != null){
                    cardPlay = hand.get(0);
                }
                else if(hand.get(1) != null){
                    cardPlay = hand.get(1);
                }
                else if(hand.get(2) != null){
                    cardPlay = hand.get(2);
                }
                else if(hand.get(3) != null){
                    cardPlay = hand.get(3);
                }
                else{
                    cardPlay = hand.get(4);
                }

                game.sendAction(new EuchrePlayCardAction(this, cardPlay));

            }
        }
    }

}
