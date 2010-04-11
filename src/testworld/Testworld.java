/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld;

import main.Main;
import processing.core.PGraphics;
import proto.world.World;
import testworld.objects.Person;
import testworld.objects.PersonExpression;
import utils.math.Vector2d;

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
        person.setExpression(PersonExpression.happySmallSmile);
        getAllObjects().add(person);

        person = new Person("Frank");
        person.getLocation().getPosition().x = 100;
        person.getLocation().getPosition().y = 100;
        person.setExpression(PersonExpression.annoyed);
        getAllObjects().add(person);

        person = new Person("Hilda");
        person.getLocation().getPosition().x = 300;
        person.getLocation().getPosition().y = 300;
        person.setExpression(PersonExpression.happyExcited);
        getAllObjects().add(person);

        person = new Person("Gayle");
        person.getLocation().getPosition().x = 400;
        person.getLocation().getPosition().y = 400;
        person.setExpression(PersonExpression.malicious);
        getAllObjects().add(person);

        setEnvironment(new RoomEnvironment());
    }

    @Override
    public void render(PGraphics g) {

        g.pushMatrix();

        Vector2d transform = transformPoint(0, 0);
        g.translate((float) -transform.x, (float) -transform.y);

        super.render(g);

        g.popMatrix();
    }

    public PlayerImplementation getPlayer() {
        return player;
    }

    @Override
    public Vector2d transformPoint(float x, float y) {
        Vector2d playerPosition = player.getLocation().getPosition();
        return new Vector2d(
                x + playerPosition.x - Main.getInstance().getWidth() / 2,
                y + playerPosition.y - Main.getInstance().getHeight() / 2);
    }
}
