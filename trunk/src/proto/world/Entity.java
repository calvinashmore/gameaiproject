/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package proto.world;

import proto.behavior.Dispatcher;
import proto.behavior.ILatentBehavior;
import proto.behavior.MultiQueue.QueueSet;

/**
 * An Entity is an object which can have behaviors and respond to events.
 * @author Calvin Ashmore
 */
abstract public class Entity extends BasicObject {

    private Dispatcher dispatcher;

    public Entity(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    abstract public String getName();

    public void update() {

        dispatcher.tick(World.getInstance());
        dispatcher.handleTimer();

        for (ILatentBehavior iLatentBehavior : dispatcher.getRole().getLatentBehaviors()) {
            if (iLatentBehavior.activate(World.getInstance())) {
                dispatcher.handleNewBehavior(iLatentBehavior.instantiate(World.getInstance()), QueueSet.LATENT_OR_LATENT_RESPONSE);
            }
        }

    }
}
