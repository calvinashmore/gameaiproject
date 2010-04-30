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

        Cast.frank.addDependentAction(new ConversationDependentAction(CharDevConversations.meetFrank, "Introduce yourself", metFrank));
        Cast.harriet.addDependentAction(new ConversationDependentAction(CharDevConversations.meetHarriet, "Introduce yourself", metHarriet));
        Cast.hilda.addDependentAction(new ConversationDependentAction(CharDevConversations.meetHilda, "Introduce yourself", metHilda));
        Cast.hughes.addDependentAction(new ConversationDependentAction(CharDevConversations.meetHughes, "Introduce yourself", metHughes));
        Cast.victim.addDependentAction(new ConversationDependentAction(CharDevConversations.meetVictim, "Introduce yourself", metVictim));
        Cast.gayle.addDependentAction(new ConversationDependentAction(CharDevConversations.meetGayle, "Introduce yourself", metGayle));
        Cast.fred.addDependentAction((new ConversationDependentAction(CharDevConversations.meetFred, "Introduce yourself.", metFred)));
        
        Cast.frank.addDependentAction(new ConversationDependentAction(metFrank, CharDevConversations.frankOnHarriet, "Ask about Harriet"));
        Cast.frank.addDependentAction(new ConversationDependentAction(metFrank, CharDevConversations.frankOnVictim, "Ask about Victim"));
        Cast.harriet.addDependentAction(new ConversationDependentAction(metHarriet, CharDevConversations.harrietOnFrank, "Ask about Frank"));
        Cast.harriet.addDependentAction(new ConversationDependentAction(metHarriet, CharDevConversations.harrietOnGayle, "Ask about Gayle"));
        Cast.hughes.addDependentAction(new ConversationDependentAction(metHughes, CharDevConversations.hughesOnHilda, "Ask about Hilda"));
        Cast.hughes.addDependentAction(new ConversationDependentAction(metHughes, CharDevConversations.hughesOnVictim, "Ask about Vicmti"));
        Cast.hilda.addDependentAction(new ConversationDependentAction(metHilda, CharDevConversations.hildaOnHughes, "Ask about Hughes"));
        Cast.hilda.addDependentAction(new ConversationDependentAction(metHilda, CharDevConversations.hildaOnFrank, "Ask about Frank"));
        Cast.gayle.addDependentAction(new ConversationDependentAction(metGayle, CharDevConversations.gayleOnHughes, "Ask about Hughes"));
        Cast.gayle.addDependentAction(new ConversationDependentAction(metGayle, CharDevConversations.gayleOnVictim, "Ask about Victim"));
        Cast.victim.addDependentAction(new ConversationDependentAction(metVictim, CharDevConversations.victimOnGayle, "Ask about Gayle"));
        Cast.victim.addDependentAction(new ConversationDependentAction(metVictim, CharDevConversations.victimOnHilda, "Ask about Hilda"));

        Cast.fred.addDependentAction(new ConversationDependentAction(metFred, CharDevConversations.fredWantsSmoke, "Offer cigarette"));
    }

}
