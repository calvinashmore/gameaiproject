/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld;

import java.util.ArrayList;
import java.util.List;
import proto.game.PlayerAction;
import proto.game.PlayerHandler;
import proto.world.Entity;
import testworld.actions.AskAboutTheWeather;
import testworld.actions.MockMercilesslyAction;
import testworld.actions.MoveToAction;
import testworld.actions.SaySomethingWittyAction;
import testworld.objects.Person;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class PlayerImplementation extends Person implements PlayerHandler {

    public PlayerImplementation(String name) {
        super(name);
    }

//    private Person player;
//
//    public PlayerImplementation(Person player) {
//        this.player = player;
//    }
    public List<PlayerAction> getActions(Entity entity, Vector2d clickLocation) {
        List<PlayerAction> actions = new ArrayList<PlayerAction>();

        // the player clicked the background, use a moveto action.
        if (entity == null) {
            actions.add(new MoveToAction(clickLocation));
            return actions;
        }

        // the player clicked on the avatar.
        if (entity == this) {
            actions.add(new SaySomethingWittyAction());
            return actions;
        }

        // the player clicked on another person
        if (entity instanceof Person) {
            actions.add(new AskAboutTheWeather((Person) entity));
            actions.add(new MockMercilesslyAction((Person) entity));
            return actions;
        }

        return actions;
    }
}
