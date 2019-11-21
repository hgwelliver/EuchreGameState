package com.example.euchregamestate.Euchre;

import android.widget.Switch;

import com.example.euchregamestate.GameFramework.infoMessage.GameState;
import com.example.euchregamestate.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EuchreState extends GameState {

    // info about the resources each player has
    public CardDeck deck;
    protected ArrayList<Card> player1Hand = new ArrayList<>();
    protected ArrayList<Card> player2Hand = new ArrayList<>();
    protected ArrayList<Card> player3Hand = new ArrayList<>();
    protected ArrayList<Card> player4Hand = new ArrayList<>();
    // info about the state of resources
    //protected ArrayList<Card> deckList = new ArrayList<>();
    // whose turn
    protected int turn; // 1/5 for player 1, 2/6 for player 2, 3/7 for player 3, 4/8 for player 4
    protected int dealer; // 1 for player 1, 2 for player 2, 3 for player 3, 4 for player 4
    protected int whoCalled; // 0 for red team, 1 for blue team
    protected int teamDealer; // 0 for red team, 1 for blue team
    // shared resources
    protected ArrayList<Card> kitty = new ArrayList<>();
    protected ArrayList<Card> currentMiddle = new ArrayList<>();
    protected Card player1Play;
    protected Card player2Play;
    protected Card player3Play;
    protected Card player4Play;
    protected Card.SUIT middleCardSuit;
    protected Card middleCard;
    protected boolean middleVisible;
    protected int whoIsAlone;
    protected Card.SUIT currentSuit; // 0 for hearts, 1 for diamonds, 2 for spades, 3 for clubs
    protected Card.SUIT firstPlayed; // first card played in each trick
    protected int numPlays; // how many cards have been played this trick
    protected int trickNum;
    // current score of each player (what card each player has played)
    // scores
    protected int redScore;
    protected int blueScore;
    protected int redTrickScore;
    protected int blueTrickScore;
    protected boolean isRoundOver;
    // current state of timer
    // current stage of game
    protected boolean startGame;
    protected boolean quit; // get rid later
    protected int gameStage; // 0 for deal, 1 for deciding middle card, 2 for deciding trump, 3 for playing cards
    protected int numPass; // count number of passes
    // random number generator
    protected Random rand = new Random();

    public int getTurn() {
        return turn;
    }

    //default constructor
    public EuchreState(){
        // init instance variables
        this.dealer = 0; // change later to start with random dealer
        this.teamDealer = 0;
        this.startGame = true;
        this.quit = false;
        this.gameStage = 2;
        this.numPass = 0;
        this.turn = 0;
        this.trickNum = 0;
        this.redScore = 0;
        this.blueScore = 0;
        this.redTrickScore = 0;
        this.blueTrickScore = 0;
        this.currentSuit = null;
        // init deck of cards
        this.deck = new CardDeck();
        //Collections.shuffle(deck.cardDeck);
        //player1Hand = new ArrayList<>(Arrays.asList(deck.cardDeck.get(0),deck.cardDeck.get(1),deck.cardDeck.get(2),deck.cardDeck.get(3),deck.cardDeck.get(4)));
        //player2Hand = new ArrayList<>(Arrays.asList(deck.cardDeck.get(5),deck.cardDeck.get(6),deck.cardDeck.get(7),deck.cardDeck.get(8),deck.cardDeck.get(9)));
        //player3Hand = new ArrayList<>(Arrays.asList(deck.cardDeck.get(10),deck.cardDeck.get(11),deck.cardDeck.get(12),deck.cardDeck.get(13),deck.cardDeck.get(14)));
        //player4Hand = new ArrayList<>(Arrays.asList(deck.cardDeck.get(15),deck.cardDeck.get(16),deck.cardDeck.get(17),deck.cardDeck.get(18),deck.cardDeck.get(19)));
        //kitty = new ArrayList<>(Arrays.asList(deck.cardDeck.get(20),deck.cardDeck.get(21),deck.cardDeck.get(22),deck.cardDeck.get(23)));
        deal();
    }

    //copy constructor
    public EuchreState(EuchreState other){
        this.dealer = other.dealer;
        this.teamDealer = other.teamDealer;
        this.startGame = other.startGame;
        this.quit = other.quit;
        this.gameStage = other.gameStage;
        this.numPass = other.numPass;
        this.turn = other.turn;
        this.trickNum = other.trickNum;
        this.redScore = other.redScore;
        this.blueScore = other.blueScore;
        this.redTrickScore = other.redTrickScore;
        this.blueTrickScore = other.blueTrickScore;
        this.player1Hand = other.player1Hand;
        this.player2Hand = other.player2Hand;
        this.player3Hand = other.player3Hand;
        this.player4Hand = other.player4Hand;
        this.player1Play = other.player1Play;
        this.player2Play = other.player2Play;
        this.player3Play = other.player3Play;
        this.player4Play = other.player4Play;
        this.currentSuit = other.currentSuit;
        // init deck of cards

    }

    public void setPlay(int ID, Card c){
        if(ID == 1){
            player1Play = c;
        }if(ID == 2){
            player2Play = c;
        }
        if(ID == 3){
            player3Play = c;
        }
        if(ID == 4){
            player4Play = c;
        }

    }

    //playerNum = 1-4
    public ArrayList<Card> getPlayerHand(int playerNum){
        //switch statememt for each player
        if(playerNum == 0){
        return player1Hand;}
        if(playerNum == 1){
            return player2Hand;}
        if(playerNum == 2){
            return player3Hand;}
        if(playerNum == 3){
            return player4Hand;}
        else{return null;}
    }

    @Override
    public String toString(){
        String string = "Player 1's Hand: " + ArrayToString(player1Hand) + "\n" +
                "Player 2's Hand: " + ArrayToString(player2Hand) + "\n" +
                "Player 3's Hand: " + ArrayToString(player3Hand) + "\n" +
                "Player 4's Hand: " + ArrayToString(player4Hand) + "\n" +
                "Turn: " + turn + "\n" +
                "Dealer: " + dealer + ", Team of Dealer: " + teamDealer + "\n" +
                "Red Score, Tricks: " + redScore + ", " + redTrickScore + "\n" +
                "Blue Score, Tricks: " + blueScore +", "+ blueTrickScore + "\n" +
                "Game Stage: " + gameStage + "\n" +
                "Going Alone Player: " + whoIsAlone + "\n" +
                "Passes: " + numPass + " Who Called: " + whoCalled + "\n" +
                "Trump Suit: " + currentSuit + "\n" +
                "Number of Plays; " + numPlays + "\n" +
                "Round is Over: " + isRoundOver() + "\n"

                //passes, who is alone, suit, numPlays,
                ;
        return string;
    }

    public String ArrayToString(ArrayList<Card> Arr){//where object is card object
        String ArrayContents1 ="";//contents of array
        for(int i =0; i< Arr.size(); i++){
            Card card = Arr.get(i);

            String cardNameString = card.getCardName();
            ArrayContents1= ArrayContents1 + ", " + cardNameString;
        }
        return  ArrayContents1;
    }

    // method to deal
    public boolean deal(){
        // deal cards when game is started and game stage is 0


            // need to clear everything
            player1Hand.clear();
            player2Hand.clear();
            player3Hand.clear();
            player4Hand.clear();
            kitty.clear();
            currentMiddle.clear();
            // shuffle deck
            Collections.shuffle(deck.cardDeck);
            // deal cards to each player
            // player 1's hand
            for(int i = 0; i < 5; i++){
                player1Hand.add(i, deck.cardDeck.get(i));
                player2Hand.add(  i, deck.cardDeck.get(5 + i));
                player3Hand.add( i, deck.cardDeck.get(10 + i));
                player4Hand.add( i, deck.cardDeck.get(15 + i));

            }

            for(int i = 0; i < 4; i++){
                kitty.add(i , deck.cardDeck.get(20 + i));
            }
            middleCard = deck.cardDeck.get(20);
            middleCardSuit = middleCard.getSuit();

            // make middle card visible
            middleVisible = true;
            // change game state to 1
            gameStage++;
            // print that cards are dealt
            return true;

    }
    // method to pass
    public boolean isPass(int playerID){
        // if there have been three passes and the user passes then the middle is turn invisible
        if(numPass == 3 && turn == playerID){
            numPass++;
            // make sure turn goes back to 1 if player is 4
            if(turn == 3){
                turn = 0;
            }
            else{
                turn++;
            }
            gameStage++;
            middleVisible = false;
            return true;
        }
        // if numPass is not three or 7 then they can pass normally
        else if(numPass < 7 && numPass != 3 && turn == playerID){
            numPass++;
            // case for player four switching to player 1
            if(turn == 3){
                turn = 0;
            }
            else{
                turn++;
            }
            return true;
        }
        // if numPass is 7 then the user cannot pass
        else if(numPass == 7 && turn == playerID){
            return false;
        }
        return false;
    }
    // method to go alone
    public boolean isGoingAlone(int playerID){
        // if it is a players turn and in round 1
        if(turn == playerID && gameStage == 1){
            // if the player is on the dealing team and not the dealer
            if(teamDealer == 0 && (playerID == 0 || playerID == 2) && (playerID != dealer)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isOrderUpTrump(dealer);
                middleVisible = false;
                whoCalled = 0;
                if(dealer == 0){
                    whoIsAlone = 2;
                    player1Hand.clear();
                }
                else{
                    whoIsAlone = 0;
                    player3Hand.clear();
                }
                return true;
            }
            if(teamDealer == 1 && (playerID == 1 || playerID == 3) && (playerID != dealer)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isOrderUpTrump(dealer);
                middleVisible = false;
                whoCalled = 1;
                if(dealer == 1){
                    whoIsAlone = 3;
                    player2Hand.clear();
                }
                else{
                    whoIsAlone = 1;
                    player4Hand.clear();
                }
                return true;
            }
            // if the player is the dealer
            else if(teamDealer == 0 && playerID == dealer){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isPickItUp(dealer);
                middleVisible = false;
                whoCalled = 0;
                whoIsAlone = dealer;
                if(dealer == 0){
                    player3Hand.clear();
                }
                else{
                    player1Hand.clear();
                }
                return true;
            }
            else if(teamDealer == 1 && playerID == dealer){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isPickItUp(dealer);
                whoCalled = 1;
                whoIsAlone = dealer;
                middleVisible = false;
                if(dealer == 2){
                    player4Hand.clear();
                }
                else{
                    player2Hand.clear();
                }
                return true;
            }
            // non-dealing team goes alone
            else if(teamDealer == 0 && (playerID == 1 || playerID == 3)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isOrderUpTrump(dealer);
                whoCalled = 1;
                if(playerID == 1){
                    whoIsAlone = 1;
                    player4Hand.clear();
                }
                else{
                    whoIsAlone = 3;
                    player2Hand.clear();
                }
                middleVisible = false;
                return true;
            }
            else if(teamDealer == 1 && (playerID == 0 || playerID == 2)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isOrderUpTrump(dealer);
                whoCalled = 0;
                if(playerID == 0){
                    whoIsAlone = 0;
                    player3Hand.clear();
                }
                else{
                    whoIsAlone = 2;
                    player1Hand.clear();
                }
                middleVisible = false;
                return true;
            }
        }
        else if(turn == playerID && gameStage == 2){
            // if the player is on the dealing team and not the dealer
            if(teamDealer == 0 && (playerID == 0 || playerID == 2) && (playerID != dealer)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                middleVisible = false;
                whoCalled = 0;
                if(dealer == 0){
                    whoIsAlone = 2;
                    player1Hand.clear();
                }
                else{
                    whoIsAlone = 0;
                    player3Hand.clear();
                }
                return true;
            }
            if(teamDealer == 1 && (playerID == 1 || playerID == 3) && (playerID != dealer)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                middleVisible = false;
                whoCalled = 1;
                if(dealer == 1){
                    whoIsAlone = 3;
                    player2Hand.clear();
                }
                else{
                    whoIsAlone = 1;
                    player4Hand.clear();
                }
                return true;
            }
            // if the player is the dealer
            else if(teamDealer == 0 && playerID == dealer){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                middleVisible = false;
                whoCalled = 0;
                whoIsAlone = dealer;
                if(dealer == 0){
                    player3Hand.clear();
                }
                else{
                    player1Hand.clear();
                }
                return true;
            }
            else if(teamDealer == 1 && playerID == dealer){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                whoCalled = 1;
                whoIsAlone = dealer;
                middleVisible = false;
                if(dealer == 1){
                    player4Hand.clear();
                }
                else{
                    player2Hand.clear();
                }
                return true;
            }
            // non-dealing team goes alone
            else if(teamDealer == 0 && (playerID == 1 || playerID == 3)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                whoCalled = 1;
                if(playerID == 1){
                    whoIsAlone = 1;
                    player4Hand.clear();
                }
                else{
                    whoIsAlone = 3;
                    player2Hand.clear();
                }
                middleVisible = false;
                return true;
            }
            else if(teamDealer == 1 && (playerID == 0 || playerID == 2)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                whoCalled = 0;
                if(playerID == 0){
                    whoIsAlone = 0;
                    player3Hand.clear();
                }
                else{
                    whoIsAlone = 2;
                    player1Hand.clear();
                }
                middleVisible = false;
                return true;
            }
        }
        return false;
    }
    // method order up trump
    public boolean isOrderUpTrump(int playerID){
        if(turn == playerID && gameStage == 1 && dealer != playerID){
            currentSuit = middleCardSuit;
            // make dealer discard a card and give them the middle card
            if(dealer == 1){
                // dealer taps card to discard
                Card discard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h); // place holder for when we find out how to set to tapped card
                // Card discard = card dealer taps
                player1Hand.remove(discard);
                player1Hand.add(middleCard);
            }
            else if(dealer == 2){
                // dealer taps card to discard
                Card discard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h); // place holder for when we find out how to set to tapped card
                // Card discard = card dealer taps
                player2Hand.remove(discard);
                player2Hand.add(middleCard);
            }
            else if(dealer == 3){
                // dealer taps card to discard
                Card discard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h); // place holder for when we find out how to set to tapped card
                // Card discard = card dealer taps
                player3Hand.remove(discard);
                player3Hand.add(middleCard);
            }
            else if(dealer == 4){
                // dealer taps card to discard
                Card discard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h); // place holder for when we find out how to set to tapped card
                // Card discard = card dealer taps
                player4Hand.remove(discard);
                player4Hand.add(middleCard);
            }
        }
        return false;
    }
    // method for pick it up
    public boolean isPickItUp(int playerID){
        if(turn == playerID && gameStage == 1 && dealer == playerID){
            currentSuit = middleCardSuit;
            // make dealer discard a card and give them the middle card
            if(dealer == 1){
                // dealer taps card to discard
                Card discard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h); // place holder for when we find out how to set to tapped card
                // Card discard = card dealer taps
                player1Hand.remove(discard);
                player1Hand.add(middleCard);
            }
            else if(dealer == 2){
                // dealer taps card to discard
                Card discard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h); // place holder for when we find out how to set to tapped card
                // Card discard = card dealer taps
                player2Hand.remove(discard);
                player2Hand.add(middleCard);
            }
            else if(dealer == 3){
                // dealer taps card to discard
                Card discard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h); // place holder for when we find out how to set to tapped card
                // Card discard = card dealer taps
                player3Hand.remove(discard);
                player3Hand.add(middleCard);
            }
            else if(dealer == 4){
                // dealer taps card to discard
                Card discard = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h); // place holder for when we find out how to set to tapped card
                // Card discard = card dealer taps
                player4Hand.remove(discard);
                player4Hand.add(middleCard);
            }
        }
        return false;
    }
    // method for select trump
    public boolean isSelectTrump(int playerID, Card.SUIT suit){
        if(turn == playerID && gameStage == 2){
            // need input of what trump is selected
            Card.SUIT selected = Card.SUIT.HEARTS;
            if(selected != middleCardSuit){
                currentSuit = selected;
                return true;
            }
            else{
                return false;
            }
        }
        return false;
    }

    // method for making a move
    public boolean validMove(int playerID, Card selectedCard){
        if(whoIsAlone == 1 && playerID == 2){
            numPlays++;
            turn++;
            return true;
        }
        else if(whoIsAlone == 2 && playerID == 3){
            numPlays++;
            turn = 1;
            return true;
        }
        else if(whoIsAlone == 3 && playerID == 0){
            numPlays++;
            turn++;
            return true;
        }
        else if(whoIsAlone == 4 && playerID == 1){
            numPlays++;
            turn++;
            return true;
        }
        // turn == playerID
        if(gameStage == 3){
            if(numPlays == 0){
                if(playerID == 0){
                    player1Play = selectedCard;
                    // card goes to middle
                    currentSuit = player1Play.getSuit();
                    firstPlayed = player1Play.getSuit();
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 1){
                    player2Play = selectedCard;
                    // card goes to middle
                    currentSuit = player2Play.getSuit();
                    firstPlayed = player2Play.getSuit();
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 2){
                    player3Play = selectedCard;
                    // card goes to middle
                    currentSuit = player3Play.getSuit();
                    firstPlayed = player3Play.getSuit();
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 3){
                    player2Play = selectedCard;
                    // card goes to middle
                    currentSuit = player4Play.getSuit();
                    firstPlayed = player4Play.getSuit();
                    numPlays++;
                    turn = 0;
                    return true;
                }
            }
            else if(numPlays < 3){
                if(playerID == 0){
                    player1Play = selectedCard;
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 1){
                    player2Play = selectedCard;
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 2){
                    player3Play = selectedCard;
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 3){
                    player4Play = selectedCard;
                    numPlays++;
                    turn = 0;
                    return true;
                }
            }
            // if three cards have been played this is the last card
            else if(numPlays == 3){
                if(playerID == 0){
                    player1Play = selectedCard;
                    isTrickComplete();
                    return true;
                }
                else if(playerID == 1){
                    player2Play = selectedCard;
                    isTrickComplete();
                    return true;
                }
                else if(playerID == 2){
                    player3Play = selectedCard;
                    isTrickComplete();
                    return true;
                }
                else if(playerID == 3){
                    player4Play = selectedCard;
                    isTrickComplete();
                    return true;
                }
            }
        }
        return false;
    }
    // method for round complete
    public void isTrickComplete(){
        // clear the board
        // if round is complete calculate who won trick
        // need to set values of cards 9-14
        Card.NUMBER one = player1Play.getValue();
        Card.NUMBER two = player2Play.getValue();
        Card.NUMBER three = player3Play.getValue();
        Card.NUMBER four = player4Play.getValue();
        int[] value = new int[5];
        value[1] = Card.getValsVal(one);
        value[2] = Card.getValsVal(two);
        value[3] = Card.getValsVal(three);
        value[4] = Card.getValsVal(four);
        if(player1Play.getSuit() == firstPlayed && firstPlayed != currentSuit){
             value[1] += 10;
        }
        else if(player2Play.getSuit() == firstPlayed && firstPlayed != currentSuit){
             value[2] += 10;
        }
        else if(player3Play.getSuit() == firstPlayed && firstPlayed != currentSuit){
             value[3] += 10;
        }
        else if(player4Play.getSuit() == firstPlayed && firstPlayed != currentSuit){
             value[4] += 10;
        }
        else if(player1Play.getSuit() == currentSuit){
             value[1] += 20;
        }
        else if(player2Play.getSuit() == currentSuit){
             value[2] += 20;
        }
        else if(player3Play.getSuit() == currentSuit){
             value[3] += 20;
        }
        else if(player4Play.getSuit() == currentSuit){
             value[4] += 20;
        }
        int winner = 0;
        for(int j = 1; j < 5; j++){
            if(value[j] > winner){
                winner = value[j];
            }
        }
        if(winner == 1 || winner == 3){
            redTrickScore++;
            trickNum++;
        }
        else if(winner == 2 || winner == 4){
            blueTrickScore++;
            trickNum++;
        }
        if(trickNum == 5){
            if(isRoundOver()){
                // need to clear and re-deal and set gameStage
                deal();
                gameStage = 0;
            }

        }


    }
    // is round over
    public boolean isRoundOver() {
        // update score
        if (whoIsAlone == 1 || whoIsAlone == 3) {
            if (redTrickScore == 5) {
                redScore += 4;
                return true;
            } else if (redTrickScore > 2) {
                redScore += 1;
                return false;
            }
        } else if (whoIsAlone == 2 || whoIsAlone == 4) {
            if (blueTrickScore == 5) {
                redScore += 4;
                return true;
            } else if (blueTrickScore > 2) {
                redScore += 1;
                return false;
            }
        } else {
            if (redTrickScore == 5) {
                redScore += 2;
                return true;
            } else if (redTrickScore > 2 && whoCalled == 1) {
                redScore += 2;
                return false;
            } else if (redTrickScore > 2 && whoCalled == 0) {
                redScore += 1;
                return false;
            } else if (blueTrickScore == 5) {
                blueScore += 2;
                return true;
            } else if (blueTrickScore > 2 && whoCalled == 0) {
                blueScore += 2;
                return false;
            } else if (blueTrickScore > 2 && whoCalled == 1) {
                redScore += 1;
                return false;
            }
        }
        return false;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}


