/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors.conversations;

import java.util.ArrayList;
import java.util.List;
import proto.behavior.CollaborationHandshake;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorTemplate.InitiationType;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IWorldState;
import proto.behavior.SyncTask;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.tasks.MoveTo;

/**
 *
 * @author Calvin Ashmore
 */
public class ApproachConversationBehavior extends ConversationBehavior {

    private Person target;

    public static ApproachConversationBehavior makeProactive(ConversationContent content) {
        return new ApproachConversationBehavior(InitiationType.proactive, PROACTIVE_ID, content, null);
    }

    public static ApproachConversationBehavior makeProactive(ConversationContent content, Person target) {
        return new ApproachConversationBehavior(InitiationType.proactive, PROACTIVE_ID, content, target);
    }

    protected ApproachConversationBehavior(InitiationType initType, String id, ConversationContent determinedContent, Person target) {
        super(initType, id, determinedContent);
        this.target = target;
    }

    @Override
    public CollaborationHandshake makeHandshake(IWorldState ws) {
        CollaborationHandshake handshake = super.makeHandshake(ws);
        handshake.setPriority(3);
        return handshake;
    }

    @Override
    public List<Dispatcher> getPotentialCollaborators() {
        if (target != null) {
            List<Dispatcher> collaborators = new ArrayList<Dispatcher>();
            collaborators.add(getDispatcher());
            collaborators.add(target.getDispatcher());
            return collaborators;
        } else {
            return super.getPotentialCollaborators();
        }
    }

    @Override
    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {
        ICollaborativeBehaviorQueue queue = super.completeHandshake(title, handshake);

        queue.queueTask(new SyncTask(), 0);

        if (title.equals("initiator")) {
            Person reactor = ((PersonDispatcher) handshake.getParticipant("reactor")).getPerson();
            queue.queueTask(new MoveTo(reactor, 50), 0);
        }
        return queue;
    }

    @Override
    protected float getConversationRange() {
        return super.getConversationRange() * 100;
    }
}
