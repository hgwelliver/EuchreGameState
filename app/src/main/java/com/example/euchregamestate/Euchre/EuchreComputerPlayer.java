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
            sleep(1000);
            // send an action from this computer player
            if(latestState.gameStage == 1 || latestState.gameStage == 2){
                game.sendAction(new EuchrePassAction(this));
            }
            else if(latestState.gameStage == 3){
                Random rand = new Random();
                ArrayList<Card> hand = latestState.getPlayerHand(playerNum);
                int num = rand.nextInt(hand.size());
                Card card = hand.get(num+1);
                game.sendAction(new EuchrePlayCardAction(this, card));

            }
        }
    }

}
