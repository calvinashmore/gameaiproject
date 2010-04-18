/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects;

import java.util.Map;
import java.util.TreeMap;
import testworld.objects.Stimuli.Effect;
import testworld.objects.Stimuli.Need;

/**
 * Static character attributes.
 * @author hartsoka
 */
public class Personality
{
    protected static final double DEFAULT_TRAIT = 0.5;

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
