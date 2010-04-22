/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testworld.objects;

import testworld.behaviors.ApproachBehavior;
import testworld.behaviors.GroupChat;
import testworld.behaviors.LatentExclamativeBehavior;
import testworld.behaviors.RequestAndServeBehavior;
import testworld.behaviors.UseODevours;
import testworld.behaviors.conversations.ConversationBehavior;

/**
 *
 * @author hartsoka
 */
public class GuestPersonRole extends PersonRole {

    public GuestPersonRole() {
        super();

        addBehaviorTemplate(new LatentExclamativeBehavior());

        addBehaviorTemplate(ConversationBehavior.makeProactive());
        addBehaviorTemplate(ConversationBehavior.makeReactive());

        addBehaviorTemplate(ApproachBehavior.makeProactive());
        addBehaviorTemplate(ApproachBehavior.makeReactive());
        
        addBehaviorTemplate(GroupChat.makeProactive());
        addBehaviorTemplate(GroupChat.makeReactive());

        addBehaviorTemplate(RequestAndServeBehavior.makeRequestBehavior());

        addBehaviorTemplate(new UseODevours());
    }
}
