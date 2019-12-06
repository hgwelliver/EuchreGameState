package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GameMainActivity;
import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.gameConfiguration.GameConfig;
import com.example.euchregamestate.GameFramework.gameConfiguration.GamePlayerType;
import com.example.euchregamestate.R;

import java.util.ArrayList;

/**
 * @author Sierra, Mikey, Haley, and Alex
 */
public class EuchreMainActivity extends GameMainActivity {

    public static final int PORT_NUMBER = 5213;

    @Override
    public GameConfig createDefaultConfig() {

        // Define the allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // human player
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

        // smart computer player
        playerTypes.add(new GamePlayerType("Computer Player (smart)"){
           public GamePlayer createPlayer(String name){
               return new EuchreSmartComputerPlayer(name);
           }
        });

        // Create a game configuration class for Euchre
        GameConfig defaultConfig = new GameConfig(playerTypes, 4, 4, "Euchre", PORT_NUMBER);

        // Add the default players
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer1", 1);
        defaultConfig.addPlayer("Computer2", 1);
        defaultConfig.addPlayer("Computer3", 1);

        // Set the initial information for the remote player
        defaultConfig.setRemoteData("Remote Player", "", 1);

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




