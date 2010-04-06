/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

import java.util.List;

/**
 * A Role should contain a set of BehaviorTemplates for an agent, and possibly
 * information for when those are selected/triggerred?
 * @author hartsoka
 */
public interface IRole {

    public IBehaviorQueue instantiateProactiveBehavior(IWorldState ws);

    public List<IProactiveBehavior> getProactiveBehaviors();

    public List<ILatentBehavior> getLatentBehaviors();

    public List<IReactiveBehavior> getReactiveBehaviors();

    /**
     * Sets the Dispatcher which contains this role - in general, should only be
     * called by dispatcher implementations when this role is set to them.
     * @param d
     */
    public void setOwningDispatcher(Dispatcher d);

    /**
     * Gets the Dispatcher which contains the role.
     * @return Owning dispatcher.
     */
    public Dispatcher getOwningDispatcher();
}
