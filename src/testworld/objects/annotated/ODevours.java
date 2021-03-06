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
import testworld.social.AttributeMap;
import testworld.social.Needs;
import testworld.tasks.EffectTask;
import testworld.tasks.SpeechTask;

/**
 *
 * @author hartsoka
 */
public class ODevours extends ADefaultAnnotatedItem {

    public ODevours() {
        this.setRepresentation(new ODevoursRepresentation(this));
    }

    @Override
    public String getName() {
        return "O'Devours";
    }

    @Override
    public List<ITask> getUsageTasks(Person person, IBehaviorQueue behavior) {
        List<ITask> tasks = new LinkedList<ITask>();
        tasks.add(new SpeechTask("*munch munch*"));
        tasks.add(new EffectTask(Needs.FOOD, 0, AttributeMap.Operation.Set));
        tasks.add(new EffectTask(Needs.TOILET, 10, AttributeMap.Operation.Add));

        return tasks;
    }

    protected class ODevoursRepresentation extends Representation<ODevours> {

        public ODevoursRepresentation(ODevours target) {
            super(target);
        }

        @Override
        public boolean inRange(float x, float y) {
            x -= getTarget().getLocation().getPosition().x;
            y -= getTarget().getLocation().getPosition().y;
            return Math.sqrt(x * x + y * y) < 50;
        }

        @Override
        public void render(PGraphics g) {
            g.pushMatrix();

            float size = 50;

            g.translate((float) getTarget().getLocation().getPosition().x,
                    (float) getTarget().getLocation().getPosition().y);

            g.stroke(0);
            g.fill(0xff55ff55);

            g.strokeWeight(5);
            g.ellipse(0, 0, size, size);
            g.ellipse(0, 0, size - 10, size - 10);

            g.strokeWeight(1);

            g.popMatrix();
        }
    }
}
