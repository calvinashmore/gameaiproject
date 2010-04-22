/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.actions;

import java.util.Random;
import proto.behavior.CollaborationHandshake;
import proto.behavior.IBehaviorTemplate;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.game.PlayerAction;
import proto.world.Entity;
import testworld.behaviors.conversations.ApproachConversationBehavior;
import testworld.behaviors.conversations.SimpleConversationContent;
import testworld.objects.Person;
import testworld.tasks.ResponseTask;

/**
 *
 * @author Calvin Ashmore
 */
public class ComplimentAction implements PlayerAction {

    private Person other;

    public ComplimentAction(Person other) {
        this.other = other;
    }

    public String getName() {
        return "Compliment " + other.getName() + ".";
    }

    public String getDescription() {
        return "You should tell " + other.getName() + " how much you appreciate them.";
    }

    public void performAction(Entity player) {

        String[][] possibleConversations = null;
        if (other.getGender() == Person.Gender.female) {
            possibleConversations = new String[][]{
                        new String[]{"Don't you look nice this evening, " + other.getName() + ".", "Why thank you!"},
                        new String[]{"I'm really happy that you were able to come out tonight.", "Thanks!"},};
        } else if (other.getGender() == Person.Gender.male) {
            possibleConversations = new String[][]{
                        new String[]{"That is a smart outfit you are wearing.", "Thanks!"},
                        new String[]{"I am hoping you're enjoying the party!", "I am, thank you!"},};
        }

        String[] conversation = possibleConversations[new Random().nextInt(possibleConversations.length)];

        ((Person) player).instantiateNewProactiveBehavior(
                ApproachConversationBehavior.makeProactive(new SimpleConversationContent("compliment", conversation) {

            @Override
            public ICollaborativeBehaviorQueue getResponderQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
                ICollaborativeBehaviorQueue bq = super.getResponderQueue(initiator, responder, behavior, handshake);
                // here is where to put in the effects of the compliment on the emotional logic
                bq.queueTask(new ResponseTask("respond_to_compliment",initiator));
                return bq;
            }
        }, other));
    }
}
