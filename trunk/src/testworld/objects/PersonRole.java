/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.objects;

import proto.behavior.ARole;
import testworld.behaviors.PauseBehavior;
import testworld.behaviors.RandomizedMoveTo;
import testworld.behaviors.UseBathroom;
import testworld.behaviors.conversations.ApproachConversationBehavior;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonRole extends ARole {

    public PersonRole() {
        //addBehaviorTemplate(new IdleBehavior());
        addBehaviorTemplate(new PauseBehavior());
        addBehaviorTemplate(new RandomizedMoveTo());
        addBehaviorTemplate(ApproachConversationBehavior.makeReactive());

        addBehaviorTemplate(new UseBathroom());
    }
}
