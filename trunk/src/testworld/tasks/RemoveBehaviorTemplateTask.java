/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import proto.behavior.IBehaviorTemplate;
import proto.behavior.InstantaneousTask;

/**
 *
 * @author Calvin Ashmore
 */
public class RemoveBehaviorTemplateTask extends PersonTask implements InstantaneousTask {

    IBehaviorTemplate template;

    public RemoveBehaviorTemplateTask(IBehaviorTemplate template) {
        this.template = template;
    }


    @Override
    protected void runImpl() {
        getPerson().getRole().removeBehaviorTemplate(template);
        finished();
    }

    public void resume() {
    }
}
