/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.navigation;

/**
 *
 * @author Calvin Ashmore
 */
public class BoundingSphere extends CollisionVolume {
    private double radius;

    public BoundingSphere(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }


}
