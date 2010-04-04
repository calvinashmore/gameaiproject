/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.CollaborationHandshake;
import proto.behavior.Dispatcher;
import proto.behavior.ICollaborativeBehavior;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class DummyCollaborativeBehavior extends ABehaviorTemplate implements IProactiveBehavior, ICollaborativeBehavior {

    public DummyCollaborativeBehavior()
    {
        super(InitiationType.proactive, CollaborationType.independent);
    }

    public String getId() {
        return "GreetStart";
    }

    public BehaviorQueue instantiate(IWorldState ws)
    {
        Dispatcher d = this.getOwningRole().getOwningDispatcher();

        BehaviorQueue bq = new BehaviorQueue(this, 1);
        bq.addTask(new DummyWordTask("Hi,"));
        bq.addTask(new DummyWordTask("how"));
        bq.addTask(new DummyWordTask("are"));
        bq.addTask(new DummyWordTask("you?"));
        bq.addTask(new WaitTask());
        bq.addTask(new DummyWordTask("Gotta"));
        bq.addTask(new DummyWordTask("run."));
        bq.addTask(new WaitTask());
        return bq;
    }

    /*
    protected CollaborationHandshake seekCollaborators(Dispatcher d)
    {
        CollaborationHandshake handshake =
                new CollaborationHandshake(d);
    }
     * 
     */

    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(3);
    }

    public BehaviorQueue completeHandshake(CollaborationHandshake handshake) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
