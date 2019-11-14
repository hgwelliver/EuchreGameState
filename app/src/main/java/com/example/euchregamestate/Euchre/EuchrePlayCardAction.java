package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.actionMessage.GameAction;

public class EuchrePlayCardAction extends GameAction {
    private Card card;
    public EuchrePlayCardAction(GamePlayer player, Card cardToPlay){
        super(player);
        card = cardToPlay;
    }

    public Card getCardToPlay()
    {
        return card;
    }
}
