/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import proto.behavior.IBehaviorQueue;
import proto.behavior.IWorldState;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.objects.annotated.Bathroom;
import testworld.social.AttributeInfo;
import testworld.social.Needs;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class UseBathroom
        extends UseRandomAnnotatedItem
{

    @Override
    public String getId() {
        return "UseBathroom";
    }

    @Override
    public IBehaviorQueue instantiate(IWorldState ws)
    {
        return super.instantiateImpl(ws, Bathroom.class);
    }

    @Override
    public int getImportance(IWorldState ws)
    {
        Person p = ((PersonDispatcher)this.getDispatcher()).getPerson();

        Double need = p.getSocialState().getAttribute(Needs.TOILET);

        if (need < AttributeInfo.getInstance().maximums.get(Needs.TOILET) * 0.6
                    + AttributeInfo.getInstance().minimums.get(Needs.TOILET))
        {
            return 0;
        }

        return RandomManager.get().nextInt(
                    (int)(need/10) + 1
                );
    }

}
