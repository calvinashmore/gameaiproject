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

    /**
     * A descriptor of the behavior, useful for determining what types of
     * behaviors an agent can perform (especially useful for attempting
     * collaborations).
     * @return A descriptive phrase of the behavior template.
     */
    public String getId();

    /**
     * Retrieves a BehaviorQueue of tasks which can be performed, based on the
     * template and the world state.
     * @param ws World state to determine the tasks.
     * @param d Dispatcher of agent who owns the behavior.
     * @return A BehaviorQueue of tasks for the agent to perform.
     */
    public abstract BehaviorQueue instantiate(IWorldState ws);

    /**
     * Gets whether this behavior is independent or collaborative.
     * @return Independent or collaborative.
     */
    public CollaborationType getCollaborationType();

    /**
     * Gets whether this behavior is proactive (selected when the agent has
     * nothing else to do), reactive (triggered by other agents who want to
     * collaborate), or latent (triggered by an external stimulus).
     * @return Proactive, reactive, or latent.
     */
    public InitiationType getInitiationType();

    /**
     * Sets the Role which contains this template - in general, should only be
     * called by role implemenations when this template is added to them.
     * @param role
     */
    public void setOwningRole(IRole role);

    /**
     * Gets the Role which contains the template.
     * @return Owning role.
     */
    public IRole getOwningRole();
}
