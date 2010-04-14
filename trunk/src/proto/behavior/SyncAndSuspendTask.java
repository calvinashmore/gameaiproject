/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 * Extension of SyncTask which immediately suspends the behavior.  This is
 * useful if a collaborating character will be doing something independently
 * and you don't want another character to timeout before picking up another
 * behavior.
 * @author hartsoka
 */
public class SyncAndSuspendTask extends SyncTask {

    @Override
    public void run() {
        this.getOwningBehaviorQueue().suspend();
        super.run();
    }

}
