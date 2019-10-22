package com.example.euchregamestate.Euchre;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Card class - information for creating a Card object
 * @author Mikey Ant, Haley Welliver, Sierra Nieland, Alex Rogers
 */
//gets info about a specific card
public class Card implements Serializable {

    int pictureID;
    enum SUIT { HEARTS, SPADES, CLUBS, DIAMONDS }
    enum NUMBER { NINE, TEN, ACE, JACK, QUEEN, KING }
    SUIT theSuit;
    NUMBER theNumber;
    String cardName;

    //sets the card's suit, number value, and picture id
    public Card(SUIT suit, NUMBER number, int pID){
        this.pictureID = pID;
        this.theSuit = suit;
        this.theNumber = number;
        cardName = number + " of " + suit;
    }

    public String getCardName() {
        return cardName;
    }

    public int getPictureID() {
        return pictureID;
    }

    public SUIT getSuitById(int id){
        SUIT suit = null;

        switch(id){
            case 1:
                suit = SUIT.HEARTS;
                break;
            case 2:
                suit = SUIT.SPADES;
                break;
            case 3:
                suit = SUIT.CLUBS;
                break;
            case 4:
                suit = SUIT.DIAMONDS;
                break;
            default:
                break;
        }
        return suit;
    }

    public NUMBER getNumberById(int id){
        NUMBER number = null;

        switch(id){
            case 1:
                number = NUMBER.NINE;
                break;
            case 2:
                number = NUMBER.TEN;
                break;
            case 3:
                number = NUMBER.ACE;
                break;
            case 4:
                number = NUMBER.JACK;
                break;
            case 5:
                number = NUMBER.QUEEN;
                break;
            case 6:
                number = NUMBER.KING;
                break;
            default:
                break;
        }
        return number;
    }


}
