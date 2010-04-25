/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.tasks;

import java.util.Map;
import testworld.objects.Person;
import testworld.social.Emotions;
import testworld.social.Relationship;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class ResponseTask extends PersonTask
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

        Emotions e = this.getPerson().getEmotions();
        Relationship r = e.getRelationship(respondee);

        e.addTemporaryAttribute("sincerity", RandomManager.get().nextDouble() * 100);
        e.addTemporaryMap(r);

        Map<String, Double> results =
                this.getPerson().getEmotions().evaluateFuzzy_PersonalityStimuliAndRelationship(fuzzyFn, respondee);

        e.applyDeltas(results);

        /*
        if (results.containsKey("d_affection")) {
            r.setAttribute(Relationship.RelationshipStat.affection, r.getAttribute(Relationship.RelationshipStat.affection.toString()) + results.get("d_affection"));
        }

        if (results.containsKey("d_euphoria")) {
            Stimuli s = this.getPerson().getEmotions().getStimuli();
            s.setEffect(Stimuli.Effect.euphoria, s.getAttribute(Stimuli.Effect.euphoria.toString()) + results.get("d_euphoria"));
        }

        if (results.containsKey("d_irritation")) {
            Stimuli s = this.getPerson().getEmotions().getStimuli();
            s.setEffect(Stimuli.Effect.irritation, s.getAttribute(Stimuli.Effect.irritation.toString()) + results.get("d_irritation"));
        }
        
        if (results.containsKey("d_anxiety")) {
            Stimuli s = this.getPerson().getEmotions().getStimuli();
            s.setEffect(Stimuli.Effect.anxiety, s.getAttribute(Stimuli.Effect.anxiety.toString()) + results.get("d_anxiety"));
        }
         */

        e.clearTemporaryMaps();

        finished();
    }

}
