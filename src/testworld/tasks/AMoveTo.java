/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import utils.math.Vector2d;

/**
 *
 * @author hartsoka
 */
public abstract class AMoveTo extends PersonTask {

    //protected Vector2d destination;
    protected float speed;
    protected float destinationRange;
    public static final float DEFAULT_SPEED = 4;
    public static final float DEFAULT_DESTINATION_RANGE = 20;

    protected Vector2d lastMovement = new Vector2d();
    
    protected abstract Vector2d getDestination();

    public AMoveTo() {
        this(DEFAULT_DESTINATION_RANGE, DEFAULT_SPEED);
    }

    // For use by subclasses, they must fill in destination
    protected AMoveTo(float destinationRange) {
        this(destinationRange, DEFAULT_SPEED);
    }

    // For use by subclasses, they must fill in destination
    protected AMoveTo(float destinationRange, float speed) {
        this.destinationRange = destinationRange;
        this.speed = speed;
    }

    protected Vector2d getMyPosition() {
        return getPerson().getLocation().getPosition();
    }

    protected boolean isWithinRange() {
        Vector2d oldPosition = getMyPosition();

        double distance = oldPosition.subtract(getDestination()).magnitude();

        return (distance <= destinationRange);
    }

    protected void look() {
        getPerson().setLookAt(getDestination());
    }

    protected void move() {
        Vector2d oldPosition = getPerson().getLocation().getPosition();
        Vector2d direction = getWorld().getPathPlanner().getDirection(getPerson(), getDestination());

        Vector2d movement = direction.multiply(speed);

        // this test checks to see if the last movement was close to the opposite of the current movement
        if(lastMovement.getNormalizedVector().dot(movement.getNormalizedVector()) < -.8) {
            // terminate the current task if this is the case
            // otherwise, jerky behavior occurs.
            finished();
        }

        lastMovement = movement;

        Vector2d newPosition = oldPosition.add(movement);
        getPerson().getLocation().setPosition(newPosition);
    }
}
