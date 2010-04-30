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
        Cast.gayle.addDependentAction(new ConversationDependentAction(null, metGayle, CharDevConversations.meetGayle, "Introduce yourself"));
        
        Cast.frank.addDependentAction(new ConversationDependentAction(metFrank, null, CharDevConversations.frankOnHarriet, "Ask about Harriet"));
        Cast.frank.addDependentAction(new ConversationDependentAction(metFrank, null, CharDevConversations.frankOnVictim, "Ask about Victim"));
        Cast.harriet.addDependentAction(new ConversationDependentAction(metHarriet, null, CharDevConversations.harrietOnFrank, "Ask about Frank"));
        Cast.harriet.addDependentAction(new ConversationDependentAction(metHarriet, null, CharDevConversations.harrietOnGayle, "Ask about Gayle"));

        Cast.hughes.addDependentAction(new ConversationDependentAction(metHughes, null, CharDevConversations.hughesOnHilda, "Ask about Hilda"));
        Cast.hughes.addDependentAction(new ConversationDependentAction(metHughes, null, CharDevConversations.hughesOnVictim, "Ask about Vicmti"));
        Cast.hilda.addDependentAction(new ConversationDependentAction(metHilda, null, CharDevConversations.hildaOnHughes, "Ask about Hughes"));
        Cast.hilda.addDependentAction(new ConversationDependentAction(metHilda, null, CharDevConversations.hildaOnFrank, "Ask about Frank"));
        Cast.gayle.addDependentAction(new ConversationDependentAction(metGayle, null, CharDevConversations.gayleOnHughes, "Ask about Hughes"));
        Cast.gayle.addDependentAction(new ConversationDependentAction(metGayle, null, CharDevConversations.gayleOnVictim, "Ask about Victim"));
        Cast.victim.addDependentAction(new ConversationDependentAction(metVictim, null, CharDevConversations.victimOnGayle, "Ask about Gayle"));
        Cast.victim.addDependentAction(new ConversationDependentAction(metVictim, null, CharDevConversations.victimOnHilda, "Ask about Hilda"));
    }

}
