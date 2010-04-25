/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.tasks.requirements;

import proto.behavior.ATaskRequirementSimple;
import testworld.objects.Person;
import testworld.tasks.PersonTask;

/**
 *
 * @author hartsoka
 */
public abstract class APersonTaskRequirement extends ATaskRequirementSimple {

    protected Person getPerson()
    {
        return ((PersonTask)this.getOwningTask()).getPerson();
    }
}
