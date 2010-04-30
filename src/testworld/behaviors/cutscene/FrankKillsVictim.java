/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.behaviors.cutscene;

import java.util.LinkedList;
import java.util.List;
import main.Main;
import proto.behavior.ATask;
import proto.behavior.CollaborationHandshake;
import proto.behavior.CollaborativeBehaviorQueue;
import proto.behavior.ICollaborativeBehaviorQueue;
import proto.behavior.IWorldState;
import proto.behavior.SyncTask;
import testworld.game.Plot;
import testworld.objects.Person;
import testworld.objects.PersonDispatcher;
import testworld.tasks.Chase;
import testworld.tasks.LookAtTask;
import testworld.tasks.MoveTo;
import testworld.tasks.RemoveBehaviorTemplateTask;
import testworld.tasks.SpeechTask;
import utils.math.RandomManager;
import utils.math.Vector2d;

/**
 *
 * @author hartsoka
 */
public class FrankKillsVictim extends Cutscene
{
    public static final int PRIORITY = 500;

    public static FrankKillsVictim makeLatent() {
        return new FrankKillsVictim(InitiationType.latent);
    }

    public static FrankKillsVictim makeReactive() {
        return new FrankKillsVictim(InitiationType.reactive);
    }

    protected FrankKillsVictim(InitiationType type) {
        super(type);
    }

    @Override
    public CollaborationHandshake makeHandshakeImpl(IWorldState ws)
    {
        CollaborationHandshake handshake = new CollaborationHandshake(PRIORITY, this.getDispatcher(), this, this.getPerson().getName());
        return handshake;
    }

    @Override
    public List<String> getInvolvedCast() {
        List<String> names = new LinkedList<String>();
        names.add("EVERYONE");
        names.add("Player");
        names.add("Victim");
        names.add("Frank");
        return names;
    }

    public String getCutsceneName() {
        return "FrankKillsVictim";
    }

    public ICollaborativeBehaviorQueue completeHandshake(String title, CollaborationHandshake handshake)
    {
        ICollaborativeBehaviorQueue cbq = new CollaborativeBehaviorQueue(this, PRIORITY, handshake);
        Person frank = ((PersonDispatcher)handshake.getParticipant("Frank")).getPerson();
        Person victim = ((PersonDispatcher)handshake.getParticipant("Victim")).getPerson();
        Person player = ((PersonDispatcher)handshake.getParticipant("Player")).getPerson();

        Vector2d awayVec = victim.getLocation().getPosition().subtract(frank.getLocation().getPosition()).getNormalizedVector().multiply(100);
        Vector2d runToVec = victim.getLocation().getPosition().add(awayVec);

        if (title.equals("Player")) {
            cbq.queueTask(new RemoveBehaviorTemplateTask(this));
            cbq.queueTask(new Chase(victim, 200));
            cbq.queueTask(new Chase(frank, 200));
            cbq.queueTask(new SyncTask()); // 1
            cbq.queueTask(new Chase(victim, 200));
            cbq.queueTask(new Chase(frank, 200));
            cbq.queueTask(new SyncTask()); // 2
            cbq.queueTask(new Chase(victim, 200));
            cbq.queueTask(new Chase(frank, 200));
            cbq.queueTask(new SyncTask()); // 3
            cbq.queueTask(new Chase(victim, 200));
            cbq.queueTask(new Chase(frank, 200));
            cbq.queueTask(new SyncTask()); // 4
            cbq.queueTask(new Chase(victim, 200));
            cbq.queueTask(new Chase(frank, 200));
            cbq.queueTask(new SyncTask()); // 5
            cbq.queueTask(new Chase(victim, 200));
            cbq.queueTask(new Chase(frank, 200));
            cbq.queueTask(new SyncTask()); // 6
            cbq.queueTask(new Chase(victim, 200));
            cbq.queueTask(new Chase(frank, 200));
            cbq.queueTask(new SyncTask()); // 7
            cbq.queueTask(new Chase(victim, 200));
            cbq.queueTask(new Chase(frank, 200));
            cbq.queueTask(new SyncTask()); // 8
            cbq.queueTask(new SyncTask()); // 9

            cbq.queueTask(new ATask() {

                @Override
                protected void runImpl() {
                    Main.getInstance().game_state = Main.WIN;
                }

                public void resume() {
                    
                }

            });
        }
        else if (title.equals("Frank")) {
            cbq.queueTask(new Chase(victim, 150));
            cbq.queueTask(new SyncTask()); // 1
            cbq.queueTask(new SpeechTask("You bastard!",victim));
            cbq.queueTask(new SyncTask()); // 2
            cbq.queueTask(new SyncTask()); // 3
            cbq.queueTask(new SpeechTask("*pulls out knife*",victim));
            cbq.queueTask(new SyncTask()); // 4
            cbq.queueTask(new SyncTask()); // 5
            cbq.queueTask(new Chase(victim, 50));
            cbq.queueTask(new SpeechTask("*stabs Victim*",victim));
            cbq.queueTask(new SyncTask()); // 6
            cbq.queueTask(new SyncTask()); // 7
            cbq.queueTask(new SpeechTask("Oh no!  What have I done?",player));
            cbq.queueTask(new SyncTask()); // 8
            cbq.queueTask(new SpeechTask("*stabs self*",frank));
            cbq.queueTask(new SpeechTask("*collapses*",frank));
            cbq.queueTask(new SyncTask()); // 9
        }
        else if (title.equals("Victim")) {
            cbq.queueTask(new SyncTask()); // 1
            cbq.queueTask(new SyncTask()); // 2
            cbq.queueTask(new SpeechTask("What's wrong, Frank?",frank));
            cbq.queueTask(new SyncTask()); // 3
            cbq.queueTask(new SyncTask()); // 4
            cbq.queueTask(new SpeechTask("Help!",player));
            cbq.queueTask(new SyncTask()); // 5
            cbq.queueTask(new MoveTo(runToVec));
            cbq.queueTask(new SyncTask()); // 6
            cbq.queueTask(new SpeechTask("*coughs blood, dies*",victim));
            cbq.queueTask(new SyncTask()); // 7
            cbq.queueTask(new SyncTask()); // 8
            cbq.queueTask(new SyncTask()); // 9
        }
        else // EVERYONE ELSE
        {
            cbq.queueTask(new SyncTask()); // 1
            cbq.queueTask(new LookAtTask(frank));
            cbq.queueTask(new Chase(victim, 300));
            cbq.queueTask(new Chase(frank, 300));
            cbq.queueTask(new SyncTask()); // 2
            cbq.queueTask(new LookAtTask(victim));
            cbq.queueTask(new SyncTask()); // 3
            cbq.queueTask(new LookAtTask(frank));
            cbq.queueTask(new SyncTask()); // 4
            int rand = RandomManager.get().nextInt(4);
            switch (rand) {
                case 0:
                case 3:
                    cbq.queueTask(new SpeechTask("*gasp*",victim)); break;
                case 1:
                    cbq.queueTask(new SpeechTask("Oh my god!",victim)); break;
                case 2:
                    cbq.queueTask(new SpeechTask("Watch out!",victim)); break;
            }
            cbq.queueTask(new LookAtTask(victim));
            cbq.queueTask(new SyncTask()); // 5
            cbq.queueTask(new Chase(victim, 400));
            cbq.queueTask(new Chase(frank, 400));
            cbq.queueTask(new SyncTask()); // 6
            if (this.getPerson().getName().equals("Harriet"))
                cbq.queueTask(new SpeechTask("No! Frank!"));
            cbq.queueTask(new LookAtTask(victim));
            cbq.queueTask(new SyncTask()); // 7
            cbq.queueTask(new LookAtTask(frank));
            cbq.queueTask(new SyncTask()); // 8
            if (this.getPerson().getName().equals("Gayle")) {
                cbq.queueTask(new SpeechTask("FRANK!"));
                cbq.queueTask(new Chase(frank, 50));
            }
            cbq.queueTask(new SyncTask()); // 9
        }
        return cbq;
    }

    public boolean activate(IWorldState iws) {
        if (Plot.frankDrivenToMurder.isFound()) {
            return true;
        }
        return false;
    }

}
