/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.world;

import java.util.ArrayList;
import java.util.List;
import processing.core.PGraphics;
import proto.navigation.PathPlanner;
import proto.representation.Representation;

/**
 * Manages all the entities in the world and handles updates and rendering.
 * @author Calvin Ashmore
 */
public class World {

    private List<BasicObject> allObjects = new ArrayList<BasicObject>();
    private Environment environment;
    private PathPlanner planner;

    public World() {
        planner = new PathPlanner(this);
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public List<BasicObject> getAllObjects() {
        return allObjects;
    }

    public PathPlanner getPathPlanner() {
        return planner;
    }

    public void render(PGraphics g) {

        environment.render(g);

        for (BasicObject object : allObjects) {
            Representation representation = object.getRepresentation();
            if (representation != null) {
                representation.render(g);
            }
        }
    }

    public void update() {
        for (BasicObject basicObject : allObjects) {
            if (basicObject instanceof Entity) {
                Entity entity = (Entity) basicObject;
                entity.update();
            }
        }
        // does this need anything?
    }
}
