/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

import java.util.List;
import proto.behavior.MultiQueue.QueueSet;

/**
 * Controls and monitors changes to the MultiQueue.  See page 25 for signals.
 * @author hartsoka
 */
public class Dispatcher implements Comparable {

    private static int nextId = 0;
    private int id;

    private IWorldState currentWorld;

    private IRole role;
    private MultiQueue mq;
    private int timeoutClock;

    private static final int MEDIUM_WAIT = 50; // TODO choose these
    private static final int LONG_WAIT = 100;

    public Dispatcher(IRole role)
    {
        this.id = nextId;
        nextId++;

        this.role = role;
        this.mq = new MultiQueue(this);

        this.role.setOwningDispatcher(this);

        resetTimeout();
    }

    private void resetTimeout()
    {
        timeoutClock = 0;
    }

    public void tick(IWorldState ws)
    {
        this.currentWorld = ws;
        timeoutClock++;
    }

    public void handleNewBehavior(IBehaviorQueue newBehavior, QueueSet qs)
    {
        IBehaviorQueue currentBehavior = mq.getCurrentBehavior();
        if (currentBehavior != null &&
            currentBehavior.getPriority() > newBehavior.getPriority())
        {
            // TODO is this right? might want to add it anyway
            return;
        }

        mq.addBehavior(newBehavior, qs);
        handleTaskStart();
    }

    public void handleTaskDone(IBehaviorQueue taskQueue)
    {
        // if a collaboration
        if (taskQueue.isCollaborative())
        {
            ICollaborativeBehaviorQueue cbq =
                    (ICollaborativeBehaviorQueue)taskQueue;

            // if we've been told to sync up at the end up this task
            if (cbq.isBarrierSet())
            {
                cbq.setSelfReadyAndWaiting();
                
                CollaborationHandshake handshake = cbq.getHandshake();
                handshake.triggerBarrier(this);
            }
            else
            {
                taskQueue.dequeueTask();
            }
        }
        else
        {
            taskQueue.dequeueTask();
        }
    }

    public void handleCollaboratorDone(ICollaborativeBehaviorQueue taskQueue)
    {
        taskQueue.endBarrier();

        if (taskQueue.isSuspended())
        {
            taskQueue.activate();
        }

        taskQueue.dequeueTask();
    }

    // NOTE: THIS IS NOT CURRENTLY CALLED
    public void handlePhaseDone()
    {
        // TODO check this
        IBehaviorQueue currentBehavior = mq.getCurrentBehavior();
        currentBehavior.dequeueTask();

        // listed in paper, but not needed as handleTaskStart() does this
        // resetTimeout();

        handleTaskStart();
    }

    public void handleTaskStart()
    {
        resetTimeout();

        IBehaviorQueue currentBehavior = safelyGetCurrentBehavior();

        if (currentBehavior != null)
        {
            ITask task = currentBehavior.peekTask();
            if(task != null)
                task.resume();
        }
    }

    public void handleKillCollaboration(ICollaborativeBehaviorQueue collaborativeQueue)
    {
        mq.remove(collaborativeQueue);

        handleTaskStart();

        // TODO implement this more fully
    }

    public void handleTimer()
    {
        IBehaviorQueue currentBehavior = safelyGetCurrentBehavior();
        if(currentBehavior != null)
        {
            if (currentBehavior.isCollaborative() &&
                ((ICollaborativeBehaviorQueue)currentBehavior).isSelfReadyAndWaiting())
            {
                // collaborative and we are finished and waiting on our
                //  collaborators, so do nothing but check timeouts
                if (timeoutClock > MEDIUM_WAIT)
                {
                    currentBehavior.suspend();
                }
                // TODO actually handle LONG_WAIT
            }
            else
            {
                ITask task = currentBehavior.peekTask();
                if(task != null)
                task.run();
            }
        }
    }

    public void offerCollaboration(CollaborationHandshake handshake)
    {
        if (mq.testEyeContact(handshake.getPriority()))
        {
            List<IReactiveBehavior> reactives = role.getReactiveBehaviors();
            for (IReactiveBehavior rb : reactives)
            {
                if(rb.canCollaborate(handshake.getInitiatingBehavior().getId()))
                    rb.tryCollaboration(handshake);
            }
        }
    }

    private IBehaviorQueue safelyGetCurrentBehavior()
    {
        IBehaviorQueue currentBehavior = mq.getCurrentBehavior();
        if (currentBehavior == null || currentBehavior.isCancelled() || currentBehavior.peekTask() == null)
        {
            currentBehavior = role.instantiateProactiveBehavior(currentWorld);
            if(currentBehavior == null)
                return null;
            handleNewBehavior(currentBehavior, QueueSet.pro);
        }

        return currentBehavior;
    }

    public IRole getRole() {
        return role;
    }

    public int compareTo(Object o) {
        Dispatcher rhs = (Dispatcher)o;
        Integer rhsId = rhs.id;
        return ((Integer)(this.id)).compareTo(rhsId);
    }
}
