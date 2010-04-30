/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.representations;

import processing.core.PConstants;
import processing.core.PGraphics;
import proto.world.World;

/**
 *
 * @author Calvin Ashmore
 */
public class WordBubble {

    private static final int MS_PER_CHARACTER = 25;
    private static final int BASE_OFFSET = 500;
    private static final int Y_TRANSLATION = 65;

    private String text;
    private long expireTime;

    public WordBubble(String text) {
        this.text = text;
        expireTime = World.getInstance().worldTime() + BASE_OFFSET + MS_PER_CHARACTER * text.length();
    }

    public void render(PGraphics g) {

        int textSize = 13;
        int padding = 2;

        float textWidth = g.textWidth(text);

        g.pushMatrix();
        g.translate(0, -textSize - padding - 10 - Y_TRANSLATION);

        g.textSize(textSize);
        g.fill(255f);
        g.stroke(0f);
        g.rect(-padding - textWidth / 2, -padding, textWidth + 2 * padding, textSize + 2 * padding);

        g.stroke(255f);
        g.line(5, textSize + padding, 9, textSize + padding);

        g.noStroke();
        g.triangle(5, textSize + padding, 0, textSize + padding + 10, 10, textSize + padding);
        g.stroke(0f);
        g.line(5, textSize + padding, 0, textSize + padding + 10);
        g.line(10, textSize + padding, 0, textSize + padding + 10);

        g.fill(0f);
        g.textAlign(PConstants.LEFT);
        g.text(text, -textWidth / 2, textSize - 1);

        g.popMatrix();
    }

    boolean isExpired() {
        return World.getInstance().worldTime() >= expireTime;
    }
}
