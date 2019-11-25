package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.R;

import org.junit.Test;

import static org.junit.Assert.*;

public class EuchreStateTest {

    @Test
    public void toString1() {
    }

    @Test
    public void arrayToString() {
    }

    @Test
    public void deal() {
    }

    @Test
    /**
     * @author Sierra
     */
    public void isPass() {
        EuchreState testState = new EuchreState();
        testState.gameStage = 1;
        testState.turn = 3;
        testState.isPass(3);
        testState.numPass = 3;
        //test correct numPasses
        assertEquals(testState.numPass, 3);
        //test that turn goes back to zero
        assertEquals(testState.turn, 0);
    }

    @Test
    /**
     * @author Mikey
     */
    public void isGoingAlone() {
        EuchreState testState = new EuchreState();
        // playerId param will be set to 3
        testState.turn = 3;
        testState.gameStage = 1;
        testState.dealer = 1;
        testState.teamDealer = 0;
        // init whoIsAlone variable to make sure it changes properly
        testState.whoIsAlone = 0;
        // call isGoingAlone for player 3
        testState.isGoingAlone(3);
        // test to make sure it updates the whoIsAlone variable properly
        assertEquals(testState.whoIsAlone, 3);
        // test to make sure it clears player1's hand
        assertEquals(testState.player2Hand.size(), 0);

    }

    @Test
    /**
     * @author Sierra
     */
    public void isOrderUpTrump() {
        EuchreState testState = new EuchreState();
        testState.gameStage = 1;
        testState.dealer = 1;
        testState.turn = 2;
        testState.isOrderUpTrump(2);
        //test size of player1's hand
        assertEquals(testState.player1Hand.size(), 6);
    }

    @Test
    /**
     * @author Alex
     */
    public void isPickItUp() {
        EuchreState testState = new EuchreState();
        testState.gameStage = 1;
        testState.dealer = 1;
        testState.turn = 1;
        testState.isPickItUp(1);
        //test size of player1's hand
        assertEquals(testState.player1Hand.size(), 6);
    }

    @Test
    /**
     * @author Haley
     */
    public void isSelectTrump() {
        EuchreState testState = new EuchreState();
        testState.gameStage = 2;
        testState.turn = 1;
        testState.middleCardSuit = Card.SUIT.HEARTS;
        testState.isSelectTrump(1, Card.SUIT.SPADES);
        //test currentTrumpSuit
        assertEquals(testState.currentTrumpSuit, Card.SUIT.SPADES);
    }

    @Test
    /**
     * @author Sierra
     */
    public void validMove() {
        EuchreState testState = new EuchreState();
        testState.gameStage = 3;
        testState.turn = 0;
        testState.numPlays = 0;
        //create card for selectedCard
        Card selectedCard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h);
        testState.player1Play = selectedCard;
        testState.validMove(0, selectedCard);
        //test numPlays increases
        assertEquals(testState.numPlays, 1);
        //test turn increases
        assertEquals(testState.turn, 1);
    }

    @Test
    public void isTrickComplete() {
    }

    @Test
    public void isRoundOver() {
    }
}