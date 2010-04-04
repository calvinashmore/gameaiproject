/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.ATask;
import proto.behavior.Dispatcher;

/**
 *
 * @author hartsoka
 */
public class WaitTask extends ATask {

    public WaitTask(Dispatcher d)
    {
        super(d);
    }

    public void resume() {
        // nch
    }

    public void run() {
        // TODO
        // check whether collaboration handshake is finished
    }

}
