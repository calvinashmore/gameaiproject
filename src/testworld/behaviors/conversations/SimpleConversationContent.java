/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors.conversations;

import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.IBehaviorTemplate;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.SyncTask;
import testworld.objects.Person;
import testworld.tasks.SpeechTask;

/**
 *
 * @author Calvin Ashmore
 */
public class SimpleConversationContent extends ConversationContent {

    private String[] lines;

    public SimpleConversationContent(String name, String... lines) {
        super(name);
        this.lines = lines;
    }

    @Override
    public ICollaborativeBehaviorQueue getInitiatorQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(behavior, 1, handshake);
        for (int i = 0; i < lines.length; i++) {
            if (i % 2 == 0) {
                // add all even lines
                bq.queueTask(new SpeechTask(lines[i], responder));
            }
            bq.queueTask(new SyncTask());
        }
        return bq;
    }

    @Override
    public ICollaborativeBehaviorQueue getResponderQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(behavior, 1, handshake);
        for (int i = 0; i < lines.length; i++) {
            if (i % 2 == 1) {
                // add all odd lines
                bq.queueTask(new SpeechTask(lines[i], responder));
            }
            bq.queueTask(new SyncTask());
        }
        return bq;
    }
}
