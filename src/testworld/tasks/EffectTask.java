/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import testworld.objects.Person;
import testworld.social.Stimuli;
import testworld.social.Stimuli.Effect;
import testworld.social.Stimuli.Need;

/**
 *
 * @author hartsoka
 */
public class EffectTask extends PersonTask {

    private enum EffectType { Effect, Need }
    public enum Operation { Set, Add, Subtract }

    private Effect effect;
    private Need need;
    private EffectType effectType;
    private double value;

    private Operation operation;

    public EffectTask(Effect effect, double value, Operation op) {
        this.effect = effect;
        this.value = value;
        this.effectType = EffectType.Effect;

        this.operation = op;
    }

    public EffectTask(Need need, double value, Operation op) {
        this.need = need;
        this.value = value;
        this.effectType = EffectType.Need;

        this.operation = op;
    }

    public void resume() {
        return;
    }

    public void run() {
        Person p = this.getPerson();
        Stimuli s = p.getEmotions().getStimuli();

        double newValue;
        

        switch (effectType)
        {
        case Effect:
            switch (operation)
            {
                case Add:
                    newValue = s.getAttribute(effect.toString()) + value;
                    break;
                case Subtract:
                    newValue = s.getAttribute(effect.toString()) - value;
                    break;
                case Set:
                default:
                    newValue = value;
                    break;
            }
            s.setEffect(effect, newValue);
            break;
        case Need:
            switch (operation)
            {
                case Add:
                    newValue = s.getAttribute(need.toString()) + value;
                    break;
                case Subtract:
                    newValue = s.getAttribute(need.toString()) - value;
                    break;
                case Set:
                default:
                    newValue = value;
                    break;
            }
            s.setNeed(need, newValue);
            break;
        }

        finished();
    }
}
