/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

/**
 *
 * @author hartsoka
 */
public class Inventory extends AAttributeMap
{
    public static final String MONEY =              "money";
    public static final String DRINKS =             "drinks";

    public Inventory() {
        this.initialize();
    }

    public void initialize()
    {
        addNewAttribute(MONEY, 100, 0, 1000);
        addNewAttribute(DRINKS, 1, 0, 4);
    }
}
