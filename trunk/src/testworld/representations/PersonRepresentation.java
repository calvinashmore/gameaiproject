/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package testworld.representations;

import processing.core.PConstants;
import processing.core.PGraphics;
import proto.representation.Representation;
import testworld.objects.Person;
import testworld.objects.PersonExpression;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonRepresentation extends Representation<Person> {

    private WordBubble myWordBubble;
    private PersonAppearance appearance;

    public PersonRepresentation(Person target) {
        super(target);
        appearance = target.getAppearance();
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
        g.text(getTarget().getName(), 0, 20);

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

        return x >= -20 && x <= 20 && y >= -60 && y <= -5;
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
        g.fill(196);

        g.rect(-20, -1, 40, 6);
        g.arc(0, 0, 40, 40, (float) Math.PI, 2 * (float) Math.PI);
    }

    private void drawHead(PGraphics g) {
        g.fill(appearance.skinColor1);
        g.stroke(appearance.skinColor2);
        g.ellipse(0, -40, 40, 40);
        drawHair(g);
        drawEyebrows(g);
        drawEyes(g);
        drawMouth(g);
    }

    private void drawEyes(PGraphics g) {
        g.fill(255f);
        g.stroke(appearance.skinColor2);
        g.ellipse(-9, -44, 15, 14);
        g.ellipse(9, -44, 15, 14);


        Vector2d lookAt = getTarget().getLookAt();
        Vector2d pos = getTarget().getLocation().getPosition();
        Vector2d diff = lookAt.subtract(pos);

        double theta = 0, r = 0;

        if (diff.magnitude() > .01) {
            theta = Math.atan2(diff.y, diff.x);

            r = diff.magnitude() / 50;//Math.min(1.0, diff.magnitude() / 80);
            r = r / (1.0 + r);
        }

        float eyeX = (float) (6 * r * Math.cos(theta));
        float eyeY = (float) (6 * r * Math.sin(theta));

        g.fill(0);
        g.stroke(appearance.eyeColor);
        g.ellipse(-8 + eyeX, -44 + eyeY, 2, 2);
        g.ellipse(8 + eyeX, -44 + eyeY, 2, 2);

    }

    private void drawHair(PGraphics g) {
    }

    private void drawEyebrows(PGraphics g) {
        g.stroke(appearance.hairColor2);
        g.noFill();

        PersonExpression expression = getTarget().getExpression();

        if (expression.getEyebrowCurve() > 0) {
            // use curving eyebrows
            float eyebrowCurve = (float) expression.getEyebrowCurve();

            float curveValue = 5 * eyebrowCurve;

            g.arc(-9, -53, 8, curveValue, (float) Math.PI, 2 * (float) Math.PI);
            g.arc(9, -53, 8, curveValue, (float) Math.PI, 2 * (float) Math.PI);

        } else {
            // use slanting eyebrows
            float eyebrowSlant = (float) expression.getEyebrowSlant();
            float dy = 2 * eyebrowSlant;

            float xMin = 5;
            float xMax = 14;
            float yBase = -55;

            g.line(xMin, yBase + dy, xMax, yBase - dy);
            g.line(-xMin, yBase + dy, -xMax, yBase - dy);
        }
    }

    private void drawMouth(PGraphics g) {

        g.stroke(appearance.mouthColor);
        g.noFill();

        PersonExpression expression = getTarget().getExpression();
        float smileAmount = (float) expression.getSmileAmount();

        float smileSize = smileAmount * 8;
        float smileWidth = 15;

        if (smileSize > 0) {
            g.arc(0, -30, smileWidth, smileSize, 0, (float) Math.PI);
        } else {
            g.arc(0, -30 - smileSize / 2, smileWidth, -smileSize, (float) Math.PI, 2 * (float) Math.PI);
        }
    }
}
