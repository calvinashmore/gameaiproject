/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.ui;

import java.util.List;
import main.Main;
import processing.core.PGraphics;
import processing.core.PMatrix;
import processing.core.PMatrix2D;
import processing.core.PVector;
import proto.game.PlayerAction;
import proto.world.Entity;
import proto.world.World;

/**
 *
 * @author Calvin Ashmore
 */
public class RadialMenu {

    private List<PlayerAction> actions;
    private Entity clickedOn;
    private boolean alive = true;
    private float xPos, yPos;
    private int selectedIndex = -1;

    public RadialMenu(Entity clickedOn, List<PlayerAction> actions, float xPos, float yPos) {
        this.actions = actions;
        this.clickedOn = clickedOn;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public boolean isAlive() {
        return alive;
    }

    public void render(PGraphics g) {

        g.pushMatrix();
        g.translate(xPos, yPos);
        if (clickedOn == null) {
            g.fill(255f, 0f, 0f);
            g.ellipse(0, 0, 20, 20);
        } else {
            renderText(g, clickedOn.getName(), -1);
        }
        g.popMatrix();

        selectedIndex = -1;

        int radius = 50;
        for (int index = 0; index < actions.size(); index++) {
            //for (PlayerAction playerAction : actions) {

            float theta = (float) index / actions.size();
            float dx = (float) (radius * Math.sin(theta * 2 * Math.PI));
            float dy = (float) -(radius * Math.cos(theta * 2 * Math.PI));

            g.pushMatrix();
            g.translate(xPos + dx, yPos + dy);
            renderText(g, actions.get(index).getName(), index);
            g.popMatrix();
        }
    }

    private void renderText(PGraphics g, String text, int index) {
        float textWidth = g.textWidth(text);
        float textSize = 13;
        int padding = 3;

        g.textSize(textSize);

        Main main = Main.getInstance();
        PVector pv = new PVector(main.mouseX, main.mouseY);
        PVector pvnew = new PVector();
        PMatrix imatrix = new PMatrix2D(main.getMatrix());
        imatrix.invert();
        imatrix.mult(pv, pvnew);
        boolean intersects =
                pvnew.x > -textWidth / 2 - padding &&
                pvnew.x < textWidth / 2 + padding &&
                pvnew.y > -padding &&
                pvnew.y < textSize + padding;

        if (intersects) {
            selectedIndex = index;
            g.fill(0f, 0f, 255f);
        } else {
            g.fill(128f, 128f, 255f);
        }
        g.rect(-textWidth / 2 - padding, -padding, textWidth + 2 * padding, textSize + 2 * padding);

        g.fill(255f);
        g.text(text, 0, textSize);
    }

    /**
     * Returns true if the menu caught the click.
     * The click will also dismiss the menu.
     * @param x
     * @param y
     * @return
     */
    public boolean onClick(float x, float y) {
        alive = false;

        if (selectedIndex >= 0) {
            actions.get(selectedIndex).performAction((Entity) World.getInstance().getPlayer());
        }

        return false;
    }
}
