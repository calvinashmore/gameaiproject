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
    protected static final double DEFAULT_TRAIT = 50;

    protected Map<String, Double> traits;

    public enum Trait
    {
        morality,
        confidence,
        arrogance,
    }

    public Personality()
    {
        traits = new TreeMap<String, Double>();
        for (Trait t : Trait.values())
        {
            traits.put(t.toString(), DEFAULT_TRAIT);
        }
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
