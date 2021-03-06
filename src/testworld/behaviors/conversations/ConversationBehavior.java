/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors.conversations;

import java.util.ArrayList;
import java.util.List;
import proto.behavior.AJointBehavior;
import proto.behavior.CollaborationHandshake;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorTemplate.InitiationType;
import proto.behavior.ICollaborativeBehavior;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import proto.world.BasicObject;
import proto.world.World;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.social.Needs;
import testworld.tasks.RemoveBehaviorTemplateTask;
import utils.math.RandomManager;

/**
 *
 * @author Calvin Ashmore
 */
public class ConversationBehavior extends AJointBehavior implements IProactiveBehavior {

    private String id;
    private ConversationContent determinedContent;
    public static final String PROACTIVE_ID = "ConversationProactive";
    public static final String REACTIVE_ID = "ConversationReactive";
    private static final int CONVERSATION_RANGE = 60;
    private boolean destroyAfterUse;
    private int importance = -1;

    /**
     * This sets a flag to make this conversation behavior be removed from the behavior templates of the initiator after it has been completed.
     */
    public void destroyAfterUse() {
        this.destroyAfterUse = true;
    }

    public static ConversationBehavior makeProactive() {
        return new ConversationBehavior(InitiationType.proactive, PROACTIVE_ID, null);
    }

    public static ConversationBehavior makeProactive(ConversationContent content) {
        return new ConversationBehavior(InitiationType.proactive, PROACTIVE_ID, content);
    }

    public static ConversationBehavior makeReactive() {
        return new ConversationBehavior(InitiationType.reactive, REACTIVE_ID, null);
    }

    /**
     * If determinedContent is non null, then the conversation is being created with specific conversation content in mind.
     * Otherwise, the conversation will be randomly determined according to the person initiating.
     * @param initType
     * @param id
     * @param determinedContent
     */
    protected ConversationBehavior(InitiationType initType, String id, ConversationContent determinedContent) {
        super(initType);
        this.id = id;
        this.determinedContent = determinedContent;
    }

    protected float getConversationRange() {
        return CONVERSATION_RANGE;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    private static class ConversationHandshake extends CollaborationHandshake {

        private ConversationContent content;

        public ConversationHandshake(int priority, Dispatcher initiator, ICollaborativeBehavior initiatingBehavior, ConversationContent content) {
            super(priority, initiator, initiatingBehavior);
            this.content = content;
        }

        public void setContent(ConversationContent content) {
            this.content = content;
        }

        public ConversationContent getContent() {
            return content;
        }
    }

    @Override
    public CollaborationHandshake makeHandshake(IWorldState ws) {
        CollaborationHandshake handshake =
                new ConversationHandshake(1, getDispatcher(), this, determinedContent);
        return handshake;
    }

    @Override
    public List<Dispatcher> getPotentialCollaborators() {
        Person initiator = ((PersonDispatcher) getDispatcher()).getPerson();

        List<Dispatcher> dispatchers = new ArrayList<Dispatcher>();
        List<BasicObject> nearbyObjects = World.getInstance().getNearbyObjects(initiator, getConversationRange());
        for (BasicObject basicObject : nearbyObjects) {
            if (basicObject instanceof Person) {
                dispatchers.add(((Person) basicObject).getDispatcher());
            }
        }
        return dispatchers;
    }

    @Override
    public boolean confirmHandshake(CollaborationHandshake handshake) {

        if (handshake.getParticipants().size() == 2) {

            if (((ConversationHandshake) handshake).getContent() == null) {
                Person initiator = ((PersonDispatcher) handshake.getInitiator()).getPerson();
                Person reactor = ((PersonDispatcher) handshake.getParticipant("reactor")).getPerson();

                ConversationContent content = initiator.makeConversation(reactor);
                ((ConversationHandshake) handshake).setContent(content);
            }

            return true;
        } else {
            return false;
        }
    }

    public String getId() {
        return id;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {
        Person initiator = ((PersonDispatcher) handshake.getParticipant("initiator")).getPerson();
        Person responder = ((PersonDispatcher) handshake.getParticipant("reactor")).getPerson();

        if (title.equals("initiator")) {

            ICollaborativeBehaviorQueue bq = ((ConversationHandshake) handshake).getContent().getInitiatorQueue(initiator, responder, this, handshake);
            if (destroyAfterUse) {
                bq.queueTask(new RemoveBehaviorTemplateTask(this));
            }
            return bq;

        } else if (title.equals("reactor")) {
            return ((ConversationHandshake) handshake).getContent().getResponderQueue(initiator, responder, this, handshake);
        } else {
            throw new IllegalArgumentException("Title " + title + " is invalid");
        }
    }

    public boolean canCollaborate(String id) {
        return id.equals(PROACTIVE_ID);
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {
        if (handshake.getParticipants().size() >= 2) {
            return false;
        }

//        PersonDispatcher initiator = (PersonDispatcher) handshake.getInitiator();
//        PersonDispatcher reactor = (PersonDispatcher) getDispatcher();

//        double distance = initiator.getPerson().getLocation().getPosition().subtract(reactor.getPerson().getLocation().getPosition()).magnitude();
//        if (distance > getConversationRange()) {
        // NOTE: This is the conversation range for the REACTOR, not the INITIATOR
//            return false;
//        }

        handshake.participate(getDispatcher(), this);
        return true;
    }

    public int getImportance(IWorldState ws) {
        if (importance > 0) {
            return importance;
        }

        Person p = ((PersonDispatcher) this.getDispatcher()).getPerson();
        double gossipNeed = p.getSocialState().getAttribute(Needs.GOSSIP);
        double gossipDesire = Math.max(50, gossipNeed);

        return RandomManager.get().nextInt(
                (int) (gossipDesire / 1) + 1);
    }
}
