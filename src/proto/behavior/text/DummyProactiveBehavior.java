/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior.text;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.BehaviorQueue;
import proto.behavior.IBehaviorQueue;
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

    public IBehaviorQueue instantiate(IWorldState ws) {
        IBehaviorQueue bq = new BehaviorQueue(this, 0);
        for (String word : sentence.split(" "))
        {
            bq.queueTask(new DummyWordTask(word));
        }
        return bq;
    }
    
    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(4);
    }
}
