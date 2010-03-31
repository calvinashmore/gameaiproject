/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.objects;

import proto.navigation.BoundingSphere;
import proto.world.Entity;
import testworld.representations.PersonRepresentation;

/**
 *
 * @author Calvin Ashmore
 */
public class Person extends Entity {

    private String name;

    public Person(String name) {
        this.name = name;
        setRepresentation(new PersonRepresentation(this));
        setCollisionVolume(new BoundingSphere(20));
    }

    public String getName() {
        return name;
    }
}
