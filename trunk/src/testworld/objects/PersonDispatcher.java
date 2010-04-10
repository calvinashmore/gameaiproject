/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.objects;

import proto.behavior.Dispatcher;

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

    @Override
    public String toString() {
        return person.getName();
    }
}
