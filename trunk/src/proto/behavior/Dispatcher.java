/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import proto.behavior.MultiQueue.QueueSet;

/**
 * Controls and monitors changes to the MultiQueue.  See page 25 for signals.
 * @author hartsoka
 */
public class Dispatcher {

    private IWorldState currentWorld;

    private IRole role;
    private MultiQueue mq;
    private int timeoutClock;

    private static final int MEDIUM_WAIT = 50; // TODO choose these
    private static final int LONG_WAIT = 100;

    public Dispatcher(IRole role)
    {
        this.role = role;
        this.mq = new MultiQueue();

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

    public void handleNewBehavior(BehaviorQueue newBehavior, QueueSet qs)
    {
        BehaviorQueue currentBehavior = mq.getCurrentBehavior();
        if (currentBehavior != null &&
                newBehavior.getPriority() < currentBehavior.getPriority())
        {
            // TODO is this right? might want to add it anyway
            return;
        }

        mq.addBehavior(newBehavior, qs);
        handleTaskStart();
    }

    public void handleTaskDone(BehaviorQueue taskQueue)
    {
        // TODO handle collaborative tasks specially
        taskQueue.popTask();
    }

    public void handleCollaboratorDone()
    {
        // TODO
    }

    // NOTE: THIS IS NOT CURRENTLY CALLED
    public void handlePhaseDone()
    {
        // TODO check this
        BehaviorQueue currentBehavior = mq.getCurrentBehavior();
        currentBehavior.popTask();

        // listed in paper, but not needed as handleTaskStart() does this
        // resetTimeout();

        handleTaskStart();
    }

    public void handleTaskStart()
    {
        resetTimeout();

        BehaviorQueue currentBehavior = safelyGetCurrentBehavior();

        currentBehavior.peekTask().resume();
    }

    public void handleKillCollaboration(BehaviorQueue collaborativeQueue)
    {
        mq.remove(collaborativeQueue);

        handleTaskStart();
    }

    public void handleTimer()
    {
        BehaviorQueue currentBehavior = safelyGetCurrentBehavior();
        if(currentBehavior != null)
            currentBehavior.peekTask().run();
    }

    private BehaviorQueue safelyGetCurrentBehavior()
    {
        BehaviorQueue currentBehavior = mq.getCurrentBehavior();
        if (currentBehavior == null)
        {
            currentBehavior = role.instantiateProactiveBehavior(currentWorld, this);
            if(currentBehavior == null)
                return null;
            handleNewBehavior(currentBehavior, QueueSet.pro);
        }

        return currentBehavior;
    }

    public IRole getRole() {
        return role;
    }
}
