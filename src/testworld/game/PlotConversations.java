/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

import proto.behavior.CollaborationHandshake;
import proto.behavior.IBehaviorTemplate;
import proto.behavior.ICollaborativeBehaviorQueue;
import testworld.behaviors.conversations.ApproachConversationBehavior;
import testworld.behaviors.conversations.ConversationContent;
import testworld.behaviors.conversations.SimpleConversationContent;
import testworld.behaviors.conversations.SimpleTokenConversationContent;
import testworld.objects.Person;
import testworld.tasks.QueueBehaviorTask;

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
    public static final ConversationContent askHarrietToPokeFrank;

    static {
        frankRumor = new SimpleTokenConversationContent("frankRumor", Plot.heardAboutRumor, "Heard any good news lately?", "Yes, Frank is having an affair with Gayle.");
        embarrassFrank = new SimpleTokenConversationContent("embarrassFrank", Plot.frankWantsRevenge, "So, I hear you're having an affair?", "Oh no! Please don't tell my wife!", "Maybe I won't...");
        approachGayle = new SimpleConversationContent("approachGayle", "So, I hear you're having an affair?", "Yep. I'd keep quiet if I were you.", "Yes ma'am");
        askHarrietToPokeFrank = new SimpleConversationContent("askHarrietToPokeFrank", "Harriet, could you kindly poke Frank in the eye for me?", "Gladly!") {

            @Override
            public ICollaborativeBehaviorQueue getResponderQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
                ICollaborativeBehaviorQueue pq = super.getResponderQueue(initiator, responder, behavior, handshake);

                ConversationContent poke = new SimpleConversationContent("poke", "*poke*", "OW!");

                pq.queueTask(new QueueBehaviorTask(ApproachConversationBehavior.makeProactive(poke, Cast.frank)));
                return pq;
            }
        };

        warnHilda = new SimpleConversationContent("warnHilda", "Frank is pretty pissed right now.", "I'll bet he is.", "Are you worried?", "Hardly!");
        annoyFrankFurther = new SimpleConversationContent("annoyFrankFurther", "If you don't want your wife to know, you need to do something for me", "Ha, you can't prove anything!");
        spikeFranksDrink = new SimpleTokenConversationContent("spikeFranksDrink", Plot.spikingFranksDrink, "Can you do me a favor and put this in Frank's drink for me?", "Sure, but it will cost you.", "I'll leave you a big tip.", "Okay then.");
    }
}
