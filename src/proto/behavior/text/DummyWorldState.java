/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior.text;

import proto.behavior.IWorldState;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class DummyWorldState implements IWorldState {
    
    public int rollDie(int max)
    {
        return RandomManager.get().nextInt(max);
    }

}
