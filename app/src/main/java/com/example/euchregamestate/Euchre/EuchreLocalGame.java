package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.LocalGame;
import com.example.euchregamestate.GameFramework.actionMessage.GameAction;
import com.example.euchregamestate.GameFramework.infoMessage.GameState;

public class EuchreLocalGame extends LocalGame {
    private EuchreState state;
    int playerNum;
    public EuchreLocalGame(){
        state = new EuchreState();
    }

    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        if(playerIdx == state.getTurn()){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected boolean makeMove(GameAction action) {
        playerNum = state.getTurn();
        if(action instanceof EuchrePlayCardAction) {
            EuchrePlayCardAction playAct = (EuchrePlayCardAction) action;
            playerNum = this.getPlayerIdx(playAct.getPlayer());
            if(state.turn == playerNum) {
                return state.validMove(playerNum, playAct.getCardToPlay());
            }
        }
        else if(action instanceof EuchrePassAction){
            EuchrePassAction passAct = (EuchrePassAction) action;
            playerNum = this.getPlayerIdx(passAct.getPlayer());
            if(state.turn == playerNum){
                return state.isPass(playerNum);
            }
        }
        else if(action instanceof EuchreOrderUpAction){
            EuchreOrderUpAction orderAct = (EuchreOrderUpAction) action;
            playerNum = this.getPlayerIdx(orderAct.getPlayer());
            if(state.turn == playerNum){
                return state.isOrderUpTrump(playerNum);
            }
        }
        else if(action instanceof EuchreSelectTrumpAction){
            EuchreSelectTrumpAction selectAct = (EuchreSelectTrumpAction) action;
            playerNum = this.getPlayerIdx(selectAct.getPlayer());
            if(state.turn == playerNum){
                return state.isSelectTrump(playerNum);
            }
        }
        else if(action instanceof EuchrePickItUpAction){
            EuchrePickItUpAction pickAct = (EuchrePickItUpAction) action;
            playerNum = this.getPlayerIdx(pickAct.getPlayer());
            if(state.turn == playerNum){
                return state.isPickItUp(playerNum);
            }
        }
        else if(action instanceof EuchreGoingAloneAction){
            EuchreGoingAloneAction aloneAct = (EuchreGoingAloneAction) action;
            playerNum = this.getPlayerIdx(aloneAct.getPlayer());
            if(state.turn == playerNum){
                return state.isGoingAlone(playerNum);
            }
        }
        return false;
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        //If there is no updated state, return
        if (state == null) return;

        //make a copy of the state
        EuchreState recent = new EuchreState(state);//copy of state

        //send copy of state to the player
        p.sendInfo(recent);
    }

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

}