/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package proto.world;

import processing.core.PGraphics;

/**
 * This represents the physical environment that entities live in.
 * Should contain pathing and navigation information
 * @author Calvin Ashmore
 */
public abstract class Environment {

    private double width;
    private double height;

    public Environment(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    abstract public void render(PGraphics g);
}
