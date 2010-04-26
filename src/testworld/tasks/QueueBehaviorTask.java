/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import proto.behavior.IBehaviorTemplate;

/**
 * This task will cause the person to perform some behavior when the current behavior is finished.
 * @author Calvin Ashmore
 */
public class QueueBehaviorTask extends PersonTask {

    private IBehaviorTemplate behavior;

    public QueueBehaviorTask(IBehaviorTemplate behavior) {
        this.behavior = behavior;
    }

    @Override
    protected void runImpl() {

        getPerson().getRole().addBehaviorTemplate(behavior);
        finished();
    }

    public void resume() {
    }
}
