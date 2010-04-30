/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld;

import java.util.ArrayList;
import java.util.List;
import proto.behavior.IProactiveBehavior;
import proto.game.PlayerAction;
import proto.game.PlayerHandler;
import proto.world.DependentAction;
import proto.world.Entity;
import testworld.actions.ComplimentAction;
import testworld.actions.MockMercilesslyAction;
import testworld.actions.MoveToAction;
import testworld.actions.SaySomethingWittyAction;
import testworld.behaviors.ApproachBehavior;
import testworld.behaviors.GroupChat;
import testworld.behaviors.RandomizedMoveTo;
import testworld.behaviors.UseRandomAnnotatedItem;
import testworld.behaviors.cutscene.FrankKillsVictim;
import testworld.objects.GuestPerson;
import testworld.objects.Person;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class PlayerImplementation extends GuestPerson implements PlayerHandler {

    public static PlayerImplementation p1; // hack for easy access to player

    public PlayerImplementation(String name) {
        super(name);
        p1 = this; // hack for easy access to player

        // TODO find a better place to put these
        this.getDispatcher().getRole().addBehaviorTemplate(FrankKillsVictim.makeLatent());

        // prevent player from making toasts or random approaches
        removeBehavior(GroupChat.class);
        removeBehavior(ApproachBehavior.class);
        removeBehavior(RandomizedMoveTo.class);
        removeBehavior(UseRandomAnnotatedItem.class);
    }

    void removeBehavior(Class behaviorClass) {

        IProactiveBehavior behavior = null;
        for (IProactiveBehavior iProactiveBehavior : getDispatcher().getRole().getProactiveBehaviors()) {
            if(behaviorClass == iProactiveBehavior.getClass())
                behavior = iProactiveBehavior;
        }
        // prevent player from making toasts.
        getDispatcher().getRole().getProactiveBehaviors().remove(behavior);
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

        // add actions dependent on the entity
        for (DependentAction dependentAction : entity.getDependentActions()) {
            if (dependentAction.canActivate()) {
                actions.add(dependentAction.createAction(entity));
            }
        }

        // the player clicked on another person
        if (entity instanceof Person) {

            Person person = (Person) entity;
            // see what we can do with this person.

            if (person instanceof GuestPerson) {
                // you can compliment and mock the guests, but not the server
                actions.add(new MockMercilesslyAction(person));
                actions.add(new ComplimentAction(person));
            }
        }

        return actions;
    }
}
