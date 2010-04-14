/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 * Task to be used by collaborative behaviors as barriers.  SyncTasks set a
 * barrier flag in the CollaborativeBehaviorQueue which prevents the behavior
 * from running until all characters in the collaboration have reached the
 * barrier.  Thus, all characters in a collaboration must have the same number
 * of SyncTasks.
 * @author hartsoka
 */
public class SyncTask extends ATask {

    public void resume() {
        
    }

    public void run() {
        this.getCollaborativeBehaviorQueue().setBarrier();
        this.getDispatcher().handleTaskDone(this.getOwningBehaviorQueue());
    }

}
