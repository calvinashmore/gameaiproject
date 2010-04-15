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
    static final protected int DESTINATION_RANGE = 50;
    static final protected int FLEE_DISTANCE = 150;

    public Flee(BasicObject target) {
        super(DESTINATION_RANGE);
        this.target = target;
    }

    public void resume() {
        // nch
    }

    @Override
    protected Vector2d getDestination() {

        Vector2d targetPos = target.getLocation().getPosition();
        Vector2d awayVector = super.getMyPosition().subtract(targetPos);

        return awayVector.getNormalizedVector().multiply(FLEE_DISTANCE);
    }

    public void run() {

        if (super.isWithinRange()) {
            finished();
            return;
        }

        look();

        move();
    }
}
