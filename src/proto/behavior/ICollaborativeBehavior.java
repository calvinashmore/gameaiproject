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

    public ICollaborativeBehaviorQueue completeHandshake(CollaborationHandshake handshake);

}
