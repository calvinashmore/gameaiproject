/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author hartsoka
 */
public class AttributeInfo {

    public Map<String,Double> minimums;
    public Map<String,Double> maximums;
    public Map<String,Double> defaults;

    protected static AttributeInfo instance;

    private AttributeInfo()
    {
        minimums = new TreeMap<String, Double>();
        maximums = new TreeMap<String, Double>();
        defaults = new TreeMap<String, Double>();
    }

    public static AttributeInfo getInstance()
    {
        if (instance == null) {
            instance = new AttributeInfo();
        }
        return instance;
    }

}
