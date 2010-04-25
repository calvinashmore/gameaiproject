/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.social;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import testworld.objects.Person;

/**
 * Container for all attributes of a character, including personality, needs,
 * current feelings, inventory, etc.
 * @author hartsoka
 */
public class SocialState
{
    // fuzzy inference systems, contains all funcs
    protected static Map<String,FIS> fuzzySystems = new TreeMap<String, FIS>();

    protected Personality personality = new Personality();
    protected Feelings feelings =  new Feelings();
    protected Needs needs =  new Needs();
    protected Inventory inventory = new Inventory();
    protected Map<Person, Relationship> relationships =
            new TreeMap<Person, Relationship>();

    protected List<AttributeMap> permanentMaps = new LinkedList<AttributeMap>();
    protected List<AttributeMap> temporaryMaps = new LinkedList<AttributeMap>();

    static
    {
        String directoryPath = "src/fuzzy/";
        File directory = new File(directoryPath);
        for (File file : directory.listFiles())
        {
            if (!file.getName().endsWith(".fcl")) continue;
            
            FIS fis = FIS.load(file.getAbsolutePath());
            String name = file.getName().substring(0, file.getName().indexOf(".fcl"));
            if (fis.getFunctionBlock(name) == null) {
                throw new UnsupportedOperationException("Fuzzy logic file" + name + " must match function inside");
            }
            fuzzySystems.put(name, fis);
        }
    }

    public SocialState()
    {
        permanentMaps.add(personality);
        permanentMaps.add(needs);
        permanentMaps.add(feelings);
        permanentMaps.add(inventory);
        temporaryMaps.add(new AAttributeMap());
    }

    public Relationship getRelationship(Person target) {
        Relationship relationship = this.relationships.get(target);
        if (relationship == null) {
            relationship = new Relationship(target);
            this.relationships.put(target, relationship);
        }
        return relationship;
    }

    /*
    public List<AttributeMap> getDefaultAttributeMaps()
    {
        List<AttributeMap> maps = new LinkedList<AttributeMap>();
        maps.add(personality);
        maps.add(needs);
        return maps;
    }
     *
     */

    public Map<String,Double> evaluateFuzzy(String fn)
    {
        return this.evaluateFuzzy(fn, false);
    }

    public Map<String,Double> evaluateFuzzy(String fn, boolean debug)
    {
        FunctionBlock block = fuzzySystems.get(fn).getFunctionBlock(fn);
        if (block == null) {
            throw new UnsupportedOperationException("Unknown fuzzy block in SocialState: " + fn);
        }

        HashMap<String,Variable> vars = block.getVariables();
        List<String> outputVars = new LinkedList<String>();
        for (Variable v : vars.values()) {
            if (!v.isOutputVarable()) {
                String name = v.getName();
                double value = getAttribute(name);
                v.setValue(value);
            }
            else
            {
                outputVars.add(v.getName());
            }
        }

        if (debug)
        {
            block.chart();
        }

        block.evaluate();

        Map<String,Double> results = new TreeMap<String, Double>();
        for (String varName : outputVars) {
            results.put(varName, block.getVariable(varName).getLatestDefuzzifiedValue());
            if (debug)
            {
                block.getVariable(varName).chartDefuzzifier(true);
            }
        }

        return results;
    }
    
    public double getAttribute(String name)
    {
        Double value = null;
        for (AttributeMap map : permanentMaps) {
            if (value == null) {
                value = map.getAttribute(name);
            }
        }
        for (AttributeMap map : temporaryMaps) {
            if (value == null) {
                value = map.getAttribute(name);
            }
        }
        if (value == null) {
            throw new UnsupportedOperationException("Fuzzy block uses unknown var: " + name);
        }
        return value;
    }

    public void setAttribute(String name, double value)
    {
        boolean done = false;
        for (AttributeMap map : permanentMaps) {
            if (done) break;
            done = map.setAttribute(name, value);
        }
        for (AttributeMap map : temporaryMaps) {
            if (done) break;
            done = map.setAttribute(name, value);
        }
        if (!done) {
            throw new UnsupportedOperationException("Tried to set unknown var: " + name);
        }
    }

    public void changeAttribute(String name, double value, AttributeMap.Operation operation)
    {
        boolean done = false;
        for (AttributeMap map : permanentMaps) {
            if (done) break;
            done = map.changeAttribute(name, value, operation);
        }
        for (AttributeMap map : temporaryMaps) {
            if (done) break;
            done = map.changeAttribute(name, value, operation);
        }
        if (!done) {
            throw new UnsupportedOperationException("Tried to change unknown var: " + name);
        }
    }

    /*
    public double debugGetAttribute(List<AttributeMap> maps, String varName)
    {
        return getAttribute(maps, varName);
    }
     * 
     */

    public List<AttributeMap> getPermanentMaps()
    {
        return this.permanentMaps;
    }

    public void addTemporaryAttribute(String name, double value)
    {
        ((AAttributeMap)temporaryMaps.get(0)).forceSetAttribute(name, value);
    }

    public void addTemporaryMap(AttributeMap map)
    {
        temporaryMaps.add(map);
    }

    public void clearTemporaryMaps()
    {
        temporaryMaps.clear();
        temporaryMaps.add(new AAttributeMap());
    }
    
    public void update()
    {
        needs.update();
        feelings.update();
        Map<String,Double> deltas = evaluateFuzzy("update_internals_from_needs");
        this.applyDeltas(deltas);
    }

    public void applyDeltas(Map<String,Double> deltas)
    {
        for (Entry<String,Double> delta : deltas.entrySet())
        {
            if (delta.getKey().startsWith("d_"))
            {
                String attribute = delta.getKey().substring(2);

                // TODO divide deltas by 10?
                this.changeAttribute(attribute, delta.getValue(), AttributeMap.Operation.Add);
            }
        }
    }


}
