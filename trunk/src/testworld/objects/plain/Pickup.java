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
public class Pickup extends BasicObject {
    
    public Pickup()
    {
        super();
        this.setRepresentation(new PickupRepresentation(this));
    }

    private class PickupRepresentation extends Representation<Pickup>
    {

        public PickupRepresentation(Pickup target) {
            super(target);
        }

        @Override
        public void render(PGraphics g) {
            Vector2d pos = getLocation().getPosition();

            g.pushMatrix();
            g.translate((float)pos.x, (float)pos.y);
            g.fill(0);
            g.ellipse(0, 0, 20, 20);
            g.popMatrix();
        }

    }

}
