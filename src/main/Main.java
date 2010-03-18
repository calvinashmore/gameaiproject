/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.awt.Dimension;
import javax.swing.JFrame;
import processing.core.PApplet;

/**
 *
 * @author Calvin Ashmore
 */
public class Main extends PApplet {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        JFrame frame = new JFrame("main");
//        frame.add(new Main());
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
        PApplet.main(new String[] { "--bgcolor=#AF8F92", "main.Main" });
    }

    public Main() {
    }



    @Override
    public void setup() {

        size(500,500);
    }

    @Override
    public void draw() {

        ellipse(100,200,100,150);
    }
}
