/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import java.util.Random;

/**
 *
 * @author Calvin Ashmore
 */
public class PauseTask extends PersonTask {

    private long expiry;

    public PauseTask() {
        this(new Random().nextInt(400) + 300);
    }

    public PauseTask(long pause) {
        expiry = System.currentTimeMillis() + pause;
    }

    public void resume() {
    }

    public void runImpl() {
        if (System.currentTimeMillis() >= expiry) {
            finished();
        }
    }
}
