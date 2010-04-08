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
import testworld.tasks.SyncTask;

/**
 *
 * @author Calvin Ashmore
 */
public class ApproachReactive extends ABehaviorTemplate implements IReactiveBehavior {

    public ApproachReactive() {
        super(InitiationType.reactive, CollaborationType.collaborative);
    }

    public String getId() {
        return getClass().getName();
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        throw new UnsupportedOperationException("Instantiation not allowed");
    }

    public boolean canCollaborate(String id) {
        return id.equals(ApproachProactive.class.getName());
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {
        if (handshake.getParticipants().size() >= 2) {
            return false;
        }

        handshake.participate(this.getDispatcher(), this);
        return true;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {

        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, 1, handshake);
        bq.queueTask(new SyncTask());
        return bq;
    }
}
