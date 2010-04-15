/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.objects;

import proto.behavior.ARole;
import testworld.behaviors.ApproachBehavior;
import testworld.behaviors.PauseBehavior;
import testworld.behaviors.RandomizedMoveTo;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonRole extends ARole {

    public PersonRole() {
        //addBehaviorTemplate(new IdleBehavior());
        addBehaviorTemplate(new PauseBehavior());
        addBehaviorTemplate(new RandomizedMoveTo());
    }
//    public void forceMoveTo(Vector2d destination) {
//        addBehaviorTemplate(new MoveToProactiveBehavior(destination));
//
//    }
}
