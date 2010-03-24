/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package proto.behavior;

/**
 * A type of behavior (e.g. Greet, Attack) which can be instantiated into tasks
 * (e.g. {Move-to-Bob, Say-hi-to-Bob}, {Move-to-Bob, Swing-sword-at-Bob}).
 * @author hartsoka
 */
public interface IBehaviorTemplate {

    public enum CollaborationType { independent, collaborative }
    public enum InitiationType { proactive, reactive, latent }

    public BehaviorQueue instantiate();

    public CollaborationType getCollaborationType();
    public InitiationType getInitiationType();
}
