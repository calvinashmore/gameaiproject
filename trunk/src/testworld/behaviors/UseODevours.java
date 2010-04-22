/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors;

import proto.behavior.IBehaviorQueue;
import proto.behavior.IWorldState;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.objects.annotated.ODevours;
import testworld.social.Stimuli;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class UseODevours
        extends UseRandomAnnotatedItem {

    @Override
    public String getId() {
        return "UseODevours";
    }

    @Override
    public IBehaviorQueue instantiate(IWorldState ws) {
        return super.instantiateImpl(ws, ODevours.class);
    }

    @Override
    public int getImportance(IWorldState ws) {
        Person p = ((PersonDispatcher) this.getDispatcher()).getPerson();

        return RandomManager.get().nextInt(
                (int) (p.getEmotions().getStimuli().getAttribute(Stimuli.Need.food.toString()) / 10) + 1);
    }
}
