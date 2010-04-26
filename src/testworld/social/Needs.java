/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

/**
 * Character needs such as hunger and desire to gossip
 * @author hartsoka
 */
public class Needs extends AAttributeMap
{
    // Needs are increased naturally over time or by external stimuli
    // They are decreased by actions, external stimuli, or latent behaviors

    protected static final double DEFAULT_MAX_VALUE = 100;
    protected static final double DEFAULT_MIN_VALUE = 0;

    protected static final double DEFAULT_NEEDS_AMT = 0.0;
    protected static final double DEFAULT_NEEDS_RATE = 0.5;

    protected long lastUpdate = 0;

    public static final String SEXDRIVE =       "sexdrive";
    public static final String ALCOHOL =        "alcohol";
    public static final String CIGARETTE =      "cigarette";
    public static final String COCAINE =        "cocaine";
    public static final String FOOD =           "food";
    public static final String BEVERAGE =       "beverage";
    public static final String TOILET =         "toilet";
    public static final String SLEEP =          "sleep";
    public static final String GOSSIP =         "gossip";
    
    public static final String RATE = "Rate";

    public static final String SEXDRIVE_RATE =       SEXDRIVE + RATE;
    public static final String ALCOHOL_RATE =        ALCOHOL + RATE;
    public static final String CIGARETTE_RATE =      CIGARETTE + RATE;
    public static final String COCAINE_RATE =        COCAINE + RATE;
    public static final String FOOD_RATE =           FOOD + RATE;
    public static final String BEVERAGE_RATE =       BEVERAGE + RATE;
    public static final String TOILET_RATE =         TOILET + RATE;
    public static final String SLEEP_RATE =          SLEEP + RATE;
    public static final String GOSSIP_RATE =         GOSSIP + RATE;

    public Needs() {
        this.initialize();
    }

    protected void initialize()
    {
        addNewAttribute(SEXDRIVE,     DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(ALCOHOL,      DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(CIGARETTE,    DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(COCAINE,      DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(FOOD,         DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(BEVERAGE,     DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(TOILET,       DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(SLEEP,        DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(GOSSIP,       40,                   DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);

        addNewAttribute(SEXDRIVE_RATE,     DEFAULT_NEEDS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(ALCOHOL_RATE,      0,                     DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(CIGARETTE_RATE,    0,                     DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(COCAINE_RATE,      0,                     DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(FOOD_RATE,         DEFAULT_NEEDS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(BEVERAGE_RATE,     0.75,                  DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(TOILET_RATE,       0,                     DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(SLEEP_RATE,        DEFAULT_NEEDS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(GOSSIP_RATE,       1.2,                   DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
    }

    public void update()
    {
        long time = System.currentTimeMillis();
        if (time - lastUpdate > 1000)
        {
            lastUpdate = time;
            updateImpl();
        }
    }

    protected void updateImpl()
    {
        this.changeAttribute(FOOD, this.getAttribute(FOOD_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(BEVERAGE, this.getAttribute(BEVERAGE_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(TOILET, this.getAttribute(TOILET_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(ALCOHOL, this.getAttribute(ALCOHOL_RATE), AttributeMap.Operation.Add);
        //this.changeAttribute(SLEEP, this.getAttribute(SLEEP_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(GOSSIP, this.getAttribute(GOSSIP_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(COCAINE, this.getAttribute(COCAINE_RATE), AttributeMap.Operation.Add);
        //this.changeAttribute(SEXDRIVE, this.getAttribute(SEXDRIVE_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(CIGARETTE, this.getAttribute(CIGARETTE_RATE), AttributeMap.Operation.Add);
    }
}
