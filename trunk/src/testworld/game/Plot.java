/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

/**
 *
 * @author Calvin Ashmore
 */
public class Plot {

    public static final Token heardAboutRumor = new Token("heardAboutRumor", "You heard about a scandalous rumor");

    public static void initializePlot() {
        Cast.hilda.addDependentAction(new DependentAction(Cast.hilda, null, PlotConversations.frankRumor, "Gossip", "Gossip"));
        Cast.frank.addDependentAction(new DependentAction(Cast.frank, heardAboutRumor, PlotConversations.embarrassFrank, "Embarrass Frank", "Embarrass Frank"));
        Cast.gayle.addDependentAction(new DependentAction(Cast.gayle, heardAboutRumor, PlotConversations.approachGayle, "Approach Gayle about affair", "Approach Gayle"));
    }
}
