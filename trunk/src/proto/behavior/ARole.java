/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
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

    private Dispatcher owningDispatcher;

    protected List<IProactiveBehavior> proactives;
    protected List<IReactiveBehavior> reactives;
    protected List<ILatentBehavior> latents;

    public ARole()
    {
        owningDispatcher = null;

        proactives = new LinkedList<IProactiveBehavior>();
        reactives = new LinkedList<IReactiveBehavior>();
        latents = new LinkedList<ILatentBehavior>();
    }

    /**
     * Adds a new behavior which the role can utilize.  Its initiation type is
     * used to determine when the role will try and instantiate it.
     * @param bt
     */
    public void addBehaviorTemplate(IBehaviorTemplate bt)
    {
        bt.setOwningRole(this);
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
                throw new UnsupportedOperationException("ARole:addBehaviorTemplate(bt) - Unknown Initiation Type.");
        }
    }

    public void removeBehaviorTemplate(IBehaviorTemplate bt) {
        switch(bt.getInitiationType())
        {
            case proactive:
                proactives.remove((IProactiveBehavior)bt);
                break;
            case reactive:
                reactives.remove((IReactiveBehavior)bt);
                break;
            case latent:
                latents.remove((ILatentBehavior)bt);
                break;
            default:
                throw new UnsupportedOperationException("ARole:removeBehaviorTemplate(bt) - Unknown Initiation Type.");
        }
    }

    /**
     * Finds the most important proactive behavior which can be instantiated
     * and does so.
     * @param ws State of the world; used by behaviors to determine importance.
     * @param d Dispatcher so that the instantiated behavior knows its owner.
     * @return The instantiated BehaviorQueue of tasks.
     */
    public IBehaviorQueue instantiateProactiveBehavior(IWorldState ws)
    {
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
        IBehaviorQueue bq = null;
        for (BehaviorRelevance br : brs)
        {
            bq = br.pb.instantiate(ws);
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

    public List<IReactiveBehavior> getReactiveBehaviors() {
        return reactives;
    }

    /**
     * Sets the Dispatcher which contains this role - in general, should only be
     * called by dispatcher implementations when this role is set to them.
     * @param d
     */
    public void setOwningDispatcher(Dispatcher d)
    {
        if (this.owningDispatcher != null)
        {
            throw new UnsupportedOperationException("ARole: cannot be given to a second dispatcher");
        }
        this.owningDispatcher = d;
    }

    /**
     * Gets the Dispatcher which contains the role.
     * @return Owning dispatcher.
     */
    public Dispatcher getOwningDispatcher()
    {
        return this.owningDispatcher;
    }

    /**
     * Helper class used to sort proactive behaviors by their importance.
     */
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
