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
import testworld.social.Feelings;
import utils.math.RandomManager;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class PersonRepresentation extends Representation<Person>
{
    private static final float HEAD_DIAMETER = 40;

    private WordBubble myWordBubble;
    private PersonAppearance appearance;

    public PersonRepresentation(Person target)
    {
        super(target);
        appearance = target.getAppearance();
    }

    @Override
    public void render(PGraphics g)
    {
        g.pushMatrix();
        g.translate((float) getTarget().getLocation().getPosition().x, (float) getTarget().getLocation().getPosition().y);

        drawHead(g);
        drawBody(g);
        drawClothes(g);

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
    public boolean inRange(float x, float y)
    {
        x -= getTarget().getLocation().getPosition().x;
        y -= getTarget().getLocation().getPosition().y;

        return x >= -20 && x <= 20 && y >= -60 && y <= -5;
    }

    private void checkSpeech(PGraphics g)
    {
        if (myWordBubble != null && myWordBubble.isExpired()) {
            myWordBubble = null;
            getTarget().popSpeech();
        }

        if (myWordBubble == null && getTarget().peekSpeech() != null) {
            StringBuilder speech = new StringBuilder(getTarget().peekSpeech());
            double alcoholLevel = getTarget().getSocialState().getAttribute(Feelings.DEPRESSANT);
            if (alcoholLevel >= 60 && !speech.toString().startsWith("*")) {
                double flipChance = (40 - (100 - alcoholLevel)) * 0.01 * 0.5;
                for (int i = 0; i < speech.length(); ++i) {
                    if (RandomManager.get().nextDouble() < flipChance) {
                        speech.setCharAt(i, 'm');
                    }
                }
            }
            myWordBubble = new WordBubble(speech.toString());
        }

        if (myWordBubble != null) {
            myWordBubble.render(g);
        }
    }

    private void drawBody(PGraphics g)
    {
        g.stroke(appearance.clothesColor2);
        g.fill(appearance.clothesColor1);
        
        g.rect(-appearance.width/2, -1, appearance.width, 6);
        g.arc(0, 0, appearance.width, appearance.torsoSize*2, (float) Math.PI, 2 * (float) Math.PI);
    }

    private void drawHead(PGraphics g)
    {
        g.pushMatrix();

        // move origin to center of the face
        g.translate(0, -appearance.torsoSize-HEAD_DIAMETER/2);

        drawHairBack(g);
        g.fill(appearance.skinColor1);
        g.stroke(appearance.skinColor2);
        //g.ellipse(0, -appearance.torsoSize-HEAD_DIAMETER/2, HEAD_DIAMETER, HEAD_DIAMETER);
        g.ellipse(0,0, HEAD_DIAMETER, HEAD_DIAMETER);
        drawHairFront(g);
        drawEyebrows(g);
        drawEyes(g);
        drawMouth(g);

        drawFaceAccessory(g);

        g.popMatrix();
    }

    private void drawClothes(PGraphics g)
    {
        g.pushMatrix();

        switch (appearance.clothes) {
            case plain:
                break;
            case bowtie:

                // undershirt
                g.fill(appearance.clothesColors.get(1));
                g.stroke(appearance.clothesColors.get(1));
                g.arc(0, 0, appearance.width, appearance.width, (float)Math.PI *3/ 2 - (float)Math.PI/6, (float)Math.PI *3/ 2 + (float)Math.PI/6);
                
                // tie
                g.translate(0, -appearance.torsoSize+3);
                g.fill(appearance.clothesColors.get(0));
                g.stroke(appearance.clothesColors.get(0));
                g.ellipse(0, 0, 15, 3);
                g.triangle(0, 0, 10, 5, 10, -5);
                g.triangle(0, 0, -10, 5, -10, -5);

                break;

            case officer:
            case jacket:

                // undershirt
                g.fill(appearance.clothesColors.get(1));
                g.stroke(appearance.clothesColors.get(1));
                g.arc(0, 0, appearance.width, appearance.width, (float)Math.PI *3/ 2 - (float)Math.PI/6, (float)Math.PI *3/ 2 + (float)Math.PI/6);

                // tie
                g.fill(appearance.clothesColors.get(0));
                g.stroke(appearance.clothesColors.get(0));
                g.ellipse(0, -appearance.torsoSize+2, 4, 4);
                g.arc(0, -appearance.torsoSize+2, appearance.width, appearance.torsoSize*2-1, (float)Math.PI/2 - (float)Math.PI/6, (float)Math.PI/2 + (float)Math.PI/6);
                //g.arc(0, -appearance.torsoSize+2, 10, 10, (float)Math.PI*3/2 - (float)Math.PI/6, (float)Math.PI*3/2 + (float)Math.PI/6);
                
                // redo overshirt
                g.stroke(appearance.clothesColor2);
                g.fill(appearance.clothesColor1);
                g.rect(-appearance.width/2, -1, appearance.width, 6);
                g.arc(0, 0, appearance.width, appearance.torsoSize*2, (float)Math.PI *3/ 2 + (float)Math.PI/6, (float)Math.PI*2);
                g.arc(0, 0, appearance.width, appearance.torsoSize*2, (float) Math.PI, (float)Math.PI *3/ 2 - (float)Math.PI/6);
                
                break;

            default:
                break;
        }

        g.popMatrix();
    }

    private void drawFaceAccessory(PGraphics g)
    {    
        switch (appearance.faceAccessory) {
            case none:
                break;
            case monocle:
                g.fill(0xaabbccdd);
                //g.stroke(appearance.skinColor2);
                g.stroke(0x00000000);
                g.ellipse(-9, -4, 19, 18); // eye size 15,14
                g.line(-9-19/2, -4, -9-19/2-1, -4+16);
                break;
            case earrings:
                g.fill(0xddccbbaa);
                g.stroke(0x00000000);
                g.rect(-HEAD_DIAMETER/2-1, 2, 2, 10);
                g.rect(HEAD_DIAMETER/2-1, 2, 2, 10);
                break;
        }
    }

    private void drawEyes(PGraphics g)
    {
        g.fill(255f);
        g.stroke(appearance.skinColor2);
        g.ellipse(-9, -4, 15, 14);
        g.ellipse(9, -4, 15, 14);

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
        g.ellipse(-8 + eyeX, -4 + eyeY, 2, 2);
        g.ellipse(8 + eyeX, -4 + eyeY, 2, 2);

    }

    private void drawHairBack(PGraphics g)
    {
        g.stroke(appearance.hairColor2);
        float oldWeight = g.strokeWeight;
        switch (appearance.hair) {
            case straightLong:
                g.strokeWeight(2);
                g.fill(appearance.hairColor1);
                //g.rect(-20, -40, 40, 40);
                g.rect(-20, 0, 40, 40);
                break;
            case straightMid:
                g.strokeWeight(2);
                g.fill(appearance.hairColor1);
                //g.rect(-20, -40, 40, 26);
                g.rect(-20, 0, 40, 26);
                break;
            case straightBob:
                g.strokeWeight(2);
                g.fill(appearance.hairColor1);
                //g.rect(-20, -40, 40, 20);
                g.rect(-20, 0, 40, 20);
                break;
            case longCurls:
                g.fill(appearance.hairColor1);
                for (int i = 0; i <= 3; i++) {
                    float x = 20;
                    //float y = -40 + 5*i;
                    float y = 5*i;
                    g.ellipse(x, y, 7, 5);
                    g.ellipse(-x, y, 7, 5);
                }

        }
        g.strokeWeight(oldWeight);
    }

    private void drawHairFront(PGraphics g)
    {
        g.stroke(appearance.hairColor2);
        float oldWeight = g.strokeWeight;

        switch (appearance.hair) {
            case simpleTopFlat:
                g.strokeWeight(2);
                g.noFill();
                //g.arc(0, -40, 40, 40, (float) (-Math.PI / 2 - .5), (float) (-Math.PI / 2 + .5));
                g.arc(0, 0, 40, 40, (float) (-Math.PI / 2 - .5), (float) (-Math.PI / 2 + .5));
                break;
            case simpleFullFlat:
                g.strokeWeight(2);
                g.noFill();
                //g.arc(0, -40, 40, 40, (float) (-Math.PI / 2 - 1.1), (float) (-Math.PI / 2 + 1.1));
                g.arc(0, 0, 40, 40, (float) (-Math.PI / 2 - 1.1), (float) (-Math.PI / 2 + 1.1));
                break;
            case straightLong:
            case straightMid:
            case straightBob:
                g.strokeWeight(2);
                g.noFill();
                //g.arc(0, -40, 40, 40, (float) (-Math.PI), 0);
                //g.line(-20, -40, -20, -30);
                //g.line(20, -40, 20, -30);
                g.arc(0, 0, 40, 40, (float) (-Math.PI), 0);
                g.line(-20, 0, -20, 10);
                g.line(20, 0, 20, 10);
                break;
            case blob:
                g.fill(appearance.hairColor1);
                //g.ellipse(0, -60, 20, 5);
                g.ellipse(0, -20, 20, 5);
                break;
            case shortTopCurls:
                g.fill(appearance.hairColor1);
                for (int i = -2; i <= 2; i++) {
                    float x = (float) (20 * Math.sin(i * .3));
                    //float y = (float) (-40 - 20 * Math.cos(i * .3));
                    float y = (float) (-20 * Math.cos(i * .3));
                    g.ellipse(x, y, 7, 5);
                }
                break;
            case shortFullCurls:
                g.fill(appearance.hairColor1);
                for (int i = -4; i <= 4; i++) {
                    float x = (float) (20 * Math.sin(i * .3));
                    //float y = (float) (-40 - 20 * Math.cos(i * .3));
                    float y = (float) (-20 * Math.cos(i * .3));
                    g.ellipse(x, y, 7, 5);
                }
                break;
            case longCurls:
                g.fill(appearance.hairColor1);
                for (int i = -5; i <= 5; i++) {
                    float x = (float) (20 * Math.sin(i * .3));
                    //float y = (float) (-40 - 20 * Math.cos(i * .3));
                    float y = (float) (-20 * Math.cos(i * .3));
                    g.ellipse(x, y, 7, 5);
                }
                break;
            case topHat:
                g.fill(appearance.hairColor1);
                g.rect(-12, -HEAD_DIAMETER/2+3, 24, 5);
                g.rect(-9, -HEAD_DIAMETER/2-7, 18, 10);
                break;
            case shortParted:
                g.fill(appearance.hairColor1);
                g.stroke(appearance.hairColor2);
                g.ellipse(-4, -20, 14, 5);
                g.ellipse(8, -20, 8, 4);
                //g.ellipse(-6, -HEAD_DIAMETER/2+1, 20, 5);
                //g.ellipse(10, -HEAD_DIAMETER/2+1, 12, 3);
                break;
        }

        g.strokeWeight(oldWeight);
    }

    private void drawEyebrows(PGraphics g)
    {
        g.stroke(appearance.hairColor2);
        g.noFill();

        PersonExpression expression = getTarget().getExpression();

        if (expression.getEyebrowCurve() > 0) {
            // use curving eyebrows
            float eyebrowCurve = (float) expression.getEyebrowCurve();

            float curveValue = 5 * eyebrowCurve;

            g.arc(-9, -13, 8, curveValue, (float) Math.PI, 2 * (float) Math.PI);
            g.arc(9, -13, 8, curveValue, (float) Math.PI, 2 * (float) Math.PI);

        } else {
            // use slanting eyebrows
            float eyebrowSlant = (float) expression.getEyebrowSlant();
            float dy = 2 * eyebrowSlant;

            float xMin = 5;
            float xMax = 14;
            float yBase = -15;

            g.line(xMin, yBase + dy, xMax, yBase - dy);
            g.line(-xMin, yBase + dy, -xMax, yBase - dy);
        }
    }

    private void drawMouth(PGraphics g)
    {
        g.stroke(appearance.mouthColor);
        g.noFill();

        PersonExpression expression = getTarget().getExpression();
        float smileAmount = (float) expression.getSmileAmount();

        float smileSize = smileAmount * 8;
        float smileWidth = 15;

        if (smileSize > 0) {
            g.arc(0, 10, smileWidth, smileSize, 0, (float) Math.PI);
        } else {
            g.arc(0, 10 - smileSize / 2, smileWidth, -smileSize, (float) Math.PI, 2 * (float) Math.PI);
        }
    }
}
