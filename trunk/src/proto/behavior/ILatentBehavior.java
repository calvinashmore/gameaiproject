/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public interface ILatentBehavior extends IBehaviorTemplate {

    public boolean activate(IWorldState iws);
}