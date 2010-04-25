/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

/**
 * Dynamic character attributes and how quickly they chang
 * @author hartsoka
 */
public class Stimuli extends AAttributeMap
{
    // Effects are increased by external stimuli, and decay naturally over time
    // The rate at which external stimuli alter them comes from the effect
    //  rates inside a personality

    // Needs are increased naturally over time, and decreased by external stimuli
    // The rate at which needs increase naturally comes from the need rates
    //  inside a personality

    protected static final double DEFAULT_MAX_VALUE = 100;
    protected static final double DEFAULT_MIN_VALUE = 0;

    protected static final double DEFAULT_EFFECTS_AMT = 0.0;
    protected static final double DEFAULT_NEEDS_AMT = 0.0;

    protected static final double DEFAULT_EFFECTS_RATE = 0.5;
    protected static final double DEFAULT_NEEDS_RATE = 0.5;

    protected long lastUpdate = 0;
    
    public static final String EUPHORIA =       "euphoria";
    public static final String ANXIETY =        "anxiety";
    public static final String IRRITATION =     "irritation";
    public static final String STIMULANT =      "stimulant";
    public static final String DEPRESSANT =     "depressant";

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

    public static final String EUPHORIA_RATE =       EUPHORIA + RATE;
    public static final String ANXIETY_RATE =        ANXIETY + RATE;
    public static final String IRRITATION_RATE =     IRRITATION + RATE;
    public static final String STIMULANT_RATE =      STIMULANT + RATE;
    public static final String DEPRESSANT_RATE =     DEPRESSANT + RATE;

    public static final String SEXDRIVE_RATE =       SEXDRIVE + RATE;
    public static final String ALCOHOL_RATE =        ALCOHOL + RATE;
    public static final String CIGARETTE_RATE =      CIGARETTE + RATE;
    public static final String COCAINE_RATE =        COCAINE + RATE;
    public static final String FOOD_RATE =           FOOD + RATE;
    public static final String BEVERAGE_RATE =       BEVERAGE + RATE;
    public static final String TOILET_RATE =         TOILET + RATE;
    public static final String SLEEP_RATE =          SLEEP + RATE;
    public static final String GOSSIP_RATE =         GOSSIP + RATE;

    public Stimuli() {
        this.initialize();
    }

    protected void initialize()
    {
        addNewAttribute(EUPHORIA,     DEFAULT_EFFECTS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(ANXIETY,      DEFAULT_EFFECTS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(IRRITATION,   DEFAULT_EFFECTS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(STIMULANT,    DEFAULT_EFFECTS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(DEPRESSANT,   DEFAULT_EFFECTS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);

        addNewAttribute(EUPHORIA_RATE,     DEFAULT_EFFECTS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(ANXIETY_RATE,      DEFAULT_EFFECTS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(IRRITATION_RATE,   DEFAULT_EFFECTS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(STIMULANT_RATE,    DEFAULT_EFFECTS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(DEPRESSANT_RATE,   DEFAULT_EFFECTS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);

        addNewAttribute(SEXDRIVE,     DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(ALCOHOL,      DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(CIGARETTE,    DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(COCAINE,      DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(FOOD,         DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(BEVERAGE,     DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(TOILET,       DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(SLEEP,        DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(GOSSIP,       DEFAULT_NEEDS_AMT,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);

        addNewAttribute(SEXDRIVE_RATE,     DEFAULT_NEEDS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(ALCOHOL_RATE,      0,                     DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(CIGARETTE_RATE,    0,                     DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(COCAINE_RATE,      0,                     DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(FOOD_RATE,         DEFAULT_NEEDS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(BEVERAGE_RATE,     0.75,                  DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(TOILET_RATE,       0,                     DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(SLEEP_RATE,        DEFAULT_NEEDS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(GOSSIP_RATE,       DEFAULT_NEEDS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
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
        this.changeAttribute(SLEEP, this.getAttribute(SLEEP_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(GOSSIP, this.getAttribute(GOSSIP_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(COCAINE, this.getAttribute(COCAINE_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(SEXDRIVE, this.getAttribute(SEXDRIVE_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(CIGARETTE, this.getAttribute(CIGARETTE_RATE), AttributeMap.Operation.Add);

        this.changeAttribute(EUPHORIA, this.getAttribute(EUPHORIA_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(IRRITATION, this.getAttribute(IRRITATION_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(ANXIETY, this.getAttribute(ANXIETY_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(DEPRESSANT, this.getAttribute(DEPRESSANT_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(STIMULANT, this.getAttribute(STIMULANT_RATE), AttributeMap.Operation.Add);
    }
}
