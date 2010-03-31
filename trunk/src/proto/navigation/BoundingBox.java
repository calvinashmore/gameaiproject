/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.navigation;

/**
 * This is an axis aligned bounding box
 * @author Calvin Ashmore
 */
public class BoundingBox extends CollisionVolume {

    private double width;
    private double height;

    public BoundingBox(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }
}
