/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */
package proto.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import processing.core.PGraphics;
import proto.behavior.CollaborationHandshake;
import proto.behavior.Dispatcher;
import proto.behavior.IWorldState;
import proto.game.PlayerHandler;
import proto.navigation.PathPlanner;
import proto.representation.Representation;
import utils.math.Vector2d;

/**
 * Manages all the entities in the world and handles updates and rendering.
 * @author Calvin Ashmore
 */
abstract public class World implements IWorldState {

    private static World instance;

    public static World getInstance() {
        return instance;
    }
    private List<BasicObject> allObjects = new ArrayList<BasicObject>();
    private Environment environment;
    private PathPlanner planner;
    private boolean paused = false;
    private long worldTime = System.currentTimeMillis();
    private long lastTimestamp = System.currentTimeMillis();

    public long worldTime() {
        return worldTime;
    }

    public World() {
        instance = this;
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

        List<BasicObject> orderedObjects = new ArrayList<BasicObject>(allObjects);
        Collections.sort(orderedObjects, new Comparator<BasicObject>() {

            public int compare(BasicObject o1, BasicObject o2) {
                return (int) Math.signum(o1.getLocation().getPosition().y - o2.getLocation().getPosition().y);
            }
        });

        for (BasicObject object : orderedObjects) {
            Representation representation = object.getRepresentation();
            if (representation != null) {
                representation.render(g);
            }
        }
    }

    public void update() {

        long currentTime = System.currentTimeMillis();
        long dt = currentTime - lastTimestamp;
        lastTimestamp = currentTime;

        if (paused) {
            return;
        }

        worldTime += dt;

        for (BasicObject basicObject : allObjects) {
            if (basicObject instanceof Entity) {
                Entity entity = (Entity) basicObject;
                entity.update();
            }
        }
        // does this need anything?
    }

    public void tryCollaborate(CollaborationHandshake handshake) {
        //for (Dispatcher d : dispatchers)
        for (BasicObject basicObject : getAllObjects()) {

            if (!(basicObject instanceof Entity)) {
                continue;
            }
            Entity entity = (Entity) basicObject;

            if (entity.getDispatcher() == null) {
                continue;
            }

            Dispatcher d = entity.getDispatcher();

            Dispatcher initiator = handshake.getInitiator();
            if (d != initiator) {
                d.offerCollaboration(handshake);
            }
        }
    }

    public Entity getEntityAt(float x, float y) {
        Entity clickedOn = null;

        for (BasicObject basicObject : getAllObjects()) {
            if (basicObject instanceof Entity &&
                    basicObject.getRepresentation() != null &&
                    basicObject.getRepresentation().inRange(x, y)) {
                clickedOn = (Entity) basicObject;
            }
        }

        //return player.getActions(clickedOn, new Vector2d(x, y));
        return clickedOn;
    }

    public abstract PlayerHandler getPlayer();

    public List<BasicObject> getNearbyObjects(BasicObject object, float range) {
        return getNearbyObjects(object.getLocation().getPosition(), range);
    }

    public List<BasicObject> getNearbyObjects(Vector2d location, float range) {
        List<BasicObject> nearbyObjects = new ArrayList<BasicObject>();

        for (BasicObject basicObject : allObjects) {
            if (basicObject.getLocation().getPosition().subtract(location).magnitude() < range) {
                nearbyObjects.add(basicObject);
            }
        }

        return nearbyObjects;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public abstract Vector2d transformPoint(float x, float y);
}
