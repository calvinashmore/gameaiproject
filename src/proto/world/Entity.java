/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.world;

import proto.behavior.BehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.ITask;
import proto.behavior.MultiQueue;

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

    public void update() {

        dispatcher.tick(World.getInstance());
        dispatcher.handleTimer();
//        BehaviorQueue currentBehavior = multiQueue.getCurrentBehavior();
//        if (currentBehavior == null) {
//            return;
//        }
//
//        ITask task = multiQueue.getCurrentBehavior().peekTask();
//        if (task != null) {
//            task.run();
//        }
    }
//    public MultiQueue getMultiQueue() {
//        return multiQueue;
//    }
}
