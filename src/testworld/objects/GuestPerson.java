/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects;

/**
 *
 * @author hartsoka
 */
public class GuestPerson extends Person {

    public GuestPerson(String name) {
        super(name, new PersonDispatcher(new GuestPersonRole()));
    }
}
