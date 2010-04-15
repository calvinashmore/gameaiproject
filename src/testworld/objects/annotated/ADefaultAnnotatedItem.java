/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects.annotated;

import testworld.objects.annotated.AAnnotatedItem;
import java.util.LinkedList;
import java.util.List;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ITask;
import testworld.objects.Person;
import testworld.tasks.MoveTo;
import utils.math.Vector2d;

/**
 *
 * @author hartsoka
 */
public abstract class ADefaultAnnotatedItem extends AAnnotatedItem {

    protected static final float DEFAULT_USAGE_RANGE = 50;
    protected static final float EPSILON = 3; // how much "extra" closer we try to move

    protected float usageRange;

    public ADefaultAnnotatedItem() {
        this(DEFAULT_USAGE_RANGE);
    }

    public ADefaultAnnotatedItem(float usageRange) {
        this.usageRange = usageRange;
    }

    @Override
    public boolean isAllowedToUse(Person person) {
        return true;
    }

    @Override
    public boolean isInPlaceToUse(Person person)
    {
        Vector2d thisPos = this.getLocation().getPosition();
        Vector2d personPos = person.getLocation().getPosition();
        double dist = personPos.subtract(thisPos).magnitude();

        return dist <= usageRange;
    }

    @Override
    public List<ITask> getMoveToTasks(Person person, IBehaviorQueue behavior)
    {
        List<ITask> tasks = new LinkedList<ITask>();
        tasks.add(new MoveTo(this.getLocation().getPosition(), usageRange - EPSILON));
        return tasks;
    }
}
