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

    public String getId();

    public BehaviorQueue instantiate(IWorldState ws, Dispatcher d);

    public CollaborationType getCollaborationType();
    public InitiationType getInitiationType();
}
