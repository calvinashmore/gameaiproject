/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.ui;

import java.util.List;
import processing.core.PGraphics;
import proto.game.PlayerAction;
import proto.world.Entity;
import proto.world.World;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class UIRoot {

    private static UIRoot instance;

    public static UIRoot getInstance() {
        return instance;
    }

    public UIRoot() {
        instance = this;
    }
    private RadialMenu activeMenu;

    public void render(PGraphics g) {
        if (activeMenu != null && activeMenu.isAlive()) {
            activeMenu.render(g);
        }
    }

    public boolean onClick(float x, float y) {

        if (activeMenu != null && activeMenu.isAlive()) {
            return activeMenu.onClick(x, y);
        }

        World world = World.getInstance();
        Vector2d pos = world.transformPoint(x, y);
        Entity entity = world.getEntityAt((float) pos.x, (float) pos.y);
        System.out.println("clicked on: "+entity);

        List<PlayerAction> actions = world.getPlayer().getActions(entity, pos);
        activeMenu = new RadialMenu(entity, actions, x, y);
        return false;
    }
}
