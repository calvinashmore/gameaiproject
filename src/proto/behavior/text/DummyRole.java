/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.ARole;
import proto.behavior.Dispatcher;

/**
 *
 * @author hartsoka
 */
public class DummyRole extends ARole {

    public DummyRole()
    {
        super();

        super.addBehaviorTemplate(new DummyProactiveBehavior("To be or not to be, that is the question?"));
        super.addBehaviorTemplate(new DummyProactiveBehavior("Sometimes I feel like talking to myself."));

        //super.addBehaviorTemplate(new DummyCollaborativeBehavior());

        super.addBehaviorTemplate(new DummyLatentBehavior());
    }

}
