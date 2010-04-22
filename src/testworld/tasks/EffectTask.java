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
public class EffectTask extends PersonTask
{

    public void resume() {
        return;
    }

    public void run() {
        Person p = this.getPerson();
        p.getEmotions().getStimuli().setNeed(Need.toilet, 0);

        finished();
    }

}
