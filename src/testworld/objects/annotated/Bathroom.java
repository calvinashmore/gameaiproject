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
import testworld.social.Stimuli;
import testworld.tasks.EffectTask;
import testworld.tasks.SpeechTask;

/**
 *
 * @author hartsoka
 */
public class Bathroom extends ADefaultAnnotatedItem {

    public Bathroom() {
        this.setRepresentation(new BathroomRepresentation(this));
    }

    @Override
    public List<ITask> getUsageTasks(Person person, IBehaviorQueue behavior) {
        List<ITask> tasks = new LinkedList<ITask>();
        tasks.add(new SpeechTask("*uses bathroom*"));
        tasks.add(new EffectTask(Stimuli.TOILET, 0, Operation.Set));

        return tasks;
    }

    protected class BathroomRepresentation extends Representation<Bathroom> {

        public BathroomRepresentation(Bathroom target) {
            super(target);
        }

        @Override
        public void render(PGraphics g) {
            g.pushMatrix();

            float size = 50;

            g.translate((float) getTarget().getLocation().getPosition().x,
                    (float) getTarget().getLocation().getPosition().y);

            g.stroke(0);
            g.fill(0xffff55ff);

            g.strokeWeight(5);
            g.ellipse(0, 0, size, size);
            g.ellipse(0, 0, size - 10, size - 10);

            g.strokeWeight(1);

            g.popMatrix();
        }
    }
}
