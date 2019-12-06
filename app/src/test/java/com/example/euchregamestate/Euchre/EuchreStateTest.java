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
    /**
     * @author Sierra
     */
    public void deal() {
        EuchreState testState = new EuchreState();
        testState.gameStage = 0;
        testState.deal();
        //test size of each player's hand
        assertEquals(testState.player1Hand.size(), 5);
        assertEquals(testState.player2Hand.size(), 5);
        assertEquals(testState.player3Hand.size(), 5);
        assertEquals(testState.player4Hand.size(), 5);
        //test that gamestage changes
        assertEquals(testState.gameStage, 1);
    }

    @Test
    /**
     * @author Sierra
     */
    public void isPass() {
        EuchreState testState = new EuchreState();
        testState.gameStage = 1;
        testState.dealer = 0;
        testState.turn = 3;
        testState.numPass = 3;
        testState.isPass(3);
        //test that gamestage moves to next gamestage
        assertEquals(testState.gameStage, 2);
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
        assertEquals(testState.player1Hand.size(), 5);
    }

    @Test
    /**
     * @author Alex
     */
    public void isPickItUp() {
        EuchreState testState = new EuchreState();
        testState.gameStage = 1;
        testState.dealer = 0;
        testState.turn = 3;
        Card discard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h);
        testState.isPickItUp(1, discard);
        //test size of player1's hand
        assertEquals(testState.player1Hand.size(), 5);
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
        EuchreState testState = new EuchreState();
        testState.trickNum = 5;
        Card playedCard1 = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h);
        Card playedCard2 = new Card(Card.SUIT.HEARTS, Card.NUMBER.ACE, R.drawable.ace_h);
        Card playedCard3 = new Card(Card.SUIT.SPADES, Card.NUMBER.ACE, R.drawable.ace_s);
        Card playedCard4 = new Card(Card.SUIT.CLUBS, Card.NUMBER.ACE, R.drawable.ace_c);
        testState.player1Play = playedCard1;
        testState.player2Play = playedCard2;
        testState.player3Play = playedCard3;
        testState.player4Play = playedCard4;
        testState.isTrickComplete();
        //test that trump suit is null now
        assertEquals(testState.currentTrumpSuit, null);
        //test that gamestage goes to zero
        assertEquals(testState.numPass, 0);
    }

    @Test
    /**
     * @author Sierra
     */
    public void isRoundOver() {
        EuchreState testState = new EuchreState();
        testState.gameStage = 3;
        testState.redTrickScore = 3;
        testState.blueTrickScore = 2;
        testState.redScore = 0;
        testState.whoCalled = 0;
        testState.isRoundOver();
        //test that red team gets 1 point
        assertEquals(testState.redScore, 1);
    }
}