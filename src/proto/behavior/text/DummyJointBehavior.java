/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import java.util.List;
import proto.behavior.AJointBehavior;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class DummyJointBehavior
        extends AJointBehavior
        implements IProactiveBehavior
{
    private static final String PROACTIVE_ID = "GreetStart";
    private static final String REACTIVE_ID = "GreetAccept";

    private static final int PRIORITY = 1;

    private String id;

    public static DummyJointBehavior makeProactive()
    {
        return new DummyJointBehavior(PROACTIVE_ID, InitiationType.proactive);
    }

    public static DummyJointBehavior makeReactive()
    {
        return new DummyJointBehavior(REACTIVE_ID, InitiationType.reactive);
    }

    private DummyJointBehavior(String id, InitiationType initType) {
        super(initType);
        this.id = id;
    }

    public CollaborationHandshake makeHandshake(IWorldState ws)
    {
        Dispatcher d = this.getOwningRole().getOwningDispatcher();
        CollaborationHandshake handshake =
                new CollaborationHandshake(PRIORITY, d, this);
        return handshake;
    }

    public List<Dispatcher> getPotentialCollaborators()
    {
        return DummyDispatcherManager.getDispatchers();
    }

    public boolean confirmHandshake(CollaborationHandshake handshake)
    {
        return (handshake.getParticipants().size() == 2);
    }

    public String getId() {
        return id;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake)
    {
        if (title.equals("initiator"))
        {
            CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, handshake);
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
        else if (title.equals("reactor"))
        {
            CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, handshake);
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
        else
        {
            throw new UnsupportedOperationException("DummyJointBehavior : Unknown title - " + title);
        }
    }

    public boolean canCollaborate(String id) {
        return id.equals(PROACTIVE_ID);
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

    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(5);
    }
}
