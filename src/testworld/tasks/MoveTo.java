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
public class MoveTo extends PersonTask {

    private Vector2d destination;
    private float speed;
    private float destinationRange;
    public static final float DEFAULT_SPEED = 4;
    public static final float DEFAULT_DESTINATION_RANGE = 20;

    public MoveTo(Vector2d destination) {
        this(destination, DEFAULT_DESTINATION_RANGE, DEFAULT_SPEED);
    }

    public MoveTo(Vector2d destination, float destinationRange, float speed) {
        this.destination = destination;
        this.destinationRange = destinationRange;
        this.speed = speed;
    }

    public void resume() {
    }

    public void run() {

        Vector2d oldPosition = getPerson().getLocation().getPosition();

        double distance = oldPosition.subtract(destination).magnitude();

        if (distance <= destinationRange) {
            finished();
            return;
        }

        Vector2d direction = getWorld().getPathPlanner().getDirection(getPerson(), destination);

        Vector2d movement = direction.multiply(speed);
        Vector2d newPosition = oldPosition.add(movement);
        getPerson().getLocation().setPosition(newPosition);
        getPerson().setLookAt(destination);
    }
}