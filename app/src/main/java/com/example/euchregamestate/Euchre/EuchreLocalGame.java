package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.LocalGame;
import com.example.euchregamestate.GameFramework.actionMessage.GameAction;
import com.example.euchregamestate.GameFramework.utilities.GameTimer;
import com.example.euchregamestate.GameFramework.utilities.Tickable;

import java.util.ArrayList;

/**
 * @author Sierra, Mikey, Haley, and Alex
 */
public class EuchreLocalGame extends LocalGame implements Tickable {
    //instance variables
    private EuchreState state;
    int playerNum;

    public EuchreLocalGame(){
        state = new EuchreState();
    }

    /**
     * can move method
     * checks if player can make a move when it is their turn
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        //return true;
        if(playerIdx == state.getTurn()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * make move method
     * updates the state based on what action was taken
     */
    @Override
    protected boolean makeMove(GameAction action) {
        playerNum = state.getTurn();
        //playCardAction
        if(action instanceof EuchrePlayCardAction) {
            EuchrePlayCardAction playAct = (EuchrePlayCardAction) action;
            playerNum = this.getPlayerIdx(playAct.getPlayer());
            if(state.turn == playerNum && state.gameStage == 3) {
                if(playerNum != 0){
                    state.validMove(playerNum, playAct.getCardToPlay());
                    if(state.numPlays < 4) {
                        sendAllUpdatedState();
                    }
                }
                else {
                    int firstSuits = 0;
                    ArrayList<Card> cHand = state.getPlayerHand(playerNum);
                    //checks if each card in current hand matches suit of the first played card suit
                    for (int i = 0; i < cHand.size(); i++) {
                        if (cHand.get(i).getSuit() == state.firstPlayedSuit) {
                            firstSuits++;
                        }
                    }
                    //must play card with matching suit of first played suit
                    if (firstSuits != 0) {
                        if (state.firstPlayedSuit == playAct.getCardToPlay().getSuit()) {
                            state.validMove(playerNum, playAct.getCardToPlay());
                            sendAllUpdatedState();
                        }
                    }
                    //can play whatever if cards in hand don't match first played suit
                    else {
                        state.validMove(playerNum, playAct.getCardToPlay());
                        sendAllUpdatedState();
                    }
                }
                //shows the completed trick after each player plays a card
                if(state.numPlays == 4){
                    sendAllUpdatedState();
                    GameTimer pause = new GameTimer(this);
                    pause.setInterval(3000);
                    pause.start();
                }
            }
        }
        //passAction
        else if(action instanceof EuchrePassAction){
            EuchrePassAction passAct = (EuchrePassAction) action;
            playerNum = this.getPlayerIdx(passAct.getPlayer());
            if(state.getTurn() > 3){state.turn = 0;}
            if(state.turn == playerNum && (state.gameStage == 1 || state.gameStage == 2)){
                //pause the pass action
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // do nothing
                }
                return state.isPass(playerNum);
            }
        }
        //orderUpAction
        else if(action instanceof EuchreOrderUpAction){
            EuchreOrderUpAction orderAct = (EuchreOrderUpAction) action;
            playerNum = this.getPlayerIdx(orderAct.getPlayer());
            if(state.turn == playerNum  && state.gameStage == 1){
                state.isOrderUpTrump(playerNum);
                sendAllUpdatedState();
            }
        }
        //selectTrumpAction
        else if(action instanceof EuchreSelectTrumpAction){
            EuchreSelectTrumpAction selectAct = (EuchreSelectTrumpAction) action;
            playerNum = this.getPlayerIdx(selectAct.getPlayer());
            if(state.turn == playerNum && state.gameStage == 2){
                state.isSelectTrump(playerNum, selectAct.getSuitToPlay());
                sendAllUpdatedState();
            }
        }
        //pickItUpAction
        else if(action instanceof EuchrePickItUpAction){
            EuchrePickItUpAction pickAct = (EuchrePickItUpAction) action;
            playerNum = this.getPlayerIdx(pickAct.getPlayer());
            if(state.turn == playerNum && state.gameStage == 1){
                state.isPickItUp(playerNum, pickAct.getCardToDiscard());
                sendAllUpdatedState();
            }
        }
        //goingAloneAction
        else if(action instanceof EuchreGoingAloneAction){
            EuchreGoingAloneAction aloneAct = (EuchreGoingAloneAction) action;
            playerNum = this.getPlayerIdx(aloneAct.getPlayer());
            if(state.turn == playerNum){
                return state.isGoingAlone(playerNum);
            }
        }
        return false;
    }

    /**
     * send updated state method
     * sends the most recent state
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        //If there is no updated state, return
        if (state == null) return;

        //make a copy of the state
        EuchreState recent = new EuchreState(state); //copy of state

        //send copy of state to the player
        p.sendInfo(recent);
    }

    /**
     * game over method
     * checks if the game is over based on the scores
     * game is over if one team reaches a score of ten
     */
    @Override
    protected String checkIfGameOver() {
        if(state.redScore >= 10){
            return "Red Team Won";
        }
        if(state.blueScore >= 10){
            return "Blue Team Won" ;
        }
        return null;
    }

    @Override
    protected  void timerTicked(){

    }

    /**
     * External Citation
     * Date: 20 November 2019
     * Problem: Did not know how to make a timer
     * Resource: Dr. Tribelhorn
     * Solution: Tribelhorn gave us some tips on how to combat this
     * Allows all the cards to be seen in a trick before removing them to start a new trick round
     */
    @Override
    public final void tick(GameTimer timer)
    {
        timer.stop();
        if(state.numPlays == 4) {
            state.isTrickComplete();
            sendAllUpdatedState();
        }
    }

}