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
            return false;
        }
        else if(action instanceof EuchrePassAction){
            boolean b = state.isPass(ID);
            return b;
        }
        else if(action instanceof EuchreOrderUpAction){
            boolean b = state.isOrderUpTrump(ID);
            return b;
        }
        else if(action instanceof EuchreSelectTrumpAction){
            boolean b = state.isSelectTrump(ID);
            return b;
        }
        else if(action instanceof EuchrePickItUpAction){
            boolean b = state.isPickItUp(ID);
        }
        else if(action instanceof EuchreGoingAloneAction){
            boolean b = state.isGoingAlone(ID);
            return b;

        }
        return false;
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