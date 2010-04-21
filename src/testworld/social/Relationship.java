/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

import java.util.Map;
import java.util.TreeMap;
import testworld.objects.Person;

/**
 *
 * @author hartsoka
 */
public class Relationship implements AttributeMap {

    protected static final double DEFAULT_MAX = 1.0;
    protected static final double DEFAULT_MIN = 0.0;
    protected static final double DEFAULT_VALUE = 0.5;

    protected Person target;
    protected Map<String, Double> stats = new TreeMap<String, Double>();

    public enum RelationshipStat
    {
        affection,
        dominance
    }

    static
    {
        AttributeInfo info = AttributeInfo.getInstance();
        for (RelationshipStat r : RelationshipStat.values())
        {
            info.maximums.put(r.toString(), DEFAULT_MAX);
            info.minimums.put(r.toString(), DEFAULT_MIN);
            info.defaults.put(r.toString(), DEFAULT_VALUE);
        }
    }

    public Relationship(Person target)
    {
        AttributeInfo info = AttributeInfo.getInstance();

        this.target = target;
        for (RelationshipStat s : RelationshipStat.values())
        {
            stats.put(s.toString(), info.defaults.get(s.toString()));
        }
    }

    public void setAttribute(RelationshipStat stat, double value)
    {
        String key = stat.toString();
        AttributeInfo info = AttributeInfo.getInstance();
        if (info.maximums.get(key) < value) value = info.maximums.get(key);
        if (info.minimums.get(key) > value) value = info.minimums.get(key);
        stats.put(key, value);
    }

    public Double getAttribute(String name)
    {
        return stats.get(name);
    }
}
