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

    static {
        frankRumor = new SimpleTokenConversationContent("frankRumor", Plot.heardAboutRumor, "Heard any good news lately?", "Yes, Frank is having an affair with Gayle.");
        embarrassFrank = new SimpleConversationContent("embarrassFrank", "So, I hear you're having an affair?", "Oh no! Please don't tell my wife!", "Maybe I won't...");
        approachGayle = new SimpleConversationContent("approachGayle", "So, I hear you're having an affair?", "Yep. I'd keep quiet if I were you.", "Yes ma'am");
    }
}
