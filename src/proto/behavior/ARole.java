/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

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

    public void updateProactiveBehaviors(IWorldState ws) {
        for (IProactiveBehavior pb : proactives)
        {
            pb.updateImportance(ws);
        }
    }

    public BehaviorQueue instantiateProactiveBehavior(IWorldState ws, Dispatcher d) {
        IProactiveBehavior best = null;
        int bestImportance = Integer.MIN_VALUE;
        for (IProactiveBehavior pb : proactives)
        {
            int importance = pb.getImportance();
            if (importance > bestImportance)
            {
                best = pb;
                bestImportance = importance;
            }
        }
        
        if (best == null)
        {
            //throw new UnsupportedOperationException("All roles must have at least 1 proactive behavior.");
            return null;
        }

        return best.instantiate(ws, d);
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

}
