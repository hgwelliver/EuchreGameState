package com.example.euchregamestate.Euchre;
import com.example.euchregamestate.R;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * CardDeck class - creates a deck of 24 euchre cards that are stored in an array
 * @author Mikey Ant, Haley Welliver, Sierra Nieland, Alex Rogers
 */
//makes all possible cards
public class CardDeck implements Serializable {

    //creates an array for all cards
    //public Card[] cardDeck = new Card[23];
    public ArrayList<Card> cardDeck = new ArrayList<>();
    //creating all cards...assigns a suit, number, and image to each card respectively
    //heart cards
    public Card nine_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.NINE, R.drawable.nine_h);
    public Card ten_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h);
    public Card ace_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.ACE, R.drawable.ace_h);
    public Card jack_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.JACK, R.drawable.jack_h);
    public Card queen_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.QUEEN, R.drawable.queen_h);
    public Card king_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.KING, R.drawable.king_h);

    //spade cards
    public Card nine_s = new Card(Card.SUIT.SPADES, Card.NUMBER.NINE, R.drawable.nine_s);
    public Card ten_s = new Card(Card.SUIT.SPADES, Card.NUMBER.TEN, R.drawable.ten_s);
    public Card ace_s = new Card(Card.SUIT.SPADES, Card.NUMBER.ACE, R.drawable.ace_s);
    public Card jack_s = new Card(Card.SUIT.SPADES, Card.NUMBER.JACK, R.drawable.jack_s);
    public Card queen_s = new Card(Card.SUIT.SPADES, Card.NUMBER.QUEEN, R.drawable.queen_s);
    public Card king_s = new Card(Card.SUIT.SPADES, Card.NUMBER.KING, R.drawable.king_s);

    //club cards
    public Card nine_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.NINE, R.drawable.nine_c);
    public Card ten_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.TEN, R.drawable.ten_c);
    public Card ace_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.ACE, R.drawable.ace_c);
    public Card jack_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.JACK, R.drawable.jack_c);
    public Card queen_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.QUEEN, R.drawable.queen_c);
    public Card king_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.KING, R.drawable.king_c);

    //diamond cards
    public Card nine_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.NINE, R.drawable.nine_d);
    public Card ten_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.TEN, R.drawable.ten_d);
    public Card ace_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.ACE, R.drawable.ace_d);
    public Card jack_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.JACK, R.drawable.jack_d);
    public Card queen_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.QUEEN, R.drawable.queen_d);
    public Card king_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.KING, R.drawable.king_d);


    //adds all possible cards to the arrayList
    public CardDeck() {
        // create a standard euchre deck with the cards above
        cardDeck.add(nine_h);
        cardDeck.add(ten_h);
        cardDeck.add(ace_h);
        cardDeck.add(jack_h);
        cardDeck.add(queen_h);
        cardDeck.add(king_h);

        cardDeck.add(nine_s);
        cardDeck.add(ten_s);
        cardDeck.add(ace_s);
        cardDeck.add(jack_s);
        cardDeck.add(queen_s);
        cardDeck.add(king_s);

        cardDeck.add(nine_c);
        cardDeck.add(ten_c);
        cardDeck.add(ace_c);
        cardDeck.add(jack_c);
        cardDeck.add(queen_c);
        cardDeck.add(king_c);

        cardDeck.add(nine_d);
        cardDeck.add(ten_d);
        cardDeck.add(ace_d);
        cardDeck.add(jack_d);
        cardDeck.add(queen_d);
        cardDeck.add(king_d);
    }

}
