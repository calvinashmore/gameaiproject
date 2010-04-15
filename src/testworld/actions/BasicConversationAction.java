/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.actions;

import proto.game.PlayerAction;
import proto.world.Entity;
import testworld.behaviors.conversations.ApproachConversationBehavior;
import testworld.behaviors.conversations.ConversationContent;
import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
public class BasicConversationAction implements PlayerAction {

    private String name;
    private String description;
    private Person other;
    private ConversationContent conversation;

    public BasicConversationAction(Person other, String name, String description, ConversationContent conversation) {
        this.other = other;
        this.name = name;
        this.description = description;
        this.conversation = conversation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void performAction(Entity player) {
        ((Person) player).instantiateNewProactiveBehavior(
                ApproachConversationBehavior.makeProactive(conversation, other));
    }
}
