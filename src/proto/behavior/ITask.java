/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

/**
 * Interface for a specific task to be completed by an agent, e.g. Move-to-Bob.
 * @author hartsoka
 */
public interface ITask {

    /**
     * Work to be performed when the task becomes active.
     */
    public void resume();

    /**
     * Perform one update cycle's worth of work towards completing the task.
     */
    public void run(); // TODO more params

    /**
     * Sets the BehaviorQueue which contains this Task - in general, should only
     * be called by BehaviorQueue implementations when this task is added.
     * @param bq BehaviorQueue to which this Task was added.
     */
    public void setOwningBehaviorQueue(IBehaviorQueue bq);

    /**
     * Gets the BehaviorQueue which contains the Task.
     * @return Owning BehaviorQueue.
     */
    public IBehaviorQueue getOwningBehaviorQueue();

    // TODO maybe a method for when the task is removed from current queue
}
