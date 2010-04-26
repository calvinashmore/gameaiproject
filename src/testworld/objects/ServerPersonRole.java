/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.objects;

import testworld.behaviors.RequestAndServeBehavior;
import testworld.behaviors.UseRandomAnnotatedItem;

/**
 *
 * @author Calvin Ashmore
 */
class ServerPersonRole extends PersonRole {

    public ServerPersonRole() {
        super();
        addBehaviorTemplate(RequestAndServeBehavior.makeServeBehavior());
    }
}
