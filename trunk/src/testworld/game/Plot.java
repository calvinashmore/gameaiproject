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

    public static final Token heardAboutRumor = new Token("heardAboutRumor", "You heard about a scandalous rumor");
    public static final Token frankWantsRevenge = new Token("frankWantsRevenge", "Frank wants revenge");
    public static final Token spikingFranksDrink = new Token("spikingFranksDrink", "You are spiking Frank's drink");

    public static void initializePlot() {
        Cast.hilda.addDependentAction(new ConversationDependentAction(null, PlotConversations.frankRumor, "Gossip"));
        Cast.frank.addDependentAction(new ConversationDependentAction(heardAboutRumor, PlotConversations.embarrassFrank, "Embarrass Frank"));
        Cast.gayle.addDependentAction(new ConversationDependentAction(heardAboutRumor, PlotConversations.approachGayle, "Approach Gayle"));
        Cast.hilda.addDependentAction(new ConversationDependentAction(frankWantsRevenge, PlotConversations.warnHilda, "Warn Hilda"));
        Cast.frank.addDependentAction(new ConversationDependentAction(frankWantsRevenge, PlotConversations.annoyFrankFurther, "Blackmail Frank"));
        Cast.fred.addDependentAction(new ConversationDependentAction(frankWantsRevenge, PlotConversations.spikeFranksDrink, "Spike Frank's Drink"));
        Cast.harriet.addDependentAction(new ConversationDependentAction(null, PlotConversations.askHarrietToPokeFrank, "Ask Harriet to poke Frank"));
    }
}
