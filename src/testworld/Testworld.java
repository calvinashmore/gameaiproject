/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld;

import main.Main;
import processing.core.PGraphics;
import proto.world.World;
import testworld.objects.GuestPerson;
import testworld.objects.Person;
import testworld.objects.PersonExpression;
import testworld.objects.Pickup;
import testworld.objects.ServerPerson;
import testworld.objects.annotated.DanceFloor;
import testworld.representations.PersonAppearance.Clothes;
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
        person.getLocation().getPosition().x = 200;
        person.getLocation().getPosition().y = 300;
        person.setExpression(PersonExpression.happySmallSmile);
        person.getAppearance().hair = Hair.simpleFullFlat;
        person.getAppearance().clothes = Clothes.tuxedo;
        person.getAppearance().clothesColor1 = 0x00000000;
        person.getAppearance().clothesColor2 = 0x00000000;
        person.getAppearance().clothesColors.add(0x00000000);
        person.getAppearance().clothesColors.add(0xffffffff);
        getAllObjects().add(person);

        person = new GuestPerson("Frank");
        person.getLocation().getPosition().x = 100;
        person.getLocation().getPosition().y = 300;
        person.setExpression(PersonExpression.annoyed);
        person.getAppearance().hair = Hair.blob;
        person.getAppearance().clothes = Clothes.tuxedo;
        person.getAppearance().clothesColor1 = 0x00000000;
        person.getAppearance().clothesColor2 = 0x00000000;
        person.getAppearance().clothesColors.add(0x00000000);
        person.getAppearance().clothesColors.add(0xffffffff);
        getAllObjects().add(person);

        person = new GuestPerson("Hilda");
        person.getLocation().getPosition().x = 300;
        person.getLocation().getPosition().y = 400;
        person.setExpression(PersonExpression.happyExcited);
        person.getAppearance().hair = Hair.longCurls;
        person.getAppearance().width = 20;
        getAllObjects().add(person);

        person = new GuestPerson("Gayle");
        person.getLocation().getPosition().x = 400;
        person.getLocation().getPosition().y = 200;
        person.setExpression(PersonExpression.malicious);
        person.getAppearance().hair = Hair.straightMid;
        getAllObjects().add(person);

        person = new ServerPerson("Fred");
        person.getLocation().getPosition().x = 10;
        person.getLocation().getPosition().y = 10;
        person.setExpression(PersonExpression.feignedInterest);
        person.getAppearance().hair = Hair.simpleFullFlat;
        getAllObjects().add(person);

        Pickup p = new Pickup();
        p.getLocation().getPosition().x = 20;
        p.getLocation().getPosition().y = 200;
        getAllObjects().add(p);

        p = new Pickup();
        p.getLocation().getPosition().x = 300;
        p.getLocation().getPosition().y = 150;
        getAllObjects().add(p);

        DanceFloor d = new DanceFloor(100);
        d.getLocation().getPosition().x = -120;
        d.getLocation().getPosition().y = 200;
        getAllObjects().add(d);

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
