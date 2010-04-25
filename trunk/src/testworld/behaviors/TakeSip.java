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
import testworld.social.AttributeMap.Operation;
import testworld.social.SocialState;
import testworld.social.Inventory;
import testworld.social.Needs;
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
        bq.queueTask(new EffectTask(Needs.BEVERAGE, 20, Operation.Subtract));
        bq.queueTask(new EffectTask(Inventory.DRINKS, 1, Operation.Subtract));
        bq.queueTask(new EffectTask(Needs.TOILET, 15, Operation.Add));
        bq.queueTask(new SpeechTask("*sip*"));
        return bq;
    }

    public boolean activate(IWorldState iws) {
        Person p = ((PersonDispatcher)this.getDispatcher()).getPerson();
        SocialState e = p.getSocialState();
        Double numDrinks =
            e.getAttribute(Inventory.DRINKS);
        if (numDrinks == 0) {
            return false;
        }
        Double beverageNeed =
            e.getAttribute(Needs.BEVERAGE);
        if (beverageNeed > RandomManager.get().nextDouble() * 100 &&
            RandomManager.get().nextDouble() < 0.005)
        {
            return true;
        }
        return false;
    }

}
