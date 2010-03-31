/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
        g.rect(10, 10, (float) getWidth()-20, (float) getHeight()-20);
    }
}
