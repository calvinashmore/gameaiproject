/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior.text;

import proto.behavior.SyncTask;
import proto.behavior.ABehaviorTemplate;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ICollaborativeBehavior;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class DummyCollaborativeBehavior
        extends ABehaviorTemplate
        implements IProactiveBehavior, ICollaborativeBehavior
{
    public DummyCollaborativeBehavior()
    {
        super(InitiationType.proactive, CollaborationType.collaborative);
    }

    public String getId() {
        return "GreetStart";
    }

    public IBehaviorQueue instantiate(IWorldState ws)
    {
        Dispatcher d = this.getOwningRole().getOwningDispatcher();
        CollaborationHandshake handshake =
                new CollaborationHandshake(1, this.getOwningRole().getOwningDispatcher(), this);

        DummyDispatcherManager.tryCollaborate(handshake);

        if (handshake.getParticipants().size() == 2)
        {
            handshake.completeHandshake();
            return handshake.getQueue(d);
        }

        return null;
    }

    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(5);
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake)
    {
        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, 1, handshake);
        bq.queueTask(new DummyWordTask("Hi,"));
        bq.queueTask(new DummyWordTask("how"));
        bq.queueTask(new DummyWordTask("are"));
        bq.queueTask(new DummyWordTask("you?"));
        bq.queueTask(new SyncTask());
        bq.queueTask(new SyncTask());
        bq.queueTask(new DummyWordTask("Gotta"));
        bq.queueTask(new DummyWordTask("run."));
        bq.queueTask(new SyncTask());
        bq.queueTask(new SyncTask());
        return bq;
    }

}
