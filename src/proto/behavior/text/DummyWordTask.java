/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior.text;

import proto.behavior.ATask;

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
