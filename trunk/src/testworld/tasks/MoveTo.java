/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.tasks;

import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class MoveTo extends AMoveTo {

    public MoveTo(Vector2d destination, float destinationRange, float speed) {
        super(destination, destinationRange, speed);
    }

    public MoveTo(Vector2d destination, float destinationRange) {
        super(destination, destinationRange);
    }

    public MoveTo(Vector2d destination) {
        super(destination);
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
