/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import java.util.LinkedList;
import proto.behavior.IBehaviorTemplate.InitiationType;

/**
 * A BehaviorTemplate instantiated into a queue of specific tasks.
 * @author hartsoka
 */
public class BehaviorQueue {

    public enum ActiveState { active, suspended, cancelled }

    private IBehaviorTemplate behavior;
    private ActiveState state;
    private LinkedList<ITask> tasks;
    private int priority;

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
            this.priority = 0;
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

    public void addTask(ITask task)
    {
        tasks.add(task);
        task.setBehaviorQueue(this);
    }

    public ITask peekTask()
    {
        return tasks.getFirst();
    }

    public void popTask()
    {
        tasks.remove();
        if (tasks.isEmpty())
        {
            state = ActiveState.cancelled;
        }
    }
}
