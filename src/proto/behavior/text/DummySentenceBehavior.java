/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.BehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorTemplate;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;

/**
 *
 * @author hartsoka
 */
public class DummySentenceBehavior implements IBehaviorTemplate, IProactiveBehavior {

    public String sentence;

    public DummySentenceBehavior(String sentence)
    {
        this.sentence = sentence;
    }

    public String getId() {
        return "Say:" + sentence;
    }

    public BehaviorQueue instantiate(IWorldState ws, Dispatcher d) {
        BehaviorQueue bq = new BehaviorQueue(this);
        for (String word : sentence.split(" "))
        {
            bq.addTask(new DummyWordTask(d, word));
        }
        return bq;
    }

    public CollaborationType getCollaborationType() {
        return CollaborationType.independent;
    }

    public InitiationType getInitiationType() {
        return InitiationType.proactive;
    }

    public void updateImportance(IWorldState ws) {
        // dummy, nch
    }

    public int getImportance() {
        return 0;
    }
}
