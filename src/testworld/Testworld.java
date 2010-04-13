/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld;

import main.Main;
import processing.core.PGraphics;
import proto.world.World;
import testworld.behaviors.GroupChat;
import testworld.objects.Person;
import testworld.objects.PersonExpression;
import testworld.representations.PersonAppearance.Hair;
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
        person.getRole().addBehaviorTemplate(GroupChat.makeReactive());
        person.getLocation().getPosition().x = 200;
        person.getLocation().getPosition().y = 200;
        person.setExpression(PersonExpression.happySmallSmile);
        person.getAppearance().hair = Hair.simpleFullFlat;
        getAllObjects().add(person);

        person = new Person("Frank");
        person.getRole().addBehaviorTemplate(GroupChat.makeProactive());
        person.getLocation().getPosition().x = 100;
        person.getLocation().getPosition().y = 100;
        person.setExpression(PersonExpression.annoyed);
        person.getAppearance().hair = Hair.blob;
        getAllObjects().add(person);

        person = new Person("Hilda");
        person.getRole().addBehaviorTemplate(GroupChat.makeReactive());
        person.getLocation().getPosition().x = 300;
        person.getLocation().getPosition().y = 300;
        person.setExpression(PersonExpression.happyExcited);
        person.getAppearance().hair = Hair.longCurls;
        getAllObjects().add(person);

        person = new Person("Gayle");
        person.getRole().addBehaviorTemplate(GroupChat.makeReactive());
        person.getLocation().getPosition().x = 400;
        person.getLocation().getPosition().y = 400;
        person.setExpression(PersonExpression.malicious);
        person.getAppearance().hair = Hair.straightMid;
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
