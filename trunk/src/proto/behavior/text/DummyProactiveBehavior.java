/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class DummyProactiveBehavior extends ABehaviorTemplate implements IProactiveBehavior {

    public String sentence;

    public DummyProactiveBehavior(String sentence)
    {
        super(InitiationType.proactive, CollaborationType.independent);
        this.sentence = sentence;
    }
    
    public DummyProactiveBehavior(String sentence, InitiationType initType, CollaborationType collabType)
    {
        super(initType, collabType);
        this.sentence = sentence;
    }

    /* won't work, needs to implement latent interface
    public static DummySentenceBehavior makeCough()
    {
        return new DummySentenceBehavior("CoughCough", InitiationType.latent, CollaborationType.independent);
    }
     */

    public String getId() {
        return "Say:" + sentence;
    }

    public BehaviorQueue instantiate(IWorldState ws, Dispatcher d) {
        BehaviorQueue bq = new BehaviorQueue(this, 0);
        for (String word : sentence.split(" "))
        {
            bq.addTask(new DummyWordTask(d, word));
        }
        return bq;
    }
    
    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(4);
    }
}
