/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior.text;

import proto.behavior.ATask;
import proto.behavior.Dispatcher;
import proto.behavior.ITask;

/**
 *
 * @author hartsoka
 */
public class DummyWordTask extends ATask {
    
    private String str;

    public DummyWordTask(String statement)
    {
        this.str = statement;
    }

    public void resume() {

    }

    public void run()
    {
        System.out.println(str);
        this.getDispatcher().handleTaskDone(this.bq);
    }

}
