/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

import java.util.Map;
import java.util.TreeMap;
import proto.behavior.IBehaviorTemplate.InitiationType;
import testworld.objects.PersonDispatcher;

/**
 * Container for information on a collaboration, both in setting up the
 * collaboration and in executing it.
 * @author hartsoka
 */
public class CollaborationHandshake {

    private int priority;

    private PersonDispatcher initiator;

    // Note: "title" refers to an agent's responsibility in the collaboration,
    //  for example, "initiator" or "drummer" or "quarterback", whatever is
    //  useful to the behavior
    private Map<String, PersonDispatcher> participants; // title to agent
    private Map<PersonDispatcher, String> titles; // agent to title
    private Map<PersonDispatcher, ICollaborativeBehavior> templates; // agent to behavior template
    private Map<PersonDispatcher, BehaviorQueue> queues; // agent to behavior implementation

    /**
     * Create a CollaborationHandshake, which is an encapsulation of all the
     * messaging between participants in either a potential collaboration (not
     * yet agreed upon) or an actual collaboration (BehaviorQueues created).
     * For this constructor, the initiator's title will be "initiator" .
     * @param priority If completed, the priority recommended to BehaviorQueues.
     * @param initiator Agent initiating the collaboration.
     */
    public CollaborationHandshake(int priority, Dispatcher initiator)
    {
        this(priority, initiator, "initiator");
    }

    /**
     * Create a CollaborationHandshake, which is an encapsulation of all the
     * messaging between participants in either a potential collaboration (not
     * yet agreed upon) or an actual collaboration (BehaviorQueues created).
     * @param priority If completed, the priority recommended to BehaviorQueues.
     * @param initiator Agent initiating the collaboration.
     * @param title Initiator's named title in the collaboration.
     */
    public CollaborationHandshake(int priority, Dispatcher initiator, String title)
    {
        this.participants = new TreeMap<String, PersonDispatcher>();
        this.titles = new TreeMap<PersonDispatcher, String>();
        this.queues = new TreeMap<PersonDispatcher, BehaviorQueue>();

        // TODO
        // change design so we don't have to use a hack
        this.initiator = (PersonDispatcher)initiator;
        this.priority = priority;
        
        this.participants.put(title, (PersonDispatcher)initiator);
        this.titles.put((PersonDispatcher)initiator, title);
    }

    /**
     * Called by non-initiators to signal the desire to participate in the
     * collaboration.  Caller's title will be set to "reactor" .
     * @param reactor Agent wanting to join the collaboration.
     */
    public void participate(Dispatcher reactor)
    {
        this.participate(reactor, "reactor");
    }

    /**
     * Called by non-initiators to signal the desire to participate in the
     * collaboration.
     * @param reactor Agent wanting to join the collaboration.
     * @param title Caller's title in the collaboration.
     */
    public void participate(Dispatcher reactor, String title)
    {
        if (participants.containsKey(title))
        {
            throw new UnsupportedOperationException("CollaborationHandshake does not yet support multiple participants for the same title");
        }
        this.participants.put(title, (PersonDispatcher)reactor);
        this.titles.put((PersonDispatcher)reactor, title);
    }

    /**
     * Returns the recommended priority of BehaviorQueues for the collaboration.
     * @return
     */
    public int getPriority()
    {
        return priority;
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
            qs = MultiQueue.QueueSet.latent;
        }
        else if (initiatingBehavior.getInitiationType() == InitiationType.proactive)
        {
            qs = MultiQueue.QueueSet.collab;
        }
        else
        {
            throw new UnsupportedOperationException("Unsupported situation in CollaborationHandshake:completeHandshake()");
        }

        // Create the actual BehaviorQueue for each participant
        for (PersonDispatcher pd : participants.values())
        {
            ICollaborativeBehavior template = this.templates.get(pd);
            BehaviorQueue bq = template.completeHandshake(this);
            pd.handleNewBehavior(bq, qs);

            this.queues.put(pd, bq);
        }
    }
}
