/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors.conversations;

import java.util.Map;
import proto.behavior.CollaborationHandshake;
import proto.behavior.IBehaviorTemplate;
import proto.behavior.ICollaborativeBehaviorQueue;
import testworld.objects.Person;
import testworld.social.AttributeMap;
import testworld.social.Relationship;
import testworld.social.SocialState;
import utils.math.RandomManager;

/**
 * This is a conversation content which depends on a fuzzy response
 * @author Calvin Ashmore
 */
abstract public class ResponseConversationContent extends ConversationContent {

    protected String fuzzyFn;

    public ResponseConversationContent(String name, String fuzzyFn) {
        super(name);
        this.fuzzyFn = fuzzyFn;
    }

    @Override
    public ICollaborativeBehaviorQueue getResponderQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake) {
        Map<String, Double> evaluation = evaluate(initiator, responder);
        return getResponderQueue(initiator, responder, behavior, handshake, evaluation);
    }

    abstract public ICollaborativeBehaviorQueue getResponderQueue(Person initiator, Person responder, IBehaviorTemplate behavior, CollaborationHandshake handshake, Map<String, Double> evaluation);

    protected Map<String, Double> evaluate(Person initiator, Person responder) {
        return this.evaluate(initiator, responder, null);
    }

    protected Map<String, Double> evaluate(Person initiator, Person responder, AttributeMap a)
    {
        SocialState e = responder.getSocialState();
        Relationship r = e.getRelationship(initiator);

        // ???
        e.addTemporaryAttribute("sincerity", RandomManager.get().nextDouble() * 100);
        e.addTemporaryMap(r);
        if (a != null) e.addTemporaryMap(a);

        Map<String, Double> results =
                responder.getSocialState().evaluateFuzzy(fuzzyFn);

        e.applyDeltas(results);

        e.clearTemporaryMaps();
        return results;
    }
}
