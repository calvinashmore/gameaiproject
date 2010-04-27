/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import proto.behavior.InstantaneousTask;
import testworld.social.AttributeMap;
import testworld.social.SocialState;

/**
 *
 * @author hartsoka
 */
public class EffectTask extends PersonTask implements InstantaneousTask {

    private String name;
    private double value;

    private AttributeMap.Operation operation;

    public EffectTask(String name, double value,  AttributeMap.Operation op)
    {
        this.name = name;
        this.value = value;
        this.operation = op;
    }

    public void resume() {
        return;
    }

    public void runImpl() {

        SocialState e = this.getPerson().getSocialState();

        e.changeAttribute(name, value, operation);

        finished();
    }
}
