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

    protected static final double DEFAULT_MAX_VALUE = 100;
    protected static final double DEFAULT_MIN_VALUE = 0;

    protected static final double DEFAULT_EFFECTS_AMT = 0.0;
    protected static final double DEFAULT_NEEDS_AMT = 0.0;

    protected static final double DEFAULT_EFFECTS_RATE = 0.5;
    protected static final double DEFAULT_NEEDS_RATE = 0.5;

    protected Map<String, Double> effects = new TreeMap<String, Double>();
    protected Map<String, Double> needs = new TreeMap<String, Double>();

    protected Map<String, Double> effectsRates = new TreeMap<String, Double>();
    protected Map<String, Double> needsRates = new TreeMap<String, Double>();

    protected long lastUpdate = 0;

    static
    {
        AttributeInfo info = AttributeInfo.getInstance();
        for (Effect e : Effect.values())
        {
            info.minimums.put(e.toString(), DEFAULT_MIN_VALUE);
            info.maximums.put(e.toString(), DEFAULT_MAX_VALUE);
            info.defaults.put(e.toString(), DEFAULT_EFFECTS_AMT);

            info.defaults.put(e.toString() + "Rate", DEFAULT_EFFECTS_RATE);
        }
        for (Need n : Need.values())
        {
            info.minimums.put(n.toString(), DEFAULT_MIN_VALUE);
            info.maximums.put(n.toString(), DEFAULT_MAX_VALUE);
            info.defaults.put(n.toString(), DEFAULT_NEEDS_AMT);

            info.defaults.put(n.toString() + "Rate", DEFAULT_NEEDS_RATE);
        }
        
        // customized defaults
        info.defaults.put(Need.alcohol.toString() + "Rate", 0.0);
        info.defaults.put(Need.cocaine.toString() + "Rate", 0.0);
        info.defaults.put(Need.toilet.toString() + "Rate", 0.0);
        info.defaults.put(Need.cigarette.toString() + "Rate", 0.0);
    }

    public Stimuli()
    {
        AttributeInfo info = AttributeInfo.getInstance();
        for (Effect e : Effect.values())
        {
            effects.put(e.toString(), info.defaults.get(e.toString()));
            effectsRates.put(e.toString(), info.defaults.get(e.toString() + "Rate"));
        }
        for (Need n : Need.values())
        {
            needs.put(n.toString(), info.defaults.get(n.toString()));
            needsRates.put(n.toString(), info.defaults.get(n.toString() + "Rate"));
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
        String key = effect.toString();
        AttributeInfo info = AttributeInfo.getInstance();
        if (info.maximums.get(key) < value) value = info.maximums.get(key);
        if (info.minimums.get(key) > value) value = info.minimums.get(key);
        effects.put(key, value);
    }

    public void setEffectRate(Effect effect, double value)
    {
        effectsRates.put(effect.toString(), value);
    }

    public void setNeed(Need need, double value)
    {
        String key = need.toString();
        AttributeInfo info = AttributeInfo.getInstance();
        if (info.maximums.get(key) < value) value = info.maximums.get(key);
        if (info.minimums.get(key) > value) value = info.minimums.get(key);
        needs.put(key, value);
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
            this.setEffect(e, newValue);
        }
        for (Need n : Need.values())
        {
            String s = n.toString();
            double newValue = needs.get(s) + needsRates.get(s);
            this.setNeed(n, newValue);
        }
    }
}
