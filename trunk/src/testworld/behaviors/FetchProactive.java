/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.IBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import testworld.objects.plain.Pickup;
import testworld.tasks.Fetch;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class FetchProactive extends ABehaviorTemplate implements IProactiveBehavior {

    public FetchProactive() {
        super(InitiationType.proactive, CollaborationType.independent);
    }

    public String getId() {
        return "FetchProactive";
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        BehaviorQueue bq = new BehaviorQueue(this);
        bq.queueTask(new Fetch<Pickup>(Pickup.class));
        return bq;
    }

    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(5);
    }

}
