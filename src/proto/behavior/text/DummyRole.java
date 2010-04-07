/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior.text;

import proto.behavior.ARole;

/**
 *
 * @author hartsoka
 */
public class DummyRole extends ARole {

    public DummyRole()
    {
        super();

        super.addBehaviorTemplate(new DummyProactiveBehavior("To be or not to be, that is the question?"));
        //super.addBehaviorTemplate(new DummyProactiveBehavior("Sometimes I feel like talking to myself."));

        //super.addBehaviorTemplate(new DummyCollaborativeBehavior());
        //super.addBehaviorTemplate(new DummyReactiveBehavior());
        super.addBehaviorTemplate(DummyJointBehavior.makeProactive());
        super.addBehaviorTemplate(DummyJointBehavior.makeReactive());

        super.addBehaviorTemplate(new DummyLatentBehavior());
    }

}
