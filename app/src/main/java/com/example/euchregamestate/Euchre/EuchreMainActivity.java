package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GameMainActivity;
import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.LocalGame;
import com.example.euchregamestate.GameFramework.gameConfiguration.GameConfig;
import com.example.euchregamestate.GameFramework.gameConfiguration.GamePlayerType;
import com.example.euchregamestate.R;


import java.util.ArrayList;

public class EuchreMainActivity extends GameMainActivity {
    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // yellow-on-blue GUI
        playerTypes.add(new GamePlayerType("Local Human Player (blue-yellow)") {
            public GamePlayer createPlayer(String name) {
                return new EuchreHumanPlayer1(name, R.layout.euchre_human_player1);
            }
        });

        // red-on-yellow GUI
        playerTypes.add(new GamePlayerType("Local Human Player (yellow-red)") {
            public GamePlayer createPlayer(String name) {
                return new EuchreHumanPlayer1(name, R.layout.euchre_human_player1_flipped);
            }
        });
        // game of 33
        playerTypes.add(new GamePlayerType("Local Human Player (game of 33)") {
            public GamePlayer createPlayer(String name) {
                return new EuchreHumanPlayer2(name);
            }
        });

        // dumb computer player
        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
            public GamePlayer createPlayer(String name) {
                return new EuchreComputerPlayer1(name);
            }
        });

        // smarter computer player
        playerTypes.add(new GamePlayerType("Computer Player (smart)") {
            public GamePlayer createPlayer(String name) {
                return new EuchreComputerPlayer2(name);
            }
        });

        // Create a game configuration class for Tic-tac-toe
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Tic-Tac-Toe", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0); // yellow-on-blue GUI
        defaultConfig.addPlayer("Computer", 1); // dumb computer player

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
    public LocalGame createLocalGame() {

        return new EuchreLocalGame();
    }


}




