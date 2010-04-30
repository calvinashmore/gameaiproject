/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

import testworld.actions.ConversationDependentAction;

/**
 *
 * @author Calvin Ashmore
 */
public class Plot {

//    public static final Token heardAboutRumor = new Token("heardAboutRumor", "You heard about a scandalous rumor");
//    public static final Token frankWantsRevenge = new Token("frankWantsRevenge", "Frank wants revenge");
//    public static final Token spikingFranksDrink = new Token("spikingFranksDrink", "You are spiking Frank's drink");
//    public static final Token frankSloshed = new Token("frankSloshed", "Frank is drunk. Really, really drunk.");

    public static final Token victimCanBlackmail = new Token("victimCanBlackmail", "Mr. Victim has information which could hurt Frank.");

    public static final Token metFrank = new Token("metFrank", "You met Frank.");
    public static final Token metHarriet = new Token("metHarriet", "You met Harriet.");
    public static final Token metHilda = new Token("metHilda", "You met Hilda.");
    public static final Token metGayle = new Token("metGayle", "You met Gayle.");
    public static final Token metHughes = new Token("metHughes", "You met Col. Hughes.");
    public static final Token metVictim = new Token("metVictim", "You met Victim.");

    public static void initializePlot() {
//        Cast.hilda.addDependentAction(new ConversationDependentAction(null, PlotConversations.frankRumor, "Gossip"));
//        Cast.frank.addDependentAction(new ConversationDependentAction(heardAboutRumor, PlotConversations.embarrassFrank, "Embarrass Frank"));
//        Cast.gayle.addDependentAction(new ConversationDependentAction(heardAboutRumor, PlotConversations.approachGayle, "Approach Gayle"));
//        Cast.hilda.addDependentAction(new ConversationDependentAction(frankWantsRevenge, PlotConversations.warnHilda, "Warn Hilda"));
//        Cast.frank.addDependentAction(new ConversationDependentAction(frankWantsRevenge, PlotConversations.annoyFrankFurther, "Blackmail Frank"));
//        Cast.fred.addDependentAction(new ConversationDependentAction(frankWantsRevenge, PlotConversations.spikeFranksDrink, "Spike Frank's Drink"));
//        Cast.harriet.addDependentAction(new ConversationDependentAction(null, PlotConversations.askHarrietToPokeFrank, "Ask Harriet to poke Frank"));
//
//        Cast.frank.addDependentAction(new ConversationDependentAction(null, PlotConversations.drunkenTease, "Tease Frank for being drunk."));

        Cast.frank.addDependentAction(new ConversationDependentAction(null, metFrank, PlotConversations.meetFrank, "Introduce yourself"));
        Cast.harriet.addDependentAction(new ConversationDependentAction(null, metHarriet, PlotConversations.meetHarriet, "Introduce yourself"));
        Cast.hilda.addDependentAction(new ConversationDependentAction(null, metHilda, PlotConversations.meetHilda, "Introduce yourself"));
        Cast.hughes.addDependentAction(new ConversationDependentAction(null, metHughes, PlotConversations.meetHughes, "Introduce yourself"));
        Cast.victim.addDependentAction(new ConversationDependentAction(null, metVictim, PlotConversations.meetVictim, "Introduce yourself"));
        
        Cast.victim.addDependentAction(new ConversationDependentAction(null, PlotConversations.victimHasInfo, "Ask about Frank"));
    }
}
