/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
        this.initType = initType;
        this.collabType = collabType;

        if (initType == InitiationType.reactive &&
            collabType != CollaborationType.collaborative)
        {
            System.out.println("Warning: Reactive behavior is not collaborative");
            //throw new Exception("Reactive behaviors must be collaborative");
        }
    }

    public CollaborationType getCollaborationType() {
        return this.collabType;
    }

    public InitiationType getInitiationType() {
        return this.initType;
    }

    public void setOwningRole(IRole role) {
        this.owningRole = role;
    }

    public IRole getOwningRole() {
        return this.owningRole;
    }
}
