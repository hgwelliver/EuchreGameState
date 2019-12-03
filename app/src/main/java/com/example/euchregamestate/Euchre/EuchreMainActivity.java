package com.example.euchregamestate.Euchre;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.euchregamestate.GameFramework.GameMainActivity;
import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.LocalGame;
import com.example.euchregamestate.GameFramework.gameConfiguration.GameConfig;
import com.example.euchregamestate.GameFramework.gameConfiguration.GamePlayerType;
import com.example.euchregamestate.R;


import java.util.ArrayList;

/**
 * @author Sierra, Mikey, Haley, and Alex
 */
public class EuchreMainActivity extends GameMainActivity {

    //Tag for logging
    public static final int PORT_NUMBER = 5213;

    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // yellow-on-blue GUI
        playerTypes.add(new GamePlayerType("Local Human Player ") {
            public GamePlayer createPlayer(String name) {
                return new EuchreHumanPlayer(name, R.layout.activity_main);
            }
        });

        // dumb computer player
        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
            public GamePlayer createPlayer(String name) {
                return new EuchreComputerPlayer(name);
            }
        });
        playerTypes.add(new GamePlayerType("Computer Player (smart)") {
            public GamePlayer createPlayer(String name) {
                return new EuchreSmartComputerPlayer(name);
            }
        });



        // Create a game configuration class for Tic-tac-toe
        GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "Euchre", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0); // yellow-on-blue GUI
        defaultConfig.addPlayer("Computer1", 1); // dumb computer player
        defaultConfig.addPlayer("Computer2", 1); // dumb computer player
        defaultConfig.addPlayer("Computer3", 1); // dumb computer player

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Remote Player", "", 1); // red-on-yellow GUI

        //done!
        return defaultConfig;
    }

    /**
     * createLocalGame
     *
     * Creates a new game that runs on the server tablet,
     *
     * @return a new, game-specific instance of a sub-class of the LocalGame
     *         class.
     */
    @Override
    public EuchreLocalGame createLocalGame() {

        return new EuchreLocalGame();
    }



}




