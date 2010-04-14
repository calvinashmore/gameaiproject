/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import java.util.LinkedList;
import java.util.List;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import proto.behavior.SyncAndSuspendTask;
import proto.behavior.SyncTask;
import proto.world.BasicObject;
import proto.world.World;
import testworld.objects.PersonDispatcher;
import testworld.objects.Pickup;
import testworld.objects.ServerPerson;
import testworld.tasks.Fetch;
import testworld.tasks.Flee;
import testworld.tasks.MoveTo;
import testworld.tasks.SpeechTask;
import utils.math.RandomManager;
import utils.math.Vector2d;

/**
 *
 * @author hartsoka
 */
public class RequestAndServeBehavior
        extends AJointPersonBehavior
        implements IProactiveBehavior
{
    protected static final int REQUEST_PRIORITY = 2;
    protected static final int RANDOM_IMPORTANCE_MAX = 5;

    protected static final int SERVER_PROXIMITY = 60;

    protected String id;

    protected static final String PROACTIVE_ID = "Request";
    protected static final String REACTIVE_ID = "Serve";

    public static RequestAndServeBehavior makeRequestBehavior() {
        return new RequestAndServeBehavior(InitiationType.proactive);
    }

    public static RequestAndServeBehavior makeServeBehavior() {
        return new RequestAndServeBehavior(InitiationType.reactive);
    }

    protected RequestAndServeBehavior(InitiationType initType) {
        super(initType);
        if (initType == InitiationType.proactive)
            this.id = PROACTIVE_ID;
        else if (initType == InitiationType.reactive)
            this.id = REACTIVE_ID;
        else
            throw new UnsupportedOperationException("RequestAndServeBehavior unsupported type");
    }

    @Override
    public CollaborationHandshake makeHandshake(IWorldState ws) {
        CollaborationHandshake handshake =
                new CollaborationHandshake(REQUEST_PRIORITY, this.getDispatcher(), this);
        return handshake;
    }

    @Override
    public List<Dispatcher> getPotentialCollaborators() {

        List<BasicObject> servers =
                World.getInstance().getAllObjectsOfType(ServerPerson.class);
        List<Dispatcher> dispatchers =
                new LinkedList<Dispatcher>();
        for (BasicObject bo : servers)
        {
            PersonDispatcher d = ((ServerPerson)bo).getDispatcher();
            dispatchers.add(d);
        }

        super.sortPeopleByDistance(dispatchers);

        return dispatchers;
    }

    @Override
    public boolean confirmHandshake(CollaborationHandshake handshake) {
        return handshake.getParticipants().size() == 2;
    }

    public String getId() {
        return id;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {
        if (title.startsWith("initiator"))
        {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, REQUEST_PRIORITY, handshake);

            bq.queueTask(new SpeechTask("*signals for server*"));
            bq.queueTask(new SyncTask()); // 1
            bq.queueTask(new SyncAndSuspendTask()); // 2

            bq.queueTask(new SpeechTask("I would like another drink, please."));
            bq.queueTask(new SyncTask()); // 3
            bq.queueTask(new SyncAndSuspendTask()); // 4

            bq.queueTask(new SpeechTask("Thank you."));
            bq.queueTask(new SpeechTask("Now scram!"));
            bq.queueTask(new SyncAndSuspendTask()); // 5

            return bq;
        }
        else if (title.startsWith("reactor"))
        {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, REQUEST_PRIORITY, handshake);

            PersonDispatcher patron = (PersonDispatcher) handshake.getParticipant("initiator");
            Vector2d destination = patron.getPerson().getLocation().getPosition();

            bq.queueTask(new SyncTask()); // 1
            bq.queueTask(new MoveTo(destination, SERVER_PROXIMITY));
            bq.queueTask(new SpeechTask("How may I help you?"));
            bq.queueTask(new SyncTask()); // 2
            bq.queueTask(new SyncTask()); // 3
            bq.queueTask(new SpeechTask("Of course, right away."));
            bq.queueTask(new Fetch<Pickup>(Pickup.class));
            bq.queueTask(new MoveTo(destination, SERVER_PROXIMITY));
            bq.queueTask(new SpeechTask("Here you are."));
            bq.queueTask(new SyncTask()); // 4
            bq.queueTask(new SyncTask()); // 5
            bq.queueTask(new Flee(patron.getPerson()));

            return bq;
        }
        else
        {
            throw new UnsupportedOperationException();
        }
    }

    public boolean canCollaborate(String id) {
        return id.equals(PROACTIVE_ID);
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {
        if (handshake.getParticipants().size() != 1)
            return false;

        handshake.participate(this.getDispatcher(), this);
        return true;
    }

    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(RANDOM_IMPORTANCE_MAX);
    }

}
