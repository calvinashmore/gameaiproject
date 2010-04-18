/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects;

import java.util.HashMap;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

/**
 * Manifestation of a character's personality and stimuli.
 * @author hartsoka
 */
public class Emotions
{

    protected static FIS fis; // fuzzy inference system, contains all funcs

    protected Personality personality;
    protected Stimuli stimuli;

    static
    {
        // Load from 'FCL' file
        String fileName = "src/fuzzy/logic.fcl";
        fis = FIS.load(fileName,true);
        // Error while loading?
        if( fis == null )
        {
            System.err.println("Can't load file: '" + fileName + "'");
        }
    }

    public Emotions()
    {
        this.personality = new Personality();
        this.stimuli = new Stimuli();
    }

    public Personality getPersonality() {
        return this.personality;
    }

    public Stimuli getStimuli() {
        return this.stimuli;
    }

    public double evaluateFuzzy(Personality p, Stimuli s, String fn)
    {
        return evaluateFuzzy(p, s, fn, false);
    }

    public double evaluateFuzzy(Personality p, Stimuli s, String fn, boolean debug)
    {
        FunctionBlock block = fis.getFunctionBlock(fn);
        if (block == null) {
            throw new UnsupportedOperationException("Unknown fuzzy block in Emotions: " + fn);
        }

        HashMap<String,Variable> vars = block.getVariables();
        String outputVarName = "";
        for (Variable v : vars.values()) {
            if (!v.isOutputVarable()) {
                String name = v.getName();
                double value = getAttribute(p,s, name);
                v.setValue(value);
            }
            else
            {
                outputVarName = v.getName();
            }
        }

        if (debug)
        {
            block.chart();
        }

        block.evaluate();
        
        double result = block.getVariable(outputVarName).getLatestDefuzzifiedValue();

        if (debug)
        {
            block.getVariable(outputVarName).chartDefuzzifier(true);
        }

        return result;
    }

    private static double getAttribute(Personality p, Stimuli s, String varName)
    {
        Double value = p.getAttribute(varName);
        if (value == null)
        {
            value = s.getAttribute(varName);
            if (value == null) {
                throw new UnsupportedOperationException("Fuzzy block uses unknown var: " + varName);
            }
        }
        return value;
    }
}
