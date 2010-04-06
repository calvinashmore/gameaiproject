/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public interface IProactiveBehavior extends IBehaviorTemplate {

    public int getImportance(IWorldState ws);
}
