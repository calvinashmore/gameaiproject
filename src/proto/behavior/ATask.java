/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public abstract class ATask implements ITask {

    protected IBehaviorQueue bq;

    /**
     * Sets the BehaviorQueue which contains this Task - in general, should only
     * be called by BehaviorQueue implementations when this task is added.
     * @param bq BehaviorQueue to which this Task was added.
     */
    public void setOwningBehaviorQueue(IBehaviorQueue bq)
    {
        this.bq = bq;
    }

    /**
     * Gets the BehaviorQueue which contains the Task.
     * @return Owning BehaviorQueue.
     */
    public IBehaviorQueue getOwningBehaviorQueue()
    {
        return this.bq;
    }

    /**
     * Get the Dispatcher of the agent who owns this Task.  Should be used by
     * tasks which need to update the Dispatcher on their progress.
     * @return The Dispatcher of the owning agent.
     */
    public Dispatcher getDispatcher()
    {
        return this.getOwningBehaviorQueue().getOwningMultiQueue().getOwningDispatcher();
    }

    /**
     * WARNING: Only call this fn from Tasks which can ONLY be instantiated
     * in ICollaborativeBehaviorQueues!  Otherwise, check first the the
     * IBehaviorQueue you are using is in fact collaborative.
     * @return The ICollaborativeBehaviorQueue which owns the task.
     */
    public ICollaborativeBehaviorQueue getCollaborativeBehaviorQueue()
    {
        return (ICollaborativeBehaviorQueue)this.getOwningBehaviorQueue();
    }

    /**
     * WARNING: Only call this fn from Tasks which can ONLY be instantiated
     * in ICollaborativeBehaviorQueues!  Otherwise, check first that the
     * IBehaviorQueue you are using is in fact collaborative.
     * @return The CollaborationHandshake used by the owning BehaviorQueue.
     */
    public CollaborationHandshake getHandshake()
    {
        return (this.getCollaborativeBehaviorQueue()).getHandshake();
    }
}
