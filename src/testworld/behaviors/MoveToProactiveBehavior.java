/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.IBehaviorTemplate.CollaborationType;
import proto.behavior.IBehaviorTemplate.InitiationType;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import testworld.tasks.MoveTo;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class MoveToProactiveBehavior extends ABehaviorTemplate implements IProactiveBehavior {

    private Vector2d destination;

    public MoveToProactiveBehavior(Vector2d destination) {
        super(InitiationType.proactive, CollaborationType.independent);
        this.destination = destination;
    }

    public int getImportance(IWorldState ws) {
        return 10; // temp value
    }

    public String getId() {
        return getClass().getSimpleName();
    }

    public BehaviorQueue instantiate(IWorldState ws) {
        BehaviorQueue bq = new BehaviorQueue(this);
        bq.addTask(new MoveTo(destination));
        return bq;
    }

}
