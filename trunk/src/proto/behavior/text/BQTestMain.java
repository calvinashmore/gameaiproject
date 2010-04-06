/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior.text;

import proto.behavior.Dispatcher;
import proto.behavior.ILatentBehavior;
import proto.behavior.MultiQueue.QueueSet;
import testworld.objects.PersonDispatcher;

/**
 *
 * @author hartsoka
 */
public class BQTestMain {

    public static void main(String[] args)
    {
        String[] characters = {"Bill", "Jane"};

        DummyWorldState dws = new DummyWorldState();

        DummyRole billRole = new DummyRole();
        DummyRole janeRole = new DummyRole();
        Dispatcher bill = new Dispatcher(billRole);
        Dispatcher jane = new Dispatcher(janeRole);

        DummyDispatcherManager.register(bill);
        DummyDispatcherManager.register(jane);

        for (int i = 0; i < 500; ++i)
        {
            //System.out.println(i);

            //System.out.println(characters[0] + ": ");
            bill.handleTimer();

            for (ILatentBehavior lb : billRole.getLatentBehaviors())
            {
                if (lb.activate(dws))
                {
                    bill.handleNewBehavior(lb.instantiate(dws), QueueSet.latent);
                }
            }
            System.out.println();

            System.out.print("\t\t\t\t\t");
            //System.out.println(characters[1] + ": ");
            jane.handleTimer();

            for (ILatentBehavior lb : janeRole.getLatentBehaviors())
            {
                if (lb.activate(dws))
                {
                    jane.handleNewBehavior(lb.instantiate(dws), QueueSet.latent);
                }
            }
            System.out.println();
        }
    }
}
