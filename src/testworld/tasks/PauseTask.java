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

    private long pause;
    private long expiry;

    public PauseTask() {
        this(new Random().nextInt(300) + 200);
    }

    public PauseTask(long pause) {
        this.pause = pause;
        expiry = System.currentTimeMillis() + pause;
    }

    public void resume() {
    }

    public void run() {
        if (System.currentTimeMillis() >= expiry) {
            finished();
        }
    }
}
