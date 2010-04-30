/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.game;

import testworld.behaviors.conversations.ConversationContent;
import testworld.behaviors.conversations.SimpleTokenConversationContent;

/**
 *
 * @author hartsoka
 */
public class CharDevConversations {
    
    public static final ConversationContent meetFrank;
    public static final ConversationContent meetHarriet;
    public static final ConversationContent meetHilda;
    public static final ConversationContent meetGayle;
    public static final ConversationContent meetHughes;
    public static final ConversationContent meetVictim;

        static {

        meetFrank = new SimpleTokenConversationContent("meetFrank", CharDev.metFrank,
                "Hi there, pleased to meet you.", "I own half of the Eastern seaboard. If I wanted, I could own you.", "...");

        meetHarriet = new SimpleTokenConversationContent("meetHarriet", CharDev.metHarriet,
                "Hi there, pleased to meet you.", "I didn't think we allowed filth like you into our party.", "...");

        meetGayle = new SimpleTokenConversationContent("meetGayle", CharDev.metGayle,
                "Hi there, pleased to meet you.", "Charmed!");

        meetHilda = new SimpleTokenConversationContent("meetHilda", CharDev.metHilda,
                "Hi there, pleased to meet you.", "Hello, I'm covering this event for the Times.");

        meetHughes = new SimpleTokenConversationContent("meetHughes", CharDev.metHughes,
                "Hi there, pleased to meet you.", "Good evening, sir.");

        meetVictim = new SimpleTokenConversationContent("meetVictim", CharDev.metVictim,
                "Hi there, pleased to meet you.", "Hi! I'm running for re-election soon. I hope to count on your vote!", "Oh, definitely...", "I'm looking to improve this country, however I can.");

        }

}
