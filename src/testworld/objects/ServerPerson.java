/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects;

/**
 *
 * @author Calvin Ashmore
 */
public class ServerPerson extends Person {

    public ServerPerson(String name) {
        super(name, new PersonDispatcher(new ServerPersonRole()));
    }

}
