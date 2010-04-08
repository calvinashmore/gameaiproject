/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Container for all BehaviorQueues instantiated for an agent.
 * @author hartsoka
 */
public class MultiQueue
{
    private Dispatcher owningDispatcher;

    // Contains three queue sets as described on bottom of page 24
    public enum QueueSet { pro, collab, latent }
    private IBehaviorQueue proactive; // IndepPro only
    private List<IBehaviorQueue> collaborative; // CollabPro, or CollabReact in response to CollabPro
    private List<IBehaviorQueue> latent; // IndepLat or CollabLat, or a CollabReact in response to a latent

    public MultiQueue(Dispatcher owner)
    {
        owningDispatcher = owner;
        proactive = null;
        collaborative = new ArrayList<IBehaviorQueue>();
        latent = new ArrayList<IBehaviorQueue>();
    }

    /**
     * Adds a newly instantiated behavior to the MultiQueue so it can be run.
     * @param bq The BehaviorQueue of the newly instantiated BehaviorTemplate.
     * @param qs A flag indicating what triggered this behavior; determines
     *              which set of queues it belongs in.
     */
    public void addBehavior(IBehaviorQueue bq, QueueSet qs)
    {
        // TODO
        // paper mentions the possibility of having to delete existing queues
        //  to make room for a new one (page 25, left bottom); evaluate whether
        //  we want to do this

        bq.setOwningMultiQueue(this);
        switch (qs)
        {
            case pro:
                proactive = bq;
                break;
            case collab:
                collaborative.add(bq);
                break;
            case latent:
                latent.add(bq);
                break;
            default:
                throw new UnsupportedOperationException("Unknown QueueSet in MultiQueue");
        }
    }

    /**
     * Finds the active instantiated behavior with the highest priority.
     * @return The current behavior.
     */
    public IBehaviorQueue getCurrentBehavior()
    {
        // TODO cache this calculation for better performance
        //  trick is to determine when to refresh

        IBehaviorQueue currentQueue = null;
        int bestPriority = Integer.MIN_VALUE;
        if (proactive != null &&
            proactive.getPriority() > bestPriority && proactive.isActive())
        {
            currentQueue = proactive;
            bestPriority = proactive.getPriority();
        }
        for (IBehaviorQueue queue : collaborative) {
            if (queue.getPriority() > bestPriority && queue.isActive())
            {
                currentQueue = queue;
                bestPriority = queue.getPriority();
            }
        }
        for (IBehaviorQueue queue : latent) {
            if (queue.getPriority() > bestPriority && queue.isActive())
            {
                currentQueue = queue;
                bestPriority = queue.getPriority();
            }
        }
        return currentQueue;
    }

    /**
     * Removes the specified behavior completely (effectively cancelling it).
     * @param queue Reference to the BehaviorQueue which should be deleted.
     */
    public void remove(IBehaviorQueue queue)
    {
        if (proactive == queue)
        {
            proactive = null;
        }
        else if (collaborative.contains(queue))
        {
            collaborative.remove(queue);
        }
        else if (latent.contains(queue))
        {
            latent.remove(queue);
        }
        else
        {
            throw new UnsupportedOperationException("MultiQueue:remove(q) was passed a behavior which doesn't exist in the MultiQueue");
        }
    }

    /**
     * Deletes all BehaviorQueues which have been cancelled.
     */
    public void cleanup()
    {
        // TODO make this more efficient
        //  terribly unefficient but currently doesn't matter
        if (proactive != null && proactive.isCancelled())
        {
            proactive = null;
        }

        List<IBehaviorQueue> toRemove =  new LinkedList<IBehaviorQueue>();
        for (IBehaviorQueue queue : collaborative) {
            if (queue.isCancelled())
            {
                toRemove.add(queue);
            }
        }
        for (IBehaviorQueue queue : toRemove) {
            collaborative.remove(queue);
        }

        toRemove.clear();
        for (IBehaviorQueue queue : latent) {
            if (queue.isCancelled())
            {
                toRemove.add(queue);
            }
        }
        for (IBehaviorQueue queue : toRemove) {
            latent.remove(queue);
        }
    }

    /**
     * Eye-contact is the third requirement for joining a collaboration.
     * From page 24 of the paper:
     * Eye-contact is successful if the potential reactor is not currently
     * involved in another active collaborative or latent behavior. Eye-contact
     * is successful if the potential reactor is involved in an active
     * independent behavior or if all of the reactor’s collaborative and latent
     * behaviors are suspended for some reason.
     * @return Whether eye-contact can be made.
     */
    public boolean testEyeContact()
    {
        // send MIN_VALUE so that any active collaborative or latent behavior
        //  will prevent eye contact
        return testEyeContact(Integer.MIN_VALUE);
    }

    /**
     * See testEyeContact() for more info.
     * This version should allow for eye-contact when we are willing to
     * interrupt current collaborative or latent behaviors.  It is not discussed
     * in the paper.  TODO: Separate functions for latent/reactive only
     * @return Whether eye-contact can be made, allowing interrupts of other
     *          behaviors with lower priority.
     */
    public boolean testEyeContact(int priority)
    {
        for (IBehaviorQueue queue : collaborative) {
            if (queue.isActive() && queue.getPriority() >= priority)
            {
                return false;
            }
        }

        for (IBehaviorQueue queue : latent) {
            if (queue.isActive() && queue.getPriority() >= priority)
            {
                return false;
            }
        }

        return true;
    }

    /**
     * Get the Dispatcher which dispatches to this MultiQueue.
     * @return Owning Dispatcher.
     */
    public Dispatcher getOwningDispatcher()
    {
        return this.owningDispatcher;
    }

    public IBehaviorQueue getProactiveBehaviorQueue()
    {
        return proactive;
    }

    public List<IBehaviorQueue> getCollaborativeBehaviorQueueSet()
    {
        return collaborative;
    }

    public List<IBehaviorQueue> getLatentBehaviorQueueSet()
    {
        return latent;
    }
}
