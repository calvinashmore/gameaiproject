/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author hartsoka
 */
public interface AttributeMap {

    public enum Operation { Set, Add, Subtract }

    public boolean setAttribute(String name, double value);

    public boolean changeAttribute(String name, double value, Operation operation);

    public Double getAttribute(String name);

    public Collection<Map.Entry<String,Double>> getValues();

}
