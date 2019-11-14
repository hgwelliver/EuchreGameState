package com.example.euchregamestate.Euchre;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.euchregamestate.GameFramework.GameHumanPlayer;
import com.example.euchregamestate.GameFramework.GameMainActivity;
import com.example.euchregamestate.GameFramework.infoMessage.GameInfo;
import com.example.euchregamestate.R;

import java.util.ArrayList;

public class EuchreHumanPlayer extends GameHumanPlayer {

    //instance variables
    private static final String TAG = "EuchreHumanPlayer";

    private Button passButton, pickItUpButton, orderUpButton, goingAloneButton,
            quitButton, helpMenuButton;
    private ImageView spadeButton, clubButton, heartButton, diamondButton;
    private ImageView playerhand1, playerhand2, playerhand3, playerhand4, playerhand5;
    private ArrayList<ImageView> playerHands = new ArrayList<ImageView>();
    private ImageView player, rightPlayer, leftPlayer, topPlayer, kitty;

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

            //draw background for draw pile
            kitty.setImageResource(R.drawable.cardback);

            player.setImageResource(R.drawable.cardback);
            topPlayer.setImageResource(R.drawable.cardback);
            rightPlayer.setImageResource(R.drawable.cardback);
            leftPlayer.setImageResource(R.drawable.cardback);

            //draw everything here based on what is in the latest state
            //playerhand1.setImageResource(R.drawable.ace_d);

            //arraylist of cards
            ArrayList<Card> p1Hand = latestState.getPlayerHand(1);

            //ask the card for its image resource id
            //int id = p1Hand.get(0).getResourceId();
            //setImageResource like above ^
            //playerhand1.setImageResource(id);
         /*   int i = 0;
           for(; i < p1Hand.size(); i++){
                int id = p1Hand.get(i).getResourceId();
                //setImageResource like above ^
                playerhand1.setImageResource(id);
            }
            for(; i < 5; i++){
                //set cards to empty
                playerhand1.setImageResource(R.drawable.cardback);
                playerhand2.setImageResource(R.drawable.cardback);
                playerhand3.setImageResource(R.drawable.cardback);
                playerhand4.setImageResource(R.drawable.cardback);
                playerhand5.setImageResource(R.drawable.cardback);
            }*/
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

        //quits game
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //quits program
                myActivity.finish();
            }
        });

        //haley will do this
/*        helpMenuButton.setOnClickListener(new View.OnClickListener() {
            //displays rules when button is pressed
            @Override
            public void onClick(View v) {
                myActivity.startActivity(new Intent(myActivity, help.class));
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
            }
        });

        playerhand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //player.setImageResource(latestState.player1Hand.get(1).getResourceId());
                //playerhand2.setImageResource(R.drawable.cardback);
                //latestState.setTurn(2);
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(1)));
            }
        });

        playerhand3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //player.setImageResource(latestState.player1Hand.get(2).getResourceId());
                //playerhand3.setImageResource(R.drawable.cardback);
                //latestState.setTurn(2);
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(2)));
            }
        });

        playerhand4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //player.setImageResource(latestState.player1Hand.get(3).getResourceId());
                //playerhand4.setImageResource(R.drawable.cardback);
                //latestState.setTurn(2);
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(3)));
            }
        });

        playerhand5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //player.setImageResource(latestState.player1Hand.get(4).getResourceId());
                //playerhand5.setImageResource(R.drawable.cardback);
                game.sendAction(new EuchrePlayCardAction(hp,latestState.player1Hand.get(4)));

            }
        });


        //sendInfo(latestState);
    }//}




}
