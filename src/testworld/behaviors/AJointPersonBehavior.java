/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import proto.behavior.AJointBehavior;
import proto.behavior.Dispatcher;
import proto.world.World;
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

    public Person getPerson() {
        return ((PersonDispatcher)this.getDispatcher()).getPerson();
    }

    public List<PersonDispatcher> getPersonDispatchers() {
        List<Dispatcher> dispatchers = World.getInstance().getDispatchers();
        List<PersonDispatcher> personDispatchers = new ArrayList<PersonDispatcher>();

        for (Dispatcher dispatcher : dispatchers) {
            if(dispatcher instanceof PersonDispatcher)
                personDispatchers.add((PersonDispatcher) dispatcher);
        }
        return personDispatchers;
    }
    
    protected void sortPeopleByDistance(List<PersonDispatcher> dispatchers)
    {
        final Person me = ((PersonDispatcher)this.getDispatcher()).getPerson();

        Collections.sort(dispatchers, new Comparator<PersonDispatcher>(){

            public int compare(PersonDispatcher o1, PersonDispatcher o2) {
                Person p1 = o1.getPerson();
                Person p2 = o2.getPerson();

                double dist1 = me.getLocation().getPosition().subtract(p1.getLocation().getPosition()).magnitude();
                double dist2 = me.getLocation().getPosition().subtract(p2.getLocation().getPosition()).magnitude();

                if (dist1 == dist2) return 0;
                else if (dist1 < dist2) return -1;
                else return 1;
            }

        });
    }

}
