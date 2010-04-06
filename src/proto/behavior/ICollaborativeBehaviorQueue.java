/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public interface ICollaborativeBehaviorQueue extends IBehaviorQueue {

    public void setBarrier();
    public void setSelfReadyAndWaiting();

    public void endBarrier();

    public boolean isBarrierSet();
    public boolean isSelfReadyAndWaiting();

    public CollaborationHandshake getHandshake();
}
