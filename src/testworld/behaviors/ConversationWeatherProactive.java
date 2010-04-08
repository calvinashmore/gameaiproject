/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.behaviors;

import proto.behavior.ABehaviorTemplate;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ICollaborativeBehavior;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import proto.world.World;
import testworld.tasks.SpeechTask;
import testworld.tasks.SyncTask;

/**
 *
 * @author Calvin Ashmore
 */
public class ConversationWeatherProactive
        extends ABehaviorTemplate
        implements IProactiveBehavior, ICollaborativeBehavior {

    public ConversationWeatherProactive() {
        super(InitiationType.proactive, CollaborationType.collaborative);
    }

    public String getId() {
        return getClass().getName();
    }

    public IBehaviorQueue instantiate(IWorldState ws) {
        
        // copied...
        Dispatcher d = getOwningRole().getOwningDispatcher();
        CollaborationHandshake handshake =
                new CollaborationHandshake(1, getOwningRole().getOwningDispatcher(), this);

        World.getInstance().tryCollaborate(handshake);

        if (handshake.getParticipants().size() == 2) {
            handshake.completeHandshake();
            return handshake.getQueue(d);
        }

        return null;
    }

    public int getImportance(IWorldState ws) {
        return 11;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {

        CollaborativeBehaviorQueue bq = new CollaborativeBehaviorQueue(this, 1, handshake);
        bq.queueTask(new SpeechTask("How about that weather?"));
        bq.queueTask(new SyncTask());
        bq.queueTask(new SyncTask());
        bq.queueTask(new SpeechTask("I sure wish I could go out right now..."));
        bq.queueTask(new SyncTask());
        return bq;
    }
}
