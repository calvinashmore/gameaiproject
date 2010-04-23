/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.tasks;

import java.util.List;
import proto.behavior.ITask;
import testworld.objects.annotated.AAnnotatedItem;

/**
 *
 * @author hartsoka
 */
public class UseAnnotatedItem extends PersonTask {

    protected AAnnotatedItem item;

    public UseAnnotatedItem(AAnnotatedItem item)
    {
        this.item = item;
    }

    public void resume()
    {
        //nch... yet
    }

    public void runImpl()
    {
        List<ITask> tasks = item.getMoveToTasks(this.getPerson(), bq);
        int count = 0;
        for (ITask task : tasks)
        {
            bq.queueTask(task, count + 1);
            ++count;
        }

        tasks = item.getUsageTasks(this.getPerson(), bq);
        for (ITask task : tasks)
        {
            bq.queueTask(task, count + 1);
            ++count;
        }
        
        finished();
    }

}
