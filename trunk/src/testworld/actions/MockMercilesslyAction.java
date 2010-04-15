/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.actions;

import java.util.Random;
import proto.game.PlayerAction;
import proto.world.Entity;
import testworld.behaviors.conversations.ApproachConversationBehavior;
import testworld.behaviors.conversations.SimpleConversationContent;
import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
public class MockMercilesslyAction implements PlayerAction {

    private Person other;

    public MockMercilesslyAction(Person other) {
        this.other = other;
    }

    public String getName() {
        return "Mock " + other.getName() + " mercilessly.";
    }

    public String getDescription() {
        return "Admittedly, " + other.getName() + " had it coming.";
    }

    public void performAction(Entity player) {
        String[][] possibleConversations = new String[][]{
            new String[]{"My goodness, you're ugly, aren't you?", "You jerk!"},
            new String[]{"That was the dumbest toast I've ever heard", "How rude!"},
            new String[]{"Don't worry, no one is paying attention to the dumb things you say.", "Ugh!"},};

        String[] conversation = possibleConversations[new Random().nextInt(possibleConversations.length)];

        ((Person) player).instantiateNewProactiveBehavior(
                ApproachConversationBehavior.makeProactive(new SimpleConversationContent("mock", conversation), other));
    }
}
