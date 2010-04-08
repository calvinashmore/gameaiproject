/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package testworld.objects;

import proto.behavior.ARole;
import testworld.behaviors.ApproachProactive;
import testworld.behaviors.ApproachReactive;
import testworld.behaviors.ConversationWeatherProactive;
import testworld.behaviors.ConversationWeatherReactive;
import testworld.behaviors.LatentExclamativeBehavior;
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
        addBehaviorTemplate(new LatentExclamativeBehavior());
        addBehaviorTemplate(new RandomizedMoveTo());

        addBehaviorTemplate(new ApproachProactive());
        addBehaviorTemplate(new ApproachReactive());

        addBehaviorTemplate(new ConversationWeatherProactive());
        addBehaviorTemplate(new ConversationWeatherReactive());
    }



//    public void forceMoveTo(Vector2d destination) {
//        addBehaviorTemplate(new MoveToProactiveBehavior(destination));
//
//    }


}
