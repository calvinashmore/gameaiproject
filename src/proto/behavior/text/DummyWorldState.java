/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
