/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
    private CollisionVolume cv;
}
