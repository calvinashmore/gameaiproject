/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import java.util.ArrayList;
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
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.objects.plain.Pickup;
import testworld.objects.ServerPerson;
import testworld.social.AttributeMap.Operation;
import testworld.social.SocialState;
import testworld.social.Inventory;
import testworld.social.Needs;
import testworld.tasks.Chase;
import testworld.tasks.EffectTask;
import testworld.tasks.Fetch;
import testworld.tasks.Flee;
import testworld.tasks.SpeechTask;
import testworld.tasks.requirements.ProximityRequirement;

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
    protected boolean requested = false; // prevents requesting another server while waiting
    protected int failsafe = 0;

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
        List<PersonDispatcher> dispatchers =
                new LinkedList<PersonDispatcher>();
        for (BasicObject bo : servers)
        {
            PersonDispatcher d = ((ServerPerson)bo).getDispatcher();
            dispatchers.add(d);
        }

        super.sortPeopleByDistance(dispatchers);

        return new ArrayList<Dispatcher>(dispatchers);
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
            this.requested = true;
            failsafe = 0;

            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, REQUEST_PRIORITY, handshake);

            Person server = ((PersonDispatcher) handshake.getParticipant("reactor")).getPerson();

            bq.queueTask(new SpeechTask("*signals for server*"));
            bq.queueTask(new SyncTask()); // 1
            bq.queueTask(new SyncAndSuspendTask()); // 2

            bq.queueTask(new SpeechTask("I would like another drink, please.", server));
            bq.queueTask(new SyncTask()); // 3
            bq.queueTask(new SyncAndSuspendTask()); // 4

            bq.queueTask(new EffectTask(Inventory.DRINKS, 6, Operation.Set));
            bq.queueTask(new SpeechTask("Thank you.", server));
            bq.queueTask(new SpeechTask("Now scram!", server));
            bq.queueTask(new SyncAndSuspendTask()); // 5

            return bq;
        }
        else if (title.startsWith("reactor"))
        {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, REQUEST_PRIORITY, handshake);

            Person patron = ((PersonDispatcher) handshake.getParticipant("initiator")).getPerson();

            bq.queueTask(new SyncTask()); // 1
            bq.queueTask(new Chase(patron, SERVER_PROXIMITY));
            bq.queueTask(new SpeechTask("How may I help you?", patron).queueTaskRequirement(new ProximityRequirement(patron, 100)));
            bq.queueTask(new SyncTask()); // 2
            bq.queueTask(new SyncTask()); // 3
            bq.queueTask(new SpeechTask("Of course, right away.", patron).queueTaskRequirement(new ProximityRequirement(patron, 100)));
            bq.queueTask(new Fetch<Pickup>(Pickup.class));
            bq.queueTask(new Chase(patron, SERVER_PROXIMITY));
            bq.queueTask(new SpeechTask("Here you are.", patron).queueTaskRequirement(new ProximityRequirement(patron, 100)));
            bq.queueTask(new SyncTask()); // 4
            bq.queueTask(new SyncTask()); // 5
            bq.queueTask(new Flee(patron));

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

        Person p = ((PersonDispatcher)this.getDispatcher()).getPerson();
        SocialState e = p.getSocialState();

        if (requested) {
            failsafe++;
            if (e.getAttribute(Inventory.DRINKS) == 0 && failsafe < 300) {
                return 0;
            }
            else {
                requested = false;
            }
        }

        if (e.getAttribute(Inventory.DRINKS) <= 0)
        {
            double beverageNeed = e.getAttribute(Needs.BEVERAGE);
            double alcoholNeed = e.getAttribute(Needs.ALCOHOL);
            double drinkNeed = Math.max(beverageNeed, alcoholNeed);
            return (int)(drinkNeed/1)+1;
        }
        return 0;
    }

}
