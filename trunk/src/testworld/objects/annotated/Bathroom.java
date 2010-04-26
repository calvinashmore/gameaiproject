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
import testworld.social.Needs;
import testworld.tasks.EffectTask;
import testworld.tasks.SpeechTask;
import testworld.tasks.requirements.ProximityRequirement;

/**
 *
 * @author hartsoka
 */
public class Bathroom extends ADefaultAnnotatedItem {

    public Bathroom() {
        this.setRepresentation(new BathroomRepresentation(this));
    }

    @Override
    public String getName() {
        return "Bathroom";
    }

    @Override
    public List<ITask> getUsageTasks(Person person, IBehaviorQueue behavior) {
        List<ITask> tasks = new LinkedList<ITask>();
        tasks.add(new EffectTask(Needs.TOILET, 0, Operation.Set));
        tasks.add(new EffectTask(Feelings.DEPRESSANT, 10, Operation.Subtract));
        tasks.add(new EffectTask(Feelings.STIMULANT, 5, Operation.Subtract));
        tasks.add(new SpeechTask("*uses bathroom*").queueTaskRequirement(new ProximityRequirement(this, usageRange)));

        return tasks;
    }

    protected class BathroomRepresentation extends Representation<Bathroom> {

        public BathroomRepresentation(Bathroom target) {
            super(target);
        }

        @Override
        public boolean inRange(float x, float y) {
            x -= getTarget().getLocation().getPosition().x;
            y -= getTarget().getLocation().getPosition().y;
            return Math.sqrt(x * x + y * y) < usageRange;
        }

        @Override
        public void render(PGraphics g) {
            g.pushMatrix();

            float size = usageRange;

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
