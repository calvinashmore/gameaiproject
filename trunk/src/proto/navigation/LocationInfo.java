/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.navigation;

import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class LocationInfo {

    private Vector2d position;
    private double orientation;

    public LocationInfo() {
        this.position = new Vector2d();
        this.orientation = 0;
    }

    public LocationInfo(Vector2d position, double orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    public void setPosition(Vector2d position) {
        this.position = position;
    }
}
