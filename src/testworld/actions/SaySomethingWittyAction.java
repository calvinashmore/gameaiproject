/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.actions;

import java.util.Random;
import proto.game.PlayerAction;
import proto.world.Entity;
import testworld.behaviors.MoveToProactiveBehavior;
import testworld.behaviors.SoliloquyBehavior;
import testworld.objects.Person;

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
        int die = new Random().nextInt(3);
        String[] speech = null;
        switch (die) {
            case 0:
                speech = new String[]{"I love doing this.", "Sometimes I don't even take a lunch break."};
                break;
            case 1:
                speech = new String[]{"One of these days..."};
                break;
            case 2:
                speech = new String[]{"This party is quite droll, actually.", "Someone needs to do something scandalous.", "...", "Not me, of course."};
                break;
        }

        ((Person) player).instantiateNewProactiveBehavior(new SoliloquyBehavior(speech));
    }
}
