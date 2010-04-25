/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *
 * @author hartsoka
 */
public class AAttributeMap implements AttributeMap {

    protected Map<String, Double> attributes = new TreeMap<String, Double>();

    protected void addNewAttribute(String name, double def, double min, double max)
    {
        if (attributes.containsKey(name)) throw new UnsupportedOperationException("Cannot add attribute which already exists");

        AttributeInfo info = AttributeInfo.getInstance();
        if (!info.defaults.containsKey(name))
        {
            info.defaults.put(name, def);
            info.minimums.put(name, min);
            info.maximums.put(name, max);
        }

        this.attributes.put(name, def);
    }

    public boolean setAttribute(String name, double value)
    {
        if (!attributes.containsKey(name)) return false;

        this.safeSetAttribute(name, value);
        return true;
    }

    public void forceSetAttribute(String name, double value)
    {
        attributes.put(name, value);
    }

    private void safeSetAttribute(String name, double value)
    {
        AttributeInfo info = AttributeInfo.getInstance();
        if (info.maximums.get(name) < value) value = info.maximums.get(name);
        if (info.minimums.get(name) > value) value = info.minimums.get(name);
        attributes.put(name, value);
    }

    public Double getAttribute(String name) {
        return attributes.get(name);
    }

    public boolean changeAttribute(String name, double value, Operation operation)
    {
        if (!attributes.containsKey(name)) return false;

        double newValue;
        switch (operation)
        {
            case Add:
                newValue = this.getAttribute(name) + value;
                break;
            case Subtract:
                newValue = this.getAttribute(name) - value;
                break;
            case Set:
            default:
                newValue = value;
                break;
        }
        this.safeSetAttribute(name, newValue);
        return true;
    }

    public Collection<Entry<String, Double>> getValues() {
        return attributes.entrySet();
    }

}
