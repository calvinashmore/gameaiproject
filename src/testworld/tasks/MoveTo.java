/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.tasks;

import proto.world.BasicObject;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class MoveTo extends AMoveTo {

    private Vector2d vectorDestination;
    private BasicObject objectDestination;

    public MoveTo(Vector2d destination, float destinationRange, float speed) {
        super(destinationRange, speed);
        this.vectorDestination = destination;
    }

    public MoveTo(Vector2d destination, float destinationRange) {
        super(destinationRange);
        this.vectorDestination = destination;
    }

    public MoveTo(Vector2d destination) {
        this.vectorDestination = destination;
    }

    public MoveTo(BasicObject destination, float destinationRange, float speed) {
        super(destinationRange, speed);
        this.objectDestination = destination;
    }

    public MoveTo(BasicObject destination, float destinationRange) {
        super(destinationRange);
        this.objectDestination = destination;
    }

    public MoveTo(BasicObject destination) {
        this.objectDestination = destination;
    }

    @Override
    protected Vector2d getDestination() {
        if (vectorDestination != null) {
            return vectorDestination;
        } else {
            return objectDestination.getLocation().getPosition();
        }
    }

    public void resume() {
    }

    public void run() {

        look();

        if (isWithinRange()) {
            finished();
            return;
        }

        move();
    }
}
