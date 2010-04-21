/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.objects;

import java.util.Map;
import java.util.Map.Entry;
import proto.behavior.Dispatcher;
import testworld.social.Stimuli.Effect;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonDispatcher extends Dispatcher {

    private Person person;

    public Person getPerson() {
        return person;
    }

    void setPerson(Person person) {
        this.person = person;
    }

    public PersonDispatcher() {
        super(new PersonRole());
    }

    public PersonDispatcher(PersonRole role) {
        super(role);
    }

    @Override
    public String toString() {
        return person.getName();
    }

    @Override
    public void handleTimer() {
        this.getPerson().getEmotions().update();
        super.handleTimer();
    }
}
