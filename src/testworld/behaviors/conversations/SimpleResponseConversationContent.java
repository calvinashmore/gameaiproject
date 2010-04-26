/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors.conversations;

import java.util.Map;
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
public class SimpleResponseConversationContent extends ResponseConversationContent {

    private String attribute;
    private double testValue;
    private String[] initiatorLines;
    private String[] responderLines1;
    private String[] responderLines2;

    /**
     * Important: initiatorLines, responderLines1, and responderLines2 must be the same length.
     * The set responderLines1 is the response if the given attribute is less than testValue.
     * @param name
     * @param fuzzyFn
     * @param valueName
     * @param valueTest
     * @param initiatorLines
     * @param responderLines1
     * @param responderLines2
     */
    public SimpleResponseConversationContent(String name, String fuzzyFn, String attribute, double testValue, String[] initiatorLines, String[] responderLines1, String[] responderLines2) {
        super(name, fuzzyFn);
        this.attribute = attribute;
        this.testValue = testValue;
        this.initiatorLines = initiatorLines;
        this.responderLines1 = responderLines1;
        this.responderLines2 = responderLines2;
    }

    @Override
    public ICollaborativeBehaviorQueue getResponderQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake, Map<String, Double> evaluation) {
        if (test(evaluation)) {
            return getResponderQueue1(initiator, responder, behavior, handshake);
        } else {
            return getResponderQueue2(initiator, responder, behavior, handshake);
        }
    }

    protected boolean test(Map<String, Double> evaluation) {
        Double value = evaluation.get(attribute);
        return value < testValue;
    }

    protected ICollaborativeBehaviorQueue getResponderQueue1(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(behavior, 1, handshake);
        for (String line : responderLines1) {
            bq.queueTask(new SyncTask());
            bq.queueTask(new SpeechTask(line));
            bq.queueTask(new SyncTask());
        }
        return bq;
    }

    protected ICollaborativeBehaviorQueue getResponderQueue2(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(behavior, 1, handshake);
        for (String line : responderLines2) {
            bq.queueTask(new SyncTask());
            bq.queueTask(new SpeechTask(line));
            bq.queueTask(new SyncTask());
        }
        return bq;
    }

    @Override
    public ICollaborativeBehaviorQueue getInitiatorQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(behavior, 1, handshake);
        for (String line : initiatorLines) {
            bq.queueTask(new SpeechTask(line));
            bq.queueTask(new SyncTask());
            bq.queueTask(new SyncTask());
        }
        return bq;
    }
}
