package com.example.euchregamestate.Euchre;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.euchregamestate.GameFramework.GameHumanPlayer;
import com.example.euchregamestate.GameFramework.GameMainActivity;
import com.example.euchregamestate.GameFramework.infoMessage.GameInfo;
import com.example.euchregamestate.R;

import java.util.ArrayList;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

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
    private TextView redTrick, blueTrick, redScore, blueScore;
    private int handIndex;

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

    @Override
    //being handed an updated game state
    //latest truth of the game
    //updateGUI
    public void receiveInfo(GameInfo info){
        if ( (info != null) && (info instanceof EuchreState) ) {
            this.latestState = (EuchreState)info;

            //maybe call updateGUI()

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

            //draw background for draw pile
            kitty.setImageResource(R.drawable.cardback);

            player.setImageResource(R.drawable.cardback);
            topPlayer.setImageResource(R.drawable.cardback);
            rightPlayer.setImageResource(R.drawable.cardback);
            leftPlayer.setImageResource(R.drawable.cardback);

            if(latestState.player1Play == null){
                player.setImageResource(R.drawable.cardback);
            }
            else{
                player.setImageResource(latestState.player1Play.getResourceId());
                playerhand1.setImageResource(R.drawable.cardback);
            }

            if(latestState.player2Play == null){
                leftPlayer.setImageResource(R.drawable.cardback);
            }
            else{
                leftPlayer.setImageResource(latestState.player2Play.getResourceId());
                playerhand2.setImageResource(R.drawable.cardback);
            }
            if(latestState.player3Play == null){
                topPlayer.setImageResource(R.drawable.cardback);
            }
            else{
                topPlayer.setImageResource(latestState.player3Play.getResourceId());
                playerhand3.setImageResource(R.drawable.cardback);
            }
            if(latestState.player4Play == null){
                rightPlayer.setImageResource(R.drawable.cardback);
            }
            else{
                rightPlayer.setImageResource(latestState.player4Play.getResourceId());
                playerhand4.setImageResource(R.drawable.cardback);
            }


            //draw everything here based on what is in the latest state
            //playerhand1.setImageResource(R.drawable.ace_d);

            //arraylist of cards
            ArrayList<Card> p1Hand = latestState.getPlayerHand(0);
            ArrayList<Card> p2Hand = latestState.getPlayerHand(1);
            ArrayList<Card> p3Hand = latestState.getPlayerHand(2);
            ArrayList<Card> p4Hand = latestState.getPlayerHand(3);

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
            //switching card and cardback when playing a card
            if(latestState.player1Play == null){
                player.setImageResource(R.drawable.cardback);
            }
            else{
                player.setImageResource(latestState.player1Play.getResourceId());
                if(handIndex == 0){
                    playerhand1.setImageResource(android.R.color.transparent);
                }
                else if(handIndex == 1){
                    playerhand2.setImageResource(android.R.color.transparent);
                }
                else if(handIndex == 2){
                    playerhand3.setImageResource(android.R.color.transparent);
                }
                else if(handIndex == 3){
                    playerhand4.setImageResource(android.R.color.transparent);
                }
                else
                    playerhand5.setImageResource(android.R.color.transparent);
                //player.setBackgroundColor(Color.YELLOW);
            }
            //player.setBackgroundColor(Color.RED);

            if(latestState.player2Play == null){
                leftPlayer.setImageResource(R.drawable.cardback);
            }
            else{
                leftPlayer.setImageResource(latestState.player2Play.getResourceId());
                //leftPlayer.setBackgroundColor(Color.YELLOW);
            }
            //leftPlayer.setBackgroundColor(Color.BLUE);

            if(latestState.player3Play == null){
                topPlayer.setImageResource(R.drawable.cardback);
            }
            else{
                topPlayer.setImageResource(latestState.player3Play.getResourceId());
                //topPlayer.setBackgroundColor(Color.YELLOW);
            }
            //topPlayer.setBackgroundColor(Color.RED);

            if(latestState.player4Play == null){
                rightPlayer.setImageResource(R.drawable.cardback);
            }
            else{
                rightPlayer.setImageResource(latestState.player4Play.getResourceId());
                //rightPlayer.setBackgroundColor(Color.YELLOW);
            }
            //rightPlayer.setBackgroundColor(Color.BLUE);

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
        spadeButton = (Button) myActivity.findViewById(R.id.spadeButton);
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

        /*helpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                LinearLayout layout;

                LayoutInflater layoutInflater = (LayoutInflater) MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.help,null);

                Button closePopupBtn = (Button) customView.findViewById(R.id.closePopUp);

                PopupWindow pop = new PopupWindow(customView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

                pop.showAtLocation(layout, Gravity.CENTER, 0, 0);

                //close the popup window on button click
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });

            }
        });*/


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
                //player.setImageResource(latestState.player1Hand.get(0).getResourceId());
                //playerhand1.setImageResource(R.drawable.cardback);
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(0)));

                handIndex = 0;
            }
        });

        playerhand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //player.setImageResource(latestState.player1Hand.get(1).getResourceId());
                //playerhand2.setImageResource(R.drawable.cardback);
                //latestState.setTurn(2);
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(1)));
                handIndex = 1;

            }
        });

        playerhand3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //player.setImageResource(latestState.player1Hand.get(2).getResourceId());
                //playerhand3.setImageResource(R.drawable.cardback);
                //latestState.setTurn(2);
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(2)));
                handIndex = 2;
            }
        });

        playerhand4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //player.setImageResource(latestState.player1Hand.get(3).getResourceId());
                //playerhand4.setImageResource(R.drawable.cardback);
                //latestState.setTurn(2);
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(3)));
                handIndex = 3;
            }
        });

        playerhand5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //player.setImageResource(latestState.player1Hand.get(4).getResourceId());
                //playerhand5.setImageResource(R.drawable.cardback);
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(4)));
                handIndex = 4;

            }
        });

        spadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //game.sendAction(new EuchreSelectTrumpAction(hp, ));
            }
        });

        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        diamondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        spadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //sendInfo(latestState);
    }//}




}
