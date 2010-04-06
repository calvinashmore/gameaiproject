/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public interface ICollaborationProgressReport {

    public enum CollaborationStatus
    {
        Working, Done
    }

    public String getTitle();

    public Dispatcher getDispatcher();

    public long getLastUpdateTime();

    public CollaborationStatus getStatus();

    public String getMessage();

    public void reset();
}
