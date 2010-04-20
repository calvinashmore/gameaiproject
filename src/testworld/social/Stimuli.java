/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

import java.util.Map;
import java.util.TreeMap;

/**
 * Dynamic character attributes and how quickly they change.
 * @author hartsoka
 */
public class Stimuli implements AttributeMap
{
    // Effects are increased by external stimuli, and decay naturally over time
    // The rate at which external stimuli alter them comes from the effect
    //  rates inside a personality
    public enum Effect
    {
        euphoria,       // happiness
        anxiety,        // fear, sadness
        irritation,     // anger, annoyance

        depressant,
        stimulant,
    }

    // Needs are increased naturally over time, and decreased by external stimuli
    // The rate at which needs increase naturally comes from the need rates
    //  inside a personality
    public enum Need
    {
        sexdrive,
        alcohol,
        cigarette,
        cocaine,
        food,
        beverage,
        toilet,
        sleep,
        gossip
    }

    protected static final double MAX_VALUE = 100;
    protected static final double MIN_VALUE = 0;

    protected static final double DEFAULT_EFFECTS_AMT = 0.0;
    protected static final double DEFAULT_NEEDS_AMT = 0.0;

    protected static final double DEFAULT_EFFECTS_RATE = 0.5;
    protected static final double DEFAULT_NEEDS_RATE = 0.5;

    protected Map<String, Double> effects = new TreeMap<String, Double>();
    protected Map<String, Double> needs = new TreeMap<String, Double>();

    protected Map<String, Double> effectsRates = new TreeMap<String, Double>();
    protected Map<String, Double> needsRates = new TreeMap<String, Double>();

    protected long lastUpdate = 0;

    public Stimuli()
    {
        for (Effect e : Effect.values())
        {
            effects.put(e.toString(), DEFAULT_EFFECTS_AMT);
        }
        for (Need n : Need.values())
        {
            needs.put(n.toString(), DEFAULT_NEEDS_AMT);
        }

        for (Effect e : Effect.values())
        {
            effectsRates.put(e.toString(), DEFAULT_EFFECTS_RATE);
        }
        for (Need n : Need.values())
        {
            needsRates.put(n.toString(), DEFAULT_NEEDS_RATE);
        }
    }

    /**
     * Gets an attribute value regardless of where in the Stimuli it is located.
     * @param name Name of the attribute - if it is a rate, it should end in "Rate"
     * @return Value of the attribute, or null if it is not recognized
     */
    public Double getAttribute(String name)
    {
        if(!name.endsWith("Rate"))
        {
            Double d = effects.get(name);
            if (d == null)
            {
                d = needs.get(name);
            }
            return d;
        }
        else
        {
            name = name.replace("Rate", "");
            Double d = effectsRates.get(name);
            if (d == null)
            {
                d = needsRates.get(name);
            }
            return d;
        }
    }

    public void setEffect(Effect effect, double value)
    {
        effects.put(effect.toString(), value);
    }

    public void setEffectRate(Effect effect, double value)
    {
        effectsRates.put(effect.toString(), value);
    }

    public void setNeed(Need need, double value)
    {
        needs.put(need.toString(), value);
    }

    public void setNeedRate(Need need, double value)
    {
        needsRates.put(need.toString(), value);
    }

    public void update()
    {
        long time = System.currentTimeMillis();
        if (time - lastUpdate > 1000)
        {
            lastUpdate = time;
            updateImpl();
        }
    }

    protected void updateImpl()
    {
        for (Effect e : Effect.values())
        {
            String s = e.toString();
            double newValue = effects.get(s) - effectsRates.get(s);
            newValue = Math.min(MAX_VALUE,
                                Math.max(MIN_VALUE, newValue));
            effects.put(s, newValue);
        }
        for (Need n : Need.values())
        {
            String s = n.toString();
            double newValue = needs.get(s) + needsRates.get(s);
            newValue = Math.min(MAX_VALUE,
                                Math.max(MIN_VALUE, newValue));
            needs.put(s, newValue);
        }
    }
}
