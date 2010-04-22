/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import testworld.objects.Person;
import testworld.social.Stimuli.Need;

/**
 *
 * @author hartsoka
 */
public class EffectTask extends PersonTask {

    private Need need;
    private double value;

    public EffectTask(Need need, double value) {
        this.need = need;
        this.value = value;
    }

    public void resume() {
        return;
    }

    public void run() {
        Person p = this.getPerson();
        p.getEmotions().getStimuli().setNeed(need, value);

        finished();
    }
}
