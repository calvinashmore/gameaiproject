/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.ATask;

/**
 *
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
