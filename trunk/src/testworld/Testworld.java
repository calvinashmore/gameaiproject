/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld;

import proto.world.BasicObject;
import proto.world.Entity;
import proto.world.World;
import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
public class Testworld extends World {

    private PlayerImplementation player;

    public Testworld() {

        Person person = new Person("Frank");
        person.getLocation().getPosition().x = 100;
        person.getLocation().getPosition().y = 100;
        getAllObjects().add(person);

        person = player = new PlayerImplementation("Player");
        person.getLocation().getPosition().x = 200;
        person.getLocation().getPosition().y = 200;
        getAllObjects().add(person);

        setEnvironment(new RoomEnvironment());
    }

    public PlayerImplementation getPlayer() {
        return player;
    }
}
