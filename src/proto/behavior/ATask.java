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

    protected Dispatcher dispatcher;
    protected BehaviorQueue bq;

    public ATask(Dispatcher d)
    {
        this.dispatcher = d;
    }

    public void setBehaviorQueue(BehaviorQueue bq)
    {
        this.bq = bq;
    }
}
