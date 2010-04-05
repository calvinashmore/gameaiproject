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
public class AskAboutTheWeather implements PlayerAction {

    private Person other;

    public AskAboutTheWeather(Person other) {
        this.other = other;
    }

    public String getName() {
        return "Ask about the weather";
    }

    public String getDescription() {
        return "Because you couldn't think of anything better to say.";
    }

    public void performAction(Entity player) {
    }
}
