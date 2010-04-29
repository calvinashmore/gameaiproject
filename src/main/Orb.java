/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import processing.core.PApplet;

/**
 *
 * @author hartsoka
 */
public class Orb
{
 float x;
 float y;
 float w;
 float maxW;

 //color c;
 float h;

 public Orb(float x, float y)
 {
  this(x, y, (float)Math.random()*100 + 100);
 }

 public Orb(float x, float y, float maxW)
 {
  this.x = x;
  this.y = y;
  this.maxW = maxW;

  this.w = 1;
  //this.c = color(128, 128, 256);
  this.h =  (float)Math.random();
 }

 public void draw(PApplet g)
 {
   if (this.w > this.maxW) return;

   g.colorMode(g.HSB, 1.0f);
   g.noStroke();
   //fill(c, (1 - w/maxW)*255);
   g.fill(h, 0.5f, .5f, (1 - w/maxW));
   g.ellipse(x,y,w,w);
 }

 public void update()
 {
  this.w += maxW/200;
 }

 public boolean isDead()
 {
  return w >= maxW;
 }
}
