/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import proto.behavior.AJointBehavior;
import proto.behavior.Dispatcher;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;

/**
 *
 * @author hartsoka
 */
public abstract class AJointPersonBehavior extends AJointBehavior {

    public AJointPersonBehavior(InitiationType initType) {
        super(initType);
    }

    protected void sortPeopleByDistance(List<Dispatcher> dispatchers)
    {
        final Person me = ((PersonDispatcher)this.getDispatcher()).getPerson();

        Collections.sort(dispatchers, new Comparator<Dispatcher>(){

            public int compare(Dispatcher o1, Dispatcher o2) {
                Person p1 = ((PersonDispatcher)o1).getPerson();
                Person p2 = ((PersonDispatcher)o2).getPerson();

                double dist1 = me.getLocation().getPosition().subtract(p1.getLocation().getPosition()).magnitude();
                double dist2 = me.getLocation().getPosition().subtract(p2.getLocation().getPosition()).magnitude();

                if (dist1 == dist2) return 0;
                else if (dist1 < dist2) return -1;
                else return 1;
            }

        });
    }

}
