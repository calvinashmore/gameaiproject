/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import java.util.List;

/**
 * A TaskRequirement type which checks some conditions.  If they fail, it
 * inserts a list of tasks to presumably handle this failure into the task
 * queue.  If the conditions fail, handle reports false, so other requirements
 * are not checked and the task is not run.
 * @author hartsoka
 */
public abstract class ATaskRequirementSimple implements ITaskRequirement
{
    protected ITask owningTask;

    public ATaskRequirementSimple(ITask owner)
    {
        this.owningTask = owner;
    }

    public ITask getOwningTask() {
        return this.owningTask;
    }

    /**
     * Checks conditions defined for the requirement.  If they fail, it
     * inserts a list of tasks to presumably handle this failure into the task
     * queue.  If the conditions fail, handle reports false, so other
     * requirements are not checked and the task is not run.
     */
    public boolean handle()
    {
        if (checkConditions() == false)
        {
            List<ITask> fixers = getFixTasks();
            int index = 0;
            for (ITask fixer : fixers)
            {
                this.owningTask.getOwningBehaviorQueue().queueTask(fixer, index);
                index++;
            }
            return false;
        }
        return true;
    }

    /**
     * Checks conditions for whether the requirement is met.
     * @return True if the requirement is met, false if tasks need to be added
     * to complete the requirement.
     */
    public abstract boolean checkConditions();

    /**
     * Creates tasks which can resolve this requirement.
     * @return Tasks which should lead to conditions such that checkConditions
     * will return true once they are complete.
     */
    public abstract List<ITask> getFixTasks();

}
