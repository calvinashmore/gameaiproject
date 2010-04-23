/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ILatentBehavior;
import proto.behavior.IWorldState;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.social.Stimuli;
import testworld.tasks.EffectTask;
import testworld.tasks.SpeechTask;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class TakeSip extends ABehaviorTemplate implements ILatentBehavior {

    public TakeSip() {
        super(InitiationType.latent, CollaborationType.independent);
    }

    public String getId() {
        return "TakeSip";
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        IBehaviorQueue bq = new BehaviorQueue(this);
        bq.queueTask(new EffectTask(Stimuli.Need.beverage, 20, EffectTask.Operation.Subtract));
        bq.queueTask(new EffectTask(Stimuli.Effect.numDrinks, 1, EffectTask.Operation.Subtract));
        bq.queueTask(new EffectTask(Stimuli.Need.toilet, 15, EffectTask.Operation.Add));
        bq.queueTask(new SpeechTask("*sip*"));
        return bq;
    }

    public boolean activate(IWorldState iws) {
        Person p = ((PersonDispatcher)this.getDispatcher()).getPerson();
        Stimuli s = p.getEmotions().getStimuli();
        Double numDrinks =
            s.getAttribute(Stimuli.Effect.numDrinks.toString());
        if (numDrinks == 0) {
            return false;
        }
        Double beverageNeed =
            s.getAttribute(Stimuli.Need.beverage.toString());
        if (beverageNeed > RandomManager.get().nextDouble() * 100 &&
            RandomManager.get().nextDouble() < 0.005)
        {
            return true;
        }
        return false;
    }

}
