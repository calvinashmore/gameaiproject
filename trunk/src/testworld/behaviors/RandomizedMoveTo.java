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
import proto.world.World;
import testworld.objects.PersonDispatcher;
import testworld.tasks.MoveTo;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class RandomizedMoveTo extends ABehaviorTemplate implements IProactiveBehavior {

    public RandomizedMoveTo() {
        super(InitiationType.proactive, CollaborationType.independent);
    }

    public int getImportance(IWorldState ws) {

        PersonDispatcher dispatcher = (PersonDispatcher) getDispatcher();
        int neighbors = World.getInstance().getNearbyObjects(dispatcher.getPerson(), 100).size();
        if (neighbors >= 3) {
            // if we have several neighbors, movement is more likely
            if (Math.random() < .5) {
                return 2;
            }
        }

        // decide to move randomly
        if (Math.random() < .1) {
            return 2;
        }
        return -1;
    }

    public String getId() {
        return getClass().getSimpleName();
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        PersonDispatcher dispatcher = (PersonDispatcher) getDispatcher();

        double dx = 200 * Math.random() - 100;
        double dy = 200 * Math.random() - 100;

        Vector2d destination = dispatcher.getPerson().getLocation().getPosition().add(new Vector2d(dx, dy));

        IBehaviorQueue bq = new BehaviorQueue(this);
        bq.queueTask(new MoveTo(destination));
        return bq;
    }
}
