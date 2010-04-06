/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public class CollaborationProgressReport implements ICollaborationProgressReport{

    private String title;
    private Dispatcher dispatcher;
    private long updateTime;
    private CollaborationStatus status;
    private String message;

    public CollaborationProgressReport(String title, Dispatcher dispatcher, long updateTime, CollaborationStatus status)
    {
        this.title = title;
        this.dispatcher = dispatcher;
        this.updateTime = updateTime;
        this.status = status;
        this.message = null;
    }

    public String getTitle() {
        return this.title;
    }

    public Dispatcher getDispatcher() {
        return this.dispatcher;
    }

    public long getLastUpdateTime() {
        return this.updateTime;
    }

    public CollaborationStatus getStatus() {
        return this.status;
    }

    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void reset() {
        status = CollaborationStatus.Working;
    }
}
