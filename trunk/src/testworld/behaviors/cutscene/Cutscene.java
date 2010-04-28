/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors.cutscene;

import java.util.LinkedList;
import java.util.List;
import proto.behavior.CollaborationHandshake;
import proto.behavior.Dispatcher;
import proto.behavior.ILatentBehavior;
import proto.behavior.IWorldState;
import testworld.behaviors.AJointPersonBehavior;
import testworld.objects.PersonDispatcher;

/**
 *
 * @author hartsoka
 */
public abstract class Cutscene extends AJointPersonBehavior implements ILatentBehavior
{
    public static final String PROACTIVE_ID = "CutsceneFrankKillsVictim";

    protected String id;

    public abstract String getCutsceneName();

    protected Cutscene(InitiationType type) {
        super(type);
        if (type == InitiationType.latent) {
            this.id = getReactiveID();
        }
        else {
            this.id = getLatentID();
        }
    }

    public String getId() {
        return id;
    }

    public abstract CollaborationHandshake makeHandshakeImpl(IWorldState ws);

    @Override
    public CollaborationHandshake makeHandshake(IWorldState ws)
    {
        CollaborationHandshake handshake = this.makeHandshakeImpl(ws);
        handshake.getBlackboard().put("CUTSCENE", Boolean.TRUE);
        return handshake;
    }
    
    public String getLatentID() {
        return "Start" + this.getCutsceneName();
    }

    public String getReactiveID() {
        return "Start" + this.getCutsceneName();
    }

    public boolean canCollaborate(String id) {
        return id.equals(this.getLatentID());
    }

    public abstract List<String> getInvolvedCast();

    @Override
    public List<Dispatcher> getPotentialCollaborators()
    {
        // we let everyone be a potential collaborator, but then in the
        //  confirm handshake fn, they only do so if in the cast
        List<PersonDispatcher> allPeople = this.getPersonDispatchers();
        List<Dispatcher> dispatchers = new LinkedList<Dispatcher>();
         for (PersonDispatcher p : allPeople) {
            dispatchers.add(p);
        }
        return dispatchers;
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {
        String name = this.getPerson().getName();
        if (getInvolvedCast().contains(name)) {
            handshake.participate(this.getDispatcher(), this, name);
            return true;
        }
        return false;
    }

    @Override
    public boolean confirmHandshake(CollaborationHandshake handshake) {
        for (String name : this.getInvolvedCast()) {
            if (!handshake.getParticipants().containsKey(name))
                return false;
        }
        return true;
    }

}
