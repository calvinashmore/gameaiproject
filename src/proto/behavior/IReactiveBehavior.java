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

    public boolean tryCollaboration(CollaborationHandshake handshake);

}
