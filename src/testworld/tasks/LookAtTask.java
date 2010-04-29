/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.tasks;

import proto.world.BasicObject;
import utils.math.Vector2d;

/**
 *
 * @author Calvin Ashmore
 */
public class LookAtTask extends PersonTask {

    private Vector2d lookAt;
    private BasicObject target = null;

    public LookAtTask(Vector2d lookAt) {
        this.lookAt = lookAt;
    }

    public LookAtTask(BasicObject target) {
        this.target = target;
    }

    public void resume() {
    }

    public void runImpl() {
        if (target == null)
            getPerson().setLookAt(lookAt);
        else
            getPerson().setLookAt(target.getLocation().getPosition());
        finished();
    }
}
