/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.objects;

import java.util.Map;
import java.util.Map.Entry;
import proto.behavior.Dispatcher;
import testworld.social.Stimuli.Effect;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonDispatcher extends Dispatcher {

    private Person person;

    public Person getPerson() {
        return person;
    }

    void setPerson(Person person) {
        this.person = person;
    }

    public PersonDispatcher() {
        super(new PersonRole());
    }

    public PersonDispatcher(PersonRole role) {
        super(role);
    }

    @Override
    public String toString() {
        return person.getName();
    }

    @Override
    public void handleTimer() {
        this.getPerson().getEmotions().getStimuli().update();
        Map<String,Double> deltas =
            this.getPerson().getEmotions().evaluateFuzzy_PersonalityAndStimuli("update_internals_from_needs");
        this.getPerson().getEmotions().getStimuli().setEffect(
                Effect.irritation, this.getPerson().getEmotions().getStimuli().getAttribute(Effect.irritation.toString()) + deltas.get("d_irritation")/10);
        super.handleTimer();
    }
}
