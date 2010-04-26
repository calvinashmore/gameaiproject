/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import proto.behavior.BehaviorQueue;
import proto.behavior.IBehaviorQueue;
import proto.behavior.IWorldState;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.social.Feelings;
import testworld.tasks.SpeechTask;

/**
 *
 * @author hartsoka
 */
public class Hiccup extends LatentExclamativeBehavior {

    @Override
    public IBehaviorQueue instantiate(IWorldState ws) {
        IBehaviorQueue bq = new BehaviorQueue(this);
        bq.queueTask(new SpeechTask("*hiccup*"));
        return bq;
    }

    @Override
    public boolean activate(IWorldState iws) {
        Person p = ((PersonDispatcher)this.getDispatcher()).getPerson();
        double alcoholLevel = p.getSocialState().getAttribute(Feelings.DEPRESSANT);
        return Math.random() < .00005 * alcoholLevel;
    }

}
