/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

import testworld.behaviors.conversations.ConversationContent;
import testworld.behaviors.conversations.SimpleConversationContent;
import testworld.behaviors.conversations.SimpleTokenConversationContent;

/**
 *
 * @author Calvin Ashmore
 */
public class PlotConversations {

    public static final ConversationContent frankRumor;
    public static final ConversationContent embarrassFrank;
    public static final ConversationContent approachGayle;
    public static final ConversationContent warnHilda;
    public static final ConversationContent annoyFrankFurther;
    public static final ConversationContent spikeFranksDrink;

    static {
        frankRumor = new SimpleTokenConversationContent("frankRumor", Plot.heardAboutRumor, "Heard any good news lately?", "Yes, Frank is having an affair with Gayle.");
        embarrassFrank = new SimpleTokenConversationContent("embarrassFrank", Plot.frankWantsRevenge, "So, I hear you're having an affair?", "Oh no! Please don't tell my wife!", "Maybe I won't...");
        approachGayle = new SimpleConversationContent("approachGayle", "So, I hear you're having an affair?", "Yep. I'd keep quiet if I were you.", "Yes ma'am");

        warnHilda = new SimpleConversationContent("warnHilda", "Frank is pretty pissed right now.", "I'll bet he is.", "Are you worried?", "Hardly!");
        annoyFrankFurther = new SimpleConversationContent("annoyFrankFurther", "If you don't want your wife to know, you need to do something for me", "Ha, you can't prove anything!");
        spikeFranksDrink = new SimpleTokenConversationContent("spikeFranksDrink", Plot.spikingFranksDrink, "Can you do me a favor and put this in Frank's drink for me?", "Sure, but it will cost you.", "I'll leave you a big tip.", "Okay then.");
    }
}
