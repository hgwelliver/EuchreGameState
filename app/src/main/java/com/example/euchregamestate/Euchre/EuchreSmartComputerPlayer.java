package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.infoMessage.GameInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EuchreSmartComputerPlayer extends EuchreComputerPlayer {
    private static final String TAG = "EuchreSmartComputerPlayer";

    protected EuchreState latestState = null;

    public EuchreSmartComputerPlayer(String name){
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
                ArrayList<Card> currentHand = latestState.getPlayerHand(playerNum);
                ArrayList<Card> hand = getValidHand(currentHand);
                Card cardPlay;

                Card[] middle = new Card[4];//middle copy to check if will be winning after playing max and losing
                int i;
                for( i = 0; i < 4; i++){ //copies middle
                    if(latestState.currentMiddle.get(i) != null) {
                        middle[i] = latestState.currentMiddle.get(i);
                    }
                    else{
                        middle[i] = null;
                    }
                }


                int winner = getCurrentWinner(latestState, middle);

                if(winner % 2 ==  playerNum %2){//team is winning
                    if(winner == playerNum){
                        int play = getBestCard(hand,latestState,true);
                        cardPlay =hand.get(play);
                    }
                    else{
                        int play = getBestCard(hand,latestState,false);
                        cardPlay =hand.get(play);
                    }
                }
                else{
                    int play = getBestCard(hand,latestState,true);
                    Card temp = hand.get(play);
                    middle[playerNum] = temp;
                    winner = getCurrentWinner(latestState, middle);
                    if(winner % 2 ==  playerNum %2) {//team is winning
                        cardPlay = temp;
                    }
                    else{
                        cardPlay = hand.get(getBestCard(hand,latestState,false));
                    }


                }


                game.sendAction(new EuchrePlayCardAction(this, cardPlay));
            }
        }
    }
    public int getCurrentWinner(EuchreState s, Card[] middle){//returns player number for whos winning, if first player then it will be their player num
        int winner = this.playerNum;
        int playedNull = 0;
        int[] nullVals = new int[4];//logs null values in middle
        int i;
        for( i = 0; i < 4; i++){
            if(middle[i] == null){
                playedNull++;
                nullVals[i] = 0;
            }
            else{
                nullVals[i] = 1;
            }
        }
        if(playedNull == 4){//no cards have been played
            return this.playerNum;
        }
        else {
            Card.NUMBER one;
            Card.NUMBER two;
            Card.NUMBER three;
            Card.NUMBER four;
            Card.SUIT oneSuit;
            Card.SUIT twoSuit;
            Card.SUIT threeSuit;
            Card.SUIT fourSuit;
            int[] value = new int[4];
            if(nullVals[0] == 1){
                one =  s.player1Play.getValue();
                oneSuit = s.player1Play.getSuit();
                value[0] = Card.getValsVal(one, oneSuit, s.currentTrumpSuit);
                if(s.player1Play.getSuit() == s.firstPlayedSuit && s.firstPlayedSuit != s.currentTrumpSuit){
                    value[0] += 10;
                }
                if(s.player1Play.getSuit() == s.currentTrumpSuit || value[0] == 7){
                    value[0] += 20;
                }
            }
            else{
                value[0] = 0;

            }

            if(nullVals[1] == 1){
                two = s.player2Play.getValue();
                twoSuit = s.player2Play.getSuit();
                value[1] = Card.getValsVal(two, twoSuit, s.currentTrumpSuit);
                if(s.player2Play.getSuit() == s.firstPlayedSuit && s.firstPlayedSuit != s.currentTrumpSuit){
                    value[1] += 10;
                }
                if(s.player2Play.getSuit() == s.currentTrumpSuit || value[1] == 7){
                    value[1] += 20;
                }
            }
            else{
                value[1] = 0;
            }
            if(nullVals[2] == 1){
                three = s.player3Play.getValue();
                threeSuit =   s.player3Play.getSuit();
                value[2] = Card.getValsVal(three, threeSuit, s.currentTrumpSuit);
                if(s.player3Play.getSuit() == s.firstPlayedSuit && s.firstPlayedSuit != s.currentTrumpSuit){
                    value[2] += 10;
                }
                if(s.player3Play.getSuit() == s.currentTrumpSuit || value[2] == 7){
                    value[2] += 20;
                }
            }
            else{
                value[2] = 0;
            }
            if(nullVals[2]==1){
                four = s.player4Play.getValue();
                fourSuit = s.player4Play.getSuit();
                value[3] = Card.getValsVal(four, fourSuit, s.currentTrumpSuit);
                if(s.player4Play.getSuit() == s.firstPlayedSuit && s.firstPlayedSuit != s.currentTrumpSuit){
                    value[3] += 10;
                }
                if(s.player4Play.getSuit() == s.currentTrumpSuit || value[3] == 7){
                    value[3] += 20;
                }
            }
            else{
                value[3] = 0;
            }


            int winVal = 0;//find winner
            for(i = 0; i < 4; i++){
                if(value[i] > winVal){
                    winVal = value[i];
                    winner = i;
                }
            }






            return winner;
        }

    }
    public int getBestCard(ArrayList<Card> hand,EuchreState s, boolean highCard){
        int index = 0;
        int maxVal = 0;//if losing

        int[] values = new int[5];
        /*
        Card.NUMBER one = hand.get(0).getValue();
        Card.NUMBER two =hand.get(1).getValue();
        Card.NUMBER three =hand.get(2).getValue();
        Card.NUMBER four =hand.get(3).getValue();
        Card.NUMBER five =hand.get(4).getValue();
        Card.SUIT oneSuit = hand.get(0).getSuit();
        Card.SUIT twoSuit = hand.get(1).getSuit();
        Card.SUIT threeSuit = hand.get(2).getSuit();
        Card.SUIT fourSuit = hand.get(3).getSuit();
        Card.SUIT fiveSuit = hand.get(4).getSuit();

        List<Card.NUMBER> numberList = Arrays.asList(one,two,three,four,five);
        List<Card.SUIT>  suitList = Arrays.asList(oneSuit,twoSuit,threeSuit,fourSuit,fiveSuit);

        ArrayList<Card.NUMBER> numberArr = new ArrayList<>();
        numberArr.addAll(numberList);
        ArrayList<Card.SUIT>  suitArr = new ArrayList<>();
        suitArr.addAll(suitList);
        */
        for(int i = 0; i < hand.size(); i++){//initializes values
            int temp = Card.getValsVal(hand.get(i).getValue(),hand.get(i).getSuit(),s.currentTrumpSuit);
            if(hand.get(i).getSuit() == s.currentTrumpSuit | temp == 7){
                temp += 20;
            }
            if(hand.get(i).getSuit() == s.firstPlayedSuit && s.firstPlayedSuit != s.currentTrumpSuit){
                temp += 10;
            }
            values[i] = temp;


        }
        int minVal = values[0];//min value if winning
        int i;
        if(highCard){
            for(i = 0; i < 4; i++){
                if(values[i] < minVal){
                     minVal = values[i];
                    index = i;
                }
            }
            return index;
        }
        else{//if losing
            for(i = 0; i < 4; i++){
                if(values[i] > maxVal){
                    maxVal = values[i];
                    index = i;
                }
            }
            return index;
        }
    }



    public ArrayList<Card> getValidHand(ArrayList<Card> hand){
        ArrayList<Card> validHand = new ArrayList<>();
        if(latestState.numPlays ==0 | latestState.firstPlayedSuit == null){
            return hand;
        }
        for(int i = 0; i< hand.size();i++){
            if(hand.get(i).getSuit() == latestState.firstPlayedSuit){
                validHand.add(hand.get(i));
            }
        }
        if(hand.isEmpty()){
            return hand;
        }
        else{
            return validHand;
        }
    }
}
