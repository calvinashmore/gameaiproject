/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.Dispatcher;

/**
 *
 * @author hartsoka
 */
public class BQTestMain {

    public static void main(String[] args)
    {
        DummyRole role = new DummyRole();
        Dispatcher d = new Dispatcher(role);

        for (int i = 0; i < 50; ++i)
        {
            d.handleTimer();
        }
    }
}
