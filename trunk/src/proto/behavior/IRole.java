/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import java.util.List;

/**
 * A Role should contain a set of BehaviorTemplates for an agent, and possibly
 * information for when those are selected/triggerred?
 * @author hartsoka
 */
public interface IRole {

    public BehaviorQueue instantiateProactiveBehavior(IWorldState ws, Dispatcher d);

    public List<IProactiveBehavior> getProactiveBehaviors();

    public List<ILatentBehavior> getLatentBehaviors();

    public BehaviorQueue getReactiveBehavior(String id, CollaborationHandshake handshake);
}
