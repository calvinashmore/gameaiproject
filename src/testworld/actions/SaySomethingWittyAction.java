/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.actions;

import proto.game.PlayerAction;
import proto.world.Entity;

/**
 *
 * @author Calvin Ashmore
 */
public class SaySomethingWittyAction implements PlayerAction {

    public String getName() {
        return "Say something witty";
    }

    public String getDescription() {
        return "Talking to yourself is the only way you can be assured of decent conversation.";
    }

    public void performAction(Entity player) {
    }
}
