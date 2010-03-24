/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 * A Role should contain a set of BehaviorTemplates for an agent, and possibly
 * information for when those are selected/triggerred?
 * @author hartsoka
 */
public interface IRole {

    public BehaviorQueue getProactiveBehavior();
}
