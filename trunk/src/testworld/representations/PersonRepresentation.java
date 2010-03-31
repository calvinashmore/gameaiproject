/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.representations;

import processing.core.PConstants;
import processing.core.PGraphics;
import proto.representation.Representation;
import testworld.objects.Person;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonRepresentation extends Representation<Person> {

    public PersonRepresentation(Person target) {
        super(target);
    }

    @Override
    public void render(PGraphics g) {
        g.pushMatrix();
        g.fill(255f);
        g.translate((float) getTarget().getLocation().getPosition().x, (float) getTarget().getLocation().getPosition().y);

        g.ellipse(0, -20, 20, 20);
        g.arc(0, 0, 20, 20, (float) Math.PI, 2 * (float) Math.PI);

        g.fill(0);
        g.textAlign(PConstants.CENTER);
        g.text(getTarget().getName(), 0, -34);

        g.ellipse(0, 0, 0, 0);
        g.popMatrix();
    }
}
