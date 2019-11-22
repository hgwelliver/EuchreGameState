package com.example.euchregamestate.Euchre;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Switch;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Card class - information for creating a Card object
 * @author Mikey Ant, Haley Welliver, Sierra Nieland, Alex Rogers
 */
//gets info about a specific card
public class Card implements Serializable {

    int pictureID;
    public enum SUIT { HEARTS, SPADES, CLUBS, DIAMONDS }
    public enum NUMBER { NINE, TEN, ACE, JACK, QUEEN, KING }
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

    public int getResourceId() {
        return pictureID;
    }

    public SUIT getSuit(){
        return this.theSuit;
    }

    public NUMBER getValue(){
        return this.theNumber;
    }

    public static int getValsVal(NUMBER num, SUIT suit, SUIT trump){
        int value = 0;
        if(num == NUMBER.NINE) { value = 1;}
        if(num == NUMBER.TEN) { value = 2;}
        if(num == NUMBER.QUEEN) { value = 3;}
        if(num == NUMBER.KING) { value = 4;}
        if(num == NUMBER.ACE) { value = 5;}
        if(num == NUMBER.JACK && suit == trump) {
            value = 6;
        }
        


        return value;
    }
}
