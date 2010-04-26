/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.IBehaviorQueue;
import proto.behavior.IBehaviorTemplate.CollaborationType;
import proto.behavior.IBehaviorTemplate.InitiationType;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import utils.math.RandomManager;

/**
 * The IdleBehavior is a baseline behavior that all characters will have. It basically amounts to doing nothing at all.
 * @author Calvin Ashmore
 */
public class IdleBehavior extends ABehaviorTemplate implements IProactiveBehavior {

    public IdleBehavior() {
        super(InitiationType.proactive, CollaborationType.independent);
    }

    public String getId() {
        return getClass().getName();
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        IBehaviorQueue bq = new BehaviorQueue(this);
        return bq;
    }

    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(100);
    }
}
