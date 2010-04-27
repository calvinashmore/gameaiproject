/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.tasks;

import java.util.Map;
import proto.behavior.InstantaneousTask;
import testworld.objects.Person;
import testworld.social.SocialState;
import testworld.social.Relationship;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class ResponseTask extends PersonTask implements InstantaneousTask
{
    protected String fuzzyFn;
    protected Person respondee;

    public ResponseTask(String fuzzyFn, Person respondee)
    {
        super();
        this.fuzzyFn = fuzzyFn;
        this.respondee = respondee;
    }

    public void resume() {
        return;
    }

    public void runImpl() {

        SocialState e = this.getPerson().getSocialState();
        Relationship r = e.getRelationship(respondee);

        e.addTemporaryAttribute("sincerity", RandomManager.get().nextDouble() * 100);
        e.addTemporaryMap(r);

        Map<String, Double> results =
                this.getPerson().getSocialState().evaluateFuzzy(fuzzyFn);

        e.applyDeltas(results);

        e.clearTemporaryMaps();

        finished();
    }

}
