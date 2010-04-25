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
import testworld.tasks.SpeechTask;
import testworld.tasks.requirements.ProximityRequirement;

/**
 *
 * @author hartsoka
 */
public class DanceFloor extends ADefaultAnnotatedItem {

    protected float size;

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

        tasks.add(new SpeechTask("*shakes groove thing*").queueTaskRequirement(new ProximityRequirement(this, 50)));
        tasks.add(new SpeechTask("*twists and shouts*").queueTaskRequirement(new ProximityRequirement(this, 50)));
        tasks.add(new SpeechTask("*boogeys*").queueTaskRequirement(new ProximityRequirement(this, 50)));
        tasks.add(new SpeechTask("*headbangs*").queueTaskRequirement(new ProximityRequirement(this, 50)));

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
            g.fill(0xaabbccff);
            g.rect(-size/2, -size/2, size, size);

            g.popMatrix();
        }
    }

}
