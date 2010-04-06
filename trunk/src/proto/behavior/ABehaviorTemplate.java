/*
 * Georgia Institute of Technology
 * Calvin Ashmore & Ken Hartsook
 */

package proto.behavior;

/**
 *
 * @author hartsoka
 */
public abstract class ABehaviorTemplate implements IBehaviorTemplate {

    private InitiationType initType;
    private CollaborationType collabType;

    private IRole owningRole;

    public ABehaviorTemplate(InitiationType initType, CollaborationType collabType)
    {
        this.owningRole = null;
        this.initType = initType;
        this.collabType = collabType;

        if (initType == InitiationType.reactive &&
            collabType != CollaborationType.collaborative)
        {
            throw new UnsupportedOperationException("ABehaviorTemplate: reactive roles must be collaborative");
        }
    }

    public CollaborationType getCollaborationType() {
        return this.collabType;
    }

    public InitiationType getInitiationType() {
        return this.initType;
    }

    public void setOwningRole(IRole role) {
        if (this.owningRole != null)
        {
            throw new UnsupportedOperationException("ABehaviorTemplate: cannot be owned by more than one role");
        }
        this.owningRole = role;
    }

    public IRole getOwningRole() {
        return this.owningRole;
    }

    public Dispatcher getDispatcher() {
        return this.owningRole.getOwningDispatcher();
    }
}
