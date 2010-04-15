/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors.conversations;

import proto.behavior.CollaborationHandshake;
import proto.behavior.IBehaviorTemplate;
import proto.behavior.ICollaborativeBehaviorQueue;
import testworld.game.Token;
import testworld.objects.Person;
import testworld.tasks.TokenTask;

/**
 * This grants the given token when the conversation is completed.
 * @author Calvin Ashmore
 */
public class SimpleTokenConversationContent extends SimpleConversationContent {

    private Token token;

    public SimpleTokenConversationContent(String name, Token token, String... lines) {
        super(name, lines);
        this.token = token;
    }

    @Override
    public ICollaborativeBehaviorQueue getInitiatorQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
        ICollaborativeBehaviorQueue bq = super.getInitiatorQueue(initiator, responder, behavior, handshake);

        bq.queueTask(new TokenTask(token));

        return bq;
    }
}
