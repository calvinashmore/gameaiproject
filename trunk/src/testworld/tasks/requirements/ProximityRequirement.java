/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.tasks.requirements;

import java.util.LinkedList;
import java.util.List;
import proto.behavior.ITask;
import proto.world.BasicObject;
import testworld.tasks.Chase;
import testworld.tasks.PersonTask;

/**
 *
 * @author hartsoka
 */
public class ProximityRequirement extends APersonTaskRequirement {

    protected BasicObject target;
    protected float distance;

    public ProximityRequirement(PersonTask owner, BasicObject target, float distance) {
        super(owner);
        this.target = target;
        this.distance = distance;
    }

    @Override
    public boolean checkConditions()
    {
        if (this.getPerson().getLocation().distance(target.getLocation()) <= distance)
        {
            return true;
        }
        return false;
    }

    @Override
    public List<ITask> getFixTasks() {
        List<ITask> fixers = new LinkedList<ITask>();
        fixers.add(new Chase(target, distance));
        return fixers;
    }

}
