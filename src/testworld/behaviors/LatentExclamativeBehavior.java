/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors;

import java.util.Random;
import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.IBehaviorQueue;
import proto.behavior.IBehaviorTemplate.CollaborationType;
import proto.behavior.IBehaviorTemplate.InitiationType;
import proto.behavior.ILatentBehavior;
import proto.behavior.IWorldState;
import testworld.tasks.SpeechTask;

/**
 *
 * @author Calvin Ashmore
 */
public class LatentExclamativeBehavior extends ABehaviorTemplate implements ILatentBehavior {

    public LatentExclamativeBehavior() {
        super(InitiationType.latent, CollaborationType.independent);
    }

    public String getId() {
        return getClass().getName();
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        IBehaviorQueue bq = new BehaviorQueue(this);
        String exclamation = null;

        int die = new Random().nextInt(3);
        switch (die) {
            case 0:
                exclamation = "*cough*";
                break;
            case 1:
            case 2:
                exclamation = "*sip*";
                break;
        }
        bq.queueTask(new SpeechTask(exclamation));
        return bq;
    }

    public boolean activate(IWorldState iws) {
        return Math.random() < .002;
    }
}
