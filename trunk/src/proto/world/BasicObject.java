/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package proto.world;

import proto.navigation.CollisionVolume;
import proto.navigation.LocationInfo;
import proto.representation.Representation;

/**
 * A static object which may exist in the environment. These can be used for collision, but they are not
 * @author Calvin Ashmore
 */
public class BasicObject {

    private Representation representation;
    private LocationInfo location;
    private CollisionVolume collisionVolume;

    public BasicObject() {
        location = new LocationInfo();
    }

    /**
     * The returned collision volume MAY be null. If this is the case, then the object does not collide with others.
     * @return
     */
    public CollisionVolume getCollisionVolume() {
        return collisionVolume;
    }

    public void setCollisionVolume(CollisionVolume collisionVolume) {
        this.collisionVolume = collisionVolume;
    }

    public LocationInfo getLocation() {
        return location;
    }

    public void setLocation(LocationInfo location) {
        this.location = location;
    }

    public Representation getRepresentation() {
        return representation;
    }

    public void setRepresentation(Representation representation) {
        this.representation = representation;
    }

    public boolean isBackground() {return false;}
}
