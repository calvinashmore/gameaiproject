/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.game;

import proto.world.Entity;

/**
 *
 * @author Calvin Ashmore
 */
public interface PlayerAction {

    public String getName();
    public String getDescription();
    public void performAction(Entity player);
}
