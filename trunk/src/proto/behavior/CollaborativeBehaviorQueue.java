/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public class CollaborativeBehaviorQueue
        extends BehaviorQueue
        implements ICollaborativeBehaviorQueue
{
    private CollaborationHandshake handshake;

    private boolean barrier;
    private boolean readyAndWaiting;

    public CollaborativeBehaviorQueue(IBehaviorTemplate behavior, CollaborationHandshake handshake)
    {
        super(behavior);
        this.initialize(handshake);
    }

    public CollaborativeBehaviorQueue(IBehaviorTemplate behavior, int priority, CollaborationHandshake handshake)
    {
        super(behavior, priority);
        this.initialize(handshake);
    }

    private void initialize(CollaborationHandshake handshake)
    {
        this.handshake = handshake;
        this.barrier = false;
        this.readyAndWaiting = false;
    }

    public CollaborationHandshake getHandshake() {
        return handshake;
    }

    public void setBarrier() {
        this.barrier = true;
    }

    public void setSelfReadyAndWaiting() {
        this.readyAndWaiting = true;
    }

    public void endBarrier() {
        this.barrier = false;
        this.readyAndWaiting = false;
    }

    public boolean isBarrierSet() {
        return this.barrier;
    }

    public boolean isSelfReadyAndWaiting() {
        return this.readyAndWaiting;
    }

}
