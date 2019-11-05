package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.LocalGame;
import com.example.euchregamestate.GameFramework.actionMessage.GameAction;
import com.example.euchregamestate.GameFramework.infoMessage.GameState;

public class EuchreLocalGame extends LocalGame {
    private EuchreState state;
    public EuchreLocalGame(){
        state = new EuchreState();
    }

    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        if(playerIdx == state.getTurn()){
            return true;
        }
        return false;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        int ID = state.getTurn();
        if(action instanceof EuchrePlayCardAction) {
        }
        if(action instanceof EuchrePassAction){

        }
        if(action instanceof EuchreOrderUpAction){

        }
        if(action instanceof EuchreSelectTrumpAction){

        }
        if(action instanceof EuchrePickItUpAction){

        }
        if(action instanceof EuchreGoingAloneAction){

        }
    }





    @Override

    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        EuchreState state2 = new EuchreState(state);
        p.sendInfo(state2);
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