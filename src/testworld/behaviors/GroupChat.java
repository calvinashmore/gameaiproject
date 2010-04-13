/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import proto.behavior.AJointBehavior;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.Dispatcher;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IProactiveBehavior;
import proto.behavior.IWorldState;
import proto.behavior.text.SyncTask;
import proto.world.World;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.tasks.SpeechTask;
import utils.math.RandomManager;

/**
 *
 * @author hartsoka
 */
public class GroupChat extends AJointBehavior implements IProactiveBehavior {

    public static final String PROACTIVE_ID = "GroupChatStart";
    public static final String REACTIVE_ID = "GroupChatAccept";

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
        CollaborationHandshake handshake =
                new CollaborationHandshake(1, this.getDispatcher(), this);
        return handshake;
    }

    @Override
    public List<Dispatcher> getPotentialCollaborators() {
        List<Dispatcher> dispatchers = World.getInstance().getDispatchers();

        final Person me = ((PersonDispatcher)this.getDispatcher()).getPerson();

        Collections.sort(dispatchers, new Comparator<Dispatcher>(){

            public int compare(Dispatcher o1, Dispatcher o2) {
                Person p1 = ((PersonDispatcher)o1).getPerson();
                Person p2 = ((PersonDispatcher)o2).getPerson();

                double dist1 = me.getLocation().getPosition().subtract(p1.getLocation().getPosition()).magnitude();
                double dist2 = me.getLocation().getPosition().subtract(p2.getLocation().getPosition()).magnitude();

                if (dist1 == dist2) return 0;
                else if (dist1 < dist2) return -1;
                else return 1;
            }

        });

        return dispatchers;
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
            bq.queueTask(new SpeechTask("I would like to propose a toast!"));
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
        int myNum = numReactors;
        handshake.participate(this.getDispatcher(), this, "reactor" + new Integer(myNum).toString());
        return true;
    }

    public int getImportance(IWorldState ws) {
        return RandomManager.get().nextInt(5);
    }

}
