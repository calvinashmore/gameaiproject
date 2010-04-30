/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.game;

import testworld.actions.ConversationDependentAction;

/**
 *
 * @author hartsoka
 */
public class CharDev {

    public static final Token metFrank = new Token("metFrank", "You met Frank.");
    public static final Token metHarriet = new Token("metHarriet", "You met Harriet.");
    public static final Token metHilda = new Token("metHilda", "You met Hilda.");
    public static final Token metGayle = new Token("metGayle", "You met Gayle.");
    public static final Token metHughes = new Token("metHughes", "You met Col. Hughes.");
    public static final Token metVictim = new Token("metVictim", "You met Victim.");

    public static void initializeCharacters() {

        Cast.frank.addDependentAction(new ConversationDependentAction(null, metFrank, CharDevConversations.meetFrank, "Introduce yourself"));
        Cast.harriet.addDependentAction(new ConversationDependentAction(null, metHarriet, CharDevConversations.meetHarriet, "Introduce yourself"));
        Cast.hilda.addDependentAction(new ConversationDependentAction(null, metHilda, CharDevConversations.meetHilda, "Introduce yourself"));
        Cast.hughes.addDependentAction(new ConversationDependentAction(null, metHughes, CharDevConversations.meetHughes, "Introduce yourself"));
        Cast.victim.addDependentAction(new ConversationDependentAction(null, metVictim, CharDevConversations.meetVictim, "Introduce yourself"));

    }

}
