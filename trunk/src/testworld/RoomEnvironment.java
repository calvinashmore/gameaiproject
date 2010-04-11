/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld;

import processing.core.PGraphics;
import proto.world.Environment;

/**
 *
 * @author Calvin Ashmore
 */
public class RoomEnvironment extends Environment {

    public RoomEnvironment() {
        super(500, 500);
    }

    @Override
    public void render(PGraphics g) {

        g.background(200f);
        g.fill(230f);
        g.stroke(0);
        g.rect(0, 0, (float) getWidth(), (float) getHeight());
    }
}
