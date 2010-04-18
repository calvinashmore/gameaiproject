/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package main;

import debugger.DebuggerFrame;
import javax.swing.JFrame;
import processing.core.PApplet;
import processing.core.PConstants;
import proto.ui.UIRoot;
import testworld.Testworld;
import testworld.actions.MoveToAction;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class Main extends PApplet {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{"--bgcolor=#AF8F92", "main.Main"});
    }
    private Testworld world;
    private UIRoot ui;
    private DebuggerFrame debugger;

    public Main() {
        instance = this;
        world = new Testworld();
        ui = new UIRoot();

        debugger = new DebuggerFrame();
        debugger.pack();
        debugger.setVisible(true);
        debugger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void setup() {
        size(640, 480);
        smooth();
    }

    @Override
    public void draw() {
        world.update();
        world.render(g);
        ui.render(g);

        if (debugger != null) {
            debugger.update();
        }
    }

    @Override
    public void mouseClicked() {
        //world.getPlayer().forceMoveTo(new Vector2d(mouseX, mouseY));

        if (mouseButton == PConstants.RIGHT) {
            Vector2d pos = world.transformPoint(mouseX, mouseY);
            new MoveToAction(pos).performAction(world.getPlayer());
        } else {
            ui.onClick(mouseX, mouseY);
        }
    }

    @Override
    public void keyTyped() {
    }
}
