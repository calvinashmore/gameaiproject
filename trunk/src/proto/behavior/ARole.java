/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author hartsoka
 */
public abstract class ARole implements IRole {

    protected List<IProactiveBehavior> proactives;
    protected List<IReactiveBehavior> reactives;
    protected List<ILatentBehavior> latents;

    public ARole()
    {
        proactives = new LinkedList<IProactiveBehavior>();
        reactives = new LinkedList<IReactiveBehavior>();
        latents = new LinkedList<ILatentBehavior>();
    }

    public void addBehaviorTemplate(IBehaviorTemplate bt)
    {
        switch(bt.getInitiationType())
        {
            case proactive:
                proactives.add((IProactiveBehavior)bt);
                break;
            case reactive:
                reactives.add((IReactiveBehavior)bt);
                break;
            case latent:
                latents.add((ILatentBehavior)bt);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Initiation Type.");
        }
    }

    public BehaviorQueue instantiateProactiveBehavior(IWorldState ws, Dispatcher d) {

        List<BehaviorRelevance> brs =
                new ArrayList<BehaviorRelevance>(proactives.size());

        // sort proactive behaviors by performance
        for (IProactiveBehavior pb : proactives)
        {
            int importance = pb.getImportance(ws);
            brs.add(new BehaviorRelevance(importance, pb));
        }
        Collections.sort(brs); // comparator is implemented backwards, so greatest to least

        // try to instantiate them from most important to least until successful
        BehaviorQueue bq = null;
        for (BehaviorRelevance br : brs)
        {
            bq = br.pb.instantiate(ws, d);
            if (bq != null)
            {
                break;
            }
        }
        
        if (bq == null)
        {
            //throw new UnsupportedOperationException("All roles must have at least 1 proactive behavior.");
        }

        return bq;
    }

    public List<IProactiveBehavior> getProactiveBehaviors() {
        return proactives;
    }

    public List<ILatentBehavior> getLatentBehaviors() {
        return latents;
    }

    public BehaviorQueue getReactiveBehavior(String id, CollaborationHandshake handshake) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private class BehaviorRelevance implements Comparable
    {
        int importance;
        IProactiveBehavior pb;

        BehaviorRelevance(int importance, IProactiveBehavior pb)
        {
            this.importance = importance;
            this.pb = pb;
        }

        public int compareTo(Object o) {
            BehaviorRelevance rhs = (BehaviorRelevance)o;

            // want reverse sort order
            return new Integer(rhs.importance).compareTo(this.importance);
        }
    }

}
