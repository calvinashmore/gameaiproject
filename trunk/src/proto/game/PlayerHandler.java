/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.game;

import java.util.List;
import proto.world.Entity;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public interface PlayerHandler {

    /**
     * Returns a list of actions that the player can perform on or with the given entity.
     * If null is passed, then the player has clicked on the background.
     * @param object
     * @return
     */
    public List<PlayerAction> getActions(Entity entity, Vector2d clickLocation);
}
