/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public interface ICollaborativeBehavior extends IBehaviorTemplate {

    /**
     * Instantiate a behavior which the agent agreed to join.
     * @param title Title the agent registered in the handshake.
     * @param handshake Handshake of collaboration which the agent joined.
     * @return BehaviorQueues for the collaboration, based upon the handshake.
     */
    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake);

}
