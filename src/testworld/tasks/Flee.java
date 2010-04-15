/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import proto.world.BasicObject;
import utils.math.Vector2d;

/**
 *
 * @author hartsoka
 */
public class Flee extends AMoveTo {

    protected BasicObject target;
    protected float safetyDistance;

    static final protected float DESTINATION_RANGE = 50;
    static final protected float DEFAULT_SAFETY_DISTANCE = 150;
    
    public Flee(BasicObject target) {
        this(target, DEFAULT_SAFETY_DISTANCE);
    }

    public Flee(BasicObject target, float safetyDistance) {
        super(DESTINATION_RANGE);
        this.target = target;
        this.safetyDistance = safetyDistance;
    }

    public void resume() {
        // nch
    }

    @Override
    protected Vector2d getDestination()
    {
        Vector2d targetPos = target.getLocation().getPosition();
        Vector2d awayVector = super.getMyPosition().subtract(targetPos);

        return awayVector.getNormalizedVector().multiply(safetyDistance);
    }

    public void run()
    {
        if (super.isWithinRange()) {
            finished();
            return;
        }

        look();
        move();
    }
}
