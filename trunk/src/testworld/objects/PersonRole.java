/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.objects;

import proto.behavior.ARole;
import testworld.behaviors.Hiccup;
import testworld.behaviors.LatentExclamativeBehavior;
import testworld.behaviors.PauseBehavior;
import testworld.behaviors.RandomizedMoveTo;
import testworld.behaviors.Smoke;
import testworld.behaviors.UseBathroom;
import testworld.behaviors.conversations.ApproachConversationBehavior;
import testworld.behaviors.cutscene.FrankKillsVictim;

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

        addBehaviorTemplate(new LatentExclamativeBehavior());
        addBehaviorTemplate(new Hiccup());

        addBehaviorTemplate(new UseBathroom());
        addBehaviorTemplate(new Smoke());

        // cutscenes
        addBehaviorTemplate(FrankKillsVictim.makeReactive());
    }
}
