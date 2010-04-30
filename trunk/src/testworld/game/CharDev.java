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
    public static final Token metFred = new Token("metFred", "You met Fred.");
    public static final Token metRose = new Token("metRose", "You met Rose.");

    public static void initializeCharacters() {

        Cast.frank.addDependentAction(new ConversationDependentAction(CharDevConversations.meetFrank, "Introduce yourself"));
        Cast.harriet.addDependentAction(new ConversationDependentAction(CharDevConversations.meetHarriet, "Introduce yourself"));
        Cast.hilda.addDependentAction(new ConversationDependentAction(CharDevConversations.meetHilda, "Introduce yourself"));
        Cast.hughes.addDependentAction(new ConversationDependentAction(CharDevConversations.meetHughes, "Introduce yourself"));
        Cast.victim.addDependentAction(new ConversationDependentAction(CharDevConversations.meetVictim, "Introduce yourself"));
        Cast.gayle.addDependentAction(new ConversationDependentAction(CharDevConversations.meetGayle, "Introduce yourself"));
        Cast.fred.addDependentAction((new ConversationDependentAction(CharDevConversations.meetFred, "Introduce yourself.")));
        
        Cast.frank.addDependentAction(new ConversationDependentAction(CharDevConversations.frankOnHarriet, "Ask about Harriet", metFrank));
        Cast.frank.addDependentAction(new ConversationDependentAction(CharDevConversations.frankOnVictim, "Ask about Victim", metFrank));
        Cast.harriet.addDependentAction(new ConversationDependentAction(CharDevConversations.harrietOnFrank, "Ask about Frank", metHarriet));
        Cast.harriet.addDependentAction(new ConversationDependentAction(CharDevConversations.harrietOnGayle, "Ask about Gayle", metHarriet));
        Cast.hughes.addDependentAction(new ConversationDependentAction(CharDevConversations.hughesOnHilda, "Ask about Hilda", metHughes));
        Cast.hughes.addDependentAction(new ConversationDependentAction(CharDevConversations.hughesOnVictim, "Ask about Victim", metHughes));
        Cast.hilda.addDependentAction(new ConversationDependentAction(CharDevConversations.hildaOnHughes, "Ask about Hughes", metHilda));
        Cast.hilda.addDependentAction(new ConversationDependentAction(CharDevConversations.hildaOnFrank, "Ask about Frank", metHilda));
        Cast.gayle.addDependentAction(new ConversationDependentAction(CharDevConversations.gayleOnHughes, "Ask about Hughes", metGayle));
        Cast.gayle.addDependentAction(new ConversationDependentAction(CharDevConversations.gayleOnVictim, "Ask about Victim", metGayle));
        Cast.victim.addDependentAction(new ConversationDependentAction(CharDevConversations.victimOnGayle, "Ask about Gayle", metVictim));
        Cast.victim.addDependentAction(new ConversationDependentAction(CharDevConversations.victimOnHilda, "Ask about Hilda", metVictim));

        Cast.fred.addDependentAction(new ConversationDependentAction(CharDevConversations.fredWantsSmoke, "Offer cigarette", metFred));
    }

}
