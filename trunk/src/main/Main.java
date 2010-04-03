/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import processing.core.PApplet;
import testworld.Testworld;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class Main extends PApplet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PApplet.main(new String[]{"--bgcolor=#AF8F92", "main.Main"});
    }

    private Testworld world;

    public Main() {
        world = new Testworld();
    }

    @Override
    public void setup() {
        size(500, 500);
    }

    @Override
    public void draw() {
        world.update();
        world.render(g);
    }

    @Override
    public void mouseClicked() {
        world.getPlayer().forceMoveTo(new Vector2d(mouseX, mouseY));
    }


}
