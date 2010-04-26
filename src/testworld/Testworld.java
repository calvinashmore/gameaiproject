/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld;

import main.Main;
import processing.core.PGraphics;
import proto.world.World;
import testworld.game.Cast;
import testworld.game.Plot;
import testworld.objects.Pickup;
import testworld.objects.annotated.Bathroom;
import testworld.objects.annotated.DanceFloor;
import testworld.objects.annotated.ODevours;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class Testworld extends World {

    private PlayerImplementation player;

    public Testworld() {

        Plot.initializePlot();

        player = (PlayerImplementation) Cast.player;

        getAllObjects().add(Cast.player);
        getAllObjects().add(Cast.frank);
        getAllObjects().add(Cast.harriet);
        getAllObjects().add(Cast.fred);
        getAllObjects().add(Cast.gayle);
        getAllObjects().add(Cast.hilda);
        getAllObjects().add(Cast.rose);
        getAllObjects().add(Cast.victim);
        getAllObjects().add(Cast.hughes);

        Pickup p = new Pickup();
        p.getLocation().getPosition().x = 20;
        p.getLocation().getPosition().y = 200;
        getAllObjects().add(p);

        p = new Pickup();
        p.getLocation().getPosition().x = 300;
        p.getLocation().getPosition().y = 50;
        getAllObjects().add(p);

        DanceFloor d = new DanceFloor(100);
        d.getLocation().getPosition().x = -120;
        d.getLocation().getPosition().y = 200;
        getAllObjects().add(d);

        Bathroom b = new Bathroom();
        b.getLocation().getPosition().x = 200;
        b.getLocation().getPosition().y = -90;
        getAllObjects().add(b);

        ODevours v = new ODevours();
        v.getLocation().getPosition().x = 50;
        v.getLocation().getPosition().y = 400;
        getAllObjects().add(v);

        v = new ODevours();
        v.getLocation().getPosition().x = 350;
        v.getLocation().getPosition().y = 390;
        getAllObjects().add(v);

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
