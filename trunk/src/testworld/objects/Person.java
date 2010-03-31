/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.objects;

import proto.world.Entity;
import testworld.representations.PersonRepresentation;

/**
 *
 * @author Calvin Ashmore
 */
public class Person extends Entity {

    public Person() {
        setRepresentation(new PersonRepresentation(this));
    }


}
