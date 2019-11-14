package com.example.euchregamestate.Euchre;

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
    public void isPass() {
    }

    @Test
    public void isGoingAlone() {
        EuchreState testState = new EuchreState();
        // playerId param will be set to 3
        testState.turn = 2;
        testState.gameStage = 1;
        testState.dealer = 1;
        testState.teamDealer = 0;
        // init whoIsAlone variable to make sure it changes properly
        testState.whoIsAlone = 0;
        // init player1Hand to make sure it clears
        testState.player1Hand.add(0, testState.deck.ace_c);
        testState.player1Hand.add(0, testState.deck.jack_d);
        testState.player1Hand.add(0, testState.deck.ten_c);
        testState.player1Hand.add(0, testState.deck.queen_s);
        testState.player1Hand.add(0, testState.deck.king_s);
        // call isGoingAlone for player 3
        testState.isGoingAlone(3);
        // test to make sure it updates the whoIsAlone variable properly
        assertEquals(testState.whoIsAlone, 3);





    }

    @Test
    public void isOrderUpTrump() {
    }

    @Test
    public void isPickItUp() {
    }

    @Test
    public void isSelectTrump() {
    }

    @Test
    public void isQuit() {
    }

    @Test
    public void validMove() {
    }

    @Test
    public void isTrickComplete() {
    }

    @Test
    public void isRoundOver() {
    }
}