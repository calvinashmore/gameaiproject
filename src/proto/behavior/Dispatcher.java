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

    protected IWorldState currentWorld;

    protected IRole role;
    protected MultiQueue mq;
    protected int timeoutClock;

    protected static final int MEDIUM_WAIT = 500; // TODO choose these
    protected static final int LONG_WAIT = 1000;

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
            // TODO may want to consider adding some lower priority behaviors
            //  anyway
            // TODO if we ARE going to skip new behaviors, do we skip only
            //  those with lesser priority or lesser-than-or-equal-to
            return;
        }

        mq.addBehavior(newBehavior, qs);
        handleTaskStart();
    }

    /**
     * Handles when the implementation of a task claims it is finished.  Only
     * removes task from the queue if synchronization requirements are met.
     * @param taskQueue
     */
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
                finishCurrentTask(taskQueue);
            }
        }
        else
        {
            finishCurrentTask(taskQueue);
        }
    }

    public void handleCollaboratorDone(ICollaborativeBehaviorQueue taskQueue)
    {
        taskQueue.endBarrier();

        if (taskQueue.isSuspended())
        {
            taskQueue.activate();
        }

        finishCurrentTask(taskQueue);
    }

    // TODO: This is not currently called.  Get rid of it?
    public void handlePhaseDone()
    {
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

    // TODO: Figure out how to handle collaboration departures.
    //  This fn is not currently called.
    public void handleKillCollaboration(ICollaborativeBehaviorQueue collaborativeQueue)
    {
        mq.remove(collaborativeQueue);

        handleTaskStart();
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
                    this.resetTimeout();
                }
                // TODO actually handle LONG_WAIT
            }
            else
            {
                ITask task = currentBehavior.peekTask();
                if(task != null) {
                    task.run();
                }
            }
        }

        mq.cleanup();
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
            if (currentBehavior.isCollaborative())
            {
                handleNewBehavior(currentBehavior, QueueSet.COLLABORATIVE_NON_LATENT);
            }
            else
            {
                handleNewBehavior(currentBehavior, QueueSet.PROACTIVE_INDEPENDENT);
            }
        }

        return currentBehavior;
    }

    /**
     * Used by Dispatcher when a task has signalled it is done AND dispatcher
     * has determined it is ready to be removed from its queue.
     * @param taskQueue
     */
    private void finishCurrentTask(IBehaviorQueue taskQueue)
    {
        taskQueue.dequeueTask();
        handleTaskStart();

        /* TODO implement this correctly

        IBehaviorQueue currentBehavior = this.safelyGetCurrentBehavior();

        if (currentBehavior.peekTask() instanceof InstantaneousTask) {
            currentBehavior.peekTask().run();
        }
         */
    }

    public IRole getRole() {
        return role;
    }

    public int compareTo(Object o) {
        Dispatcher rhs = (Dispatcher)o;
        Integer rhsId = rhs.id;
        return ((Integer)(this.id)).compareTo(rhsId);
    }

    public MultiQueue getMultiQueue()
    {
        return this.mq;
    }
}
