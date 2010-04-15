/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects.annotated;

import java.util.List;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ITask;
import proto.world.BasicObject;
import testworld.objects.Person;

/**
 *
 * @author hartsoka
 */
public abstract class AAnnotatedItem extends BasicObject {

    public abstract boolean isAllowedToUse(Person person);

    public abstract boolean isInPlaceToUse(Person person);

    public abstract List<ITask> getMoveToTasks(Person person, IBehaviorQueue behavior);

    public abstract List<ITask> getUsageTasks(Person person, IBehaviorQueue behavior);

}
