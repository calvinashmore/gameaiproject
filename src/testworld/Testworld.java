/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld;

import java.util.List;
import proto.game.PlayerAction;
import proto.world.BasicObject;
import proto.world.Entity;
import proto.world.World;
import testworld.objects.Person;
import utils.math.Vector2d;

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

    public List<PlayerAction> onClick(int x, int y) {
        Entity clickedOn = null;

        for (BasicObject basicObject : getAllObjects()) {
            if (basicObject instanceof Entity &&
                    basicObject.getRepresentation() != null &&
                    basicObject.getRepresentation().inRange(x, y)) {
                clickedOn = (Entity) basicObject;
            }
        }

        return player.getActions(clickedOn, new Vector2d(x, y));
    }
}
