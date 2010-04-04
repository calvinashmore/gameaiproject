/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.Dispatcher;
import proto.behavior.ILatentBehavior;
import proto.behavior.MultiQueue.QueueSet;

/**
 *
 * @author hartsoka
 */
public class BQTestMain {

    public static void main(String[] args)
    {
        DummyWorldState dws = new DummyWorldState();

        DummyRole role = new DummyRole();
        Dispatcher d = new Dispatcher(role);

        for (int i = 0; i < 50; ++i)
        {
            d.handleTimer();

            for (ILatentBehavior lb : role.getLatentBehaviors())
            {
                if (lb.activate(dws))
                {
                    d.handleNewBehavior(lb.instantiate(dws), QueueSet.latent);
                }
            }
        }
    }
}
