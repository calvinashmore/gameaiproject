/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

/**
 * Internal feelings of a character such as euphoria and anxiety
 * @author hartsoka
 */
public class Feelings extends AAttributeMap {

    // Feelings are decreased naturally over time
    // They are affected by Needs, actions, stimuli, etc.

    protected static final double DEFAULT_MAX_VALUE = 100;
    protected static final double DEFAULT_MIN_VALUE = 0;

    protected static final double DEFAULT_EFFECTS_AMT = 0.0;
    protected static final double DEFAULT_EFFECTS_RATE = -0.5;

    protected long lastUpdate = 0;

    public static final String EUPHORIA =       "euphoria";
    public static final String ANXIETY =        "anxiety";
    public static final String IRRITATION =     "irritation";
    public static final String STIMULANT =      "stimulant";
    public static final String DEPRESSANT =     "depressant";

    public static final String RATE = "Rate";

    public static final String EUPHORIA_RATE =       EUPHORIA + RATE;
    public static final String ANXIETY_RATE =        ANXIETY + RATE;
    public static final String IRRITATION_RATE =     IRRITATION + RATE;
    public static final String STIMULANT_RATE =      STIMULANT + RATE;
    public static final String DEPRESSANT_RATE =     DEPRESSANT + RATE;

    public Feelings() {
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
        addNewAttribute(ANXIETY_RATE,      -0.75,                   DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(IRRITATION_RATE,   -0.75,                   DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(STIMULANT_RATE,    DEFAULT_EFFECTS_RATE,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
        addNewAttribute(DEPRESSANT_RATE,   -0.75,    DEFAULT_MIN_VALUE,  DEFAULT_MAX_VALUE);
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
        this.changeAttribute(EUPHORIA, this.getAttribute(EUPHORIA_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(IRRITATION, this.getAttribute(IRRITATION_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(ANXIETY, this.getAttribute(ANXIETY_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(DEPRESSANT, this.getAttribute(DEPRESSANT_RATE), AttributeMap.Operation.Add);
        this.changeAttribute(STIMULANT, this.getAttribute(STIMULANT_RATE), AttributeMap.Operation.Add);
    }

}
