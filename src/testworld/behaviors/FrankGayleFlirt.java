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
import testworld.game.Cast;
import testworld.game.Plot;
import testworld.tasks.MoveTo;
import testworld.tasks.RemoveBehaviorTemplateTask;
import testworld.tasks.SpeechTask;
import testworld.tasks.TokenTask;
import utils.math.Vector2d;

/**
 * This is a special event whereby Frank flirts with Gayle.
 * @author Calvin Ashmore
 */
public class FrankGayleFlirt
        extends AJointPersonBehavior
        implements IProactiveBehavior {

    public static final String PROACTIVE_ID = "FrankGayleFlirtStart";
    public static final String REACTIVE_ID = "FrankGayleFlirtAccept";
    private String id;

    public static FrankGayleFlirt makeProactive() {
        return new FrankGayleFlirt(InitiationType.proactive, PROACTIVE_ID);
    }

    public static FrankGayleFlirt makeReactive() {
        return new FrankGayleFlirt(InitiationType.reactive, REACTIVE_ID);
    }

    private FrankGayleFlirt(InitiationType initType, String id) {
        super(initType);
        this.id = id;
    }

    @Override
    public CollaborationHandshake makeHandshake(IWorldState ws) {

        CollaborationHandshake handshake =
                new CollaborationHandshake(100, this.getDispatcher(), this);
        return handshake;
    }

    @Override
    public List<Dispatcher> getPotentialCollaborators() {
        List<Dispatcher> dispatchers = new ArrayList<Dispatcher>();
        dispatchers.add(Cast.player.getDispatcher());
        dispatchers.add(Cast.gayle.getDispatcher());
        return dispatchers;
    }

    @Override
    public boolean confirmHandshake(CollaborationHandshake handshake) {

        //Vector2d frankPos = Cast.frank.getLocation().getPosition();
        Vector2d gaylePos = Cast.gayle.getLocation().getPosition();
        Vector2d harrietPos = Cast.harriet.getLocation().getPosition();
        Vector2d playerPos = Cast.player.getLocation().getPosition();

//        if (gaylePos.distance(harrietPos) < 100) {
//            return false;
//        }
//
//        if (gaylePos.distance(playerPos) > 200) {
//            return false;
//        }

        return handshake.getParticipants().size() == 3;
    }

    public String getId() {
        return id;
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake) {
        if (title.startsWith("initiator")) {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, 1, handshake);

            bq.queueTask(new MoveTo(Cast.gayle));
            bq.queueTask(new SpeechTask("Hey beautiful!", Cast.gayle));
            bq.queueTask(new SyncTask());
            bq.queueTask(new SyncTask());
            bq.queueTask(new SpeechTask("Later then?", Cast.gayle));
            bq.queueTask(new RemoveBehaviorTemplateTask(this));
            return bq;

        } else if (getDispatcher() == Cast.gayle.getDispatcher()) {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, 1, handshake);

            bq.queueTask(new SyncTask()); // initiator
            bq.queueTask(new SpeechTask("Frank, not now!", Cast.frank));
            bq.queueTask(new SyncTask());
            return bq;

        } else if (getDispatcher() == Cast.player.getDispatcher()) {
            ICollaborativeBehaviorQueue bq =
                    new CollaborativeBehaviorQueue(this, 1, handshake);
            bq.queueTask(new SyncTask());
            bq.queueTask(new SpeechTask("(Hmmm..... is Frank flirting with Gayle?)", Cast.frank));
            bq.queueTask(new TokenTask(Plot.seenFrankAndGayleFlirt));
            return bq;

        } else {
            throw new UnsupportedOperationException();
        }
    }

    public boolean canCollaborate(String id) {
        return id.equals(PROACTIVE_ID);
    }

    public boolean tryCollaboration(CollaborationHandshake handshake) {
        int numReactors = handshake.getParticipants().size() - 1;
        int myNum = numReactors;
        handshake.participate(this.getDispatcher(), this, "reactor" + myNum);
        return true;
    }

    public int getImportance(IWorldState ws) {
        return 1000;
    }
}
