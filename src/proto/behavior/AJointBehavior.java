/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import java.util.List;
import proto.behavior.text.DummyDispatcherManager;

/**
 *
 * @author hartsoka
 */
public abstract class AJointBehavior
        extends ABehaviorTemplate
        implements ICollaborativeBehavior, IReactiveBehavior
{

    public AJointBehavior(InitiationType initType) {
        super(initType, CollaborationType.collaborative);
    }

    public IBehaviorQueue instantiate(IWorldState ws)
    {
        Dispatcher thisDispatcher = this.getDispatcher();

        // Behavior creates a handshake (fill in priority and initiator's title)
        CollaborationHandshake handshake = makeHandshake(ws);

        // Behavior supplies a sorted list of collaborators it wants to extend
        //  the handshake to
        List<Dispatcher> potentials = getPotentialCollaborators();

        // Extend handshake to the potential collaborators; skip ourself in case
        //  the behavior accidentally included it
        for (Dispatcher d : potentials)
        {
            if (d != thisDispatcher)
            {
                d.offerCollaboration(handshake);
            }
        }

        // Behavior confirms that the handshake was successful
        if (confirmHandshake(handshake))
        {
            handshake.completeHandshake();
            return handshake.getQueue(thisDispatcher);
        }

        return null;
    }

    /**
     * Create a handshake to offer other collaborators.
     * @param ws World state context in which the handshake is being made.
     * @return A handshake, with the agent having added itself.
     */
    public abstract CollaborationHandshake makeHandshake(IWorldState ws);

    /**
     * Create a list of collaborators to whom the handshake will be extended,
     * in order of first priority to last priority. (If the agent includes
     * itself in this list, it will be skipped).
     * @return A list of agents to whom the handshake will be extended, in order
     * of first priority to last priority.
     */
    public abstract List<Dispatcher> getPotentialCollaborators();

    /**
     * Validate that the handshake was successful.
     * @param handshake Handshake to validate.
     * @return True if the behavior can be carried out, false otherwise.
     */
    public abstract boolean confirmHandshake(CollaborationHandshake handshake);
}
