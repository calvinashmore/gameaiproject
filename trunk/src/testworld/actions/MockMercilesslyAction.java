/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.actions;

import proto.game.PlayerAction;
import proto.world.Entity;
import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
public class MockMercilesslyAction implements PlayerAction {

    private Person other;

    public MockMercilesslyAction(Person other) {
        this.other = other;
    }

    public String getName() {
        return "Mock " + other.getName() + " mercilessly, with wanton disrespect.";
    }

    public String getDescription() {
        return "Admittedly, " + other.getName() + " had it coming.";
    }

    public void performAction(Entity player) {
    }
}
