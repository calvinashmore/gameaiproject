/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld;

import proto.world.World;
import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
public class Testworld extends World {

    private Person player;

    public Testworld() {

        Person person = new Person("Frank");
        person.getLocation().getPosition().x = 100;
        person.getLocation().getPosition().y = 100;
        getAllObjects().add(person);

        player = person = new Person("Player");
        person.getLocation().getPosition().x = 200;
        person.getLocation().getPosition().y = 200;
        getAllObjects().add(person);

        setEnvironment(new RoomEnvironment());
    }

    public Person getPlayer() {
        return player;
    }
}
