/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.world;

import java.util.ArrayList;
import java.util.List;
import processing.core.PGraphics;

/**
 * Manages all the entities in the world and handles updates and rendering.
 * @author Calvin Ashmore
 */
public class World {

    private List<Entity> allEntities = new ArrayList<Entity>();
    private Environment environment;

    public void render(PGraphics g) {
    }

    public void update(double dt) {
        // does this need anything?
    }
}
