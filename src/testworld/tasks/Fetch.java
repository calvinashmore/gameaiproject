/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import proto.world.BasicObject;
import proto.world.World;
import utils.math.Vector2d;

/**
 *
 * @author hartsoka
 */
public class Fetch<T extends BasicObject> extends AMoveTo {

    private Class type;
    private T target;

    /**
     * "type" param MUST equal the generics param!!!
     * @param type
     */
    public Fetch(Class type) {
        super(DEFAULT_DESTINATION_RANGE, DEFAULT_SPEED);
        this.type = type; // TODO get rid of this by extracting T's type
    }

    public void resume() {

        Vector2d myPos = this.getPerson().getLocation().getPosition();

        BasicObject nearest = World.getInstance().getClosestObjectOfType(type, myPos);

        if (nearest == null) {
            // TODO handle this
            throw new UnsupportedOperationException("Fetch: do not yet know how to handle fetching when no instances of type in world");
        }

        this.target = (T) nearest;
    }

    @Override
    protected Vector2d getDestination() {
        return target.getLocation().getPosition();
    }

    public void runImpl() {

        look();

        if (isWithinRange()) {
            finished();
            return;
        }

        move();
    }
}
