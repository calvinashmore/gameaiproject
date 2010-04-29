/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package main;

import debugger.DebuggerFrame;
import debugger.EmotionsFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import processing.core.PApplet;
import processing.core.PConstants;
import proto.ui.UIRoot;
import proto.world.BasicObject;
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
    private EmotionsFrame emotions;

    private int game_state = 0;

    public Main() {
        instance = this;
        world = new Testworld();
        ui = new UIRoot();

        debugger = new DebuggerFrame();
        debugger.pack();
        debugger.setVisible(true);
        debugger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        emotions = new EmotionsFrame();
        emotions.pack();
        emotions.setVisible(true);
        emotions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void setup() {
        size(640, 480);
        smooth();
    }

    @Override
    public void draw() {

        if (game_state == 0)
        {
            pushMatrix();
            drawTitle();
            popMatrix();
        }
        else if (game_state == 1)
        {
            world.update();
            world.render(g);
            ui.render(g);

            if (debugger != null) {
                debugger.update();
            }
            if (emotions != null) {
                emotions.update();
            }
        }
        else
        {

        }
    }

    @Override
    public void mouseClicked() {
        //world.getPlayer().forceMoveTo(new Vector2d(mouseX, mouseY));

        if (game_state == 0)
        {
            game_state = 1;
            colorMode(RGB, 255);
        }
        else if (game_state == 1)
        {
            if (mouseButton == PConstants.RIGHT) {
                Vector2d pos = world.transformPoint(mouseX, mouseY);
                new MoveToAction(pos).performAction(world.getPlayer());
            } else {
                ui.onClick(mouseX, mouseY);
            }
        }
    }

    @Override
    public void keyTyped() {
    }

    protected List<Orb> orbs = new ArrayList<Orb>();
    protected int orbCounter = 0;

    public void drawTitle()
    {
        orbCounter++;
        if (orbCounter > 50) {
            orbs.add(
                    new Orb(
                        (float)Math.random() * width,
                        (float)Math.random() * height,
                        (float)Math.random() * 100 + 250));
            orbCounter = 0;
        }

        // draw orbs
        colorMode(RGB, 255);
        background(225, 225, 225);

        for(int i = 0; i < orbs.size(); ++i)
        {
            Orb o = orbs.get(i);

            if (o.isDead())
            {
                orbs.set(i, orbs.get(orbs.size()-1));
                orbs.remove(orbs.size()-1);
                if (i == orbs.size())
                {
                    continue;
                }
                o = (Orb)orbs.get(i);
            }

            o.update();
            o.draw(this);
        }

        // draw text
        this.fill(0);
        this.textAlign(CENTER);
        this.textSize(72);
        this.text("SPY GAMES", width / 2, height / 2);
    }
}
