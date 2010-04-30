/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

import java.util.Map;
import java.util.TreeMap;
import utils.math.RandomManager;

/**
 * Static character attributes.
 * @author hartsoka
 */
public class Personality extends AAttributeMap
{
    protected static final double DEFAULT_MIN = -100;
    protected static final double DEFAULT_MAX = 100;
    protected static final double DEFAULT_VALUE = 0;

    public static final String MORALITY =           "morality";
    public static final String CONFIDENCE =         "confidence";
    public static final String OUTLOOK =            "outlook";
    //public static final String OPEN_MINDED =        "open_minded";
    //public static final String MANNERS =            "manners";

    public Personality() {
        this.initialize();
    }

    public void initialize()
    {
        addNewAttribute(MORALITY, DEFAULT_VALUE, DEFAULT_MIN, DEFAULT_MAX);
        addNewAttribute(CONFIDENCE, DEFAULT_VALUE, DEFAULT_MIN, DEFAULT_MAX);
        addNewAttribute(OUTLOOK, DEFAULT_VALUE, DEFAULT_MIN, DEFAULT_MAX);
        //(OPEN_MINDED, DEFAULT_VALUE, DEFAULT_MIN, DEFAULT_MAX);
        //addNewAttribute(MANNERS, DEFAULT_VALUE, DEFAULT_MIN, DEFAULT_MAX);
    }
}
