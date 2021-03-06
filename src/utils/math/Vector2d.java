/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package utils.math;

/**
 *
 * @author Calvin Ashmore
 */
public class Vector2d {

    public double x;
    public double y;

    public Vector2d() {
        this(0, 0);
    }

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2d v) {
        this.x = v.x;
        this.y = v.y;
    }

    public Vector2d add(Vector2d v) {
        return new Vector2d(x + v.x, y + v.y);
    }

    public Vector2d subtract(Vector2d v) {
        return new Vector2d(x - v.x, y - v.y);
    }

    public Vector2d multiply(double c) {
        return new Vector2d(x * c, y * c);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector2d getNormalizedVector() {
        return multiply(1.0 / magnitude());
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public double dot(Vector2d v) {
        return x * v.x + y * v.y;
    }

    public double distance(Vector2d other) {
        return this.subtract(other).magnitude();
    }
}
