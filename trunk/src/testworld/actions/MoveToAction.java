/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.actions;

import proto.game.PlayerAction;
import proto.world.Entity;
import testworld.objects.Person;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class MoveToAction implements PlayerAction {

    private Vector2d destination;

    public MoveToAction(Vector2d destination) {
        this.destination = destination;
    }

    public String getName() {
        return "Move To";
    }

    public String getDescription() {
        return null;
    }

    public void performAction(Entity player) {
        ((Person) player).forceMoveTo(destination);
    }
}
