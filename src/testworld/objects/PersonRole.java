/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package testworld.objects;

import proto.behavior.ARole;
import testworld.behaviors.IdleBehavior;
import testworld.behaviors.LatentExclamativeBehavior;
import testworld.behaviors.MoveToProactiveBehavior;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonRole extends ARole {

    public PersonRole() {
        addBehaviorTemplate(new IdleBehavior());
        addBehaviorTemplate(new LatentExclamativeBehavior());
    }

//    public void forceMoveTo(Vector2d destination) {
//        addBehaviorTemplate(new MoveToProactiveBehavior(destination));
//
//    }


}
