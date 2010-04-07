/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior.text;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IReactiveBehavior;
import proto.behavior.IWorldState;

/**
 *
 * @author hartsoka
 */
public class DummyReactiveBehavior extends ABehaviorTemplate implements IReactiveBehavior {

    public DummyReactiveBehavior()
    {
        super(InitiationType.reactive, CollaborationType.collaborative);
    }

    public String getId() {
        return "GreetAccept";
    }

    public IBehaviorQueue instantiate(IWorldState ws)
    {
        throw new UnsupportedOperationException("Purely reactive behaviors should not be instantiated outside of collaboration.");
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake)
    {
        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, 1, handshake);
        bq.queueTask(new SyncTask());
        bq.queueTask(new DummyWordTask("I"));
        bq.queueTask(new DummyWordTask("am"));
        bq.queueTask(new DummyWordTask("good,"));
        bq.queueTask(new DummyWordTask("thanks."));
        bq.queueTask(new SyncTask());
        bq.queueTask(new SyncTask());
        bq.queueTask(new DummyWordTask("See"));
        bq.queueTask(new DummyWordTask("you."));
        bq.queueTask(new SyncTask());
        return bq;
        
    }

    public boolean tryCollaboration(CollaborationHandshake handshake)
    {
        if (handshake.getParticipants().size() >= 2)
        {
            return false;
        }

        handshake.participate(this.getDispatcher(), this);
        return true;
    }

    public boolean canCollaborate(String id) {
        return id.equals("GreetStart");
    }


}
