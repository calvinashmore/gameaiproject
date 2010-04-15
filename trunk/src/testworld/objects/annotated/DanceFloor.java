/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects.annotated;

import java.util.List;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ITask;
import testworld.objects.Person;

/**
 *
 * @author hartsoka
 */
public class DanceFloor extends ADefaultAnnotatedItem {

    @Override
    public List<ITask> getUsageTasks(Person person, IBehaviorQueue behavior)
    {
        // TODO
        return null;
    }

}
