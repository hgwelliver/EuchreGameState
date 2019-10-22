package com.example.euchregamestate.Euchre;
import com.example.euchregamestate.R;

import java.io.Serializable;

/**
 * CardDeck class - creates a deck of 24 euchre cards that are stored in an array
 * @author Mikey Ant, Haley Welliver, Sierra Nieland, Alex Rogers
 */
//makes all possible cards
public class CardDeck implements Serializable {

    //creates an array for all cards
    public Card[] cardDeck = new Card[23];

    //creating all cards...assigns a suit, number, and image to each card respectively
    //heart cards
    Card nine_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.NINE, R.drawable.nine_h);
    Card ten_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.TEN, R.drawable.ten_h);
    Card ace_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.ACE, R.drawable.ace_h);
    Card jack_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.JACK, R.drawable.jack_h);
    Card queen_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.QUEEN, R.drawable.queen_h);
    Card king_h = new Card(Card.SUIT.HEARTS, Card.NUMBER.KING, R.drawable.king_h);

    //spade cards
    Card nine_s = new Card(Card.SUIT.SPADES, Card.NUMBER.NINE, R.drawable.nine_s);
    Card ten_s = new Card(Card.SUIT.SPADES, Card.NUMBER.TEN, R.drawable.ten_s);
    Card ace_s = new Card(Card.SUIT.SPADES, Card.NUMBER.ACE, R.drawable.ace_s);
    Card jack_s = new Card(Card.SUIT.SPADES, Card.NUMBER.JACK, R.drawable.jack_s);
    Card queen_s = new Card(Card.SUIT.SPADES, Card.NUMBER.QUEEN, R.drawable.queen_s);
    Card king_s = new Card(Card.SUIT.SPADES, Card.NUMBER.KING, R.drawable.king_s);

    //club cards
    Card nine_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.NINE, R.drawable.nine_c);
    Card ten_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.TEN, R.drawable.ten_c);
    Card ace_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.ACE, R.drawable.ace_c);
    Card jack_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.JACK, R.drawable.jack_c);
    Card queen_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.QUEEN, R.drawable.queen_c);
    Card king_c = new Card(Card.SUIT.CLUBS, Card.NUMBER.KING, R.drawable.king_c);

    //diamond cards
    Card nine_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.NINE, R.drawable.nine_d);
    Card ten_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.TEN, R.drawable.ten_d);
    Card ace_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.ACE, R.drawable.ace_d);
    Card jack_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.JACK, R.drawable.jack_d);
    Card queen_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.QUEEN, R.drawable.queen_d);
    Card king_d = new Card(Card.SUIT.DIAMONDS, Card.NUMBER.KING, R.drawable.king_d);


    //adds all possible cards to the arrayList
    public CardDeck() {
        cardDeck[0] = nine_h;
        cardDeck[1] = ten_h;
        cardDeck[2] = ace_h;
        cardDeck[3] = jack_h;
        cardDeck[4] = queen_h;
        cardDeck[5] = king_h;

        cardDeck[6] = nine_s;
        cardDeck[7] = ten_s;
        cardDeck[8] = ace_s;
        cardDeck[9] = jack_s;
        cardDeck[10] = queen_s;
        cardDeck[11] = king_s;

        cardDeck[12] = nine_c;
        cardDeck[13] = ten_c;
        cardDeck[14] = ace_c;
        cardDeck[15] = jack_c;
        cardDeck[16] = queen_c;
        cardDeck[17] = king_c;

        cardDeck[18] = nine_d;
        cardDeck[19] = ten_d;
        cardDeck[20] = ace_d;
        cardDeck[21] = jack_d;
        cardDeck[22] = queen_d;
        cardDeck[23] = king_d;
    }
}
