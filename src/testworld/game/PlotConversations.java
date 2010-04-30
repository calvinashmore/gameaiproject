/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.game;

import proto.behavior.CollaborationHandshake;
import proto.behavior.IBehaviorTemplate;
import proto.behavior.ICollaborativeBehaviorQueue;
import testworld.behaviors.conversations.ApproachConversationBehavior;
import testworld.behaviors.conversations.ConversationBehavior;
import testworld.behaviors.conversations.ConversationContent;
import testworld.behaviors.conversations.SimpleConversationContent;
import testworld.behaviors.conversations.SimpleResponseConversationContent;
import testworld.behaviors.conversations.SimpleTokenConversationContent;
import testworld.objects.Person;
import testworld.social.Feelings;
import testworld.social.Relationship;
import testworld.tasks.AddBehaviorTemplateTask;

/**
 *
 * @author Calvin Ashmore
 */
public class PlotConversations
{

    public static final ConversationContent frankRumor;
    public static final ConversationContent embarrassFrank;
    public static final ConversationContent approachGayle;
    public static final ConversationContent warnHilda;
    public static final ConversationContent annoyFrankFurther;
    public static final ConversationContent spikeFranksDrink;
    public static final ConversationContent askHarrietToPokeFrank;
    public static final ConversationContent drunkenTease;

    public static final ConversationContent victimHasInfo;

    static {
        frankRumor = new SimpleTokenConversationContent("frankRumor", Plot.heardAboutRumor, "Heard any good news lately?", "Yes, Frank is having an affair with Gayle.");
        embarrassFrank = new SimpleTokenConversationContent("embarrassFrank", Plot.frankWantsRevenge, "So, I hear you're having an affair?", "Oh no! Please don't tell my wife!", "Maybe I won't...");
        approachGayle = new SimpleConversationContent("approachGayle", "So, I hear you're having an affair?", "Yep. I'd keep quiet if I were you.", "Yes ma'am");
        askHarrietToPokeFrank = new SimpleConversationContent("askHarrietToPokeFrank", "Harriet, could you kindly poke Frank in the eye for me?", "Gladly!") {

            @Override
            public ICollaborativeBehaviorQueue getResponderQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
                ICollaborativeBehaviorQueue pq = super.getResponderQueue(initiator, responder, behavior, handshake);

                ConversationContent poke = new SimpleConversationContent("poke", "*poke*", "OW!");
                ConversationBehavior doPoke = ApproachConversationBehavior.makeProactive(poke, Cast.frank);

                doPoke.setImportance(100);
                doPoke.destroyAfterUse();

                pq.queueTask(new AddBehaviorTemplateTask(doPoke));
                return pq;
            }
        };

        warnHilda = new SimpleConversationContent("warnHilda", "Frank is pretty pissed right now.", "I'll bet he is.", "Are you worried?", "Hardly!");
        annoyFrankFurther = new SimpleConversationContent("annoyFrankFurther", "If you don't want your wife to know, you need to do something for me", "Ha, you can't prove anything!");
        spikeFranksDrink = new SimpleTokenConversationContent("spikeFranksDrink", Plot.spikingFranksDrink, "Can you do me a favor and put this in Frank's drink for me?", "Sure, but it will cost you.", "I'll leave you a big tip.", "Okay then.");

        drunkenTease = new SimpleResponseConversationContent("drunkenTease", null, Feelings.DEPRESSANT, 30,
                new String[]{"You really are sloshed right now."},
                new String[]{"Psssh. You haven't seen anything yet."},
                new String[]{"*sob* You're right!"}, null, Plot.frankSloshed);

        victimHasInfo = new SimpleResponseConversationContent("victimHasInfo", "respond_to_inforequest", "difficulty", 5,
                new String[]{"So what's the word on Frank?"},
                new String[]{"I don't think we should talk about him at his party."},
                new String[]{"He's done some things which he doesn't want people knowing about."},
                null, Plot.victimCanBlackmail);
    }
}
