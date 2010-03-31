/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.world;

import proto.behavior.BehaviorQueue;
import proto.behavior.ITask;
import proto.behavior.MultiQueue;

/**
 * An Entity is an object which can have behaviors and respond to events.
 * @author Calvin Ashmore
 */
abstract public class Entity extends BasicObject {

    private MultiQueue multiQueue;

    public Entity() {
        multiQueue = new MultiQueue();
    }

    public void update() {

        BehaviorQueue currentBehavior = multiQueue.getCurrentBehavior();
        if (currentBehavior == null) {
            return;
        }

        ITask task = multiQueue.getCurrentBehavior().peekTask();
        if (task != null) {
            task.run();
        }
    }

    public MultiQueue getMultiQueue() {
        return multiQueue;
    }
}
