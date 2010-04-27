/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import proto.behavior.IBehaviorTemplate.InitiationType;

/**
 * Container for information on a collaboration, both in setting up the
 * collaboration and in executing it.
 * @author hartsoka
 */
public class CollaborationHandshake {

    private int priority;

    private Dispatcher initiator;

    // Note: "title" refers to an agent's responsibility in the collaboration,
    //  for example, "initiator" or "drummer" or "quarterback", whatever is
    //  useful to the behavior
    private Map<String, Dispatcher> participants; // title to agent
    private Map<Dispatcher, String> titles; // agent to title
    private Map<Dispatcher, ICollaborativeBehavior> templates; // agent to behavior template
    private Map<Dispatcher, ICollaborativeBehaviorQueue> queues; // agent to behavior implementation

    private Map<Dispatcher, ICollaborationProgressReport> reports; // TODO get rid of this?
    private Map<Dispatcher, Boolean> barrier;

    private Map<String, Object> blackboard;

    /**
     * Create a CollaborationHandshake, which is an encapsulation of all the
     * messaging between participants in either a potential collaboration (not
     * yet agreed upon) or an actual collaboration (BehaviorQueues created).
     * For this constructor, the initiator's title will be "initiator" .
     * @param priority If completed, the priority recommended to BehaviorQueues.
     * @param initiator Agent initiating the collaboration.
     */
    public CollaborationHandshake(int priority, Dispatcher initiator, ICollaborativeBehavior initiatingBehavior)
    {
        this(priority, initiator, initiatingBehavior, "initiator");
    }

    /**
     * Create a CollaborationHandshake, which is an encapsulation of all the
     * messaging between participants in either a potential collaboration (not
     * yet agreed upon) or an actual collaboration (BehaviorQueues created).
     * @param priority If completed, the priority recommended to BehaviorQueues.
     * @param initiator Agent initiating the collaboration.
     * @param title Initiator's named title in the collaboration.
     */
    public CollaborationHandshake(int priority, Dispatcher initiator, ICollaborativeBehavior initiatingBehavior, String title)
    {
        this.participants = new TreeMap<String, Dispatcher>();
        this.titles = new TreeMap<Dispatcher, String>();
        this.templates = new TreeMap<Dispatcher, ICollaborativeBehavior>();
        this.queues = new TreeMap<Dispatcher, ICollaborativeBehaviorQueue>();

        this.reports = new TreeMap<Dispatcher, ICollaborationProgressReport>();
        this.barrier = new TreeMap<Dispatcher, Boolean>();

        this.blackboard = null; // uses lazy instantiation

        // TODO
        // change design so we don't have to use a hack
        this.initiator = initiator;
        this.priority = priority;
        
        this.participants.put(title, initiator);
        this.titles.put(initiator, title);
        this.templates.put(initiator, initiatingBehavior);
    }

    /**
     * Called by non-initiators to signal the desire to participate in the
     * collaboration.  Caller's title will be set to "reactor".
     * @param reactor Agent wanting to join the collaboration.
     */
    public void participate(Dispatcher reactor, IReactiveBehavior reactiveBehavior)
    {
        this.participate(reactor, reactiveBehavior, "reactor");
    }

    /**
     * Called by non-initiators to signal the desire to participate in the
     * collaboration.
     * @param reactor Agent wanting to join the collaboration.
     * @param title Caller's title in the collaboration.
     */
    public void participate(Dispatcher reactor, IReactiveBehavior reactiveBehavior, String title)
    {
        if (participants.containsKey(title))
        {
            throw new UnsupportedOperationException("CollaborationHandshake does not yet support multiple participants for the same title");
        }
        this.participants.put(title, reactor);
        this.titles.put(reactor, title);
        this.templates.put(reactor, reactiveBehavior);
    }

    /**
     * Gets the recommended priority of BehaviorQueues for the collaboration.
     * @return Priority that the BehaviorQueues are recommended to be given.
     */
    public int getPriority()
    {
        return priority;
    }

    /**
     * Gets the agent which initiated the collaboration.
     * @return Initiating agent.
     */
    public Dispatcher getInitiator()
    {
        return this.initiator;
    }

    /**
     * Returns the BehaviorTemplate which initiated the attempted collaboration.
     * Useful to potential collaborators for determining if they want to join.
     * @return
     */
    public IBehaviorTemplate getInitiatingBehavior()
    {
        IBehaviorTemplate initiatingBehavior = templates.get(initiator);
        return initiatingBehavior;
    }

    /**
     * Returns the title of a participant in the collaboration.
     * Useful to collaborators when completing the handshake, as it helps them
     * determine how to instantiate their behavior queues.
     * @param d The agent whose title named is desired.
     * @return The title of that agent.
     */
    public String getTitle(Dispatcher d)
    {
        return titles.get(d);
    }

    /**
     * Tells each collaborator who has agreed to the collaboration to
     * instantiate the appropriate BehaviorQueues.
     */
    public void completeHandshake()
    {
        // Determine which QueueSet new BehaviorQueues should be stored in
        MultiQueue.QueueSet qs;
        IBehaviorTemplate initiatingBehavior = getInitiatingBehavior();
        if (initiatingBehavior.getInitiationType() == InitiationType.latent)
        {
            qs = MultiQueue.QueueSet.LATENT_OR_LATENT_RESPONSE;
        }
        else if (initiatingBehavior.getInitiationType() == InitiationType.proactive)
        {
            qs = MultiQueue.QueueSet.COLLABORATIVE_NON_LATENT;
        }
        else
        {
            throw new UnsupportedOperationException("Unsupported situation in CollaborationHandshake:completeHandshake()");
        }

        // Create the actual BehaviorQueue for each participant
        for (Dispatcher d : participants.values())
        {
            ICollaborativeBehavior template = this.templates.get(d);
            String title = this.titles.get(d);
            ICollaborativeBehaviorQueue bq = template.completeHandshake(title, this);

            if (d != initiator || template.getInitiationType() == InitiationType.latent)
            {
                // we do not want to add Proactive behavior queues to the initiator
                //  from here because it is done in the dispatcher
                d.handleNewBehavior(bq, qs);
            }

            this.queues.put(d, bq);
            this.barrier.put(d, Boolean.FALSE);
        }
    }

    /**
     * Signals that an agent has reached a barrier point in the collaboration.
     * If all agents have reached the barrier, sends a handleCollaborationDone
     * signal to each agent.
     * @param agent Agent who has reached the barrier
     * @return True if all agents have reached the barrier, false otherwise.
     */
    public boolean triggerBarrier(Dispatcher agent)
    {
        this.barrier.put(agent, Boolean.TRUE);
        
        boolean done = true;
        for (Boolean b : barrier.values())
        {
            if (!b)
            {
                done = false;
            }
        }

        if (!done)
        {
            return false;
        }

        // otherwise done, so notify everyone that we're ready to move on
        // reason for two loops:
        //  first resets the barrier so that when handleCollaboratorDone() is
        //    called, it can trigger instantaneous tasks - which might be
        //    more sync tasks - w/o those tasks thinking we are still at a
        //    barrier
        Collection<Entry<Dispatcher,Boolean>> entries = barrier.entrySet();
        for (Entry<Dispatcher,Boolean> e : entries)
        {
            e.setValue(Boolean.FALSE); // reset barrier
        }
        for (Entry<Dispatcher,Boolean> e : entries)
        {
            Dispatcher d = e.getKey();
            d.handleCollaboratorDone(queues.get(d));
        }

        return true;
    }

    /**
     * Used for getting the Dispatcher of an agent from its collaborative Title.
     * @return A map of Titles to Dispatchers.
     */
    public Map<String, Dispatcher> getParticipants()
    {
        return this.participants;
    }

    /**
     * Used for getting the Title of an agent from its Dispatcher.
     * @return A map of Dispatchers to Titles.
     */
    public Map<Dispatcher, String> getTitles()
    {
        return this.titles;
    }

    /**
     * Used for getting the Dispatcher of an agent from its collaborative Title.
     * @param title Collaborative Title whose Dispatcher you want.
     * @return Dispatcher of the agent with that Title.
     */
    public Dispatcher getParticipant(String title)
    {
        return this.participants.get(title);
    }

    /**
     * Used for getting the actual collaboration task queue of an agent
     * @param d The agent's Dispatcher.
     * @return That agent's behavior queue.
     */
    public ICollaborativeBehaviorQueue getQueue(Dispatcher d)
    {
        return this.queues.get(d);
    }

    /**
     * A collaborative blackboard for agents to share information during a
     * collaboration.
     * @return A blackboard.
     */
    public Map<String, Object> getBlackboard()
    {
        if (this.blackboard == null)
        {
            this.blackboard = new TreeMap<String, Object>();
        }
        return this.blackboard;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
