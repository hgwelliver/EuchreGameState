package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.actionMessage.GameAction;

/**
 * @author Sierra, Mikey, Haley, and Alex
 */
public class EuchrePickItUpAction extends GameAction {
    private Card card;
    public EuchrePickItUpAction(GamePlayer player, Card cardToDiscard){
        super(player);
        card = cardToDiscard;
    }
    public Card getCardToDiscard(){return card;}
}
