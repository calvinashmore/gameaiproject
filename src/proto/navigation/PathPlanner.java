/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.navigation;

import proto.world.BasicObject;
import proto.world.World;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class PathPlanner {

    private World world;

    public PathPlanner(World world) {
        this.world = world;
    }

    /**
     * This returns a vector representing the best direction to take to get the given object to the listed destination.
     * The object should have a spherical collision volume.
     * @param object
     * @param destination
     * @return
     */
    public Vector2d getDirection(BasicObject object, Vector2d destination) {

        double objectRadius = 0;

        // if the object has a sperical collision volume, use that radius as the collision radius.
        CollisionVolume objectCollisionVolume = object.getCollisionVolume();
        if (objectCollisionVolume instanceof BoundingSphere) {
            objectRadius = ((BoundingSphere) objectCollisionVolume).getRadius();
        }

        Vector2d direction = destination.subtract(object.getLocation().getPosition()).getNormalizedVector();

        // go through each object in the world and avoid it.
        for (BasicObject basicObject : world.getAllObjects()) {
            if (basicObject == object) {
                continue;
            }

            CollisionVolume toAvoid = basicObject.getCollisionVolume();
            if (toAvoid == null) {
                continue;
            }

            Vector2d influence = getInfluence(object.getLocation().getPosition(), objectRadius, toAvoid, basicObject.getLocation().getPosition());

            // multiply by a number to increase the repelling force
            influence = influence.multiply(1);
            direction = direction.add(influence);
        }

        return direction.getNormalizedVector();
    }

    /**
     * Get the repelling influence of a collision volume at the given location.
     * @param objectPosition
     * @param objectRadius 
     * @param obstale 
     * @param obstaclePosition 
     * @return
     */
    private Vector2d getInfluence(Vector2d objectPosition, double objectRadius, CollisionVolume obstacle, Vector2d obstaclePosition) {

        double minDistance;
        Vector2d difference = objectPosition.subtract(obstaclePosition);
        double distance = difference.magnitude();

        minDistance = objectRadius;

        if (obstacle instanceof BoundingSphere) {
            minDistance += ((BoundingSphere) obstacle).getRadius();
        } else if (obstacle instanceof BoundingBox) {
            BoundingBox bbox = (BoundingBox) obstacle;
//            double xdist = objectPosition.x - obstaclePosition.x;
//            double ydist = objectPosition.y - obstaclePosition.y;
            // just use circular calculation for now
            minDistance += Math.sqrt(bbox.getWidth() * bbox.getWidth() + bbox.getHeight() + bbox.getHeight()) / 2;
        }

        double maxDistance = 3 * minDistance;

        // if the distance is greater than the max effect distance, return 0
        if (distance > maxDistance) {
            return new Vector2d();
        }

        // minDistance should be less than distance, so t should be > 0
        double t = 1 - (distance - minDistance) / (maxDistance - minDistance);

        // give the force a curve.
        t = Math.pow(t, 2);

        // repulsion force.
        Vector2d v = difference.getNormalizedVector().multiply(t);
        return v;
    }
}
