package com.example.euchregamestate.Euchre;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.euchregamestate.GameFramework.GameHumanPlayer;
import com.example.euchregamestate.GameFramework.GameMainActivity;
import com.example.euchregamestate.GameFramework.infoMessage.GameInfo;
import com.example.euchregamestate.R;

import java.util.ArrayList;

/**
 * @author Sierra, Mikey, Haley, and Alex
 */
public class EuchreHumanPlayer extends GameHumanPlayer {

    //instance variables
    private static final String TAG = "EuchreHumanPlayer";

    private Button passButton, pickItUpButton, orderUpButton, goingAloneButton,
            quitButton;
    private Button helpButton, resetButton;
    private Button spadeButton, clubButton, heartButton, diamondButton;
    private ImageView playerhand1, playerhand2, playerhand3, playerhand4, playerhand5;
    private ArrayList<ImageView> playerHands = new ArrayList<ImageView>();
    private ImageView player, rightPlayer, leftPlayer, topPlayer, kitty;
    private TextView redTrick, blueTrick, redScore, blueScore, selectTrumpButton;

    private Activity myActivity;

    private int layoutId;


    private EuchreState latestState = null;

    public EuchreHumanPlayer(String name, int layoutID){
        super(name);
        this.layoutId = layoutID;
    }

    protected void initAfterReady() {


    }

    protected View v;
    public View getTopView(){
        return v;
    }

    /**
     * receive info method
     * being handed an updated game state
     * latest truth of the game
     * updateGUI
     */
    @Override
    public void receiveInfo(GameInfo info){
        if ( (info != null) && (info instanceof EuchreState) ) {
            this.latestState = (EuchreState)info;


            playerHands.add(player);
            playerHands.add(topPlayer);
            playerHands.add(leftPlayer);
            playerHands.add(rightPlayer);
            playerHands.add(kitty);

            //set textviews
            redTrick.setText("Red Trick Score: " + latestState.redTrickScore);
            blueTrick.setText(" Blue Trick Score: " + latestState.blueTrickScore);
            redScore.setText("Red Team  Score: " + latestState.redScore);
            blueScore.setText("Blue Team  Score: " + latestState.blueScore);

            //draw background to be used as placeholder for draw pile and other cards on the table
            kitty.setImageResource(R.drawable.cardback);
            player.setImageResource(R.drawable.cardback);
            topPlayer.setImageResource(R.drawable.cardback);
            rightPlayer.setImageResource(R.drawable.cardback);
            leftPlayer.setImageResource(R.drawable.cardback);

            //sets middle cards to card backs and sets image for kitty in each gamestage

            if(latestState.gameStage <= 1){
                kitty.setImageResource(latestState.kittyTop.getResourceId());

                spadeButton.setAlpha(0);
                diamondButton.setAlpha(0);
                clubButton.setAlpha(0);
                heartButton.setAlpha(0);

                selectTrumpButton.setAlpha(0);

                // kitty interaction buttons enabled
                orderUpButton.setAlpha(1);
                pickItUpButton.setAlpha(1);
                goingAloneButton.setAlpha(1);
                passButton.setAlpha(1);
            }
            else if(latestState.gameStage == 2){
                kitty.setImageResource(R.drawable.cardback);
            }
            else if(latestState.gameStage == 3){
                kitty.setImageResource(android.R.color.transparent);
            }

            //trump buttons alpha
            if(latestState.gameStage == 2){
                //draw all trump suits
                spadeButton.setAlpha(1);
                diamondButton.setAlpha(1);
                clubButton.setAlpha(1);
                heartButton.setAlpha(1);
                if(latestState.kittyTop.getSuit() == Card.SUIT.SPADES){
                    spadeButton.setAlpha(0);
                }
                if(latestState.kittyTop.getSuit() == Card.SUIT.DIAMONDS){
                    diamondButton.setAlpha(0);
                }
                if(latestState.kittyTop.getSuit() == Card.SUIT.CLUBS){
                    clubButton.setAlpha(0);
                }
                if(latestState.kittyTop.getSuit() == Card.SUIT.HEARTS){
                    heartButton.setAlpha(0);
                }


                selectTrumpButton.setAlpha(1);

                // kitty interaction buttons disabled
                orderUpButton.setAlpha(0);
                pickItUpButton.setAlpha(0);
                goingAloneButton.setAlpha(0);
                if(latestState.turn == latestState.dealer){
                    // pass button disabled to stick the dealer
                    passButton.setAlpha(0);
                }
            }
            else{
                //set all 4 trump buttons transparent
                spadeButton.setAlpha(0);
                diamondButton.setAlpha(0);
                clubButton.setAlpha(0);
                heartButton.setAlpha(0);

                if(latestState.currentTrumpSuit == Card.SUIT.CLUBS){
                    clubButton.setAlpha(1);
                }
                else if(latestState.currentTrumpSuit == Card.SUIT.HEARTS){
                    heartButton.setAlpha(1);
                }
                else if(latestState.currentTrumpSuit == Card.SUIT.DIAMONDS){
                    diamondButton.setAlpha(1);
                }
                else if(latestState.currentTrumpSuit == Card.SUIT.SPADES){
                    spadeButton.setAlpha(1);
                }


            }

            //arraylist of cards
            ArrayList<Card> p1Hand = latestState.getPlayerHand(0);

            //shows dealed cards
            if(p1Hand.size() > 0){
             int id = p1Hand.get(0).getResourceId();
             playerhand1.setImageResource(id);
            }
            else{
                playerhand1.setImageResource(R.drawable.cardback);
            }
            if(p1Hand.size() > 1){
             int id = p1Hand.get(1).getResourceId();
             playerhand2.setImageResource(id);
            }
            else{
                playerhand2.setImageResource(R.drawable.cardback);
            }
            if(p1Hand.size() > 2){
                int id = p1Hand.get(2).getResourceId();
                playerhand3.setImageResource(id);
            }
            else{
                playerhand3.setImageResource(R.drawable.cardback);
            }
            if(p1Hand.size() > 3){
                int id = p1Hand.get(3).getResourceId();
                playerhand4.setImageResource(id);
            }
            else{
                playerhand4.setImageResource(R.drawable.cardback);
            }
            if(p1Hand.size() > 4){
                int id = p1Hand.get(4).getResourceId();
                playerhand5.setImageResource(id);
            }
            else{
                playerhand5.setImageResource(R.drawable.cardback);
            }

            player.setBackgroundColor(Color.YELLOW);
            //switching card and card back when playing a card
            if(latestState.player1Play == null){
                player.setImageResource(R.drawable.cardback);
            }
            else{
                player.setImageResource(latestState.player1Play.getResourceId());
            }

            if(latestState.player2Play == null){
                leftPlayer.setImageResource(R.drawable.cardback);
            }
            else{
                leftPlayer.setImageResource(latestState.player2Play.getResourceId());
            }


            if(latestState.player3Play == null){
                topPlayer.setImageResource(R.drawable.cardback);
            }
            else{
                topPlayer.setImageResource(latestState.player3Play.getResourceId());
            }

            if(latestState.player4Play == null){
                rightPlayer.setImageResource(R.drawable.cardback);
            }
            else{
                rightPlayer.setImageResource(latestState.player4Play.getResourceId());
            }

            int lightBlue = 0xff33b5e5;
            int yellow = 0xffffbb33;
            int red = 0xffcc0000;
            if(latestState.turn == 0)
                player.setBackgroundColor(yellow);

            else
                player.setBackgroundColor(red);

            if(latestState.turn == 1)
                leftPlayer.setBackgroundColor(yellow);

            else
                leftPlayer.setBackgroundColor(lightBlue);

            if(latestState.turn == 2)
                topPlayer.setBackgroundColor(yellow);

            else
                topPlayer.setBackgroundColor(red);

            if(latestState.turn == 3)
                rightPlayer.setBackgroundColor(yellow);

            else
                rightPlayer.setBackgroundColor(lightBlue);

        }
        else {
            return;
        }

    }

    /**
     * set GUI method
     * updates the interface
     * sets onClick methods for the buttons and image views
     */
    @Override
    public void setAsGui(GameMainActivity activity){
        // Load the layout resource for the new configuration

        myActivity = activity;

        final EuchreHumanPlayer hp = this;

        if(myActivity == null)
            return;

        activity.setContentView(R.layout.activity_main);

        //creating the button and ImageViews used to draw the cards

        passButton = (Button) myActivity.findViewById(R.id.passButton);
        pickItUpButton = (Button) myActivity.findViewById(R.id.pickItUpButton);
        orderUpButton = (Button) myActivity.findViewById(R.id.orderUpButton);
        goingAloneButton = (Button) myActivity.findViewById(R.id.aloneButton);
        quitButton = (Button) myActivity.findViewById(R.id.quitButton);
        helpButton = (Button) myActivity.findViewById(R.id.helpMenuButton);
        resetButton = (Button) myActivity.findViewById(R.id.resetButton);

        redTrick = (TextView) myActivity.findViewById(R.id.redTricks);
        blueTrick = (TextView) myActivity.findViewById(R.id.blueTricks);
        redScore = (TextView) myActivity.findViewById(R.id.redScore);
        blueScore = (TextView) myActivity.findViewById(R.id.blueScore);
        selectTrumpButton = (TextView) myActivity.findViewById(R.id.selectTrumpButton);


        //cards on table
        //these do not need onClickListeners because they do not
        // need to be clicked, they serve as placeholders
        player = (ImageView) myActivity.findViewById(R.id.player);
        topPlayer = (ImageView) myActivity.findViewById(R.id.topplayer);
        rightPlayer = (ImageView) myActivity.findViewById(R.id.rightplayer);
        leftPlayer = (ImageView) myActivity.findViewById(R.id.leftplayer);
        kitty = (ImageView) myActivity.findViewById(R.id.kitty);


        //cards in hand
        playerhand1 = (ImageView) myActivity.findViewById(R.id.playerhand1);
        playerhand2 = (ImageView) myActivity.findViewById(R.id.playerhand2);
        playerhand3 = (ImageView) myActivity.findViewById(R.id.playerhand3);
        playerhand4 = (ImageView) myActivity.findViewById(R.id.playerhand4);
        playerhand5 = (ImageView) myActivity.findViewById(R.id.playerhand5);

        //suit buttons
        clubButton = (Button) myActivity.findViewById(R.id.clubButton);
        heartButton = (Button) myActivity.findViewById(R.id.heartButton);
        diamondButton = (Button) myActivity.findViewById(R.id.diamondButton);
        spadeButton = (Button) myActivity.findViewById(R.id.spadeButton);

        //quits game
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quits program
                myActivity.finish();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //restarts program
                Intent intent = myActivity.getIntent();
                myActivity.finish();
                myActivity.startActivity(intent);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final Dialog helpMenu = new Dialog(myActivity);
                helpMenu.setContentView(myActivity.getLayoutInflater().inflate(R.layout.help, null));
                ImageView helpImage = (ImageView) helpMenu.findViewById(R.id.helpmenu_page_one);
                helpImage.setImageResource(R.drawable.euchre_help_menu);

                //close the popup window on button click
                helpImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        helpMenu.dismiss();
                    }
                });

                helpMenu.show();
            }
        });


        //if(latestState.getTurn() ==0){//can only do actions on turn

        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.sendAction(new EuchrePassAction(hp));

            }
        });

        pickItUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.sendAction(new EuchrePickItUpAction(hp));

            }
        });

        orderUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.sendAction(new EuchreOrderUpAction(hp));

            }
        });

        goingAloneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.sendAction(new EuchreGoingAloneAction(hp));

            }
        });

        //when the cards in player hand are clicked
        playerhand1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(0)));
            }
        });

        playerhand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(latestState.player1Hand.size() > 1){
                    game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(1)));
                }
            }
        });

        playerhand3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(latestState.player1Hand.size() > 2){
                    game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(2)));
                }
            }
        });

        playerhand4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(latestState.player1Hand.size() > 3){
                    game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(3)));
                }
            }
        });

        playerhand5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(latestState.player1Hand.size() > 4){
                    game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(4)));
                }
            }
        });

        spadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.sendAction(new EuchreSelectTrumpAction(hp, Card.SUIT.SPADES));
            }
        });

        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.sendAction(new EuchreSelectTrumpAction(hp, Card.SUIT.HEARTS));
            }
        });

        diamondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.sendAction(new EuchreSelectTrumpAction(hp, Card.SUIT.DIAMONDS));
            }
        });

        clubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.sendAction(new EuchreSelectTrumpAction(hp, Card.SUIT.CLUBS));
            }
        });

    }
}
