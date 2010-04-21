/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

import java.util.Map;
import java.util.TreeMap;
import testworld.social.Stimuli.Effect;
import testworld.social.Stimuli.Need;

/**
 * Static character attributes.
 * @author hartsoka
 */
public class Personality implements AttributeMap
{
    protected static final double DEFAULT_MIN = -100;
    protected static final double DEFAULT_MAX = 100;
    protected static final double DEFAULT_VALUE = 0;

    protected Map<String, Double> traits;

    public enum Trait
    {
        morality,
        confidence,
        arrogance,
    }

    static
    {
        AttributeInfo info = AttributeInfo.getInstance();
        for (Trait t : Trait.values())
        {
            info.maximums.put(t.toString(), DEFAULT_MAX);
            info.minimums.put(t.toString(), DEFAULT_MIN);
            info.defaults.put(t.toString(), DEFAULT_VALUE);
        }
    }

    public Personality()
    {
        AttributeInfo info = AttributeInfo.getInstance();

        traits = new TreeMap<String, Double>();
        for (Trait t : Trait.values())
        {
            traits.put(t.toString(), info.defaults.get(t.toString()));
        }
    }

    public void setTrait(Trait trait, double value)
    {
        String key = trait.toString();
        AttributeInfo info = AttributeInfo.getInstance();
        if (info.maximums.get(key) < value) value = info.maximums.get(key);
        if (info.minimums.get(key) > value) value = info.minimums.get(key);
        traits.put(key, value);
    }

    /**
     * Gets an attribute value regardless of where in the Stimuli it is located.
     * @param name Name of the attribute
     * @return Value of the attribute, or null if it is not recognized
     */
    public Double getAttribute(String name)
    {
        return traits.get(name);
    }
}
