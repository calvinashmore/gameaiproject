/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld;

import testworld.objects.Person;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class TestPathplanner {

    public static void main(String args[]) {


        Testworld world = new Testworld();


        Person person = new Person("Bill");
        person.getLocation().getPosition().x = 200;
        person.getLocation().getPosition().y = 110;
        world.getAllObjects().add(person);


        person = new Person("Gurgle");
        person.getLocation().getPosition().x = 230;
        person.getLocation().getPosition().y = 90;
        world.getAllObjects().add(person);

        System.out.println(world.getPathPlanner().getDirection(person, new Vector2d(100, 100)));
    }
}
