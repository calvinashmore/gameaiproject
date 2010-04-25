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
public class Relationship extends AAttributeMap {

    protected static final double DEFAULT_MAX = 100.0;
    protected static final double DEFAULT_MIN = -100.0;
    protected static final double DEFAULT_VALUE = 0.0;

    public static final String AFFECTION =              "affection";
    public static final String DOMINANCE =              "dominance";

    protected Person target;
    protected Map<String, Double> stats = new TreeMap<String, Double>();

    public Relationship(Person target) {
        this.target = target;
        this.initialize();
    }

    public void initialize()
    {
        addNewAttribute(AFFECTION, DEFAULT_VALUE, DEFAULT_MIN, DEFAULT_MAX);
        addNewAttribute(DOMINANCE, DEFAULT_VALUE, DEFAULT_MIN, DEFAULT_MAX);
    }
}
