/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld;

import proto.world.World;
import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
public class Testworld extends World {

    private PlayerImplementation player;

    public Testworld() {

        Person person;

        person = player = new PlayerImplementation("Player");
        person.getLocation().getPosition().x = 200;
        person.getLocation().getPosition().y = 200;
        getAllObjects().add(person);

        person = new Person("Frank");
        person.getLocation().getPosition().x = 100;
        person.getLocation().getPosition().y = 100;
        getAllObjects().add(person);

        person = new Person("Hilda");
        person.getLocation().getPosition().x = 300;
        person.getLocation().getPosition().y = 300;
        getAllObjects().add(person);

        person = new Person("Gayle");
        person.getLocation().getPosition().x = 400;
        person.getLocation().getPosition().y = 400;
        getAllObjects().add(person);

        setEnvironment(new RoomEnvironment());
    }

    public PlayerImplementation getPlayer() {
        return player;
    }
}
