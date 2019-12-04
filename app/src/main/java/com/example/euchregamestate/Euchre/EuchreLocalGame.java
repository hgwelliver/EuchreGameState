package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.LocalGame;
import com.example.euchregamestate.GameFramework.actionMessage.GameAction;
import com.example.euchregamestate.GameFramework.infoMessage.GameState;
import com.example.euchregamestate.GameFramework.utilities.GameTimer;
import com.example.euchregamestate.GameFramework.utilities.Logger;
import com.example.euchregamestate.GameFramework.utilities.Tickable;

import java.util.ArrayList;
import java.util.Timer;

/**
 * @author Sierra, Mikey, Haley, and Alex
 */
public class EuchreLocalGame extends LocalGame implements Tickable {
    private EuchreState state;
    int playerNum;
    boolean waiting;
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
        if(action instanceof EuchrePlayCardAction) {
            Logger.log("MakeMove","HavePlayCardAction");
            EuchrePlayCardAction playAct = (EuchrePlayCardAction) action;
            playerNum = this.getPlayerIdx(playAct.getPlayer());
            if(state.turn == playerNum && state.gameStage == 3) {
                if(playerNum != 0){
                    state.validMove(playerNum, playAct.getCardToPlay());
                    sendAllUpdatedState();
                }
                else {
                    int firstSuits = 0;
                    ArrayList<Card> cHand = state.getPlayerHand(playerNum);
                    for (int i = 0; i < cHand.size(); i++) {
                        if (cHand.get(i).getSuit() == state.firstPlayedSuit) {
                            firstSuits++;
                        }
                    }
                    if (firstSuits != 0) {
                        if (state.firstPlayedSuit == playAct.getCardToPlay().getSuit()) {
                            state.validMove(playerNum, playAct.getCardToPlay());
                            sendAllUpdatedState();
                        }
                    } else {
                        state.validMove(playerNum, playAct.getCardToPlay());
                        sendAllUpdatedState();
                    }
                }
                if(state.numPlays == 4){
                    sendAllUpdatedState();
                    GameTimer pause = new GameTimer(this);
                    pause.setInterval(3000);
                    pause.start();
                    
                }
            }
        }
        else if(action instanceof EuchrePassAction){
            EuchrePassAction passAct = (EuchrePassAction) action;
            playerNum = this.getPlayerIdx(passAct.getPlayer());
            if(state.turn == playerNum && (state.gameStage == 1 || state.gameStage == 2)){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    // do nothing
                }
                return state.isPass(playerNum);
            }
        }
        else if(action instanceof EuchreOrderUpAction){
            EuchreOrderUpAction orderAct = (EuchreOrderUpAction) action;
            playerNum = this.getPlayerIdx(orderAct.getPlayer());
            if(state.turn == playerNum  && state.gameStage == 1){
                return state.isOrderUpTrump(playerNum);
            }
        }
        else if(action instanceof EuchreSelectTrumpAction){
            EuchreSelectTrumpAction selectAct = (EuchreSelectTrumpAction) action;
            playerNum = this.getPlayerIdx(selectAct.getPlayer());
            if(state.turn == playerNum && state.gameStage == 2){
                state.isSelectTrump(playerNum, selectAct.getSuitToPlay());
                sendAllUpdatedState();
            }
        }
        else if(action instanceof EuchrePickItUpAction){
            EuchrePickItUpAction pickAct = (EuchrePickItUpAction) action;
            playerNum = this.getPlayerIdx(pickAct.getPlayer());
            if(state.turn == playerNum && state.gameStage == 1){
                state.isPickItUp(playerNum, pickAct.getCardToDiscard());
                sendAllUpdatedState();
            }
        }
        else if(action instanceof EuchreGoingAloneAction){
            EuchreGoingAloneAction aloneAct = (EuchreGoingAloneAction) action;
            playerNum = this.getPlayerIdx(aloneAct.getPlayer());
            if(state.turn == playerNum){
                return state.isGoingAlone(playerNum);
            }
        }
        else if(action instanceof EuchreDealAction){
            EuchreDealAction dealAct = (EuchreDealAction) action;
            return state.deal();
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
        //TODO  You will implement this method
        if(state.redScore >= 10){
            return "Red Team Won ";
        }
        if(state.blueScore >= 10){
            return "Blue Team Won" ;
        }
        return null;
    }

    @Override
    protected  void timerTicked(){
        //state.isTrickComplete();
        //sendAllUpdatedState();
    }

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