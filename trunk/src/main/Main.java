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

    public int game_state = 0;
    public static final int TITLE = 0;
    public static final int PLAY = 1;
    public static final int WIN = 2;
    public static final int INFO = 3;

    public Main() {
        instance = this;
        world = new Testworld();
        ui = new UIRoot();

        debugger = new DebuggerFrame();
        debugger.pack();
//        debugger.setVisible(true);
//        debugger.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        emotions = new EmotionsFrame();
        emotions.pack();
//        emotions.setVisible(true);
//        emotions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //emotions.setLocation(width, debugger.getLocation().y);
    }

    @Override
    public void setup() {
        size(640, 480);
        smooth();
    }

    @Override
    public void draw() {

        if (game_state == TITLE)
        {
            pushMatrix();
            drawTitle();
            popMatrix();
        }
        else if (game_state == INFO)
        {
            pushMatrix();
            drawInfo();
            popMatrix();
        }
        else if (game_state == PLAY)
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
        else if (game_state == WIN)
        {
            pushMatrix();
            drawWinScreen();
            popMatrix();
        }
    }

    @Override
    public void mouseClicked() {
        //world.getPlayer().forceMoveTo(new Vector2d(mouseX, mouseY));

        if (game_state == TITLE)
        {
            game_state = INFO;
        }
        else if (game_state == INFO)
        {
            game_state = PLAY;
            colorMode(RGB, 255);
        }
        else if (game_state == PLAY)
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

    @Override
    public void keyPressed() {
//        if (key == 'w') {
//            game_state = 2;
//        }
        if(key == ' ') {
            world.setPaused(!world.isPaused());
        }
        if(key == 'D') {
            debugger.setVisible(true);
            emotions.setVisible(true);
        }
    }

    protected List<Orb> orbs = new ArrayList<Orb>();
    protected int orbCounter = 0;
    protected int orbRate = 30;

    public void drawTitle()
    {
        drawOrbs();

        this.fill(0);
        this.textAlign(CENTER);
        this.textSize(72);
        this.text("SPY GAMES", width / 2, height / 2 - 50);

        this.textSize(24);
        this.text("Click to Continue", width / 2, height / 2 + 50);
    }

    public void drawInfo()
    {
        orbRate = 100;
        drawOrbs();

        this.fill(0);
        this.textAlign(CENTER);
        this.textSize(24);

        StringBuilder txt = new StringBuilder();
        txt.append("Welcome to Spy Games.\n\n");
        txt.append("Your goal is to kill Mr. Victim.\n");
        txt.append("But you can't do it yourself!\n");
        txt.append("That would be too suspicious.\n");
        txt.append("So, you must get someone to do it for you...\n\n");
        txt.append("Left-click on a character to interact with them.\n");
        txt.append("Right-click to walk to a location.\n\n");
        txt.append("Please click to continue.");
        this.text(txt.toString(), width / 2, 40);
    }

    public void drawWinScreen()
    {
        drawOrbs();

        this.fill(0);
        this.textAlign(CENTER);
        this.textSize(72);
        this.text("CONGRATS", width / 2, height / 2);
    }

    public void drawOrbs()
    {
        // add new orbs if necessary
        orbCounter++;
        if (orbCounter > orbRate) {
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
    }
}
