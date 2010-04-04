/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.IReactiveBehavior;
import proto.behavior.IWorldState;

/**
 *
 * @author hartsoka
 */
public class DummyReactiveBehavior extends ABehaviorTemplate implements IReactiveBehavior {

    public DummyReactiveBehavior()
    {
        super(InitiationType.reactive, CollaborationType.collaborative);
    }

    public String getId() {
        return "GreetAccept";
    }

    public BehaviorQueue instantiate(IWorldState ws, Dispatcher d) {
        BehaviorQueue bq = new BehaviorQueue(this, 1);
        bq.addTask(new WaitTask(d));
        bq.addTask(new DummyWordTask(d, "I"));
        bq.addTask(new DummyWordTask(d, "am"));
        bq.addTask(new DummyWordTask(d, "good,"));
        bq.addTask(new DummyWordTask(d, "thanks."));
        bq.addTask(new WaitTask(d));
        bq.addTask(new DummyWordTask(d, "See"));
        bq.addTask(new DummyWordTask(d, "you."));
        return bq;
    }

}
