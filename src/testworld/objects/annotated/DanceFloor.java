/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testworld.objects.annotated;

import java.util.LinkedList;
import java.util.List;
import processing.core.PGraphics;
import proto.behavior.IBehaviorQueue;
import proto.behavior.ITask;
import proto.representation.Representation;
import testworld.objects.Person;
import testworld.social.AttributeMap.Operation;
import testworld.social.Feelings;
import testworld.tasks.EffectTask;
import testworld.tasks.MoveTo;
import testworld.tasks.SpeechTask;
import testworld.tasks.requirements.ProximityRequirement;
import utils.math.RandomManager;
import utils.math.Vector2d;

/**
 *
 * @author hartsoka
 */
public class DanceFloor extends ADefaultAnnotatedItem {

    protected float size;

    @Override
    public String getName() {
        return "Dance Floor";
    }

    public DanceFloor(float size)
    {
        super(size/2);
        this.setRepresentation(new DanceFloorRepresentation(this));
        this.size = size;
    }

    @Override
    public List<ITask> getUsageTasks(Person person, IBehaviorQueue behavior)
    {
        List<ITask> tasks = new LinkedList<ITask>();

        tasks.add(new SpeechTask("*shakes groove thing*").queueTaskRequirement(new ProximityRequirement(this, size/3)));
        tasks.add(new EffectTask(Feelings.EUPHORIA, 10, Operation.Add));

        tasks.add(new MoveTo(this.getLocation().getPosition().add(new Vector2d(RandomManager.get().nextDouble()*size-size/2, RandomManager.get().nextDouble()*size-size/2))));
        tasks.add(new SpeechTask("*twists and shouts*").queueTaskRequirement(new ProximityRequirement(this, size/2)));
        tasks.add(new EffectTask(Feelings.EUPHORIA, 10, Operation.Add));

        tasks.add(new MoveTo(this.getLocation().getPosition().add(new Vector2d(RandomManager.get().nextDouble()*size-size/2, RandomManager.get().nextDouble()*size-size/2))));
        tasks.add(new SpeechTask("*boogeys*").queueTaskRequirement(new ProximityRequirement(this, size/2)));
        tasks.add(new EffectTask(Feelings.EUPHORIA, 10, Operation.Add));

        tasks.add(new MoveTo(this.getLocation().getPosition().add(new Vector2d(RandomManager.get().nextDouble()*size-size/2, RandomManager.get().nextDouble()*size-size/2))));
        tasks.add(new SpeechTask("*headbangs*").queueTaskRequirement(new ProximityRequirement(this, size/2)));
        tasks.add(new EffectTask(Feelings.EUPHORIA, 10, Operation.Add));

        return tasks;
    }

    public float getSize()
    {
        return size;
    }

    protected class DanceFloorRepresentation extends Representation<DanceFloor>
    {

        public DanceFloorRepresentation(DanceFloor target) {
            super(target);
        }

        @Override
        public void render(PGraphics g)
        {
            g.pushMatrix();

            float size = getTarget().getSize();

            g.translate((float)getTarget().getLocation().getPosition().x,
                        (float)getTarget().getLocation().getPosition().y);

            g.stroke(0);
            g.fill(0x20bbccff);
            g.rect(-size/2, -size/2, size, size);

            g.popMatrix();
        }
    }

}
