/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public interface IProactiveBehavior extends IBehaviorTemplate {

    public void updateImportance(IWorldState ws);

    public int getImportance();
}
