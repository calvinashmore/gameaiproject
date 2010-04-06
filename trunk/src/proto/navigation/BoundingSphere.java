/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
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
