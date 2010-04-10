/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package main;

import debugger.DebuggerFrame;
import java.util.Random;
import processing.core.PApplet;
import proto.ui.UIRoot;
import testworld.Testworld;

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
    }

    @Override
    public void setup() {
        size(500, 500);
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
        ui.onClick(mouseX, mouseY);
    }

    @Override
    public void keyTyped() {
        if (key == 'g') {

            String[] thingsToSay = new String[]{
                "Have we met somewhere before?",
                "I think the host is a real bore.",
                "Does the pate taste funny to you?",
                "Nice jacket!",
                "No, do go on. That's terribly fascinating.",
                "I see that you're certainly enjoying yourself.",
                "..."};

            int index = new Random().nextInt(thingsToSay.length);
            world.getPlayer().pushSpeech(thingsToSay[index]);
        }
    }
}
