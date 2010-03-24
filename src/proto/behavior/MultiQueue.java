/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import java.util.LinkedList;
import java.util.List;

/**
 * Container for all BehaviorQueues instantiated for an agent.
 * @author hartsoka
 */
public class MultiQueue {

    // Contains three queue sets as described on bottom of page 24
    enum QueueSet { pro, collab, latent }
    private BehaviorQueue proactive; // IndepPro only
    private List<BehaviorQueue> collaborative; // CollabPro, or CollabReact in response to CollabPro
    private List<BehaviorQueue> latent; // IndepLat or CollabLat, or a CollabReact in response to a latent

    /**
     * Finds the active instantiated behavior with the highest priority.
     * @return The current behavior.
     */
    public BehaviorQueue getCurrentBehavior()
    {
        // TODO cache this calculation for better performance
        //  trick is to determine when to refresh

        BehaviorQueue currentQueue = null;
        int bestPriority = Integer.MIN_VALUE;
        if (proactive.getPriority() > bestPriority && proactive.isActive())
        {
            currentQueue = proactive;
            bestPriority = proactive.getPriority();
        }
        for (BehaviorQueue queue : collaborative) {
            if (queue.getPriority() > bestPriority && queue.isActive())
            {
                currentQueue = queue;
                bestPriority = queue.getPriority();
            }
        }
        for (BehaviorQueue queue : latent) {
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
     * @param queue Pointer to the BehaviorQueue which should be deleted.
     */
    public void remove(BehaviorQueue queue)
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
            // TODO throw an exception?
            System.out.println("Tried to remove a behavior which doesn't exist");
        }
    }

    /**
     * Deletes all BehaviorQueues which have been cancelled.
     */
    public void cleanup()
    {
        // TODO make this more efficient
        //  terribly unefficient but currently doesn't matter
        if (proactive.isCancelled())
        {
            proactive = null;
        }

        List<BehaviorQueue> toRemove =  new LinkedList<BehaviorQueue>();
        for (BehaviorQueue queue : collaborative) {
            if (queue.isCancelled())
            {
                toRemove.add(queue);
            }
        }
        for (BehaviorQueue queue : toRemove) {
            collaborative.remove(queue);
        }

        toRemove.clear();
        for (BehaviorQueue queue : latent) {
            if (queue.isCancelled())
            {
                toRemove.add(queue);
            }
        }
        for (BehaviorQueue queue : toRemove) {
            latent.remove(queue);
        }
    }
}
