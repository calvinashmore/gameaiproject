/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.CollaborationHandshake;
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

    public BehaviorQueue instantiate(IWorldState ws)
    {
        throw new UnsupportedOperationException("Purely reactive behaviors should not be instantiated outside of collaboration.");
    }

    public BehaviorQueue completeHandshake(CollaborationHandshake handshake)
    {
        Dispatcher d = this.getOwningRole().getOwningDispatcher();

        BehaviorQueue bq = new BehaviorQueue(this, 1);
        bq.addTask(new WaitTask());
        bq.addTask(new DummyWordTask("I"));
        bq.addTask(new DummyWordTask("am"));
        bq.addTask(new DummyWordTask("good,"));
        bq.addTask(new DummyWordTask("thanks."));
        bq.addTask(new WaitTask());
        bq.addTask(new DummyWordTask("See"));
        bq.addTask(new DummyWordTask("you."));
        return bq;
        
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
