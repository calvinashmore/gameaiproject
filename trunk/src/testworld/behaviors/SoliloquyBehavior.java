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
import testworld.tasks.SpeechTask;

/**
 *
 * @author Calvin Ashmore
 */
public class SoliloquyBehavior extends ABehaviorTemplate implements IProactiveBehavior {

    private String[] soliloquy;

    public SoliloquyBehavior(String... soliloquy) {
        super(InitiationType.proactive, CollaborationType.independent);
        this.soliloquy = soliloquy;
    }

    public String getId() {
        return getClass().getName();
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        IBehaviorQueue bq = new BehaviorQueue(this);
        for (String speech : soliloquy) {
            bq.queueTask(new SpeechTask(speech));
        }

        return bq;
    }

    public int getImportance(IWorldState ws) {
        return 10; // arbitrary
    }
}
