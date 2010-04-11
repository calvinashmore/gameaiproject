/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.representations;

import processing.core.PConstants;
import processing.core.PGraphics;
import proto.representation.Representation;
import testworld.objects.Person;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonRepresentation extends Representation<Person> {

    private WordBubble myWordBubble;

    public PersonRepresentation(Person target) {
        super(target);
    }

    @Override
    public void render(PGraphics g) {
        g.pushMatrix();
        g.translate((float) getTarget().getLocation().getPosition().x, (float) getTarget().getLocation().getPosition().y);

        drawHead(g);
        drawBody(g);

        g.fill(0);
        g.textAlign(PConstants.CENTER);
        g.textSize(14);
        g.text(getTarget().getName(), 0, 15);

        checkSpeech(g);

        // a dot to represent the zero point for this character.
        g.ellipse(0, 0, 0, 0);

        g.popMatrix();
    }

    /**
     * Returns true if the given coordinates overlap the representation.
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean inRange(float x, float y) {
        x -= getTarget().getLocation().getPosition().x;
        y -= getTarget().getLocation().getPosition().y;

        return x >= -10 && x <= 10 && y >= -30 && y <= 0;
    }

    private void checkSpeech(PGraphics g) {
        if (myWordBubble != null && myWordBubble.isExpired()) {
            myWordBubble = null;
            getTarget().popSpeech();
        }

        if (myWordBubble == null && getTarget().peekSpeech() != null) {
            myWordBubble = new WordBubble(getTarget().peekSpeech());
        }

        if (myWordBubble != null) {
            myWordBubble.render(g);
        }
    }

    private void drawBody(PGraphics g) {
        g.fill(128);
        g.arc(0, 0, 24, 24, (float) Math.PI, 2 * (float) Math.PI);
    }

    private void drawHead(PGraphics g) {
        g.fill(255f);
        g.ellipse(0, -24, 24, 24);
        drawEyes(g);
    }

    private void drawEyes(PGraphics g) {
        g.ellipse(-6, -26, 8, 8);
        g.ellipse(6, -26, 8, 8);


        Vector2d lookAt = getTarget().getLookAt();
        Vector2d pos = getTarget().getLocation().getPosition();
        Vector2d diff = lookAt.subtract(pos);

        double theta = 0, r = 0;

        if (diff.magnitude() > .01) {
            theta = Math.atan2(diff.y, diff.x);

            r = diff.magnitude() / 50;//Math.min(1.0, diff.magnitude() / 80);
            r = r/(1.0+r);
        }

        float eyeX = (float) (3 * r * Math.cos(theta));
        float eyeY = (float) (3 * r * Math.sin(theta));

        g.fill(0);
        g.ellipse(-6 + eyeX, -26 + eyeY, 2, 2);
        g.ellipse(6 + eyeX, -26 + eyeY, 2, 2);

    }
}
