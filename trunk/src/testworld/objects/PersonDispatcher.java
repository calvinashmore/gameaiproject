/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
}
