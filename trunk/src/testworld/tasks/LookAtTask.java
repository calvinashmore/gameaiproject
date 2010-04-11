/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class LookAtTask extends PersonTask {

    private Vector2d lookAt;

    public LookAtTask(Vector2d lookAt) {
        this.lookAt = lookAt;
    }

    public void resume() {
    }

    public void run() {
        getPerson().setLookAt(lookAt);
        finished();
    }
}
