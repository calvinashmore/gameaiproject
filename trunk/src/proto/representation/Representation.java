/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.representation;

import processing.core.PGraphics;
import proto.world.BasicObject;

/**
 *
 * @author Calvin Ashmore
 */
public abstract class Representation<T extends BasicObject> {

    private T target;

    public Representation(T target) {
        this.target = target;
    }

    public T getTarget() {
        return target;
    }

    abstract public void render(PGraphics g);

    /**
     * Returns true if the given 2d coordinate overlaps with this representation.
     * @param x
     * @param y
     * @return
     */
    public boolean inRange(float x, float y) {
        return false;
    }
}
