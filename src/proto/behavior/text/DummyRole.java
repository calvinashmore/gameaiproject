/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.ARole;
import proto.behavior.BehaviorQueue;
import proto.behavior.IWorldState;

/**
 *
 * @author hartsoka
 */
public class DummyRole extends ARole {

    public DummyRole()
    {
        super();

        super.addBehaviorTemplate(new DummySentenceBehavior("To be or not to be, that is the question?"));
        super.addBehaviorTemplate(new DummySentenceBehavior("This is a test of the emergency broadcast system."));
        super.addBehaviorTemplate(new DummySentenceBehavior("Zoiks!"));
    }

}
