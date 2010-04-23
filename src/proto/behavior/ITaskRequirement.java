/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public interface ITaskRequirement {

    /**
     * Handles any conditions for a task to run successfully.
     * @return True if further requirements should be checked and the task run,
     * false otherwise.
     */
    public boolean handle();
}
