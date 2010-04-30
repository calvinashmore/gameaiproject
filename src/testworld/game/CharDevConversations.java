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
 * @author hartsoka
 */
public class CharDevConversations {
    
    public static final ConversationContent meetFrank;
    public static final ConversationContent meetHarriet;
    public static final ConversationContent meetHilda;
    public static final ConversationContent meetGayle;
    public static final ConversationContent meetHughes;
    public static final ConversationContent meetVictim;

    public static final ConversationContent frankOnHarriet;
    public static final ConversationContent frankOnVictim;
    public static final ConversationContent harrietOnFrank;
    public static final ConversationContent harrietOnGayle;
    public static final ConversationContent hughesOnHilda;
    public static final ConversationContent hughesOnVictim;
    public static final ConversationContent hildaOnHughes;
    public static final ConversationContent hildaOnFrank;
    public static final ConversationContent gayleOnHughes;
    public static final ConversationContent gayleOnVictim;
    public static final ConversationContent victimOnGayle;
    public static final ConversationContent victimOnHilda;

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

        frankOnHarriet = new SimpleConversationContent("frankOnHarriet",
                "So, tell me about your wife.", "Harriet?  Not much to say.");
        frankOnVictim = new SimpleConversationContent("frankOnVictim",
                "Inviting a politician to your party?  That's bold.", "Blame Harriet. She won't let me make the guest list.");
        harrietOnFrank = new SimpleConversationContent("harrietOnFrank",
                "So, tell me about your husband.", "Just because it's his birthday, doesn't make him worth drooling about.");
        harrietOnGayle = new SimpleConversationContent("harrietOnGayle",
                "So Gayle is your friend?", "Excuse me? The woman is a peasant.");
        hughesOnHilda = new SimpleConversationContent("hughesOnHilda",
                "I'm surprised to see a reporter here.", "Perdy little lass, isn't she?", "Well... I suppose.", "Too bad she's a reporter, though.", "You don't like reporters?", "No. They get in my way.");
        hughesOnVictim = new SimpleConversationContent("hughesOnVictim",
                "So, Colonel, are you going to vote for Mr. Victim?", "I try not to get involved in politics.");
        hildaOnHughes = new SimpleConversationContent("hildaOnHughes",
                "Seems like a reporter's dream... politicians and military officers.","Well, if you can really call Hughes an officer...","What do you mean?","He's more of a bureaucrat than a soldier.");
        hildaOnFrank = new SimpleConversationContent("hildaOnFrank",
                "No offense, but...","Yes?","Why would Frank want a reporter at his birthday party?","Harriet makes sure all their parties get as much attention as possible.","Ah, I see.","She's quite the... well, something.");
        gayleOnHughes = new SimpleConversationContent("gayleOnHughes",
                "Are you friends with the Colonel?","Of course, darling!","Do tell.","I like men with power.","And?","There needs to be something else?");
        gayleOnVictim = new SimpleConversationContent("gayleOnVictim",
                "Planning on voting for Mr. Victim?","Oh, I should say so.  He's absolutely delightful.");
        victimOnGayle = new SimpleConversationContent("victimOnGayle",
                "What do you know about Gayle?","She does a lot of charity work. I usually respect that.","Usually?","Some folks just do it for show.");
        victimOnHilda = new SimpleConversationContent("victimOnHilda",
                "What's it like, having reporters follow you everywhere?","It can be bothersome at times.","You don't love the attention?","Sometimes, sure. But sometimes I just want to do my job.");
        }

}
