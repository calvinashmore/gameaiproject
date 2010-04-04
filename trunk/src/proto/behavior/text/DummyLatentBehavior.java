/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import java.util.Random;
import proto.behavior.BehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.ILatentBehavior;
import proto.behavior.IWorldState;

/**
 *
 * @author hartsoka
 */
public class DummyLatentBehavior implements ILatentBehavior {

    public boolean activate(IWorldState iws) {
        return ((DummyWorldState)iws).rollDie(10) == 0;
    }

    public String getId() {
        return "Cough";
    }

    public BehaviorQueue instantiate(IWorldState ws, Dispatcher d) {
        Random r = new Random();
        int numCoughs = r.nextInt(3) + 1;
        BehaviorQueue bq = new BehaviorQueue(this, 2);
        for (int i = 0; i < numCoughs; ++i)
        {
            bq.addTask(new DummyWordTask(d, "*cough*"));
        }
        return bq;
    }

    public CollaborationType getCollaborationType() {
        return CollaborationType.independent;
    }

    public InitiationType getInitiationType() {
        return InitiationType.latent;
    }
}
