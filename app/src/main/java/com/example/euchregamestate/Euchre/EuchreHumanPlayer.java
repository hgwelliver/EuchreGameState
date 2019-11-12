package com.example.euchregamestate.Euchre;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.euchregamestate.GameFramework.GameHumanPlayer;
import com.example.euchregamestate.GameFramework.GameMainActivity;
import com.example.euchregamestate.GameFramework.infoMessage.GameInfo;
import com.example.euchregamestate.R;

public class EuchreHumanPlayer extends GameHumanPlayer {

    //instance variables
    private static final String TAG = "EuchreHumanPlayer";

    private Activity myActivity;

    private int layoutId;

    public EuchreHumanPlayer(String name, int layoutID){
        super(name);
        this.layoutId = layoutID;
    }

    protected void initAfterReady() {
        if(myActivity == null)
            return;

        final EuchreHumanPlayer hp = this;

        //linking action buttons to listeners
        Button passButton = (Button) myActivity.findViewById(R.id.passButton);
        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call pass function
                game.sendAction(new EuchrePassAction(hp));
            }
        });

        Button pickItUpButton = (Button) myActivity.findViewById(R.id.pickItUpButton);
        pickItUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call pickitup function
                game.sendAction(new EuchrePickItUpAction(hp));
            }
        });

        Button orderUpButton = (Button) myActivity.findViewById(R.id.orderUpButton);
        orderUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call order up function
                game.sendAction(new EuchreOrderUpAction(hp));
            }
        });

        Button goingAloneButton = (Button) myActivity.findViewById(R.id.aloneButton);


        goingAloneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call going alone
                game.sendAction(new EuchreGoingAloneAction(hp));
            }
        });

        Button quitButton = (Button) myActivity.findViewById(R.id.quitButton);
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call quit
                //game.sendAction(new EuchreQuitAction(hp));
            }
        });


        //linking trump selection buttons to listeners
        Button spadeButton = (Button) myActivity.findViewById(R.id.spadeButton);
        spadeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //changes trump to spades
            }
        });

        Button clubButton = (Button) myActivity.findViewById(R.id.clubButton);
        clubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //changes trump to clubs
            }
        });

        Button heartButton = (Button) myActivity.findViewById(R.id.heartButton);
        heartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //changes trump to hearts
            }
        });

        Button diamondButton = (Button) myActivity.findViewById(R.id.diamondButton);
        diamondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //changes trump to diamonds
            }
        });



        //linking cards on layout
        //player hand
        ImageView playerHand1 = (ImageView) myActivity.findViewById(R.id.playerhand1);
        playerHand1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sends card out to [player] when clicked
            }
        });

        ImageView playerHand2 = (ImageView) myActivity.findViewById(R.id.playerhand2);
        playerHand2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sends card out to [player] when clicked
            }
        });

        ImageView playerHand3 = (ImageView) myActivity.findViewById(R.id.playerhand3);
        playerHand3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sends card out to [player] when clicked
            }
        });

        ImageView playerHand4 = (ImageView) myActivity.findViewById(R.id.playerhand4);
        playerHand4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sends card out to [player] when clicked
            }
        });

        ImageView playerHand5 = (ImageView) myActivity.findViewById(R.id.playerhand5);
        playerHand5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sends card out to [player] when clicked
            }
        });


        //game play cards - these do not need onClickListeners because they do not
        // need to be clicked, they serve as placeholders
        ImageView player = (ImageView) myActivity.findViewById(R.id.player);

        ImageView rightPlayer = (ImageView) myActivity.findViewById(R.id.rightplayer);

        ImageView leftPlayer = (ImageView) myActivity.findViewById(R.id.leftplayer);

        ImageView topPlayer = (ImageView) myActivity.findViewById(R.id.topplayer);
    }

    protected View v;
    public View getTopView(){
        return v;
    }

    public void receiveInfo(GameInfo info){

    }

    public void setAsGui(GameMainActivity activity){

    }
}
