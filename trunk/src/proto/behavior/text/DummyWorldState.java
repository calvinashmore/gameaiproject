/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import java.util.Random;
import proto.behavior.IWorldState;

/**
 *
 * @author hartsoka
 */
public class DummyWorldState implements IWorldState {

    public Random r;
    
    public DummyWorldState()
    {
        r = new Random();
    }
    
    public int rollDie(int max)
    {
        return r.nextInt(max);
    }

}
