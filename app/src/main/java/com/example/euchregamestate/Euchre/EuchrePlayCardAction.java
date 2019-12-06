package com.example.euchregamestate.Euchre;

import com.example.euchregamestate.GameFramework.GamePlayer;
import com.example.euchregamestate.GameFramework.actionMessage.GameAction;

/**
 * @author Sierra, Mikey, Haley, and Alex
 */
public class EuchrePlayCardAction extends GameAction {
    private static final long serialVersionUID = -2242980258970485343L;
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
