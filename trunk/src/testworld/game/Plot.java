/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

import testworld.actions.ConversationDependentAction;
import testworld.behaviors.FrankGayleFlirt;

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
    public static final Token victimMoreThreatening = new Token("victimMoreThreatening", "Frank does seem to be awfully bothered by Victim.");
    public static final Token seenFrankAndGayleFlirt = new Token("seenFrankAndGayleFlirt", "Frank and Gayle seem to be awfuly... close.");
    public static final Token knowAboutAffair = new Token("knowAboutAffair", "Frank and Gayle are definitely having an affair.");
    public static final Token frankSlapped = new Token("frankSlapped", "That must have hurt...");
    public static final Token frankDrivenToMurder = new Token("frankDrivenToMurder", "Frank sure is angry!");

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

        Cast.victim.addDependentAction(new ConversationDependentAction(PlotConversations.victimHasInfo, "Ask about Frank", CharDev.metVictim));
        Cast.frank.addDependentAction(new ConversationDependentAction(PlotConversations.talkToFrankAboutVictim, "Victim knows something...", CharDev.metFrank, victimCanBlackmail));
        Cast.hilda.addDependentAction(new ConversationDependentAction(PlotConversations.talkToHildaAboutFrank, "Frank+Gayle?", CharDev.metHilda, seenFrankAndGayleFlirt));
        Cast.harriet.addDependentAction(new ConversationDependentAction(PlotConversations.tellHarrietAboutAffair, "So, your husband...", CharDev.metHarriet, knowAboutAffair));
        //Cast.harriet.addDependentAction(new ConversationDependentAction(PlotConversations.tellHarrietAboutAffair, "So, your husband..."));
        Cast.frank.addDependentAction(new ConversationDependentAction(PlotConversations.convinceFrankToKillVictim, "My regards...", frankSlapped));

        //Cast.frank.getRole().addBehaviorTemplate(FrankGayleFlirt.makeProactive());
        Cast.gayle.getRole().addBehaviorTemplate(FrankGayleFlirt.makeReactive());
        Cast.player.getRole().addBehaviorTemplate(FrankGayleFlirt.makeReactive());
    }
}
