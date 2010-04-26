/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proto.world;

import proto.game.PlayerAction;

/**
 *
 * @author Calvin Ashmore
 */
public abstract class DependentAction {

    protected String name;

    public DependentAction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public boolean canActivate();

    abstract public PlayerAction createAction(Entity other);
}
