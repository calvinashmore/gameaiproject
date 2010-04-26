/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects.plain;

import processing.core.PGraphics;
import proto.representation.Representation;
import proto.world.BasicObject;
import utils.math.Vector2d;

/**
 *
 * @author hartsoka
 */
public class Balcony extends BasicObject {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 500;

    public Balcony()
    {
        super();
        this.setRepresentation(new BalconyRepresentation(this));
    }

    public float getSize()
    {
        return Math.min(WIDTH, HEIGHT);
    }

    private class BalconyRepresentation extends Representation<Balcony>
    {

        public BalconyRepresentation(Balcony target) {
            super(target);
        }

        @Override
        public void render(PGraphics g) {

            g.pushMatrix();

            g.translate((float)getTarget().getLocation().getPosition().x,
                        (float)getTarget().getLocation().getPosition().y);

            g.stroke(0);
            g.strokeWeight(3);
            g.fill(0x22ff9999);
            g.rect(-WIDTH/2, -HEIGHT/2, WIDTH, HEIGHT);

            g.strokeWeight(1);
            g.popMatrix();
        }

    }


}
