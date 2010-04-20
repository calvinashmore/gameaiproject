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

    protected static final double DEFAULT_STAT = 0.5;

    protected Person target;
    protected Map<String, Double> stats = new TreeMap<String, Double>();

    public enum RelationshipStat
    {
        affection,
        dominance
    }

    public Relationship(Person target)
    {
        this.target = target;
        for (RelationshipStat s : RelationshipStat.values())
        {
            stats.put(s.toString(), DEFAULT_STAT);
        }
    }

    public void setAttribute(RelationshipStat stat, double value)
    {
        this.stats.put(stat.toString(), value);
    }

    public Double getAttribute(String name)
    {
        return stats.get(name);
    }
}
