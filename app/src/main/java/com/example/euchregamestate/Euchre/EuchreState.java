package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.infoMessage.GameState;
import com.example.euchregamestate.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EuchreState extends GameState {

    // info about the resources each player has
    protected ArrayList<Card> player1Hand = new ArrayList<>();
    protected ArrayList<Card> player2Hand = new ArrayList<>();
    protected ArrayList<Card> player3Hand = new ArrayList<>();
    protected ArrayList<Card> player4Hand = new ArrayList<>();
    // info about the state of resources
    protected ArrayList<Card> deck = new ArrayList<>();
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
    protected Card.SUIT middleCardSuit; // redundant bc suit is within card
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

    //default constructor
    public EuchreState(){
        // init instance variables
        this.dealer = 1; // change later to start with random dealer
        this.teamDealer = 0;
        this.startGame = true;
        this.quit = false;
        this.gameStage = 0;
        this.numPass = 0;
        this.turn = 0;
        this.trickNum = 0;
        this.redScore = 0;
        this.blueScore = 0;
        this.redTrickScore = 0;
        this.blueTrickScore = 0;
        // init deck of cards
        for(int y = 0; y < 24; y++){
            // fill deck with the 32 cards from the CardDeck class
            // need to make getCard method that allows deck to get cards
            deck.add(y, deck.get(y));
        }
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
        // init deck of cards
        for(int y = 0; y < 24; y++){
            // fill deck with the 32 cards from the CardDeck class
            // need to make getCard method that allows deck to get cards
            deck.add(y, deck.get(y));
        }
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
                "Round is Over: " + isRoundOver(trickNum) + "\n"

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
        if(startGame && gameStage == 0){

            // need to clear everything
            player1Hand.clear();
            player2Hand.clear();
            player3Hand.clear();
            player4Hand.clear();
            kitty.clear();
            currentMiddle.clear();
            // shuffle deck
            Collections.shuffle(deck);
            // deal cards to each player
            // player 1's hand
            for(int i = 0; i < 5; i++){
                player1Hand.add(i, deck.get(i));
            }
            // player 2's hand
            for(int i = 5; i < 10; i++){
                player2Hand.add(i, deck.get(i));
            }
            // player 3's hand
            for(int i = 10; i < 15; i++){
                player3Hand.add(i, deck.get(i));
            }
            // player 4's hand
            for(int i = 15; i < 20; i++){
                player4Hand.add(i, deck.get(i));
            }
            // deal remaining cards to the kitty and choose one as middle card
            for(int i = 20; i < 24; i++){
                if(i == 20){
                    middleCard = deck.get(i);
                    middleCardSuit = middleCard.getSuit();
                }
                kitty.add(i, deck.get(i));
            }

            // make middle card visible
            middleVisible = true;
            // change game state to 1
            gameStage++;
            // print that cards are dealt
            return true;
        }
        return false;
    }
    // method to pass
    public boolean isPass(int playerID){
        // if there have been three passes and the user passes then the middle is turn invisible
        if(numPass == 3 && turn == playerID){
            numPass++;
            // make sure turn goes back to 1 if player is 4
            turn++;
            gameStage++;
            middleVisible = false;
            return true;
        }
        // if numPass is not three or 7 then they can pass normally
        else if(numPass < 7 && numPass != 3 && turn == playerID){
            numPass++;
            // case for player four switching to player 1
            turn++;
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
            if(teamDealer == 0 && (playerID == 1 || playerID == 3) && (playerID != dealer)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isOrderUpTrump(dealer);
                middleVisible = false;
                whoCalled = 0;
                if(dealer == 1){
                    whoIsAlone = 3;
                    player1Hand.clear();
                }
                else{
                    whoIsAlone = 1;
                    player3Hand.clear();
                }
                return true;
            }
            if(teamDealer == 1 && (playerID == 2 || playerID == 4) && (playerID != dealer)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isOrderUpTrump(dealer);
                middleVisible = false;
                whoCalled = 1;
                if(dealer == 2){
                    whoIsAlone = 4;
                    player2Hand.clear();
                }
                else{
                    whoIsAlone = 2;
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
                if(dealer == 1){
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
            else if(teamDealer == 0 && (playerID == 2 || playerID == 4)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isOrderUpTrump(dealer);
                whoCalled = 1;
                if(playerID == 2){
                    whoIsAlone = 2;
                    player4Hand.clear();
                }
                else{
                    whoIsAlone = 4;
                    player2Hand.clear();
                }
                middleVisible = false;
                return true;
            }
            else if(teamDealer == 1 && (playerID == 1 || playerID == 3)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                isOrderUpTrump(dealer);
                whoCalled = 0;
                if(playerID == 1){
                    whoIsAlone = 1;
                    player3Hand.clear();
                }
                else{
                    whoIsAlone = 3;
                    player1Hand.clear();
                }
                middleVisible = false;
                return true;
            }
        }
        else if(turn == playerID && gameStage == 2){
            // if the player is on the dealing team and not the dealer
            if(teamDealer == 0 && (playerID == 1 || playerID == 3) && (playerID != dealer)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                middleVisible = false;
                whoCalled = 0;
                if(dealer == 1){
                    whoIsAlone = 3;
                    player1Hand.clear();
                }
                else{
                    whoIsAlone = 1;
                    player3Hand.clear();
                }
                return true;
            }
            if(teamDealer == 1 && (playerID == 2 || playerID == 4) && (playerID != dealer)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                middleVisible = false;
                whoCalled = 1;
                if(dealer == 2){
                    whoIsAlone = 4;
                    player2Hand.clear();
                }
                else{
                    whoIsAlone = 2;
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
                if(dealer == 1){
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
                if(dealer == 2){
                    player4Hand.clear();
                }
                else{
                    player2Hand.clear();
                }
                return true;
            }
            // non-dealing team goes alone
            else if(teamDealer == 0 && (playerID == 2 || playerID == 4)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                whoCalled = 1;
                if(playerID == 2){
                    whoIsAlone = 2;
                    player4Hand.clear();
                }
                else{
                    whoIsAlone = 4;
                    player2Hand.clear();
                }
                middleVisible = false;
                return true;
            }
            else if(teamDealer == 1 && (playerID == 1 || playerID == 3)){
                // set trump to suit of middle card
                currentSuit = middleCardSuit;
                whoCalled = 0;
                if(playerID == 1){
                    whoIsAlone = 1;
                    player3Hand.clear();
                }
                else{
                    whoIsAlone = 3;
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
    public boolean isSelectTrump(int playerID){
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
    // method for quitting
    public boolean isQuit(){
        startGame = false;
        return true;
    }
    // method for making a move
    public boolean validMove(int playerID){
        if(whoIsAlone == 1 && playerID == 3){
            numPlays++;
            turn++;
            return true;
        }
        else if(whoIsAlone == 2 && playerID == 4){
            numPlays++;
            turn = 1;
            return true;
        }
        else if(whoIsAlone == 3 && playerID == 1){
            numPlays++;
            turn++;
            return true;
        }
        else if(whoIsAlone == 4 && playerID == 2){
            numPlays++;
            turn++;
            return true;
        }
        if(turn == playerID && gameStage == 3){
            if(numPlays == 0){
                if(playerID == 1){
                    // player1Play = card selected
                    // card goes to middle
                    currentSuit = player1Play.getSuit();
                    firstPlayed = player1Play.getSuit();
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 2){
                    // player2Play = card selected
                    // card goes to middle
                    currentSuit = player2Play.getSuit();
                    firstPlayed = player2Play.getSuit();
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 3){
                    // player3Play = card selected
                    // card goes to middle
                    currentSuit = player3Play.getSuit();
                    firstPlayed = player3Play.getSuit();
                    numPlays++;
                    turn++;
                    return true;
                }
                else if(playerID == 4){
                    // player2Play = card selected
                    // card goes to middle
                    currentSuit = player2Play.getSuit();
                    firstPlayed = player3Play.getSuit();
                    numPlays++;
                    turn = 1;
                    return true;
                }
            }
            else if(numPlays < 3){
                if(playerID == 1){
                    ArrayList<Card> valid = new ArrayList<>();
                    for(int i = 0; i < valid.size(); i++){
                        if(player1Play.getSuit() == currentSuit){
                          valid.add(player1Play); // adds card to possible valid plays
                        }
                    }
                    // if valid array is empty then any card is valid
                    if(valid.isEmpty()){
                        // player1Play = card selected
                        // card goes to middle
                        currentSuit = player1Play.getSuit();
                        numPlays++;
                        turn++;
                        return true;
                    }
                    else{
                        if(valid.contains(player1Play)){
                            // player1Play = card selected
                            // card goes to middle
                            currentSuit = player1Play.getSuit();
                            numPlays++;
                            turn++;
                            return true;
                        }
                        else{
                            return false;
                        }
                    }

                }
                else if(playerID == 2){
                    ArrayList<Card> valid = new ArrayList<>();
                    for(int i = 0; i < valid.size(); i++){
                        if(player2Play.getSuit() == currentSuit){
                          valid.add(player2Play); //adds card to possible valid plays
                        }
                    }
                    // if valid array is empty then any card is valid
                    if(valid.isEmpty()){
                        // player2Play = card selected
                        // card goes to middle
                        currentSuit = player1Play.getSuit();
                        numPlays++;
                        turn++;
                        return true;
                    }
                    else{
                        if(valid.contains(player2Play)){
                            // player2Play = card selected
                            // card goes to middle
                            currentSuit = player2Play.getSuit();
                            numPlays++;
                            turn++;
                            return true;
                        }
                        else{
                            return false;
                        }
                    }

                }
                else if(playerID == 3){
                    ArrayList<Card> valid = new ArrayList<>();
                    for(int i = 0; i < valid.size(); i++){
                        if(player3Play.getSuit() == currentSuit){
                          valid.add(player3Play); //adds card to possible valid plays
                        }
                    }
                    // if valid array is empty then any card is valid
                    if(valid.isEmpty()){
                        // player3Play = card selected
                        // card goes to middle
                        currentSuit = player3Play.getSuit();
                        numPlays++;
                        turn++;
                        return true;
                    }
                    else{
                        if(valid.contains(player3Play)){
                            // player3Play = card selected
                            // card goes to middle
                            currentSuit = player3Play.getSuit();
                            numPlays++;
                            turn++;
                            return true;
                        }
                        else{
                            return false;
                        }
                    }

                }
                else if(playerID == 4){
                    ArrayList<Card> valid = new ArrayList<>();
                    for(int i = 0; i < valid.size(); i++){
                        if(player4Play.getSuit() == currentSuit){
                          valid.add(player4Play); // adds card to possible valid plays
                        }
                    }
                    // if valid array is empty then any card is valid
                    if(valid.isEmpty()){
                        // player4Play = card selected
                        // card goes to middle
                        currentSuit = player4Play.getSuit();
                        numPlays++;
                        turn = 1;
                        return true;
                    }
                    else{
                        if(valid.contains(player4Play)){
                            // player4Play = card selected
                            // card goes to middle
                            currentSuit = player4Play.getSuit();
                            numPlays++;
                            turn = 1;
                            return true;
                        }
                        else{
                            return false;
                        }
                    }

                }
            }
            // if three cards have been played this is the last card
            else if(numPlays == 3){
                if(playerID == 1){
                    ArrayList<Card> valid = new ArrayList<>();
                    for(int i = 0; i < valid.size(); i++){
                        if(player1Play.getSuit() == currentSuit){
                          valid.add(player1Play); // adds card to possible valid plays
                        }
                    }
                    // if valid array is empty then any card is valid
                    if(valid.isEmpty()){
                        // player1Play = card selected
                        // card goes to middle
                        currentSuit = player1Play.getSuit();
                        isTrickComplete(true);
                        return true;
                    }
                    else{
                        if(valid.contains(player1Play)){
                            // player1Play = card selected
                            // card goes to middle
                            currentSuit = player1Play.getSuit();
                            isTrickComplete(true);
                            return true;
                        }
                        else{
                            return false;
                        }
                    }

                }
                else if(playerID == 2){
                    ArrayList<Card> valid = new ArrayList<>();
                    for(int i = 0; i < valid.size(); i++){
                        if(player2Play.getSuit() == currentSuit){
                          valid.add(player2Play); // adds card to possible valid plays
                        }
                    }
                    // if valid array is empty then any card is valid
                    if(valid.isEmpty()){
                        // player2Play = card selected
                        // card goes to middle
                        currentSuit = player1Play.getSuit();
                        isTrickComplete(true);
                        return true;
                    }
                    else{
                        if(valid.contains(player2Play)){
                            // player2Play = card selected
                            // card goes to middle
                            currentSuit = player2Play.getSuit();
                            isTrickComplete(true);
                            return true;
                        }
                        else{
                            return false;
                        }
                    }

                }
                else if(playerID == 3){
                    ArrayList<Card> valid = new ArrayList<>();
                    for(int i = 0; i < valid.size(); i++){
                        if(player3Play.getSuit() == currentSuit){
                          valid.add(player3Play); // adds card to possible valid plays
                        }
                    }
                    // if valid array is empty then any card is valid
                    if(valid.isEmpty()){
                        // player3Play = card selected
                        // card goes to middle
                        currentSuit = player3Play.getSuit();
                        isTrickComplete(true);
                        return true;
                    }
                    else{
                        if(valid.contains(player3Play)){
                            // player3Play = card selected
                            // card goes to middle
                            currentSuit = player3Play.getSuit();
                            isTrickComplete(true);
                            return true;
                        }
                        else{
                            return false;
                        }
                    }

                }
                else if(playerID == 4){
                    ArrayList<Card> valid = new ArrayList<>();
                    for(int i = 0; i < valid.size(); i++){
                        if(player4Play.getSuit() == currentSuit){
                          valid.add(player4Play); //adds card to possible valid plays
                        }
                    }
                    // if valid array is empty then any card is valid
                    if(valid.isEmpty()){
                        // player4Play = card selected
                        // card goes to middle
                        currentSuit = player4Play.getSuit();
                        isTrickComplete(true);
                        return true;
                    }
                    else{
                        if(valid.contains(player4Play)){
                            // player4Play = card selected
                            // card goes to middle
                            currentSuit = player4Play.getSuit();
                            isTrickComplete(true);
                            return true;
                        }
                        else{
                            return false;
                        }
                    }

                }
            }
        }
        return false;
    }
    // method for round complete
    public void isTrickComplete(boolean complete){
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
        else if(player3Play.getSuit() == firstPlayed && firstPlayed != currentSuit){
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
            isRoundOver(trickNum);
        }
    }
    // is round over
    public boolean isRoundOver(int trickNum) {
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
}


