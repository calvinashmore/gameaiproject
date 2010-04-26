/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import java.util.ArrayList;
import java.util.List;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import proto.behavior.SyncTask;
import testworld.objects.PersonDispatcher;
import testworld.social.AttributeMap.Operation;
import testworld.social.Needs;
import testworld.tasks.EffectTask;
import testworld.tasks.MoveTo;
import testworld.tasks.SpeechTask;
import utils.math.RandomManager;
import utils.math.Vector2d;

/**
 *
 * @author hartsoka
 */
public class GroupChat
        extends AJointPersonBehavior
        implements IProactiveBehavior
{

    public static final String PROACTIVE_ID = "GroupChatStart";
    public static final String REACTIVE_ID = "GroupChatAccept";

    private static final int RADIUS_PER_PERSON = 50;

    private String id;

    public static GroupChat makeProactive()
    {
        return new GroupChat(InitiationType.proactive, "GroupChatStart");
    }

    public static GroupChat makeReactive()
    {
        return new GroupChat(InitiationType.reactive, "GroupChatAccept");
    }

    private GroupChat(InitiationType initType, String id) {
        super(initType);
        this.id = id;
    }

    @Override
    public CollaborationHandshake makeHandshake(IWorldState ws) {
        if(Math.random() < .7)
            return null;

        CollaborationHandshake handshake =
                new CollaborationHandshake(1, this.getDispatcher(), this);
        return handshake;
    }

    @Override
    public List<Dispatcher> getPotentialCollaborators() {
        List<PersonDispatcher> personDispatchers = getPersonDispatchers();
        sortPeopleByDistance(personDispatchers);
        return new ArrayList<Dispatcher>(personDispatchers);
    }

    @Override
    public boolean confirmHandshake(CollaborationHandshake handshake) {
        return handshake.getParticipants().size() > 1;
    }

    public String getId() {
        return id;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {
        if (title.startsWith("initiator"))
        {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, 1, handshake);

            bq.queueTask(new MoveTo(calculateGroupCenter(handshake),50));
            bq.queueTask(new SpeechTask("I would like to propose a toast!"));
            bq.queueTask(new EffectTask(Needs.GOSSIP, 50, Operation.Subtract));
            bq.queueTask(new SyncTask());

            int numReactors = handshake.getParticipants().size() - 1;
            for (int i = 0; i < numReactors; ++i)
            {
                bq.queueTask(new SyncTask());
            }
            return bq;
        }
        else if (title.startsWith("reactor"))
        {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, 1, handshake);
            
            int myNum = Integer.parseInt(title.replaceFirst("reactor", ""));
            int radius = handshake.getParticipants().size() * RADIUS_PER_PERSON;

            bq.queueTask(new MoveTo(calculateGroupCenter(handshake),radius));
            bq.queueTask(new SyncTask()); // initiator
            
            int numReactors = handshake.getParticipants().size() - 1;
            for (int i = 0; i < numReactors; ++i)
            {
                if (i == myNum)
                {
                    if (i == 0)
                        bq.queueTask(new SpeechTask("For life and liberty!"));
                    else if (i == 1)
                        bq.queueTask(new SpeechTask("Death to the Yankees!"));
                    else if (i >= 2)
                        bq.queueTask(new SpeechTask("Whoa, that much Yankee hate?  Really?"));
                    bq.queueTask(new EffectTask(Needs.GOSSIP, 50, Operation.Subtract));
                }
                bq.queueTask(new SyncTask());
            }
            return bq;
        }
        else
        {
            throw new UnsupportedOperationException();
        }
    }

    public boolean canCollaborate(String id) {
        return id.equals(PROACTIVE_ID);
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {
        int numReactors = handshake.getParticipants().size() - 1;
        if (numReactors >= 4) return false;
        int myNum = numReactors;
        handshake.participate(this.getDispatcher(), this, "reactor" + new Integer(myNum).toString());
        return true;
    }

    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(5);
    }

    private Vector2d calculateGroupCenter(CollaborationHandshake handshake)
    {
        Vector2d center = new Vector2d();
        for (Dispatcher d : handshake.getParticipants().values())
        {
            center = center.add(((PersonDispatcher)d).getPerson().getLocation().getPosition());
        }
        center = center.multiply(1.0 / handshake.getParticipants().size());

        return center;
    }

}
