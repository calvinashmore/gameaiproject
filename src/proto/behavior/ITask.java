/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 * Interface for a specific task to be completed by an agent, e.g. Move-to-Bob.
 * @author hartsoka
 */
public interface ITask {

    /**
     * Work to be performed when the task becomes active.
     */
    public void resume();

    /**
     * Perform one frame's worth of work towards completing the task.
     */
    public void run(); // TODO more params

    public void setBehaviorQueue(BehaviorQueue bq);

    // TODO maybe an isFinished() flag?
    // TODO maybe a method for when the task is removed from current
}
