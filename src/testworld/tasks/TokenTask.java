/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import proto.behavior.InstantaneousTask;
import testworld.game.Token;

/**
 * this task marks the given token as found.
 * @author Calvin Ashmore
 */
public class TokenTask extends PersonTask implements InstantaneousTask {

    private Token token;

    public TokenTask(Token token) {
        this.token = token;
    }

    public void resume() {
    }

    public void runImpl() {
        // mark the token as found and then finish the task.
        token.setFound(true);
        finished();
    }
}
