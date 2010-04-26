/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import proto.behavior.AJointBehavior;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorTemplate.InitiationType;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import proto.behavior.SyncTask;
import proto.world.World;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.social.Needs;
import testworld.tasks.LookAtTask;
import testworld.tasks.MoveTo;
import utils.math.RandomManager;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class ApproachBehavior extends AJointBehavior implements IProactiveBehavior {

    private String id;
    public static final String PROACTIVE_ID = "ApproachProactive";
    public static final String REACTIVE_ID = "ApproachReactive";

    public static ApproachBehavior makeProactive() {
        return new ApproachBehavior(InitiationType.proactive, PROACTIVE_ID);
    }

    public static ApproachBehavior makeReactive() {
        return new ApproachBehavior(InitiationType.reactive, REACTIVE_ID);
    }

    protected ApproachBehavior(InitiationType initType, String id) {
        super(initType);
        this.id = id;
    }

    @Override
    public CollaborationHandshake makeHandshake(IWorldState ws) {
        CollaborationHandshake handshake =
                new CollaborationHandshake(1, getDispatcher(), this);
        return handshake;
    }

    @Override
    public List<Dispatcher> getPotentialCollaborators() {
        List<Dispatcher> allDispatchers = World.getInstance().getDispatchers();
        allDispatchers.remove(getDispatcher());

        Dispatcher collaborator = allDispatchers.get(new Random().nextInt(allDispatchers.size()));
        return Collections.singletonList(collaborator);
    }

    @Override
    public boolean confirmHandshake(CollaborationHandshake handshake) {
        return handshake.getParticipants().size() == 2;
    }

    public String getId() {
        return id;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {

        if (title.startsWith("initiator")) {
            PersonDispatcher approached = (PersonDispatcher) handshake.getParticipant("reactor");
            Vector2d destination = approached.getPerson().getLocation().getPosition();

            CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, 1, handshake);
            bq.queueTask(new MoveTo(destination, 50, MoveTo.DEFAULT_SPEED));
            bq.queueTask(new SyncTask());
            return bq;
        } else if (title.startsWith("reactor")) {
            Vector2d position = ((PersonDispatcher) handshake.getInitiator()).getPerson().getLocation().getPosition();

            CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, 1, handshake);
            bq.queueTask(new LookAtTask(position));
            bq.queueTask(new SyncTask());
            return bq;
        } else {
            throw new IllegalArgumentException("Title " + title + " is invalid");
        }
    }

    public boolean canCollaborate(String id) {
        return id.equals(PROACTIVE_ID);
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {

        if (handshake.getParticipants().size() >= 2) {
            return false;
        }

        handshake.participate(this.getDispatcher(), this, "reactor");
        return true;
    }

    public int getImportance(IWorldState ws) {
        Person p = ((PersonDispatcher) this.getDispatcher()).getPerson();
        double gossipNeed = p.getSocialState().getAttribute(Needs.GOSSIP);
        //double gossipDesire = Math.max(50, gossipNeed);

        return RandomManager.get().nextInt(
                (int) (gossipNeed / 1) + 1);
    }
}
