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
public class Chase extends AMoveTo {

    protected BasicObject target;

    public Chase(BasicObject target) {
        this(target, DEFAULT_DESTINATION_RANGE);
    }

    public Chase(BasicObject target, float destinationRange) {
        super(destinationRange);
        this.target = target;
    }

    @Override
    protected Vector2d getDestination()
    {
        return target.getLocation().getPosition();
    }

    public void resume() {
        // nch
    }

    public void runImpl()
    {
        if (super.isWithinRange()) {
            finished();
            return;
        }

        look();
        move();
    }

}
