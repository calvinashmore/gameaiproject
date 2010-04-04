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
 * Container for information on a collaboration.  Should have pointers to the
 * BehaviorQueues for the collaboration and the flags for when certain tasks
 * are finished.
 * @author hartsoka
 */
public class CollaborationHandshake {

    private int priority;

    private PersonDispatcher initiator;

    private Map<String, PersonDispatcher> participants; // role to agent
    private Map<PersonDispatcher, String> roles; // agent to role
    private Map<PersonDispatcher, ICollaborativeBehavior> templates; // agent to behavior template
    private Map<PersonDispatcher, BehaviorQueue> queues; // agent to behavior implementation

    /**
     * Create a CollaborationHandshake, which is an encapsulation of all the
     * messaging between participants in either a potential collaboration (not
     * yet agreed upon) or an actual collaboration (BehaviorQueues created).
     * For this constructor, the initiator's role will be "initiator" .
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
     * @param role Initiator's named role in the collaboration.
     */
    public CollaborationHandshake(int priority, Dispatcher initiator, String role)
    {
        this.participants = new TreeMap<String, PersonDispatcher>();
        this.roles = new TreeMap<PersonDispatcher, String>();
        this.queues = new TreeMap<PersonDispatcher, BehaviorQueue>();

        // TODO
        // change design so we don't have to use a hack
        this.initiator = (PersonDispatcher)initiator;
        this.priority = priority;
        
        this.participants.put(role, (PersonDispatcher)initiator);
        this.roles.put((PersonDispatcher)initiator, role);
    }

    /**
     * Called by non-initiators to signal the desire to participate in the
     * collaboration.  Caller's role will be set to "reactor" .
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
     * @param role Caller's named role in the collaboration.
     */
    public void participate(Dispatcher reactor, String role)
    {
        this.participants.put(role, (PersonDispatcher)reactor);
        this.roles.put((PersonDispatcher)reactor, role);
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
     * Returns the named role of a participant in the collaboration.
     * Useful to collaborators when completing the handshake, as it helps them
     * determine how to instantiate their behavior queues.
     * @param d The agent whose role named is desired.
     * @return The named role of that agent.
     */
    public String getRole(Dispatcher d)
    {
        return roles.get(d);
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
            System.out.println("Warning: unknown situation in completeHandshake in CollaborationHandshake");
            qs = MultiQueue.QueueSet.collab;
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
