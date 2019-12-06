package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GameComputerPlayer;
import com.example.euchregamestate.GameFramework.infoMessage.GameInfo;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Sierra, Mikey, Haley, and Alex
 */
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
                if(latestState.gameStage == 1){
                    game.sendAction(new EuchrePassAction(this));
                }
                else if(latestState.numPass == 7){
                    Card.SUIT computerSuit;
                    if(latestState.kittyTop.getSuit() == Card.SUIT.CLUBS){
                        computerSuit = Card.SUIT.HEARTS;
                    }
                    else if(latestState.kittyTop.getSuit() == Card.SUIT.DIAMONDS){
                        computerSuit = Card.SUIT.HEARTS;
                    }
                    else if(latestState.kittyTop.getSuit() == Card.SUIT.SPADES){
                        computerSuit = Card.SUIT.HEARTS;
                    }
                    else{
                        computerSuit = Card.SUIT.SPADES;
                    }
                    game.sendAction(new EuchreSelectTrumpAction(this, computerSuit));
                }
                else{
                    game.sendAction(new EuchrePassAction(this));
                }
            }
             if(latestState.gameStage == 3){
                ArrayList<Card> hand = latestState.getPlayerHand(playerNum);

                ArrayList<Card> valid = new ArrayList<>();

                for(int i = 0; i < hand.size(); i++){
                    if(hand.get(i).getSuit() == latestState.currentTrumpSuit){
                        valid.add(hand.get(i)); // adds card to possible valid plays
                    }
                }
                // if valid array is empty then any card is valid
                if(valid.isEmpty()){
                    sleep(1);
                    Card cardPlay = hand.get(0);
                    game.sendAction(new EuchrePlayCardAction(this, cardPlay));
                }
                else {
                    sleep(1);
                    Card cardPlay = valid.get(0);
                    game.sendAction(new EuchrePlayCardAction(this, cardPlay));
                }
            }
        }
    }
}
