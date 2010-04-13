/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import java.util.List;

/**
 *
 * @author hartsoka
 */
public interface IBehaviorQueue {

    void activate();

    void queueTask(ITask task);

    void cancel();

    /**
     * Get the MultiQueue which owns this BehaviorQueue.
     * @return Owning MultiQueue.
     */
    MultiQueue getOwningMultiQueue();

    int getPriority();

    boolean isActive();

    boolean isCancelled();

    boolean isCollaborative();

    boolean isSuspended();

    ITask peekTask();

    void dequeueTask();

    /**
     * Sets the MultiQueue which is running this behavior  - in general, should
     * only be called by MultiQueue when this behavior is added.
     * @param mq MultiQueue adding this BehaviorQueue to a QueueSet.
     */
    void setOwningMultiQueue(MultiQueue mq);

    void suspend();

    IBehaviorTemplate getBehaviorTemplate();

    List<ITask> getTasks();

}
