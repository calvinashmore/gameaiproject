/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 * Controls and monitors changes to the MultiQueue.  See page 25 for signals.
 * @author hartsoka
 */
public class Dispatcher {

    private IRole role;
    private MultiQueue mq;
    private int timeoutClock;

    private static final int MEDIUM_WAIT = 50; // TODO choose these
    private static final int LONG_WAIT = 100;

    private void resetTimeout()
    {
        timeoutClock = 0;
    }

    public void tick()
    {
        timeoutClock++;
    }

    public void handleNewBehavior(BehaviorQueue newBehavior)
    {
        BehaviorQueue currentBehavior = mq.getCurrentBehavior();
        if (newBehavior.getPriority() < currentBehavior.getPriority())
        {
            // TODO is this right? might want to add it anyway
            return;
        }

        // TODO
    }

    public void handleTaskDone()
    {
        // TODO handle collaborative tasks specially
        BehaviorQueue currentBehavior = mq.getCurrentBehavior();
        currentBehavior.popTask();
    }

    public void handleCollaboratorDone()
    {
        // TODO
    }

    public void handlePhaseDone()
    {
        BehaviorQueue currentBehavior = mq.getCurrentBehavior();
        currentBehavior.popTask();

        // listed in paper, but not needed as handleTaskStart() does this
        // resetTimeout();

        handleTaskStart();
    }

    public void handleTaskStart()
    {
        resetTimeout();

        BehaviorQueue currentBehavior = mq.getCurrentBehavior();
        if (currentBehavior == null)
        {
            currentBehavior = role.getProactiveBehavior();
            handleNewBehavior(currentBehavior);
        }

        currentBehavior.peekTask().resume();
    }

    public void handleKillCollaboration(BehaviorQueue collaborativeQueue)
    {
        mq.remove(collaborativeQueue);

        handleTaskStart();
    }

    public void handleTimer()
    {
        // TODO
    }
}
