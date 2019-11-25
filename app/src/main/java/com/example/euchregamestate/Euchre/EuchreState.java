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
    protected Card.SUIT currentTrumpSuit;
    protected Card.SUIT firstPlayedSuit; // suit of first card played in each trick
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
        this.currentTrumpSuit = Card.SUIT.DIAMONDS;
        this.numPlays = 0;
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
        this.currentTrumpSuit = other.currentTrumpSuit;
        this.numPlays = other.numPlays;
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
                "Trump Suit: " + currentTrumpSuit + "\n" +
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
                currentTrumpSuit = middleCardSuit;
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
            currentTrumpSuit = middleCardSuit;
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
            currentTrumpSuit = middleCardSuit;
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
            if(suit != middleCardSuit){
                currentTrumpSuit = suit;
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
                    firstPlayedSuit = player1Play.getSuit();
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 1){
                    player2Play = selectedCard;
                    // card goes to middle
                    firstPlayedSuit = player2Play.getSuit();
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 2){
                    player3Play = selectedCard;
                    // card goes to middle
                    firstPlayedSuit = player3Play.getSuit();
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 3){
                    player2Play = selectedCard;
                    // card goes to middle
                    firstPlayedSuit = player4Play.getSuit();
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
                    numPlays++;
                    return true;
                }
                else if(playerID == 1){
                    player2Play = selectedCard;
                    numPlays++;
                    return true;
                }
                else if(playerID == 2){
                    player3Play = selectedCard;
                    numPlays++;
                    return true;
                }
                else if(playerID == 3){
                    player4Play = selectedCard;
                    numPlays++;
                    return true;
                }
            }
        }
        return false;
    }
    // method for round complete
    public void isTrickComplete(){
        // reset numPlayed
        numPlays = 0;
        // find if the round is over
        trickNum++;
        int trickWinner;
        if(trickNum == 5){
            isRoundOver();
            gameStage = 0;
            deal();
            trickNum = 0;
        }
        // if not, check whose turn is next
        else{
            trickWinner = trickWinner();
            dealer = trickWinner;
            turn = trickWinner + 1;
            if(trickWinner == 3){
                turn = 0;
            }

            if(trickWinner == 0 | trickWinner == 2){
                redTrickScore++;
            }
            else if(trickWinner == 1 | trickWinner == 3){
                blueTrickScore++;
            }
        }

        player1Play = null;
        player2Play = null;
        player3Play = null;
        player4Play = null;


    }
    // find round winner
    public int trickWinner(){
        Card.NUMBER one = player1Play.getValue();
        Card.NUMBER two = player2Play.getValue();
        Card.NUMBER three = player3Play.getValue();
        Card.NUMBER four = player4Play.getValue();
        Card.SUIT oneSuit = player1Play.getSuit();
        Card.SUIT twoSuit = player2Play.getSuit();
        Card.SUIT threeSuit = player3Play.getSuit();
        Card.SUIT fourSuit = player4Play.getSuit();
        int[] value = new int[4];
        value[0] = Card.getValsVal(one, oneSuit, currentTrumpSuit);
        value[1] = Card.getValsVal(two, twoSuit, currentTrumpSuit);
        value[2] = Card.getValsVal(three, threeSuit, currentTrumpSuit);
        value[3] = Card.getValsVal(four, fourSuit, currentTrumpSuit);
        if(player1Play.getSuit() == firstPlayedSuit && firstPlayedSuit != currentTrumpSuit){
            value[0] += 10;
        }
        if(player2Play.getSuit() == firstPlayedSuit && firstPlayedSuit != currentTrumpSuit){
            value[1] += 10;
        }
        if(player3Play.getSuit() == firstPlayedSuit && firstPlayedSuit != currentTrumpSuit){
            value[2] += 10;
        }
        if(player4Play.getSuit() == firstPlayedSuit && firstPlayedSuit != currentTrumpSuit){
            value[3] += 10;
        }
        if(player1Play.getSuit() == currentTrumpSuit || value[0] == 7){
            value[0] += 20;
        }
        if(player2Play.getSuit() == currentTrumpSuit || value[1] == 7){
            value[1] += 20;
        }
        if(player3Play.getSuit() == currentTrumpSuit || value[2] == 7){
            value[2] += 20;
        }
        if(player4Play.getSuit() == currentTrumpSuit || value[3] == 7){
            value[3] += 20;
        }
        int winner = 0;
        int winVal = 0;
        for(int i = 0; i < 4; i++){
            if(value[i] > winVal){
                winVal = value[i];
                winner = i;
            }
        }
        return winner;
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


