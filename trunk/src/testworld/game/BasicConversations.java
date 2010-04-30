/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

import testworld.behaviors.conversations.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Calvin Ashmore
 */
public class BasicConversations {

    public static final List<ConversationContent> conversations;

    static {

        List<ConversationContent> allConversations = new ArrayList<ConversationContent>();

        allConversations.add(new SimpleConversationContent("idle1",
                "Are you enjoying the party?", "It's less exciting than I had hoped..."));

        allConversations.add(new SimpleConversationContent("idle2",
                "I can't believe that she actually wore that dress!", "I know!"));

        allConversations.add(new SimpleConversationContent("idle3",
                "What do you usually do for entertainment around here?", "It's mostly just the drinking and socializing.",
                "Hmm... I think I can manage that.", "It works well for everyone."));

        allConversations.add(new SimpleConversationContent("idle4",
                "When are we going to have you over for tea?", "As soon as I can make it.",
                "Yeah, I know things are busy with your you-know-what.", "Please don't talk about that."));

        allConversations.add(new SimpleConversationContent("idle5",
                "Who did your outfit?", "Oh, a custom place in the city.",
                "You must have paid a fortune!", "Yeah, pretty much.", "Is it worth it?", "No, not really..."));

        allConversations.add(new SimpleConversationContent("idle6",
                "Hey! How are you?", "Great! How are you?", "Not too bad..."));

        allConversations.add(new SimpleConversationContent("idle7",
                "So, how about these o'devours?", "They're good, I guess"));

        allConversations.add(new SimpleConversationContent("idle8",
                "You know what I love?", "What's that?",
                "Money!", "Aaah, me too.",
                "I love the sight, the smell, and the sound of money.", "Amen."));

        allConversations.add(new SimpleResponseConversationContent(
                "testCompliment", "respond_to_compliment", null, 0,
                new String[]{"Hey, you're looking good tonight."},
                new String[]{"Whatever, creep."},
                new String[]{"Why thank you!"}));

        conversations = Collections.unmodifiableList(allConversations);
    }
}
