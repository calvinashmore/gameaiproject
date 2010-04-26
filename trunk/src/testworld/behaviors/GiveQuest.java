/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import java.util.LinkedList;
import java.util.List;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.ILatentBehavior;
import proto.behavior.IWorldState;
import testworld.PlayerImplementation;

/**
 *
 * @author hartsoka
 */
public class GiveQuest
        extends AJointPersonBehavior
        implements ILatentBehavior
{

    protected static final int REQUEST_PRIORITY = 2;

    protected static final int PROXIMITY = 50;

    protected String id;

    protected static final String LATENT_ID = "GiveQuest";
    protected static final String REACTIVE_ID = "ReceiveQuest";


    public GiveQuest() {
        super(InitiationType.latent);
    }

    @Override
    public CollaborationHandshake makeHandshake(IWorldState ws) {
        CollaborationHandshake handshake =
                new CollaborationHandshake(REQUEST_PRIORITY, this.getDispatcher(), this);
        return handshake;
    }

    @Override
    public List<Dispatcher> getPotentialCollaborators() {
        List<Dispatcher> collabs = new LinkedList<Dispatcher>();
        collabs.add(PlayerImplementation.p1.getDispatcher());
        return collabs;
    }

    @Override
    public boolean confirmHandshake(CollaborationHandshake handshake) {
        return handshake.getParticipants().size() == 2;
    }

    public String getId() {
        return this.id;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {
        if (title.startsWith("initiator"))
        {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, REQUEST_PRIORITY, handshake);



            return bq;
        }
        else if (title.startsWith("reactor"))
        {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, REQUEST_PRIORITY, handshake);



            return bq;
        }
        else
        {
            throw new UnsupportedOperationException();
        }
    }

    public boolean canCollaborate(String id) {
        return id.equals(LATENT_ID);
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {

        // player should always agree to participate
        handshake.participate(this.getDispatcher(), this);
        return true;
    }

    public boolean activate(IWorldState iws) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
