/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import proto.behavior.BehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.ITask;

/**
 *
 * @author hartsoka
 */
public abstract class ATask implements ITask {

    protected BehaviorQueue bq;

    public ATask()
    {
        
    }

    public void setOwningBehaviorQueue(BehaviorQueue bq)
    {
        this.bq = bq;
    }

    public BehaviorQueue getOwningBehaviorQueue()
    {
        return this.bq;
    }

    public Dispatcher getDispatcher()
    {
        return this.getOwningBehaviorQueue().getOwningMultiQueue().getOwningDispatcher();
    }
}
