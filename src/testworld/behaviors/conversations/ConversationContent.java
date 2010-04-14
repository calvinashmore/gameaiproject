/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors.conversations;

import proto.behavior.CollaborationHandshake;
import proto.behavior.IBehaviorTemplate;
import proto.behavior.ICollaborativeBehaviorQueue;
import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
abstract public class ConversationContent {

    private String name;

    public ConversationContent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public ICollaborativeBehaviorQueue getInitiatorQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake);
    abstract public ICollaborativeBehaviorQueue getResponderQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake);
}
