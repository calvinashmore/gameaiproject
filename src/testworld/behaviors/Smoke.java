/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.IBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.objects.plain.Balcony;
import testworld.social.AttributeInfo;
import testworld.social.AttributeMap.Operation;
import testworld.social.Feelings;
import testworld.social.Needs;
import testworld.tasks.EffectTask;
import testworld.tasks.Fetch;
import testworld.tasks.SpeechTask;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class Smoke extends ABehaviorTemplate implements IProactiveBehavior {

    public Smoke() {
        super(InitiationType.proactive, CollaborationType.independent);
    }

    @Override
    public String getId() {
        return "Smoke";
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        IBehaviorQueue bq = new BehaviorQueue(this);
        bq.queueTask(new Fetch<Balcony>(Balcony.class, 150));
        bq.queueTask(new EffectTask(Needs.CIGARETTE, 0, Operation.Set));
        bq.queueTask(new EffectTask(Feelings.ANXIETY, 20, Operation.Subtract));
        bq.queueTask(new EffectTask(Feelings.IRRITATION, 20, Operation.Subtract));
        bq.queueTask(new SpeechTask("*smokes*"));
        return bq;
    }

    @Override
    public int getImportance(IWorldState ws)
    {
        Person p = ((PersonDispatcher)this.getDispatcher()).getPerson();

        Double cigaretteNeed = p.getSocialState().getAttribute(Needs.CIGARETTE);

        if (cigaretteNeed == 0) return -1;

        if (cigaretteNeed < AttributeInfo.getInstance().maximums.get(Needs.CIGARETTE) * 0.6
                    + AttributeInfo.getInstance().minimums.get(Needs.CIGARETTE))
        {
            return 0;
        }

        return RandomManager.get().nextInt(
                    (int)(cigaretteNeed/1) + 1
                );
    }
}
