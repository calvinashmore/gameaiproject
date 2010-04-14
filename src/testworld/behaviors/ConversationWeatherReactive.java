/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IReactiveBehavior;
import proto.behavior.IWorldState;
import proto.behavior.SyncTask;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.tasks.SpeechTask;

/**
 *
 * @author Calvin Ashmore
 */
public class ConversationWeatherReactive extends ABehaviorTemplate implements IReactiveBehavior {

    public ConversationWeatherReactive() {
        super(InitiationType.reactive, CollaborationType.collaborative);
    }

    public String getId() {
        return getClass().getName();
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        throw new UnsupportedOperationException("Instantiation not allowed");
    }

    public boolean canCollaborate(String id) {
        return id.equals(ConversationWeatherProactive.class.getName());
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {

        if (handshake.getParticipants().size() >= 2) {
            return false;
        }

        PersonDispatcher initiator = (PersonDispatcher) handshake.getInitiator();
        PersonDispatcher reactor = (PersonDispatcher) getDispatcher();

        double distance = initiator.getPerson().getLocation().getPosition().subtract(reactor.getPerson().getLocation().getPosition()).magnitude();
        if (distance > 60) {
            return false;
        }

        handshake.participate(getDispatcher(), this);
        return true;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {

        Person person = ((PersonDispatcher) handshake.getInitiator()).getPerson();

        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, 1, handshake);
        bq.queueTask(new SyncTask());
        if (Math.random() < .5) {
            bq.queueTask(new SpeechTask("Bah, I hate sunny weather", person));
        } else {
            bq.queueTask(new SpeechTask("Looking forward to when it rains...", person));
        }
        bq.queueTask(new SyncTask());
        bq.queueTask(new SyncTask());
        return bq;
    }
}
