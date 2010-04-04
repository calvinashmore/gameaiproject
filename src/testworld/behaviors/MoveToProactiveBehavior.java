/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors;

import proto.behavior.BehaviorQueue;
import proto.behavior.Dispatcher;
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
public class MoveToProactiveBehavior implements IProactiveBehavior {

    private Vector2d destination;

    public MoveToProactiveBehavior(Vector2d destination) {
        this.destination = destination;
    }

    public int getImportance(IWorldState ws) {
        return 10; // temp value
    }

    public String getId() {
        return getClass().getSimpleName();
    }

    public BehaviorQueue instantiate(IWorldState ws, Dispatcher d) {
        BehaviorQueue bq = new BehaviorQueue(this);
        bq.addTask(new MoveTo(d, destination));
        return bq;
    }

    public CollaborationType getCollaborationType() {
        return CollaborationType.independent;
    }

    public InitiationType getInitiationType() {
        return InitiationType.proactive;
    }
}
