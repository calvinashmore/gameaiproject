/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.objects;

import proto.behavior.MultiQueue.QueueSet;
import proto.navigation.BoundingSphere;
import proto.world.Entity;
import proto.world.World;
import testworld.behaviors.MoveToProactiveBehavior;
import testworld.representations.PersonRepresentation;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class Person extends Entity {

    private String name;

    public Person(String name) {
        super(new PersonDispatcher());
        ((PersonDispatcher) getDispatcher()).setPerson(this);
        this.name = name;
        setRepresentation(new PersonRepresentation(this));
        setCollisionVolume(new BoundingSphere(20));
    }

    public PersonRole getRole() {
        return (PersonRole) getDispatcher().getRole();
    }

    @Override
    public PersonDispatcher getDispatcher() {
        return (PersonDispatcher) super.getDispatcher();
    }

    public String getName() {
        return name;
    }

    // TEMP: THIS DOES NOT BELONG HERE
    public void forceMoveTo(Vector2d destination) {
        //addBehaviorTemplate(new MoveToProactiveBehavior(destination));
        // ALSO: Implementation needs changing
        getDispatcher().handleNewBehavior(
                new MoveToProactiveBehavior(destination).instantiate(World.getInstance(), getDispatcher()), QueueSet.pro);
    }
}
