/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior.text;

import java.util.Random;
import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ILatentBehavior;
import proto.behavior.IWorldState;

/**
 *
 * @author hartsoka
 */
public class DummyLatentBehavior extends ABehaviorTemplate implements ILatentBehavior {
    
    public DummyLatentBehavior()
    {
        super(InitiationType.latent, CollaborationType.independent);
    }

    public boolean activate(IWorldState iws) {
        return ((DummyWorldState)iws).rollDie(10) == 0;
    }

    public String getId() {
        return "Cough";
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        Random r = new Random();
        int numCoughs = r.nextInt(3) + 1;
        IBehaviorQueue bq = new BehaviorQueue(this, 2);
        for (int i = 0; i < numCoughs; ++i)
        {
            bq.queueTask(new DummyWordTask("*cough*"));
        }
        return bq;
    }
}
