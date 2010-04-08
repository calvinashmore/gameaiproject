/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ICollaborativeBehavior;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import proto.world.World;
import testworld.objects.PersonDispatcher;
import testworld.tasks.MoveTo;
import testworld.tasks.SyncTask;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class ApproachProactive
        extends ABehaviorTemplate
        implements IProactiveBehavior, ICollaborativeBehavior {

    public ApproachProactive() {
        super(InitiationType.proactive, CollaborationType.collaborative);
    }

    public String getId() {
        return getClass().getName();
    }

    public IBehaviorQueue instantiate(IWorldState ws) {

        Dispatcher d = getOwningRole().getOwningDispatcher();
        CollaborationHandshake handshake =
                new CollaborationHandshake(1, getOwningRole().getOwningDispatcher(), this);

        World.getInstance().tryCollaborate(handshake);

        if (handshake.getParticipants().size() == 2) {
            handshake.completeHandshake();
            return handshake.getQueue(d);
        }

        return null;
    }

    public int getImportance(IWorldState ws) {
        if (Math.random() < .2) {
            return 5;
        }
        return -1;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {

        PersonDispatcher approached = (PersonDispatcher) handshake.getParticipant("reactor");
        Vector2d destination = approached.getPerson().getLocation().getPosition();

        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, 1, handshake);
        bq.queueTask(new MoveTo(destination, 50, MoveTo.DEFAULT_SPEED));
        bq.queueTask(new SyncTask());
        return bq;
    }
}
