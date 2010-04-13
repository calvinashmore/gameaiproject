/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

import java.util.LinkedList;
import java.util.List;
import proto.behavior.IBehaviorTemplate.CollaborationType;
import proto.behavior.IBehaviorTemplate.InitiationType;

/**
 * A BehaviorTemplate instantiated into a queue of specific tasks.
 * @author hartsoka
 */
public class BehaviorQueue implements IBehaviorQueue {

    public enum ActiveState { active, suspended, cancelled }

    private IBehaviorTemplate behavior;
    private ActiveState state;
    private LinkedList<ITask> tasks;
    private int priority;

    private MultiQueue owningMultiQueue;

    /**
     * Create a BehaviorQueue for the provided template.  Priority will be set
     * based on system defaults for the properties of the template.
     * @param behavior Template from which the behavior is instantiated.
     */
    public BehaviorQueue(IBehaviorTemplate behavior)
    {
        this(behavior, 0);
        if (behavior.getInitiationType() == InitiationType.proactive)
        {
            if (behavior.getCollaborationType() == CollaborationType.collaborative)
            {
                this.priority = 1;
            }
            else
            {
                this.priority = 0;
            }
        }
        else if (behavior.getInitiationType() == InitiationType.reactive)
        {
            this.priority = 1;
        }
        else if (behavior.getInitiationType() == InitiationType.latent)
        {
            this.priority = 2;
        }
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }



    /**
     * Create a BehaviorQueue for the provided template.
     * @param behavior Template from which the behavior is instantiated.
     * @param priority Precedence over other active behaviors.
     */
    public BehaviorQueue(IBehaviorTemplate behavior, int priority)
    {
        this.behavior = behavior;
        this.state = ActiveState.active;
        this.tasks = new LinkedList<ITask>();
        this.priority = priority;
    }

    public boolean isCollaborative()
    {
        return behavior.getCollaborationType() ==
                IBehaviorTemplate.CollaborationType.collaborative;
    }

    public int getPriority()
    {
        return priority;
    }

    public void activate()
    {
        state = ActiveState.active;
    }

    public void suspend()
    {
        state = ActiveState.suspended;
    }

    public void cancel()
    {
        state = ActiveState.cancelled;
    }

    public boolean isActive()
    {
        return state == ActiveState.active;
    }

    public boolean isSuspended()
    {
        return state == ActiveState.suspended;
    }

    public boolean isCancelled()
    {
        return state == ActiveState.cancelled;
    }

    public void queueTask(ITask task)
    {
        tasks.add(task);
        task.setOwningBehaviorQueue(this);
    }

    public ITask peekTask()
    {
        if(tasks.isEmpty())
            return null;
        return tasks.getFirst();
    }

    public void dequeueTask()
    {
        tasks.remove();
        if (tasks.isEmpty())
        {
            state = ActiveState.cancelled;
        }
    }

    /**
     * Sets the MultiQueue which is running this behavior  - in general, should
     * only be called by MultiQueue when this behavior is added.
     * @param mq MultiQueue adding this BehaviorQueue to a QueueSet.
     */
    public void setOwningMultiQueue(MultiQueue mq)
    {
        this.owningMultiQueue = mq;
    }

    /**
     * Get the MultiQueue which owns this BehaviorQueue.
     * @return Owning MultiQueue.
     */
    public MultiQueue getOwningMultiQueue()
    {
        return this.owningMultiQueue;
    }

    public IBehaviorTemplate getBehaviorTemplate()
    {
        return this.behavior;
    }

    public List<ITask> getTasks()
    {
        return this.tasks;
    }
}
