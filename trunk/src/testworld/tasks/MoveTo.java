/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import proto.behavior.Dispatcher;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class MoveTo extends PersonTask {

    private Vector2d destination;
    private float speed;
    private static final float DEFAULT_SPEED = 5;
    private static final float DESTINATION_RANGE = 20;

    public MoveTo(Dispatcher d, Vector2d destination) {
        this(d, destination, DEFAULT_SPEED);
    }

    public MoveTo(Dispatcher d, Vector2d destination, float speed) {
        super(d);
        this.destination = destination;
        this.speed = speed;
    }

    public void resume() {
    }

    public void run() {

        Vector2d oldPosition = getPerson().getLocation().getPosition();

        double distance = oldPosition.subtract(destination).magnitude();

        if(distance <= DESTINATION_RANGE) {
            finished();
            return;
        }

        Vector2d direction = getWorld().getPathPlanner().getDirection(getPerson(), destination);

        Vector2d movement = direction.multiply(speed);
        Vector2d newPosition = oldPosition.add(movement);
        getPerson().getLocation().setPosition(newPosition);
    }
}