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

    public ABehaviorTemplate(InitiationType initType, CollaborationType collabType)
    {
        this.initType = initType;
        this.collabType = collabType;
    }

    public CollaborationType getCollaborationType() {
        return this.collabType;
    }

    public InitiationType getInitiationType() {
        return this.initType;
    }
}
