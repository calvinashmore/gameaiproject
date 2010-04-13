/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public interface IReactiveBehavior extends ICollaborativeBehavior {
    // all reactive behaviors are collaborative

    /**
     * Determines whether this behavior knows how to react to another
     * behavior template, based on its id.
     * @param id Id of the initiating collaborative behavior.
     * @return True if this behavior understands the initiating behavior, false otherwise.
     */
    public boolean canCollaborate(String id);

    /**
     * Provide the agent a chance to join a particular collaboration.
     * @param handshake Handshake to which the agent can add itself.
     * @return True if the agent joined the collaboration, false otherwise.
     */
    public boolean tryCollaboration(CollaborationHandshake handshake);

}
